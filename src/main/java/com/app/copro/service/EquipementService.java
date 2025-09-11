package com.app.copro.service;

import com.app.copro.dto.EquipementDto;
import com.app.copro.exception.EquipementNotFoundException;
import com.app.copro.model.Chapitre;
import com.app.copro.model.Equipement;
import com.app.copro.repository.ChapitreRepository;
import com.app.copro.repository.EquipementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipementService {

    private final EquipementRepository repository;
    private final ChapitreRepository chapitreRepository;

    public EquipementService(EquipementRepository repository, ChapitreRepository chapitreRepository) {
        this.repository = repository;
        this.chapitreRepository = chapitreRepository;
    }

    @Transactional
    public EquipementDto create(Long chapitreId, EquipementDto dto) {
        Chapitre chapitre = chapitreRepository.findById(chapitreId)
                .orElseThrow(() -> new RuntimeException("Chapitre not found"));
        Equipement entity = new Equipement();
        applyDtoToEntity(dto, entity);
        entity.setChapitre(chapitre);
        Equipement saved = repository.save(entity);
        return mapToDto(saved);
    }

    public List<EquipementDto> getByChapitre(Long chapitreId) {
        return repository.findByChapitreId(chapitreId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public EquipementDto getById(Long id) {
        Equipement entity = repository.findById(id)
                .orElseThrow(() -> new EquipementNotFoundException(id));
        return mapToDto(entity);
    }

    @Transactional
    public EquipementDto update(Long id, EquipementDto dto) {
        Equipement entity = repository.findById(id)
                .orElseThrow(() -> new EquipementNotFoundException(id));
        applyDtoToEntity(dto, entity);
        Equipement saved = repository.save(entity);
        return mapToDto(saved);
    }

    private EquipementDto mapToDto(Equipement entity) {
        EquipementDto dto = new EquipementDto();
        dto.setId(entity.getId());
        dto.setLibelle(entity.getLibelle());
        dto.setMarque(entity.getMarque());
        dto.setReference(entity.getReference());
        dto.setModele(entity.getModele());
        dto.setEmplacement(entity.getEmplacement());
        dto.setQuantite(entity.getQuantite());
        dto.setDateAchat(entity.getDateAchat());
        dto.setDateFinGarantie(entity.getDateFinGarantie());
        dto.setPrestataire(entity.getPrestataire());
        dto.setNumeroContrat(entity.getNumeroContrat());
        return dto;
    }

    private void applyDtoToEntity(EquipementDto dto, Equipement entity) {
        entity.setLibelle(dto.getLibelle());
        entity.setMarque(dto.getMarque());
        entity.setReference(dto.getReference());
        entity.setModele(dto.getModele());
        entity.setEmplacement(dto.getEmplacement());
        entity.setQuantite(dto.getQuantite());
        entity.setDateAchat(dto.getDateAchat());
        entity.setDateFinGarantie(dto.getDateFinGarantie());
        entity.setPrestataire(dto.getPrestataire());
        entity.setNumeroContrat(dto.getNumeroContrat());
    }
}
