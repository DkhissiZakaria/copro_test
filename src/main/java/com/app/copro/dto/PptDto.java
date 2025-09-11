package com.app.copro.dto;

import java.time.LocalDate;

public class PptDto {
    private Long id;
    private String natureTravaux;
    private LocalDate dateProjet;
    private String detailProjet;
    private String fichierRef;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNatureTravaux() {
        return natureTravaux;
    }

    public void setNatureTravaux(String natureTravaux) {
        this.natureTravaux = natureTravaux;
    }

    public LocalDate getDateProjet() {
        return dateProjet;
    }

    public void setDateProjet(LocalDate dateProjet) {
        this.dateProjet = dateProjet;
    }

    public String getDetailProjet() {
        return detailProjet;
    }

    public void setDetailProjet(String detailProjet) {
        this.detailProjet = detailProjet;
    }

    public String getFichierRef() {
        return fichierRef;
    }

    public void setFichierRef(String fichierRef) {
        this.fichierRef = fichierRef;
    }
}
