package com.app.copro.repository;

import com.app.copro.model.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterventionRepository extends JpaRepository<Intervention, Long> {
    List<Intervention> findByEquipementId(Long equipementId);
}
