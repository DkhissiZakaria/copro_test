package com.app.copro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Contrat d'entretien et maintenance des équipements communs.
 */
@Entity
@Table(name = "contrat_maintenance")
public class ContratMaintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nom du contrat. */
    @Column(nullable = false)
    private String contrat;

    /** Numéro de contrat. */
    @Column(name = "numero_contrat", nullable = false)
    private String numeroContrat;

    /** Date d'effet. */
    @Column(name = "date_effet", nullable = false)
    private LocalDate dateEffet;

    /** Date d'échéance. */
    @Column(name = "date_echeance", nullable = false)
    private LocalDate dateEcheance;

    /** Localisation concernée par le contrat. */
    private String localisation;

    /** Périodicité du contrat. */
    private String periodicite;

    /** Entreprise prestataire. */
    @Column(nullable = false)
    private String entreprise;

    /** Montant du contrat. */
    private Double montant;

    /** Référence du fichier associé. */
    @Column(length = 255)
    private String fichierRef;

    /**
     * Equipement concerné par ce contrat.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "equipement_id")
    @JsonIgnore
    private Equipement equipement;

    // ===== Getters/Setters =====
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

    public String getNumeroContrat() {
        return numeroContrat;
    }

    public void setNumeroContrat(String numeroContrat) {
        this.numeroContrat = numeroContrat;
    }

    public LocalDate getDateEffet() {
        return dateEffet;
    }

    public void setDateEffet(LocalDate dateEffet) {
        this.dateEffet = dateEffet;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(String periodicite) {
        this.periodicite = periodicite;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getFichierRef() {
        return fichierRef;
    }

    public void setFichierRef(String fichierRef) {
        this.fichierRef = fichierRef;
    }

    public Equipement getEquipement() {
        return equipement;
    }

    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }
}

