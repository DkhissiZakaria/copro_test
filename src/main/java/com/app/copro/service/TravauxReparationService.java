package com.app.copro.service;

import com.app.copro.dto.TravauxReparationDto;
import com.app.copro.exception.TravauxReparationNotFoundException;
import com.app.copro.model.Equipement;
import com.app.copro.model.TravauxReparation;
import com.app.copro.repository.EquipementRepository;
import com.app.copro.repository.TravauxReparationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravauxReparationService {

    private final TravauxReparationRepository repository;
    private final EquipementRepository equipementRepository;

    public TravauxReparationService(TravauxReparationRepository repository, EquipementRepository equipementRepository) {
        this.repository = repository;
        this.equipementRepository = equipementRepository;
    }

    @Transactional
    public TravauxReparationDto create(Long equipementId, TravauxReparationDto dto) {
        Equipement equipement = equipementRepository.findById(equipementId)
                .orElseThrow(() -> new RuntimeException("Equipement not found"));
        TravauxReparation entity = new TravauxReparation();
        applyDtoToEntity(dto, entity);
        entity.setEquipement(equipement);
        TravauxReparation saved = repository.save(entity);
        return mapToDto(saved);
    }

    public List<TravauxReparationDto> getByEquipement(Long equipementId) {
        return repository.findByEquipementId(equipementId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public TravauxReparationDto getById(Long id) {
        TravauxReparation entity = repository.findById(id)
                .orElseThrow(() -> new TravauxReparationNotFoundException(id));
        return mapToDto(entity);
    }

    @Transactional
    public TravauxReparationDto update(Long id, TravauxReparationDto dto) {
        TravauxReparation entity = repository.findById(id)
                .orElseThrow(() -> new TravauxReparationNotFoundException(id));
        applyDtoToEntity(dto, entity);
        TravauxReparation saved = repository.save(entity);
        return mapToDto(saved);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new TravauxReparationNotFoundException(id);
        }
        repository.deleteById(id);
    }

    private TravauxReparationDto mapToDto(TravauxReparation entity) {
        TravauxReparationDto dto = new TravauxReparationDto();
        dto.setId(entity.getId());
        dto.setTypeTravaux(entity.getTypeTravaux());
        dto.setDescription(entity.getDescription());
        dto.setDateRealisation(entity.getDateRealisation());
        dto.setEntreprisePrestataire(entity.getEntreprisePrestataire());
        dto.setMontantFinal(entity.getMontantFinal());
        dto.setObservations(entity.getObservations());
        dto.setAvancement(entity.getAvancement());
        dto.setDevisRef(entity.getDevisRef());
        dto.setFactureRef(entity.getFactureRef());
        dto.setPhotoAvantRef(entity.getPhotoAvantRef());
        dto.setPhotoApresRef(entity.getPhotoApresRef());
        return dto;
    }

    private void applyDtoToEntity(TravauxReparationDto dto, TravauxReparation entity) {
        entity.setTypeTravaux(dto.getTypeTravaux());
        entity.setDescription(dto.getDescription());
        entity.setDateRealisation(dto.getDateRealisation());
        entity.setEntreprisePrestataire(dto.getEntreprisePrestataire());
        entity.setMontantFinal(dto.getMontantFinal());
        entity.setObservations(dto.getObservations());
        entity.setAvancement(dto.getAvancement());
        entity.setDevisRef(dto.getDevisRef());
        entity.setFactureRef(dto.getFactureRef());
        entity.setPhotoAvantRef(dto.getPhotoAvantRef());
        entity.setPhotoApresRef(dto.getPhotoApresRef());
    }
}
