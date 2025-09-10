package com.app.copro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "diagnostic_collectif")
public class DiagnosticCollectif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String diagnostiqueur;

    @Column(name = "type_diagnostic", nullable = false)
    private String typeDiagnostic;

    @Column(name = "ref_pv", nullable = false)
    private String refPv;

    private Double montant;

    @Column(name = "date_controle", nullable = false)
    private LocalDate dateControle;

    @Column(name = "date_echeance")
    private LocalDate dateEcheance;

    private String valeur;

    private String indicateur;

    @Column(length = 1000)
    private String description;

    @Column(length = 255)
    private String fichierRef;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carnet_id")
    @JsonIgnore
    private Carnet carnet;

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

    public Carnet getCarnet() {
        return carnet;
    }

    public void setCarnet(Carnet carnet) {
        this.carnet = carnet;
    }
}

