package com.app.copro.repository;

import com.app.copro.model.DiagnosticCollectif;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DiagnosticCollectifRepository extends JpaRepository<DiagnosticCollectif, Long> {
    List<DiagnosticCollectif> findByCarnetId(Long carnetId);
}

