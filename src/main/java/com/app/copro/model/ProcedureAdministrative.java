package com.app.copro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entité représentant une procédure administrative ou juridique.
 */
@Entity
@Table(name = "procedure_administrative")
public class ProcedureAdministrative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Type de procédure (ex. Arrêté relevant du code de la santé publique). */
    @Column(nullable = false)
    private String typeProcedure;

    /** Libellé de la procédure. */
    @Column(nullable = false)
    private String libelle;

    /** Date de l'arrêté. */
    private LocalDate dateArrete;

    /** Date de mainlevée. */
    private LocalDate dateMainlevee;

    /** Description détaillée. */
    @Column(length = 3000)
    private String description;

    /** Référence vers le fichier associé. */
    @Column(length = 255)
    private String fichierRef;

    // Relation N-1 vers Carnet
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carnet_id")
    @JsonIgnore
    private Carnet carnet;

    // ===== Getters / Setters =====
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

    public Carnet getCarnet() { return carnet; }
    public void setCarnet(Carnet carnet) { this.carnet = carnet; }
}
