package com.app.copro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity @Table(name = "contrat_assurance")
public class ContratAssurance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String assureur;
    private String numeroPolice;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String type;            // RC, MRH, multirisque, etc.

    // N–1 vers Carnet (côté propriétaire)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carnet_id")
    @JsonIgnore
    private Carnet carnet;
}
