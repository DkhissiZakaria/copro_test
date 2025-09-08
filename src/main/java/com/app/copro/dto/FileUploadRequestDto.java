package com.app.copro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadRequestDto {

    @NotNull(message = "Le fichier est obligatoire")
    private MultipartFile file;

    @NotBlank(message = "La catégorie est obligatoire")
    private String category;

    @NotBlank(message = "Le type d'entité est obligatoire")
    private String entityType;

    @NotNull(message = "L'ID de l'entité est obligatoire")
    private Long entityId;

    private String description;

    public MultipartFile getFile() { return file; }
    public void setFile(MultipartFile file) { this.file = file; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getEntityType() { return entityType; }
    public void setEntityType(String entityType) { this.entityType = entityType; }

    public Long getEntityId() { return entityId; }
    public void setEntityId(Long entityId) { this.entityId = entityId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}