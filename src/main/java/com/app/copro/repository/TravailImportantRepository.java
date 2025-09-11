package com.app.copro.repository;

import com.app.copro.model.TravailImportant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravailImportantRepository extends JpaRepository<TravailImportant, Long> {
    List<TravailImportant> findByCarnetId(Long carnetId);
}

