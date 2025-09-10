package com.app.copro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class DiagnosticCollectifDto {
    private Long id;

    @NotBlank
    private String diagnostiqueur;

    @NotBlank
    private String typeDiagnostic;

    @NotBlank
    private String refPv;

    private Double montant;

    @NotNull
    private LocalDate dateControle;

    private LocalDate dateEcheance;

    private String valeur;

    private String indicateur;

    private String description;

    private String fichierRef;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiagnostiqueur() {
        return diagnostiqueur;
    }

    public void setDiagnostiqueur(String diagnostiqueur) {
        this.diagnostiqueur = diagnostiqueur;
    }

    public String getTypeDiagnostic() {
        return typeDiagnostic;
    }

    public void setTypeDiagnostic(String typeDiagnostic) {
        this.typeDiagnostic = typeDiagnostic;
    }

    public String getRefPv() {
        return refPv;
    }

    public void setRefPv(String refPv) {
        this.refPv = refPv;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public LocalDate getDateControle() {
        return dateControle;
    }

    public void setDateControle(LocalDate dateControle) {
        this.dateControle = dateControle;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getIndicateur() {
        return indicateur;
    }

    public void setIndicateur(String indicateur) {
        this.indicateur = indicateur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFichierRef() {
        return fichierRef;
    }

    public void setFichierRef(String fichierRef) {
        this.fichierRef = fichierRef;
    }
}

