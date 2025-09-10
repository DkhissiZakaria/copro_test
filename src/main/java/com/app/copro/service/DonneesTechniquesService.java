package com.app.copro.service;

import com.app.copro.dto.DonneesTechniquesDto;
import com.app.copro.model.Carnet;
import com.app.copro.model.DonneesTechniques;
import com.app.copro.repository.CarnetRepository;
import com.app.copro.repository.DonneesTechniquesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DonneesTechniquesService {

    private final DonneesTechniquesRepository repository;
    private final CarnetRepository carnetRepository;

    public DonneesTechniquesService(DonneesTechniquesRepository repository, CarnetRepository carnetRepository) {
        this.repository = repository;
        this.carnetRepository = carnetRepository;
    }

    @Transactional
    public DonneesTechniquesDto create(Long carnetId, DonneesTechniquesDto dto) {
        Carnet carnet = carnetRepository.findById(carnetId)
                .orElseThrow(() -> new RuntimeException("Carnet not found"));

        if (repository.findByCarnetId(carnetId).isPresent()) {
            throw new IllegalStateException("Technical data already exists for this carnet");
        }

        DonneesTechniques entity = new DonneesTechniques();
        applyDtoToEntity(dto, entity);
        entity.setCarnet(carnet);
        DonneesTechniques saved = repository.save(entity);
        return mapToDto(saved);
    }

    public DonneesTechniquesDto getByCarnet(Long carnetId) {
        DonneesTechniques entity = repository.findByCarnetId(carnetId)
                .orElseThrow(() -> new RuntimeException("Technical data not found for carnet " + carnetId));
        return mapToDto(entity);
    }

    @Transactional
    public DonneesTechniquesDto update(Long carnetId, DonneesTechniquesDto dto) {
        DonneesTechniques entity = repository.findByCarnetId(carnetId)
                .orElseThrow(() -> new RuntimeException("Technical data not found for carnet " + carnetId));
        applyDtoToEntity(dto, entity);
        DonneesTechniques saved = repository.save(entity);
        return mapToDto(saved);
    }

    private DonneesTechniquesDto mapToDto(DonneesTechniques entity) {
        DonneesTechniquesDto dto = new DonneesTechniquesDto();
        dto.setId(entity.getId());
        dto.setPeriodeConstruction(entity.getPeriodeConstruction());
        dto.setAnneeConstruction(entity.getAnneeConstruction());
        dto.setNbAscenseur(entity.getNbAscenseur());
        dto.setNombreMonteCharge(entity.getNombreMonteCharge());
        dto.setErp(entity.getErp());
        dto.setTypeEauFroide(entity.getTypeEauFroide());
        dto.setTypeEauChaude(entity.getTypeEauChaude());
        dto.setTypeChauffage(entity.getTypeChauffage());
        dto.setEnergieChauffage(entity.getEnergieChauffage());
        dto.setChauffageUrbain(entity.getChauffageUrbain());
        dto.setNbBatimentEtiquetteEnergieA(entity.getNbBatimentEtiquetteEnergieA());
        dto.setNbBatimentEtiquetteEnergieB(entity.getNbBatimentEtiquetteEnergieB());
        dto.setNbBatimentEtiquetteEnergieC(entity.getNbBatimentEtiquetteEnergieC());
        dto.setNbBatimentEtiquetteEnergieD(entity.getNbBatimentEtiquetteEnergieD());
        dto.setNbBatimentEtiquetteEnergieE(entity.getNbBatimentEtiquetteEnergieE());
        dto.setNbBatimentEtiquetteEnergieF(entity.getNbBatimentEtiquetteEnergieF());
        dto.setNbBatimentEtiquetteEnergieG(entity.getNbBatimentEtiquetteEnergieG());
        return dto;
    }

    private void applyDtoToEntity(DonneesTechniquesDto dto, DonneesTechniques entity) {
        entity.setPeriodeConstruction(dto.getPeriodeConstruction());
        entity.setAnneeConstruction(dto.getAnneeConstruction());
        entity.setNbAscenseur(dto.getNbAscenseur());
        entity.setNombreMonteCharge(dto.getNombreMonteCharge());
        entity.setErp(dto.getErp());
        entity.setTypeEauFroide(dto.getTypeEauFroide());
        entity.setTypeEauChaude(dto.getTypeEauChaude());
        entity.setTypeChauffage(dto.getTypeChauffage());
        entity.setEnergieChauffage(dto.getEnergieChauffage());
        entity.setChauffageUrbain(dto.getChauffageUrbain());
        entity.setNbBatimentEtiquetteEnergieA(dto.getNbBatimentEtiquetteEnergieA());
        entity.setNbBatimentEtiquetteEnergieB(dto.getNbBatimentEtiquetteEnergieB());
        entity.setNbBatimentEtiquetteEnergieC(dto.getNbBatimentEtiquetteEnergieC());
        entity.setNbBatimentEtiquetteEnergieD(dto.getNbBatimentEtiquetteEnergieD());
        entity.setNbBatimentEtiquetteEnergieE(dto.getNbBatimentEtiquetteEnergieE());
        entity.setNbBatimentEtiquetteEnergieF(dto.getNbBatimentEtiquetteEnergieF());
        entity.setNbBatimentEtiquetteEnergieG(dto.getNbBatimentEtiquetteEnergieG());
    }
}

