package com.app.copro.dto;

public class SyndicResponseDto {

    private Long id;
    private String nom;
    private String email;
    private String telephone;
    private String telecopie;
    private String web;
    private String adresseNumeroRue;
    private String adresseComplement;
    private String adresseCodePostal;
    private String adresseVille;
    private String adresseRegion;
    private String adressePays;
    private String siret;
    private String ape;
    private String carteProfessionnelle;
    private String capital;
    private String logoCoordonneesPath;
    private String logoSimplePath;
    private String pointeFinanciere;
    private String societeGarant;
    private String numeroTeleDeclarant;
    private String mailTeleDeclarant;
    private String description;
    private String docCarteProfessionnellePath;
    private String docAssuranceRcPath;
    private String docGarantieFinancierePath;
    private String docTamponSignaturePath;
    private Long projetId;
    private Boolean isActive;

    public SyndicResponseDto() {}

    // getters and setters

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

    public String getLogoCoordonneesPath() { return logoCoordonneesPath; }
    public void setLogoCoordonneesPath(String logoCoordonneesPath) { this.logoCoordonneesPath = logoCoordonneesPath; }

    public String getLogoSimplePath() { return logoSimplePath; }
    public void setLogoSimplePath(String logoSimplePath) { this.logoSimplePath = logoSimplePath; }

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

    public String getDocCarteProfessionnellePath() { return docCarteProfessionnellePath; }
    public void setDocCarteProfessionnellePath(String docCarteProfessionnellePath) { this.docCarteProfessionnellePath = docCarteProfessionnellePath; }

    public String getDocAssuranceRcPath() { return docAssuranceRcPath; }
    public void setDocAssuranceRcPath(String docAssuranceRcPath) { this.docAssuranceRcPath = docAssuranceRcPath; }

    public String getDocGarantieFinancierePath() { return docGarantieFinancierePath; }
    public void setDocGarantieFinancierePath(String docGarantieFinancierePath) { this.docGarantieFinancierePath = docGarantieFinancierePath; }

    public String getDocTamponSignaturePath() { return docTamponSignaturePath; }
    public void setDocTamponSignaturePath(String docTamponSignaturePath) { this.docTamponSignaturePath = docTamponSignaturePath; }

    public Long getProjetId() { return projetId; }
    public void setProjetId(Long projetId) { this.projetId = projetId; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}
