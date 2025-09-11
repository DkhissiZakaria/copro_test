package com.app.copro.repository;

import com.app.copro.model.ContratMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratMaintenanceRepository extends JpaRepository<ContratMaintenance, Long> {
    List<ContratMaintenance> findByEquipementId(Long equipementId);
}
