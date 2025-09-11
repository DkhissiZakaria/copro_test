package com.app.copro.repository;

import com.app.copro.model.Ppt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PptRepository extends JpaRepository<Ppt, Long> {
    Optional<Ppt> findByCarnetId(Long carnetId);
}
