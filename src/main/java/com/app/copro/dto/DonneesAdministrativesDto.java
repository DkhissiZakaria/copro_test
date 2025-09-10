package com.app.copro.dto;

import java.time.LocalDate;

public class DonneesAdministrativesDto {
    private Long id;

    // Adresse principale
    private String adressePrincipaleNumeroRue;
    private String adressePrincipaleComplement;
    private String adressePrincipaleCodePostal;
    private String adressePrincipaleVille;
    private String adressePrincipalePays;

    // Adresse secondaire
    private String adresseSecondaireNumeroRue;
    private String adresseSecondaireComplement;
    private String adresseSecondaireCodePostal;
    private String adresseSecondaireVille;
    private String adresseSecondairePays;

    // Autre adresse secondaire
    private String autreAdresseSecondaireNumeroRue;
    private String autreAdresseSecondaireComplement;
    private String autreAdresseSecondaireCodePostal;
    private String autreAdresseSecondaireVille;
    private String autreAdresseSecondairePays;

    // Autres informations
    private LocalDate dateReglementCopropriete;
    private LocalDate dateDerniereModificationReglementCopropriete;
    private Boolean residenceService;
    private String siret;
    private Boolean syndicatCooperatif;
    private Boolean syndicatPrincipal;
    private String numeroImmatriculationCoproprietePrincipale;
    private Integer nbASL;
    private Integer nbAFUL;
    private Integer nbUnionSyndical;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdressePrincipaleNumeroRue() {
        return adressePrincipaleNumeroRue;
    }

    public void setAdressePrincipaleNumeroRue(String adressePrincipaleNumeroRue) {
        this.adressePrincipaleNumeroRue = adressePrincipaleNumeroRue;
    }

    public String getAdressePrincipaleComplement() {
        return adressePrincipaleComplement;
    }

    public void setAdressePrincipaleComplement(String adressePrincipaleComplement) {
        this.adressePrincipaleComplement = adressePrincipaleComplement;
    }

    public String getAdressePrincipaleCodePostal() {
        return adressePrincipaleCodePostal;
    }

    public void setAdressePrincipaleCodePostal(String adressePrincipaleCodePostal) {
        this.adressePrincipaleCodePostal = adressePrincipaleCodePostal;
    }

    public String getAdressePrincipaleVille() {
        return adressePrincipaleVille;
    }

    public void setAdressePrincipaleVille(String adressePrincipaleVille) {
        this.adressePrincipaleVille = adressePrincipaleVille;
    }

    public String getAdressePrincipalePays() {
        return adressePrincipalePays;
    }

    public void setAdressePrincipalePays(String adressePrincipalePays) {
        this.adressePrincipalePays = adressePrincipalePays;
    }

    public String getAdresseSecondaireNumeroRue() {
        return adresseSecondaireNumeroRue;
    }

    public void setAdresseSecondaireNumeroRue(String adresseSecondaireNumeroRue) {
        this.adresseSecondaireNumeroRue = adresseSecondaireNumeroRue;
    }

    public String getAdresseSecondaireComplement() {
        return adresseSecondaireComplement;
    }

    public void setAdresseSecondaireComplement(String adresseSecondaireComplement) {
        this.adresseSecondaireComplement = adresseSecondaireComplement;
    }

    public String getAdresseSecondaireCodePostal() {
        return adresseSecondaireCodePostal;
    }

    public void setAdresseSecondaireCodePostal(String adresseSecondaireCodePostal) {
        this.adresseSecondaireCodePostal = adresseSecondaireCodePostal;
    }

    public String getAdresseSecondaireVille() {
        return adresseSecondaireVille;
    }

    public void setAdresseSecondaireVille(String adresseSecondaireVille) {
        this.adresseSecondaireVille = adresseSecondaireVille;
    }

    public String getAdresseSecondairePays() {
        return adresseSecondairePays;
    }

    public void setAdresseSecondairePays(String adresseSecondairePays) {
        this.adresseSecondairePays = adresseSecondairePays;
    }

    public String getAutreAdresseSecondaireNumeroRue() {
        return autreAdresseSecondaireNumeroRue;
    }

    public void setAutreAdresseSecondaireNumeroRue(String autreAdresseSecondaireNumeroRue) {
        this.autreAdresseSecondaireNumeroRue = autreAdresseSecondaireNumeroRue;
    }

    public String getAutreAdresseSecondaireComplement() {
        return autreAdresseSecondaireComplement;
    }

    public void setAutreAdresseSecondaireComplement(String autreAdresseSecondaireComplement) {
        this.autreAdresseSecondaireComplement = autreAdresseSecondaireComplement;
    }

    public String getAutreAdresseSecondaireCodePostal() {
        return autreAdresseSecondaireCodePostal;
    }

    public void setAutreAdresseSecondaireCodePostal(String autreAdresseSecondaireCodePostal) {
        this.autreAdresseSecondaireCodePostal = autreAdresseSecondaireCodePostal;
    }

    public String getAutreAdresseSecondaireVille() {
        return autreAdresseSecondaireVille;
    }

    public void setAutreAdresseSecondaireVille(String autreAdresseSecondaireVille) {
        this.autreAdresseSecondaireVille = autreAdresseSecondaireVille;
    }

    public String getAutreAdresseSecondairePays() {
        return autreAdresseSecondairePays;
    }

    public void setAutreAdresseSecondairePays(String autreAdresseSecondairePays) {
        this.autreAdresseSecondairePays = autreAdresseSecondairePays;
    }

    public LocalDate getDateReglementCopropriete() {
        return dateReglementCopropriete;
    }

    public void setDateReglementCopropriete(LocalDate dateReglementCopropriete) {
        this.dateReglementCopropriete = dateReglementCopropriete;
    }

    public LocalDate getDateDerniereModificationReglementCopropriete() {
        return dateDerniereModificationReglementCopropriete;
    }

    public void setDateDerniereModificationReglementCopropriete(LocalDate dateDerniereModificationReglementCopropriete) {
        this.dateDerniereModificationReglementCopropriete = dateDerniereModificationReglementCopropriete;
    }

    public Boolean getResidenceService() {
        return residenceService;
    }

    public void setResidenceService(Boolean residenceService) {
        this.residenceService = residenceService;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public Boolean getSyndicatCooperatif() {
        return syndicatCooperatif;
    }

    public void setSyndicatCooperatif(Boolean syndicatCooperatif) {
        this.syndicatCooperatif = syndicatCooperatif;
    }

    public Boolean getSyndicatPrincipal() {
        return syndicatPrincipal;
    }

    public void setSyndicatPrincipal(Boolean syndicatPrincipal) {
        this.syndicatPrincipal = syndicatPrincipal;
    }

    public String getNumeroImmatriculationCoproprietePrincipale() {
        return numeroImmatriculationCoproprietePrincipale;
    }

    public void setNumeroImmatriculationCoproprietePrincipale(String numeroImmatriculationCoproprietePrincipale) {
        this.numeroImmatriculationCoproprietePrincipale = numeroImmatriculationCoproprietePrincipale;
    }

    public Integer getNbASL() {
        return nbASL;
    }

    public void setNbASL(Integer nbASL) {
        this.nbASL = nbASL;
    }

    public Integer getNbAFUL() {
        return nbAFUL;
    }

    public void setNbAFUL(Integer nbAFUL) {
        this.nbAFUL = nbAFUL;
    }

    public Integer getNbUnionSyndical() {
        return nbUnionSyndical;
    }

    public void setNbUnionSyndical(Integer nbUnionSyndical) {
        this.nbUnionSyndical = nbUnionSyndical;
    }
}
