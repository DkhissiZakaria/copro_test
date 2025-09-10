package com.app.copro.dto;

public class DonneesTechniquesDto {
    private Long id;
    // Informations générales
    private String periodeConstruction;
    private String anneeConstruction;
    private Integer nbAscenseur;
    private Integer nombreMonteCharge;
    private String erp;

    // Équipements et énergie
    private String typeEauFroide;
    private String typeEauChaude;
    private String typeChauffage;
    private String energieChauffage;
    private Boolean chauffageUrbain;

    // Bâtiments (étiquette énergie)
    private Integer nbBatimentEtiquetteEnergieA;
    private Integer nbBatimentEtiquetteEnergieB;
    private Integer nbBatimentEtiquetteEnergieC;
    private Integer nbBatimentEtiquetteEnergieD;
    private Integer nbBatimentEtiquetteEnergieE;
    private Integer nbBatimentEtiquetteEnergieF;
    private Integer nbBatimentEtiquetteEnergieG;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeriodeConstruction() {
        return periodeConstruction;
    }

    public void setPeriodeConstruction(String periodeConstruction) {
        this.periodeConstruction = periodeConstruction;
    }

    public String getAnneeConstruction() {
        return anneeConstruction;
    }

    public void setAnneeConstruction(String anneeConstruction) {
        this.anneeConstruction = anneeConstruction;
    }

    public Integer getNbAscenseur() {
        return nbAscenseur;
    }

    public void setNbAscenseur(Integer nbAscenseur) {
        this.nbAscenseur = nbAscenseur;
    }

    public Integer getNombreMonteCharge() {
        return nombreMonteCharge;
    }

    public void setNombreMonteCharge(Integer nombreMonteCharge) {
        this.nombreMonteCharge = nombreMonteCharge;
    }

    public String getErp() {
        return erp;
    }

    public void setErp(String erp) {
        this.erp = erp;
    }

    public String getTypeEauFroide() {
        return typeEauFroide;
    }

    public void setTypeEauFroide(String typeEauFroide) {
        this.typeEauFroide = typeEauFroide;
    }

    public String getTypeEauChaude() {
        return typeEauChaude;
    }

    public void setTypeEauChaude(String typeEauChaude) {
        this.typeEauChaude = typeEauChaude;
    }

    public String getTypeChauffage() {
        return typeChauffage;
    }

    public void setTypeChauffage(String typeChauffage) {
        this.typeChauffage = typeChauffage;
    }

    public String getEnergieChauffage() {
        return energieChauffage;
    }

    public void setEnergieChauffage(String energieChauffage) {
        this.energieChauffage = energieChauffage;
    }

    public Boolean getChauffageUrbain() {
        return chauffageUrbain;
    }

    public void setChauffageUrbain(Boolean chauffageUrbain) {
        this.chauffageUrbain = chauffageUrbain;
    }

    public Integer getNbBatimentEtiquetteEnergieA() {
        return nbBatimentEtiquetteEnergieA;
    }

    public void setNbBatimentEtiquetteEnergieA(Integer nbBatimentEtiquetteEnergieA) {
        this.nbBatimentEtiquetteEnergieA = nbBatimentEtiquetteEnergieA;
    }

    public Integer getNbBatimentEtiquetteEnergieB() {
        return nbBatimentEtiquetteEnergieB;
    }

    public void setNbBatimentEtiquetteEnergieB(Integer nbBatimentEtiquetteEnergieB) {
        this.nbBatimentEtiquetteEnergieB = nbBatimentEtiquetteEnergieB;
    }

    public Integer getNbBatimentEtiquetteEnergieC() {
        return nbBatimentEtiquetteEnergieC;
    }

    public void setNbBatimentEtiquetteEnergieC(Integer nbBatimentEtiquetteEnergieC) {
        this.nbBatimentEtiquetteEnergieC = nbBatimentEtiquetteEnergieC;
    }

    public Integer getNbBatimentEtiquetteEnergieD() {
        return nbBatimentEtiquetteEnergieD;
    }

    public void setNbBatimentEtiquetteEnergieD(Integer nbBatimentEtiquetteEnergieD) {
        this.nbBatimentEtiquetteEnergieD = nbBatimentEtiquetteEnergieD;
    }

    public Integer getNbBatimentEtiquetteEnergieE() {
        return nbBatimentEtiquetteEnergieE;
    }

    public void setNbBatimentEtiquetteEnergieE(Integer nbBatimentEtiquetteEnergieE) {
        this.nbBatimentEtiquetteEnergieE = nbBatimentEtiquetteEnergieE;
    }

    public Integer getNbBatimentEtiquetteEnergieF() {
        return nbBatimentEtiquetteEnergieF;
    }

    public void setNbBatimentEtiquetteEnergieF(Integer nbBatimentEtiquetteEnergieF) {
        this.nbBatimentEtiquetteEnergieF = nbBatimentEtiquetteEnergieF;
    }

    public Integer getNbBatimentEtiquetteEnergieG() {
        return nbBatimentEtiquetteEnergieG;
    }

    public void setNbBatimentEtiquetteEnergieG(Integer nbBatimentEtiquetteEnergieG) {
        this.nbBatimentEtiquetteEnergieG = nbBatimentEtiquetteEnergieG;
    }
}

