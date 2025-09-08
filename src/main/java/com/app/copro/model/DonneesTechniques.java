package com.app.copro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity @Table(name = "donnees_techniques")
public class DonneesTechniques {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // exemples de champs (mets les tiens)
    private String anneeConstruction;
    private Integer nombreLots;
    private String classificationEnergie;

    // propriétaire 1–1
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carnet_id", unique = true, nullable = false)
    @JsonIgnore
    private Carnet carnet;
}
