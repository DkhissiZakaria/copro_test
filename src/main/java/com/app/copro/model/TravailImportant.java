package com.app.copro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity @Table(name = "travail_important")
public class TravailImportant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String objet;          // ex: "Ravalement façade"
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String entreprise;     // prestataire
    @Column(length = 3000) private String details;   // description, notes

    // N–1 vers Carnet
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carnet_id")
    @JsonIgnore
    private Carnet carnet;
}

