package com.app.copro.repository;

import com.app.copro.model.Chapitre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapitreRepository extends JpaRepository<Chapitre, Long> {
    List<Chapitre> findByCarnetId(Long carnetId);
}

