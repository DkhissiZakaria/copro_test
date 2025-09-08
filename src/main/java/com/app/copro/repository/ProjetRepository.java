package com.app.copro.repository;

import com.app.copro.model.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long> {

    Optional<Projet> findByNom(String nom);

    boolean existsByNom(String nom);

    Optional<Projet> findByIdMakePlan(Long idMakePlan);
}