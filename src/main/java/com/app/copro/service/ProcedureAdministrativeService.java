package com.app.copro.service;

import com.app.copro.dto.ProcedureAdministrativeDto;
import com.app.copro.exception.ProcedureAdministrativeNotFoundException;
import com.app.copro.model.Carnet;
import com.app.copro.model.ProcedureAdministrative;
import com.app.copro.repository.CarnetRepository;
import com.app.copro.repository.ProcedureAdministrativeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcedureAdministrativeService {

    private final ProcedureAdministrativeRepository repository;
    private final CarnetRepository carnetRepository;

    public ProcedureAdministrativeService(ProcedureAdministrativeRepository repository, CarnetRepository carnetRepository) {
        this.repository = repository;
        this.carnetRepository = carnetRepository;
    }

    @Transactional
    public ProcedureAdministrativeDto create(Long carnetId, ProcedureAdministrativeDto dto) {
        Carnet carnet = carnetRepository.findById(carnetId)
                .orElseThrow(() -> new RuntimeException("Carnet not found"));
        ProcedureAdministrative entity = new ProcedureAdministrative();
        applyDtoToEntity(dto, entity);
        entity.setCarnet(carnet);
        ProcedureAdministrative saved = repository.save(entity);
        return mapToDto(saved);
    }

    public List<ProcedureAdministrativeDto> getByCarnet(Long carnetId) {
        return repository.findByCarnetId(carnetId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ProcedureAdministrativeDto getById(Long id) {
        ProcedureAdministrative entity = repository.findById(id)
                .orElseThrow(() -> new ProcedureAdministrativeNotFoundException(id));
        return mapToDto(entity);
    }

    @Transactional
    public ProcedureAdministrativeDto update(Long id, ProcedureAdministrativeDto dto) {
        ProcedureAdministrative entity = repository.findById(id)
                .orElseThrow(() -> new ProcedureAdministrativeNotFoundException(id));
        applyDtoToEntity(dto, entity);
        ProcedureAdministrative saved = repository.save(entity);
        return mapToDto(saved);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ProcedureAdministrativeNotFoundException(id);
        }
        repository.deleteById(id);
    }

    private ProcedureAdministrativeDto mapToDto(ProcedureAdministrative entity) {
        ProcedureAdministrativeDto dto = new ProcedureAdministrativeDto();
        dto.setId(entity.getId());
        dto.setTypeProcedure(entity.getTypeProcedure());
        dto.setLibelle(entity.getLibelle());
        dto.setDateArrete(entity.getDateArrete());
        dto.setDateMainlevee(entity.getDateMainlevee());
        dto.setDescription(entity.getDescription());
        dto.setFichierRef(entity.getFichierRef());
        return dto;
    }

    private void applyDtoToEntity(ProcedureAdministrativeDto dto, ProcedureAdministrative entity) {
        entity.setTypeProcedure(dto.getTypeProcedure());
        entity.setLibelle(dto.getLibelle());
        entity.setDateArrete(dto.getDateArrete());
        entity.setDateMainlevee(dto.getDateMainlevee());
        entity.setDescription(dto.getDescription());
        entity.setFichierRef(dto.getFichierRef());
    }
}
