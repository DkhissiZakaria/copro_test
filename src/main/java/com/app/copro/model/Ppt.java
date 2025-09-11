package com.app.copro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ppt")
public class Ppt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nature_travaux")
    private String natureTravaux;

    @Column(name = "date_projet")
    private LocalDate dateProjet;

    @Column(name = "detail_projet", length = 1000)
    private String detailProjet;

    @Column(name = "fichier_ref", length = 255)
    private String fichierRef;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carnet_id", unique = true, nullable = false)
    @JsonIgnore
    private Carnet carnet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNatureTravaux() {
        return natureTravaux;
    }

    public void setNatureTravaux(String natureTravaux) {
        this.natureTravaux = natureTravaux;
    }

    public LocalDate getDateProjet() {
        return dateProjet;
    }

    public void setDateProjet(LocalDate dateProjet) {
        this.dateProjet = dateProjet;
    }

    public String getDetailProjet() {
        return detailProjet;
    }

    public void setDetailProjet(String detailProjet) {
        this.detailProjet = detailProjet;
    }

    public String getFichierRef() {
        return fichierRef;
    }

    public void setFichierRef(String fichierRef) {
        this.fichierRef = fichierRef;
    }

    public Carnet getCarnet() {
        return carnet;
    }

    public void setCarnet(Carnet carnet) {
        this.carnet = carnet;
    }
}
