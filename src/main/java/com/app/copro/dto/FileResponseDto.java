package com.app.copro.dto;

import com.app.copro.model.FileEntity;
import java.time.LocalDateTime;

public class FileResponseDto {

    private Long id;
    private String fileName;
    private String contentType;
    private Long fileSize;
    private String description;
    private String category;
    private String entityType;
    private Long entityId;
    private LocalDateTime uploadedAt;
    private String publicUrl;
    private Boolean isActive;

    public FileResponseDto() {}

    public FileResponseDto(FileEntity fileEntity) {
        this.id = fileEntity.getId();
        this.fileName = fileEntity.getFileName();
        this.contentType = fileEntity.getContentType();
        this.fileSize = fileEntity.getFileSize();
        this.description = fileEntity.getDescription();
        this.category = fileEntity.getCategory();
        this.entityType = fileEntity.getEntityType();
        this.entityId = fileEntity.getEntityId();
        this.uploadedAt = fileEntity.getUploadedAt();
        this.publicUrl = fileEntity.getPublicUrl();
        this.isActive = fileEntity.getIsActive();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }

    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getEntityType() { return entityType; }
    public void setEntityType(String entityType) { this.entityType = entityType; }

    public Long getEntityId() { return entityId; }
    public void setEntityId(Long entityId) { this.entityId = entityId; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }

    public String getPublicUrl() { return publicUrl; }
    public void setPublicUrl(String publicUrl) { this.publicUrl = publicUrl; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}