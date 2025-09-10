package com.app.copro.repository;

import com.app.copro.model.DonneesAdministratives;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DonneesAdministrativesRepository extends JpaRepository<DonneesAdministratives, Long> {
    Optional<DonneesAdministratives> findByCarnetId(Long carnetId);
}
