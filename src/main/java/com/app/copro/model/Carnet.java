package com.app.copro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "carnet")
public class Carnet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ManyToOne vers Syndic (existant)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "syndic_id")
    @JsonIgnore
    private Syndic syndic;

    // 1–1 Données Administratives (FK côté DonneesAdministratives)
    @OneToOne(mappedBy = "carnet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private DonneesAdministratives donneesAdministratives;

    // 1–1 Données Techniques (FK côté DonneesTechniques)
    @OneToOne(mappedBy = "carnet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private DonneesTechniques donneesTechniques;

    // 1–1 Plan Pluriannuel de Travaux (PPT) (FK côté Ppt)
    @OneToOne(mappedBy = "carnet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Ppt ppt;

    // 1–N Contrats d'assurance (FK côté ContratAssurance)
    @OneToMany(mappedBy = "carnet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContratAssurance> contrats = new ArrayList<>();

    // 1–N Contrats dommages-ouvrage (FK côté ContratDommageOuvrage)
    @OneToMany(mappedBy = "carnet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContratDommageOuvrage> contratsDommageOuvrage = new ArrayList<>();

    // 1–N Procédures administratives (FK côté ProcedureAdministrative)
    @OneToMany(mappedBy = "carnet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProcedureAdministrative> proceduresAdministratives = new ArrayList<>();

    // 1–N Travaux importants (FK côté TravailImportant)
    @OneToMany(mappedBy = "carnet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TravailImportant> travauxImportants = new ArrayList<>();

    // 1–N Diagnostics collectifs (FK côté DiagnosticCollectif)
    @OneToMany(mappedBy = "carnet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiagnosticCollectif> diagnosticsCollectifs = new ArrayList<>();

    // 1–N Chapitres (FK côté Chapitre)
    @OneToMany(mappedBy = "carnet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chapitre> chapitres = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Syndic getSyndic() {
        return syndic;
    }

    public void setSyndic(Syndic syndic) {
        this.syndic = syndic;
    }

    public DonneesAdministratives getDonneesAdministratives() {
        return donneesAdministratives;
    }

    public void setDonneesAdministratives(DonneesAdministratives donneesAdministratives) {
        this.donneesAdministratives = donneesAdministratives;
    }

    public DonneesTechniques getDonneesTechniques() {
        return donneesTechniques;
    }

    public void setDonneesTechniques(DonneesTechniques donneesTechniques) {
        this.donneesTechniques = donneesTechniques;
    }

    public Ppt getPpt() {
        return ppt;
    }

    public void setPpt(Ppt ppt) {
        this.ppt = ppt;
    }

    public List<ContratAssurance> getContrats() {
        return contrats;
    }

    public void setContrats(List<ContratAssurance> contrats) {
        this.contrats = contrats;
    }

    public List<ContratDommageOuvrage> getContratsDommageOuvrage() {
        return contratsDommageOuvrage;
    }

    public void setContratsDommageOuvrage(List<ContratDommageOuvrage> contratsDommageOuvrage) {
        this.contratsDommageOuvrage = contratsDommageOuvrage;
    }

    public List<ProcedureAdministrative> getProceduresAdministratives() {
        return proceduresAdministratives;
    }

    public void setProceduresAdministratives(List<ProcedureAdministrative> proceduresAdministratives) {
        this.proceduresAdministratives = proceduresAdministratives;
    }

    public List<TravailImportant> getTravauxImportants() {
        return travauxImportants;
    }

    public void setTravauxImportants(List<TravailImportant> travauxImportants) {
        this.travauxImportants = travauxImportants;
    }

    public List<DiagnosticCollectif> getDiagnosticsCollectifs() {
        return diagnosticsCollectifs;
    }

    public void setDiagnosticsCollectifs(List<DiagnosticCollectif> diagnosticsCollectifs) {
        this.diagnosticsCollectifs = diagnosticsCollectifs;
    }

    public List<Chapitre> getChapitres() {
        return chapitres;
    }

    public void setChapitres(List<Chapitre> chapitres) {
        this.chapitres = chapitres;
    }
}
