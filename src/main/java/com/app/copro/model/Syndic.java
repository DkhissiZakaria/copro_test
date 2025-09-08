package com.app.copro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "syndic")
public class Syndic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ===== Détail du Syndic =====
    @Column(nullable = false, length = 120)
    private String nom;


    @Column(nullable = false, length = 150)
    private String email;

    @Column(length = 50)
    private String telephone;

    @Column(length = 50)
    private String telecopie; // fax

    @Column(length = 255)
    private String web; // url

    // ===== Adresse =====
    @Column(length = 180)
    private String adresseNumeroRue;   // N° et rue

    @Column(length = 180)
    private String adresseComplement;  // Complément

    @Column(length = 20)
    private String adresseCodePostal;

    @Column(length = 120)
    private String adresseVille;

    @Column(length = 120)
    private String adresseRegion;

    @Column(length = 120)
    private String adressePays;

    // ===== Informations juridiques =====
    @Column(length = 20)
    private String siret;

    @Column(length = 20)
    private String ape;

    @Column(length = 120)
    private String carteProfessionnelle; // n°/référence

    // Capital : string pour tolérer formats non strictement numériques
    @Column(length = 50)
    private String capital;

    // Logos (références vers FileEntity)
    @Column(length = 255)
    private String logoCoordonneesRef; // Format: FILE_ID:123

    @Column(length = 255)
    private String logoSimpleRef; // Format: FILE_ID:123

    // ===== Garantie Financière =====
    @Column(length = 150)
    private String pointeFinanciere ;

    @Column(length = 120)
    private String societeGarant;

    // ===== Registre Copropriété =====
    @Column(length = 50)
    private String numeroTeleDeclarant;

    @Column(length = 150)
    private String mailTeleDeclarant;

    // ===== Description =====
    @Column(length = 2000)
    private String description;

    // ===== Documents (références vers FileEntity) =====
    @Column(length = 255)
    private String docCarteProfessionnelleRef; // Format: FILE_ID:123

    @Column(length = 255)
    private String docAssuranceRcRef; // Format: FILE_ID:123

    @Column(length = 255)
    private String docGarantieFinanciereRef; // Format: FILE_ID:123

    @Column(length = 255)
    private String docTamponSignatureRef; // Format: FILE_ID:123

    // ===== Statut =====
    @Column(nullable = false)
    private Boolean isActive = false;

    // ===== Relations existantes =====

    // ManyToOne vers Projet
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "projet_id")
    @JsonIgnore
    private Projet projet;

    // Relations temporairement commentées pour éviter les problèmes de mapping
    // @OneToOne(mappedBy = "syndic", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // private Mandat mandat;

    // @OneToMany(mappedBy = "syndic", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Carnet> carnets = new ArrayList<>();

    // ===== Getters/Setters =====

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getTelecopie() { return telecopie; }
    public void setTelecopie(String telecopie) { this.telecopie = telecopie; }

    public String getWeb() { return web; }
    public void setWeb(String web) { this.web = web; }

    public String getAdresseNumeroRue() { return adresseNumeroRue; }
    public void setAdresseNumeroRue(String adresseNumeroRue) { this.adresseNumeroRue = adresseNumeroRue; }

    public String getAdresseComplement() { return adresseComplement; }
    public void setAdresseComplement(String adresseComplement) { this.adresseComplement = adresseComplement; }

    public String getAdresseCodePostal() { return adresseCodePostal; }
    public void setAdresseCodePostal(String adresseCodePostal) { this.adresseCodePostal = adresseCodePostal; }

    public String getAdresseVille() { return adresseVille; }
    public void setAdresseVille(String adresseVille) { this.adresseVille = adresseVille; }

    public String getAdresseRegion() { return adresseRegion; }
    public void setAdresseRegion(String adresseRegion) { this.adresseRegion = adresseRegion; }

    public String getAdressePays() { return adressePays; }
    public void setAdressePays(String adressePays) { this.adressePays = adressePays; }

    public String getSiret() { return siret; }
    public void setSiret(String siret) { this.siret = siret; }

    public String getApe() { return ape; }
    public void setApe(String ape) { this.ape = ape; }

    public String getCarteProfessionnelle() { return carteProfessionnelle; }
    public void setCarteProfessionnelle(String carteProfessionnelle) { this.carteProfessionnelle = carteProfessionnelle; }

    public String getCapital() { return capital; }
    public void setCapital(String capital) { this.capital = capital; }

    public String getLogoCoordonneesRef() { return logoCoordonneesRef; }
    public void setLogoCoordonneesRef(String logoCoordonneesRef) { this.logoCoordonneesRef = logoCoordonneesRef; }

    public String getLogoSimpleRef() { return logoSimpleRef; }
    public void setLogoSimpleRef(String logoSimpleRef) { this.logoSimpleRef = logoSimpleRef; }

    public String getPointeFinanciere() {
        return pointeFinanciere;
    }

    public void setPointeFinanciere(String pointeFinanciere) {
        this.pointeFinanciere = pointeFinanciere;
    }

    public String getSocieteGarant() { return societeGarant; }
    public void setSocieteGarant(String societeGarant) { this.societeGarant = societeGarant; }

    public String getNumeroTeleDeclarant() { return numeroTeleDeclarant; }
    public void setNumeroTeleDeclarant(String numeroTeleDeclarant) { this.numeroTeleDeclarant = numeroTeleDeclarant; }

    public String getMailTeleDeclarant() { return mailTeleDeclarant; }
    public void setMailTeleDeclarant(String mailTeleDeclarant) { this.mailTeleDeclarant = mailTeleDeclarant; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDocCarteProfessionnelleRef() { return docCarteProfessionnelleRef; }
    public void setDocCarteProfessionnelleRef(String docCarteProfessionnelleRef) { this.docCarteProfessionnelleRef = docCarteProfessionnelleRef; }

    public String getDocAssuranceRcRef() { return docAssuranceRcRef; }
    public void setDocAssuranceRcRef(String docAssuranceRcRef) { this.docAssuranceRcRef = docAssuranceRcRef; }

    public String getDocGarantieFinanciereRef() { return docGarantieFinanciereRef; }
    public void setDocGarantieFinanciereRef(String docGarantieFinanciereRef) { this.docGarantieFinanciereRef = docGarantieFinanciereRef; }

    public String getDocTamponSignatureRef() { return docTamponSignatureRef; }
    public void setDocTamponSignatureRef(String docTamponSignatureRef) { this.docTamponSignatureRef = docTamponSignatureRef; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public Projet getProjet() { return projet; }
    public void setProjet(Projet projet) { this.projet = projet; }

    // Temporairement commenté
    // public Mandat getMandat() { return mandat; }
    // public void setMandat(Mandat mandat) { this.mandat = mandat; }

    // public List<Carnet> getCarnets() { return carnets; }
    // public void setCarnets(List<Carnet> carnets) { this.carnets = carnets; }
}
