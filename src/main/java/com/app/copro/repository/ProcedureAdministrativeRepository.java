package com.app.copro.repository;

import com.app.copro.model.ProcedureAdministrative;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcedureAdministrativeRepository extends JpaRepository<ProcedureAdministrative, Long> {
    List<ProcedureAdministrative> findByCarnetId(Long carnetId);
}
