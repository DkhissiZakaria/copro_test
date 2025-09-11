package com.app.copro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Contrat d'assurance dommages-ouvrage.
 */
@Entity
@Table(name = "contrat_dommage_ouvrage")
public class ContratDommageOuvrage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Type de contrat (ex. Dommage-ouvrage). */
    @Column(nullable = false)
    private String contrat;

    /** Assureur sélectionné. */
    @Column(nullable = false)
    private String assureur;

    /** Compagnie. */
    private String compagnie;

    /** Numéro de contrat. */
    private String numeroContrat;

    /** Numéro de police. */
    private String numeroPolice;

    /** Date d'effet du contrat. */
    private LocalDate dateEffet;

    /** Date de fin du contrat. */
    private LocalDate dateFin;

    /** Référence vers les travaux concernés (optionnel). */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travail_id")
    private TravailImportant travail;

    /** Souscripteur du contrat. */
    private String souscripteur;

    // Relation N-1 vers Carnet
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carnet_id")
    @JsonIgnore
    private Carnet carnet;

    // ===== Getters / Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContrat() { return contrat; }
    public void setContrat(String contrat) { this.contrat = contrat; }

    public String getAssureur() { return assureur; }
    public void setAssureur(String assureur) { this.assureur = assureur; }

    public String getCompagnie() { return compagnie; }
    public void setCompagnie(String compagnie) { this.compagnie = compagnie; }

    public String getNumeroContrat() { return numeroContrat; }
    public void setNumeroContrat(String numeroContrat) { this.numeroContrat = numeroContrat; }

    public String getNumeroPolice() { return numeroPolice; }
    public void setNumeroPolice(String numeroPolice) { this.numeroPolice = numeroPolice; }

    public LocalDate getDateEffet() { return dateEffet; }
    public void setDateEffet(LocalDate dateEffet) { this.dateEffet = dateEffet; }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public TravailImportant getTravail() { return travail; }
    public void setTravail(TravailImportant travail) { this.travail = travail; }

    public String getSouscripteur() { return souscripteur; }
    public void setSouscripteur(String souscripteur) { this.souscripteur = souscripteur; }

    public Carnet getCarnet() { return carnet; }
    public void setCarnet(Carnet carnet) { this.carnet = carnet; }
}
