package com.app.copro.service;

import com.app.copro.dto.ContratMaintenanceDto;
import com.app.copro.exception.ContratMaintenanceNotFoundException;
import com.app.copro.model.ContratMaintenance;
import com.app.copro.model.Equipement;
import com.app.copro.repository.ContratMaintenanceRepository;
import com.app.copro.repository.EquipementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContratMaintenanceService {

    private final ContratMaintenanceRepository repository;
    private final EquipementRepository equipementRepository;

    public ContratMaintenanceService(ContratMaintenanceRepository repository, EquipementRepository equipementRepository) {
        this.repository = repository;
        this.equipementRepository = equipementRepository;
    }

    @Transactional
    public ContratMaintenanceDto create(Long equipementId, ContratMaintenanceDto dto) {
        Equipement equipement = equipementRepository.findById(equipementId)
                .orElseThrow(() -> new RuntimeException("Equipement not found"));
        ContratMaintenance entity = new ContratMaintenance();
        applyDtoToEntity(dto, entity);
        entity.setEquipement(equipement);
        ContratMaintenance saved = repository.save(entity);
        return mapToDto(saved);
    }

    public List<ContratMaintenanceDto> getByEquipement(Long equipementId) {
        return repository.findByEquipementId(equipementId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ContratMaintenanceDto getById(Long id) {
        ContratMaintenance entity = repository.findById(id)
                .orElseThrow(() -> new ContratMaintenanceNotFoundException(id));
        return mapToDto(entity);
    }

    @Transactional
    public ContratMaintenanceDto update(Long id, ContratMaintenanceDto dto) {
        ContratMaintenance entity = repository.findById(id)
                .orElseThrow(() -> new ContratMaintenanceNotFoundException(id));
        applyDtoToEntity(dto, entity);
        ContratMaintenance saved = repository.save(entity);
        return mapToDto(saved);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ContratMaintenanceNotFoundException(id);
        }
        repository.deleteById(id);
    }

    private ContratMaintenanceDto mapToDto(ContratMaintenance entity) {
        ContratMaintenanceDto dto = new ContratMaintenanceDto();
        dto.setId(entity.getId());
        dto.setContrat(entity.getContrat());
        dto.setNumeroContrat(entity.getNumeroContrat());
        dto.setDateEffet(entity.getDateEffet());
        dto.setDateEcheance(entity.getDateEcheance());
        dto.setLocalisation(entity.getLocalisation());
        dto.setPeriodicite(entity.getPeriodicite());
        dto.setEntreprise(entity.getEntreprise());
        dto.setMontant(entity.getMontant());
        dto.setFichierRef(entity.getFichierRef());
        return dto;
    }

    private void applyDtoToEntity(ContratMaintenanceDto dto, ContratMaintenance entity) {
        entity.setContrat(dto.getContrat());
        entity.setNumeroContrat(dto.getNumeroContrat());
        entity.setDateEffet(dto.getDateEffet());
        entity.setDateEcheance(dto.getDateEcheance());
        entity.setLocalisation(dto.getLocalisation());
        entity.setPeriodicite(dto.getPeriodicite());
        entity.setEntreprise(dto.getEntreprise());
        entity.setMontant(dto.getMontant());
        entity.setFichierRef(dto.getFichierRef());
    }
}
