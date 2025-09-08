package com.app.copro.util;

import com.app.copro.dto.FileResponseDto;
import com.app.copro.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class FileManagerHelper {

    @Autowired
    private FileStorageService fileStorageService;

    public String uploadSyndicDocument(MultipartFile file, Long syndicId, String category, String description) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        
        FileResponseDto uploadedFile = fileStorageService.uploadFile(
            file, 
            category, 
            FileConstants.EntityTypes.SYNDIC, 
            syndicId, 
            description
        );
        
        return generateFileReference(uploadedFile);
    }

    public String getLatestSyndicDocumentReference(Long syndicId, String category) {
        FileResponseDto latestFile = fileStorageService.getLatestFileByEntityAndCategory(
            FileConstants.EntityTypes.SYNDIC, 
            syndicId, 
            category
        );
        
        return latestFile != null ? generateFileReference(latestFile) : null;
    }

    public List<FileResponseDto> getAllSyndicFiles(Long syndicId) {
        return fileStorageService.getFilesByEntity(FileConstants.EntityTypes.SYNDIC, syndicId);
    }

    public List<FileResponseDto> getSyndicFilesByCategory(Long syndicId, String category) {
        return fileStorageService.getFilesByEntityAndCategory(FileConstants.EntityTypes.SYNDIC, syndicId, category);
    }

    public String generatePresignedUrl(Long fileId) {
        return fileStorageService.generatePresignedUrl(fileId, FileConstants.DEFAULT_PRESIGNED_URL_DURATION_MINUTES);
    }

    public String generatePresignedUrl(Long fileId, int durationMinutes) {
        return fileStorageService.generatePresignedUrl(fileId, durationMinutes);
    }

    public boolean deleteSyndicFile(Long fileId) {
        return fileStorageService.deleteFile(fileId);
    }

    public boolean deleteAllSyndicFiles(Long syndicId) {
        return fileStorageService.deleteFilesByEntity(FileConstants.EntityTypes.SYNDIC, syndicId);
    }

    public FileResponseDto getFileDetails(Long fileId) {
        return fileStorageService.getFileById(fileId);
    }

    public FileResponseDto parseFileReference(String fileReference) {
        if (fileReference == null || !fileReference.startsWith("FILE_ID:")) {
            return null;
        }
        
        try {
            Long fileId = Long.parseLong(fileReference.substring(8));
            return fileStorageService.getFileById(fileId);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public String uploadEntityDocument(MultipartFile file, String entityType, Long entityId, String category, String description) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        
        FileResponseDto uploadedFile = fileStorageService.uploadFile(
            file, 
            category, 
            entityType, 
            entityId, 
            description
        );
        
        return generateFileReference(uploadedFile);
    }

    public String getLatestEntityDocumentReference(String entityType, Long entityId, String category) {
        FileResponseDto latestFile = fileStorageService.getLatestFileByEntityAndCategory(entityType, entityId, category);
        return latestFile != null ? generateFileReference(latestFile) : null;
    }

    public List<FileResponseDto> getEntityFiles(String entityType, Long entityId) {
        return fileStorageService.getFilesByEntity(entityType, entityId);
    }

    public List<FileResponseDto> getEntityFilesByCategory(String entityType, Long entityId, String category) {
        return fileStorageService.getFilesByEntityAndCategory(entityType, entityId, category);
    }

    private String generateFileReference(FileResponseDto file) {
        if (file == null || file.getId() == null) {
            return null;
        }
        return "FILE_ID:" + file.getId();
    }
}