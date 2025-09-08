package com.app.copro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateProjetDto {

    @NotBlank(message = "Le nom du projet ne peut pas être vide")
    @Size(max = 120, message = "Le nom du projet ne peut pas dépasser 120 caractères")
    private String nom;

    private Long idMakePlan;

    public CreateProjetDto() {}

    public CreateProjetDto(String nom) {
        this.nom = nom;
    }

    public CreateProjetDto(String nom, Long idMakePlan) {
        this.nom = nom;
        this.idMakePlan = idMakePlan;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getIdMakePlan() {
        return idMakePlan;
    }

    public void setIdMakePlan(Long idMakePlan) {
        this.idMakePlan = idMakePlan;
    }
}