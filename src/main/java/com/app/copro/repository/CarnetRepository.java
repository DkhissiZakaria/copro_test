package com.app.copro.repository;

import com.app.copro.model.Carnet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarnetRepository extends JpaRepository<Carnet, Long> {
    List<Carnet> findBySyndic_Projet_IdMakePlan(Long idMakePlan);
    Optional<Carnet> findBySyndic_IsActiveTrue();
    Optional<Carnet> findFirstBySyndic_Projet_IdMakePlanAndSyndic_IsActiveTrueOrderByIdAsc(Long idMakePlan);
}
