package com.app.copro.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class TravauxReparationDto {
    private Long id;

    @NotBlank
    private String typeTravaux;

    @NotBlank
    private String description;

    private LocalDate dateRealisation;

    @NotBlank
    private String entreprisePrestataire;

    private Double montantFinal;
    private String observations;
    private String avancement;
    private String devisRef;
    private String factureRef;
    private String photoAvantRef;
    private String photoApresRef;

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
}
