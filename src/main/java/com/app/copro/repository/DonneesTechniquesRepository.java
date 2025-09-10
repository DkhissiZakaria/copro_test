package com.app.copro.repository;

import com.app.copro.model.DonneesTechniques;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DonneesTechniquesRepository extends JpaRepository<DonneesTechniques, Long> {
    Optional<DonneesTechniques> findByCarnetId(Long carnetId);
}

