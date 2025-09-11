package com.app.copro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class InterventionDto {
    private Long id;

    @NotBlank
    private String natureIntervention;

    @NotNull
    private LocalDate date;

    private Double montant;
    private String fournisseur;
    private String observation;
    private String avancement;
    private String fichierRef;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNatureIntervention() {
        return natureIntervention;
    }

    public void setNatureIntervention(String natureIntervention) {
        this.natureIntervention = natureIntervention;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getAvancement() {
        return avancement;
    }

    public void setAvancement(String avancement) {
        this.avancement = avancement;
    }

    public String getFichierRef() {
        return fichierRef;
    }

    public void setFichierRef(String fichierRef) {
        this.fichierRef = fichierRef;
    }
}
