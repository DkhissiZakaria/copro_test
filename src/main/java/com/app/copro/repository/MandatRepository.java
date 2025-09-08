package com.app.copro.repository;

import com.app.copro.model.Mandat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MandatRepository extends JpaRepository<Mandat, Long> {
    Optional<Mandat> findBySyndicId(Long syndicId);
}
