package com.app.copro.service;

import com.app.copro.dto.InterventionDto;
import com.app.copro.exception.InterventionNotFoundException;
import com.app.copro.model.Equipement;
import com.app.copro.model.Intervention;
import com.app.copro.repository.EquipementRepository;
import com.app.copro.repository.InterventionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterventionService {

    private final InterventionRepository repository;
    private final EquipementRepository equipementRepository;

    public InterventionService(InterventionRepository repository, EquipementRepository equipementRepository) {
        this.repository = repository;
        this.equipementRepository = equipementRepository;
    }

    @Transactional
    public InterventionDto create(Long equipementId, InterventionDto dto) {
        Equipement equipement = equipementRepository.findById(equipementId)
                .orElseThrow(() -> new RuntimeException("Equipement not found"));
        Intervention entity = new Intervention();
        applyDtoToEntity(dto, entity);
        entity.setEquipement(equipement);
        Intervention saved = repository.save(entity);
        return mapToDto(saved);
    }

    public List<InterventionDto> getByEquipement(Long equipementId) {
        return repository.findByEquipementId(equipementId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public InterventionDto getById(Long id) {
        Intervention entity = repository.findById(id)
                .orElseThrow(() -> new InterventionNotFoundException(id));
        return mapToDto(entity);
    }

    @Transactional
    public InterventionDto update(Long id, InterventionDto dto) {
        Intervention entity = repository.findById(id)
                .orElseThrow(() -> new InterventionNotFoundException(id));
        applyDtoToEntity(dto, entity);
        Intervention saved = repository.save(entity);
        return mapToDto(saved);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new InterventionNotFoundException(id);
        }
        repository.deleteById(id);
    }

    private InterventionDto mapToDto(Intervention entity) {
        InterventionDto dto = new InterventionDto();
        dto.setId(entity.getId());
        dto.setNatureIntervention(entity.getNatureIntervention());
        dto.setDate(entity.getDate());
        dto.setMontant(entity.getMontant());
        dto.setFournisseur(entity.getFournisseur());
        dto.setObservation(entity.getObservation());
        dto.setAvancement(entity.getAvancement());
        dto.setFichierRef(entity.getFichierRef());
        return dto;
    }

    private void applyDtoToEntity(InterventionDto dto, Intervention entity) {
        entity.setNatureIntervention(dto.getNatureIntervention());
        entity.setDate(dto.getDate());
        entity.setMontant(dto.getMontant());
        entity.setFournisseur(dto.getFournisseur());
        entity.setObservation(dto.getObservation());
        entity.setAvancement(dto.getAvancement());
        entity.setFichierRef(dto.getFichierRef());
    }
}
