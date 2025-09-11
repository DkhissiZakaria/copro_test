package com.app.copro.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public class ProcedureAdministrativeDto {
    private Long id;

    @NotBlank
    private String typeProcedure;

    @NotBlank
    private String libelle;

    private LocalDate dateArrete;
    private LocalDate dateMainlevee;
    private String description;
    private String fichierRef;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTypeProcedure() { return typeProcedure; }
    public void setTypeProcedure(String typeProcedure) { this.typeProcedure = typeProcedure; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public LocalDate getDateArrete() { return dateArrete; }
    public void setDateArrete(LocalDate dateArrete) { this.dateArrete = dateArrete; }

    public LocalDate getDateMainlevee() { return dateMainlevee; }
    public void setDateMainlevee(LocalDate dateMainlevee) { this.dateMainlevee = dateMainlevee; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getFichierRef() { return fichierRef; }
    public void setFichierRef(String fichierRef) { this.fichierRef = fichierRef; }
}
