package com.app.copro.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public class ContratAssuranceDto {
    private Long id;

    @NotBlank
    private String contrat;

    @NotBlank
    private String assureur;

    @NotBlank
    private String compagnie;

    private String numeroContrat;
    private String numeroPolice;
    private LocalDate dateEffet;
    private LocalDate dateFin;
    private Double prime;
    private String fichierRef;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContrat() {
        return contrat;
    }

    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    public String getAssureur() {
        return assureur;
    }

    public void setAssureur(String assureur) {
        this.assureur = assureur;
    }

    public String getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(String compagnie) {
        this.compagnie = compagnie;
    }

    public String getNumeroContrat() {
        return numeroContrat;
    }

    public void setNumeroContrat(String numeroContrat) {
        this.numeroContrat = numeroContrat;
    }

    public String getNumeroPolice() {
        return numeroPolice;
    }

    public void setNumeroPolice(String numeroPolice) {
        this.numeroPolice = numeroPolice;
    }

    public LocalDate getDateEffet() {
        return dateEffet;
    }

    public void setDateEffet(LocalDate dateEffet) {
        this.dateEffet = dateEffet;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Double getPrime() {
        return prime;
    }

    public void setPrime(Double prime) {
        this.prime = prime;
    }

    public String getFichierRef() {
        return fichierRef;
    }

    public void setFichierRef(String fichierRef) {
        this.fichierRef = fichierRef;
    }
}

