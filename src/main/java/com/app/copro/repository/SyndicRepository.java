package com.app.copro.repository;

import com.app.copro.model.Syndic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyndicRepository extends JpaRepository<Syndic, Long> {
    
    @Query("SELECT s FROM Syndic s JOIN FETCH s.projet p WHERE p.idMakePlan = :idMakePlan")
    List<Syndic> findByProjetIdMakePlan(@Param("idMakePlan") Long idMakePlan);

    @Query("UPDATE Syndic s SET s.isActive = false WHERE s.projet.idMakePlan = :idMakePlan")
    @Modifying
    void deactivateAllSyndicsByProjetIdMakePlan(@Param("idMakePlan") Long idMakePlan);

    @Query("SELECT s FROM Syndic s JOIN FETCH s.projet p WHERE p.idMakePlan = :idMakePlan AND s.isActive = true")
    List<Syndic> findActiveSyndicsByProjetIdMakePlan(@Param("idMakePlan") Long idMakePlan);
}
