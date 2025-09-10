package com.app.copro.repository;

import com.app.copro.model.ContratAssurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratAssuranceRepository extends JpaRepository<ContratAssurance, Long> {
    List<ContratAssurance> findByCarnetId(Long carnetId);
}
