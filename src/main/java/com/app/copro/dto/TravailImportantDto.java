package com.app.copro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * DTO pour l'entité {@code TravailImportant}.
 */
public class TravailImportantDto {

    private Long id;

    @NotBlank
    private String nomTravaux;

    @NotNull
    private LocalDate dateDebut;

    @NotNull
    private LocalDate dateFin;

    private String assembleeGenerale;
    private String cleCharges;
    private Double montantTravaux;
    private Integer nombreEcheances;

    @NotBlank
    private String details;

    private String image1Ref;
    private String image2Ref;
    private String image3Ref;

    // ===== Getters / Setters =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomTravaux() {
        return nomTravaux;
    }

    public void setNomTravaux(String nomTravaux) {
        this.nomTravaux = nomTravaux;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getAssembleeGenerale() {
        return assembleeGenerale;
    }

    public void setAssembleeGenerale(String assembleeGenerale) {
        this.assembleeGenerale = assembleeGenerale;
    }

    public String getCleCharges() {
        return cleCharges;
    }

    public void setCleCharges(String cleCharges) {
        this.cleCharges = cleCharges;
    }

    public Double getMontantTravaux() {
        return montantTravaux;
    }

    public void setMontantTravaux(Double montantTravaux) {
        this.montantTravaux = montantTravaux;
    }

    public Integer getNombreEcheances() {
        return nombreEcheances;
    }

    public void setNombreEcheances(Integer nombreEcheances) {
        this.nombreEcheances = nombreEcheances;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage1Ref() {
        return image1Ref;
    }

    public void setImage1Ref(String image1Ref) {
        this.image1Ref = image1Ref;
    }

    public String getImage2Ref() {
        return image2Ref;
    }

    public void setImage2Ref(String image2Ref) {
        this.image2Ref = image2Ref;
    }

    public String getImage3Ref() {
        return image3Ref;
    }

    public void setImage3Ref(String image3Ref) {
        this.image3Ref = image3Ref;
    }
}

