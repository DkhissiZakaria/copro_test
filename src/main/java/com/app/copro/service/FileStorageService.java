package com.app.copro.service;

import com.app.copro.dto.FileResponseDto;
import com.app.copro.dto.FileUploadRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileStorageService {

    FileResponseDto uploadFile(FileUploadRequestDto uploadRequest);

    FileResponseDto uploadFile(MultipartFile file, String category, String entityType, Long entityId, String description);

    List<FileResponseDto> getFilesByEntity(String entityType, Long entityId);

    List<FileResponseDto> getFilesByEntityAndCategory(String entityType, Long entityId, String category);

    FileResponseDto getLatestFileByEntityAndCategory(String entityType, Long entityId, String category);

    boolean deleteFile(Long fileId);

    boolean deleteFilesByEntity(String entityType, Long entityId);

    byte[] downloadFile(Long fileId);

    String generatePresignedUrl(Long fileId, int durationMinutes);

    FileResponseDto getFileById(Long fileId);
}