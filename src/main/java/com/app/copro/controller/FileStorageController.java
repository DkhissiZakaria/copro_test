package com.app.copro.controller;

import com.app.copro.dto.FileResponseDto;
import com.app.copro.dto.FileUploadRequestDto;
import com.app.copro.service.FileStorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileStorageController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<FileResponseDto> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("category") String category,
            @RequestParam("entityType") String entityType,
            @RequestParam("entityId") Long entityId,
            @RequestParam(value = "description", required = false) String description) {
        
        try {
            FileResponseDto response = fileStorageService.uploadFile(file, category, entityType, entityId, description);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/entity/{entityType}/{entityId}")
    public ResponseEntity<List<FileResponseDto>> getFilesByEntity(
            @PathVariable String entityType,
            @PathVariable Long entityId) {
        
        List<FileResponseDto> files = fileStorageService.getFilesByEntity(entityType, entityId);
        return ResponseEntity.ok(files);
    }

    @GetMapping("/entity/{entityType}/{entityId}/category/{category}")
    public ResponseEntity<List<FileResponseDto>> getFilesByEntityAndCategory(
            @PathVariable String entityType,
            @PathVariable Long entityId,
            @PathVariable String category) {
        
        List<FileResponseDto> files = fileStorageService.getFilesByEntityAndCategory(entityType, entityId, category);
        return ResponseEntity.ok(files);
    }

    @GetMapping("/entity/{entityType}/{entityId}/category/{category}/latest")
    public ResponseEntity<FileResponseDto> getLatestFileByEntityAndCategory(
            @PathVariable String entityType,
            @PathVariable Long entityId,
            @PathVariable String category) {
        
        FileResponseDto file = fileStorageService.getLatestFileByEntityAndCategory(entityType, entityId, category);
        if (file != null) {
            return ResponseEntity.ok(file);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<FileResponseDto> getFileById(@PathVariable Long fileId) {
        FileResponseDto file = fileStorageService.getFileById(fileId);
        if (file != null) {
            return ResponseEntity.ok(file);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long fileId) {
        boolean deleted = fileStorageService.deleteFile(fileId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/entity/{entityType}/{entityId}")
    public ResponseEntity<Void> deleteFilesByEntity(
            @PathVariable String entityType,
            @PathVariable Long entityId) {
        
        boolean deleted = fileStorageService.deleteFilesByEntity(entityType, entityId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{fileId}/download")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId) {
        try {
            FileResponseDto fileInfo = fileStorageService.getFileById(fileId);
            if (fileInfo == null) {
                return ResponseEntity.notFound().build();
            }

            byte[] fileData = fileStorageService.downloadFile(fileId);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(fileInfo.getContentType()));
            headers.setContentDispositionFormData("attachment", fileInfo.getFileName());
            headers.setContentLength(fileData.length);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(fileData);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{fileId}/presigned-url")
    public ResponseEntity<String> generatePresignedUrl(
            @PathVariable Long fileId,
            @RequestParam(defaultValue = "60") int durationMinutes) {
        
        try {
            String url = fileStorageService.generatePresignedUrl(fileId, durationMinutes);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}