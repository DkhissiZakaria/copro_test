package com.app.copro.dto;

public class ProjetResponseDto {

    private Long id;
    private String nom;
    private Long idMakePlan;

    public ProjetResponseDto() {}

    public ProjetResponseDto(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public ProjetResponseDto(Long id, String nom, Long idMakePlan) {
        this.id = id;
        this.nom = nom;
        this.idMakePlan = idMakePlan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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