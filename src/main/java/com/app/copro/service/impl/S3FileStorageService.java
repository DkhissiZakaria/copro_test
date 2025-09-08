package com.app.copro.service.impl;

import com.app.copro.dto.FileResponseDto;
import com.app.copro.dto.FileUploadRequestDto;
import com.app.copro.model.FileEntity;
import com.app.copro.repository.FileEntityRepository;
import com.app.copro.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class S3FileStorageService implements FileStorageService {

    private static final Logger logger = LoggerFactory.getLogger(S3FileStorageService.class);

    @Autowired
    private S3Client s3Client;

    @Autowired
    private FileEntityRepository fileEntityRepository;

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Override
    public FileResponseDto uploadFile(FileUploadRequestDto uploadRequest) {
        return uploadFile(
            uploadRequest.getFile(),
            uploadRequest.getCategory(),
            uploadRequest.getEntityType(),
            uploadRequest.getEntityId(),
            uploadRequest.getDescription()
        );
    }

    @Override
    public FileResponseDto uploadFile(MultipartFile file, String category, String entityType, Long entityId, String description) {
        try {
            validateFile(file);
            
            String s3Key = generateS3Key(entityType, entityId, category, file.getOriginalFilename());
            
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(s3Key)
                .contentType(file.getContentType())
                .contentLength(file.getSize())
                .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

            FileEntity fileEntity = new FileEntity(
                file.getOriginalFilename(),
                s3Key,
                bucketName,
                file.getContentType(),
                file.getSize(),
                category,
                entityType,
                entityId
            );
            fileEntity.setDescription(description);

            fileEntity = fileEntityRepository.save(fileEntity);
            
            logger.info("Fichier uploadé avec succès: {}", s3Key);
            return new FileResponseDto(fileEntity);

        } catch (IOException e) {
            logger.error("Erreur lors de l'upload du fichier: {}", e.getMessage(), e);
            throw new RuntimeException("Erreur lors de l'upload du fichier", e);
        }
    }

    @Override
    public List<FileResponseDto> getFilesByEntity(String entityType, Long entityId) {
        List<FileEntity> files = fileEntityRepository.findByEntityTypeAndEntityIdAndIsActiveTrue(entityType, entityId);
        return files.stream()
                .map(FileResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<FileResponseDto> getFilesByEntityAndCategory(String entityType, Long entityId, String category) {
        List<FileEntity> files = fileEntityRepository.findByEntityTypeAndEntityIdAndCategoryAndIsActiveTrue(entityType, entityId, category);
        return files.stream()
                .map(FileResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public FileResponseDto getLatestFileByEntityAndCategory(String entityType, Long entityId, String category) {
        Optional<FileEntity> fileEntity = fileEntityRepository.findByEntityTypeAndEntityIdAndCategoryAndIsActiveTrueOrderByUploadedAtDesc(entityType, entityId, category);
        return fileEntity.map(FileResponseDto::new).orElse(null);
    }

    @Override
    public boolean deleteFile(Long fileId) {
        Optional<FileEntity> fileEntityOpt = fileEntityRepository.findById(fileId);
        if (fileEntityOpt.isPresent()) {
            FileEntity fileEntity = fileEntityOpt.get();
            try {
                DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileEntity.getS3Key())
                    .build();
                
                s3Client.deleteObject(deleteRequest);
                
                fileEntity.setIsActive(false);
                fileEntityRepository.save(fileEntity);
                
                logger.info("Fichier supprimé: {}", fileEntity.getS3Key());
                return true;
            } catch (Exception e) {
                logger.error("Erreur lors de la suppression du fichier: {}", e.getMessage(), e);
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean deleteFilesByEntity(String entityType, Long entityId) {
        List<FileEntity> files = fileEntityRepository.findByEntityTypeAndEntityIdAndIsActiveTrue(entityType, entityId);
        boolean allDeleted = true;
        
        for (FileEntity file : files) {
            if (!deleteFile(file.getId())) {
                allDeleted = false;
            }
        }
        
        return allDeleted;
    }

    @Override
    public byte[] downloadFile(Long fileId) {
        Optional<FileEntity> fileEntityOpt = fileEntityRepository.findById(fileId);
        if (fileEntityOpt.isPresent()) {
            FileEntity fileEntity = fileEntityOpt.get();
            try {
                GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileEntity.getS3Key())
                    .build();
                
                return s3Client.getObjectAsBytes(getObjectRequest).asByteArray();
            } catch (Exception e) {
                logger.error("Erreur lors du téléchargement du fichier: {}", e.getMessage(), e);
                throw new RuntimeException("Erreur lors du téléchargement du fichier", e);
            }
        }
        throw new RuntimeException("Fichier non trouvé");
    }

    @Override
    public String generatePresignedUrl(Long fileId, int durationMinutes) {
        Optional<FileEntity> fileEntityOpt = fileEntityRepository.findById(fileId);
        if (fileEntityOpt.isPresent()) {
            FileEntity fileEntity = fileEntityOpt.get();
            try (S3Presigner presigner = S3Presigner.create()) {
                GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileEntity.getS3Key())
                    .build();

                GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(durationMinutes))
                    .getObjectRequest(getObjectRequest)
                    .build();

                return presigner.presignGetObject(presignRequest).url().toString();
            } catch (Exception e) {
                logger.error("Erreur lors de la génération de l'URL présignée: {}", e.getMessage(), e);
                throw new RuntimeException("Erreur lors de la génération de l'URL présignée", e);
            }
        }
        throw new RuntimeException("Fichier non trouvé");
    }

    @Override
    public FileResponseDto getFileById(Long fileId) {
        if (fileId == null) {
            throw new IllegalArgumentException("L'ID du fichier ne peut pas être nul");
        }
        Optional<FileEntity> fileEntity = fileEntityRepository.findById(fileId);
        return fileEntity.map(FileResponseDto::new).orElse(null);
    }

    private void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Le fichier ne peut pas être vide");
        }
        
        if (file.getOriginalFilename() == null || file.getOriginalFilename().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du fichier ne peut pas être vide");
        }
        
        if (file.getSize() > 10 * 1024 * 1024) { // 10MB
            throw new IllegalArgumentException("Le fichier ne peut pas dépasser 10MB");
        }
        
        // Validation du type de contenu
        String contentType = file.getContentType();
        if (contentType == null) {
            throw new IllegalArgumentException("Le type de contenu du fichier ne peut pas être déterminé");
        }
        
        // Validation de l'extension de fichier
        String fileName = file.getOriginalFilename().toLowerCase();
        if (fileName.contains("..")) {
            throw new IllegalArgumentException("Le nom de fichier contient des caractères non autorisés");
        }
    }

    private String generateS3Key(String entityType, Long entityId, String category, String fileName) {
        if (entityType == null || entityType.trim().isEmpty()) {
            throw new IllegalArgumentException("Le type d'entité ne peut pas être vide");
        }
        if (entityId == null || entityId <= 0) {
            throw new IllegalArgumentException("L'ID d'entité doit être positif");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("La catégorie ne peut pas être vide");
        }
        
        String sanitizedFileName = sanitizeFileName(fileName);
        String timestamp = String.valueOf(System.currentTimeMillis());
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String fileExtension = getFileExtension(sanitizedFileName);
        
        return String.format("files/%s/%d/%s/%s_%s%s", 
            entityType.toLowerCase().trim(), 
            entityId, 
            category.toLowerCase().trim(), 
            timestamp, 
            uuid, 
            fileExtension
        );
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.trim().isEmpty() || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf('.'));
    }
    
    private String sanitizeFileName(String fileName) {
        if (fileName == null) {
            return "";
        }
        // Remplace les caractères spéciaux par des underscores et supprime les espaces multiples
        return fileName.trim()
                .replaceAll("[^a-zA-Z0-9.-]", "_")
                .replaceAll("_{2,}", "_")
                .replaceAll("^_+|_+$", "");
    }
}