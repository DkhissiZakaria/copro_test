package com.app.copro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class MandatDto {
    private Long id;

    @NotBlank
    private String numero;

    @NotNull
    private LocalDate dateDebut;

    @NotNull
    private LocalDate dateFin;

    private LocalDate dateSignature;
    private String typeMandat;
    private Boolean administrateurProvisoire;
    private String adresse;
    private String complement;
    private String codePostal;
    private String ville;
    private String fichierRef;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public LocalDate getDateSignature() {
        return dateSignature;
    }

    public void setDateSignature(LocalDate dateSignature) {
        this.dateSignature = dateSignature;
    }

    public String getTypeMandat() {
        return typeMandat;
    }

    public void setTypeMandat(String typeMandat) {
        this.typeMandat = typeMandat;
    }

    public Boolean getAdministrateurProvisoire() {
        return administrateurProvisoire;
    }

    public void setAdministrateurProvisoire(Boolean administrateurProvisoire) {
        this.administrateurProvisoire = administrateurProvisoire;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getFichierRef() {
        return fichierRef;
    }

    public void setFichierRef(String fichierRef) {
        this.fichierRef = fichierRef;
    }
}

