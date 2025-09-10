package com.app.copro.service;

import com.app.copro.dto.ContratAssuranceDto;
import com.app.copro.exception.ContratAssuranceNotFoundException;
import com.app.copro.model.Carnet;
import com.app.copro.model.ContratAssurance;
import com.app.copro.repository.CarnetRepository;
import com.app.copro.repository.ContratAssuranceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContratAssuranceService {

    private final ContratAssuranceRepository repository;
    private final CarnetRepository carnetRepository;

    public ContratAssuranceService(ContratAssuranceRepository repository, CarnetRepository carnetRepository) {
        this.repository = repository;
        this.carnetRepository = carnetRepository;
    }

    @Transactional
    public ContratAssuranceDto create(Long carnetId, ContratAssuranceDto dto) {
        Carnet carnet = carnetRepository.findById(carnetId)
                .orElseThrow(() -> new RuntimeException("Carnet not found"));
        ContratAssurance entity = new ContratAssurance();
        applyDtoToEntity(dto, entity);
        entity.setCarnet(carnet);
        ContratAssurance saved = repository.save(entity);
        return mapToDto(saved);
    }

    public List<ContratAssuranceDto> getByCarnet(Long carnetId) {
        return repository.findByCarnetId(carnetId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ContratAssuranceDto getById(Long id) {
        ContratAssurance entity = repository.findById(id)
                .orElseThrow(() -> new ContratAssuranceNotFoundException(id));
        return mapToDto(entity);
    }

    @Transactional
    public ContratAssuranceDto update(Long id, ContratAssuranceDto dto) {
        ContratAssurance entity = repository.findById(id)
                .orElseThrow(() -> new ContratAssuranceNotFoundException(id));
        applyDtoToEntity(dto, entity);
        ContratAssurance saved = repository.save(entity);
        return mapToDto(saved);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ContratAssuranceNotFoundException(id);
        }
        repository.deleteById(id);
    }

    private ContratAssuranceDto mapToDto(ContratAssurance entity) {
        ContratAssuranceDto dto = new ContratAssuranceDto();
        dto.setId(entity.getId());
        dto.setContrat(entity.getContrat());
        dto.setAssureur(entity.getAssureur());
        dto.setCompagnie(entity.getCompagnie());
        dto.setNumeroContrat(entity.getNumeroContrat());
        dto.setNumeroPolice(entity.getNumeroPolice());
        dto.setDateEffet(entity.getDateEffet());
        dto.setDateFin(entity.getDateFin());
        dto.setPrime(entity.getPrime());
        dto.setFichierRef(entity.getFichierRef());
        return dto;
    }

    private void applyDtoToEntity(ContratAssuranceDto dto, ContratAssurance entity) {
        entity.setContrat(dto.getContrat());
        entity.setAssureur(dto.getAssureur());
        entity.setCompagnie(dto.getCompagnie());
        entity.setNumeroContrat(dto.getNumeroContrat());
        entity.setNumeroPolice(dto.getNumeroPolice());
        entity.setDateEffet(dto.getDateEffet());
        entity.setDateFin(dto.getDateFin());
        entity.setPrime(dto.getPrime());
        entity.setFichierRef(dto.getFichierRef());
    }
}
