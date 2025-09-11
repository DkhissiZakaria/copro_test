package com.app.copro.repository;

import com.app.copro.model.TravauxReparation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravauxReparationRepository extends JpaRepository<TravauxReparation, Long> {
    List<TravauxReparation> findByEquipementId(Long equipementId);
}
