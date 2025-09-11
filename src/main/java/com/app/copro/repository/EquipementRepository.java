package com.app.copro.repository;

import com.app.copro.model.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipementRepository extends JpaRepository<Equipement, Long> {
    List<Equipement> findByChapitreId(Long chapitreId);
}
