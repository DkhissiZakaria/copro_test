package com.app.copro.repository;

import com.app.copro.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileEntityRepository extends JpaRepository<FileEntity, Long> {

    List<FileEntity> findByEntityTypeAndEntityIdAndIsActiveTrue(String entityType, Long entityId);

    List<FileEntity> findByEntityTypeAndEntityIdAndCategoryAndIsActiveTrue(String entityType, Long entityId, String category);

    Optional<FileEntity> findByEntityTypeAndEntityIdAndCategoryAndIsActiveTrueOrderByUploadedAtDesc(String entityType, Long entityId, String category);

    List<FileEntity> findByEntityTypeAndEntityId(String entityType, Long entityId);

    @Query("SELECT f FROM FileEntity f WHERE f.entityType = :entityType AND f.entityId = :entityId AND f.category = :category AND f.isActive = true")
    List<FileEntity> findActiveFilesByEntityAndCategory(@Param("entityType") String entityType, @Param("entityId") Long entityId, @Param("category") String category);

    boolean existsByS3Key(String s3Key);

    Optional<FileEntity> findByS3KeyAndIsActiveTrue(String s3Key);
}