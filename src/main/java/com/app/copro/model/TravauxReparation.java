package com.app.copro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "travaux_reparation")
public class TravauxReparation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_travaux", nullable = false)
    private String typeTravaux;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "date_realisation")
    private LocalDate dateRealisation;

    @Column(name = "entreprise_prestataire", nullable = false)
    private String entreprisePrestataire;

    private Double montantFinal;

    @Column(columnDefinition = "TEXT")
    private String observations;

    private String avancement;

    @Column(length = 255)
    private String devisRef;

    @Column(length = 255)
    private String factureRef;

    @Column(length = 255)
    private String photoAvantRef;

    @Column(length = 255)
    private String photoApresRef;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "equipement_id")
    @JsonIgnore
    private Equipement equipement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeTravaux() {
        return typeTravaux;
    }

    public void setTypeTravaux(String typeTravaux) {
        this.typeTravaux = typeTravaux;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateRealisation() {
        return dateRealisation;
    }

    public void setDateRealisation(LocalDate dateRealisation) {
        this.dateRealisation = dateRealisation;
    }

    public String getEntreprisePrestataire() {
        return entreprisePrestataire;
    }

    public void setEntreprisePrestataire(String entreprisePrestataire) {
        this.entreprisePrestataire = entreprisePrestataire;
    }

    public Double getMontantFinal() {
        return montantFinal;
    }

    public void setMontantFinal(Double montantFinal) {
        this.montantFinal = montantFinal;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getAvancement() {
        return avancement;
    }

    public void setAvancement(String avancement) {
        this.avancement = avancement;
    }

    public String getDevisRef() {
        return devisRef;
    }

    public void setDevisRef(String devisRef) {
        this.devisRef = devisRef;
    }

    public String getFactureRef() {
        return factureRef;
    }

    public void setFactureRef(String factureRef) {
        this.factureRef = factureRef;
    }

    public String getPhotoAvantRef() {
        return photoAvantRef;
    }

    public void setPhotoAvantRef(String photoAvantRef) {
        this.photoAvantRef = photoAvantRef;
    }

    public String getPhotoApresRef() {
        return photoApresRef;
    }

    public void setPhotoApresRef(String photoApresRef) {
        this.photoApresRef = photoApresRef;
    }

    public Equipement getEquipement() {
        return equipement;
    }

    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }
}
