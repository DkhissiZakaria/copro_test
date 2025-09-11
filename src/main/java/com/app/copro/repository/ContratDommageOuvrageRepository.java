package com.app.copro.repository;

import com.app.copro.model.ContratDommageOuvrage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratDommageOuvrageRepository extends JpaRepository<ContratDommageOuvrage, Long> {
    List<ContratDommageOuvrage> findByCarnetId(Long carnetId);
}
