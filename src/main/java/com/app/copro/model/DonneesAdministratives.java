package com.app.copro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity @Table(name = "donnees_administratives")
public class DonneesAdministratives {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // exemples de champs (mets les tiens)
    private String siren;
    private String adresseSiege;
    private String representantLegal;

    // propriétaire de la relation 1–1 (clé unique)
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carnet_id", unique = true, nullable = false)
    @JsonIgnore
    private Carnet carnet;
}
