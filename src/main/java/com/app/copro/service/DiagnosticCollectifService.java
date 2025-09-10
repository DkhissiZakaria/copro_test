package com.app.copro.service;

import com.app.copro.dto.DiagnosticCollectifDto;
import com.app.copro.exception.DiagnosticCollectifNotFoundException;
import com.app.copro.model.Carnet;
import com.app.copro.model.DiagnosticCollectif;
import com.app.copro.repository.CarnetRepository;
import com.app.copro.repository.DiagnosticCollectifRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiagnosticCollectifService {

    private final DiagnosticCollectifRepository repository;
    private final CarnetRepository carnetRepository;

    public DiagnosticCollectifService(DiagnosticCollectifRepository repository, CarnetRepository carnetRepository) {
        this.repository = repository;
        this.carnetRepository = carnetRepository;
    }

    @Transactional
    public DiagnosticCollectifDto create(Long carnetId, DiagnosticCollectifDto dto) {
        Carnet carnet = carnetRepository.findById(carnetId)
                .orElseThrow(() -> new RuntimeException("Carnet not found"));
        DiagnosticCollectif entity = new DiagnosticCollectif();
        applyDtoToEntity(dto, entity);
        entity.setCarnet(carnet);
        DiagnosticCollectif saved = repository.save(entity);
        return mapToDto(saved);
    }

    public List<DiagnosticCollectifDto> getByCarnet(Long carnetId) {
        return repository.findByCarnetId(carnetId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public DiagnosticCollectifDto getById(Long id) {
        DiagnosticCollectif entity = repository.findById(id)
                .orElseThrow(() -> new DiagnosticCollectifNotFoundException(id));
        return mapToDto(entity);
    }

    @Transactional
    public DiagnosticCollectifDto update(Long id, DiagnosticCollectifDto dto) {
        DiagnosticCollectif entity = repository.findById(id)
                .orElseThrow(() -> new DiagnosticCollectifNotFoundException(id));
        applyDtoToEntity(dto, entity);
        DiagnosticCollectif saved = repository.save(entity);
        return mapToDto(saved);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new DiagnosticCollectifNotFoundException(id);
        }
        repository.deleteById(id);
    }

    private DiagnosticCollectifDto mapToDto(DiagnosticCollectif entity) {
        DiagnosticCollectifDto dto = new DiagnosticCollectifDto();
        dto.setId(entity.getId());
        dto.setDiagnostiqueur(entity.getDiagnostiqueur());
        dto.setTypeDiagnostic(entity.getTypeDiagnostic());
        dto.setRefPv(entity.getRefPv());
        dto.setMontant(entity.getMontant());
        dto.setDateControle(entity.getDateControle());
        dto.setDateEcheance(entity.getDateEcheance());
        dto.setValeur(entity.getValeur());
        dto.setIndicateur(entity.getIndicateur());
        dto.setDescription(entity.getDescription());
        dto.setFichierRef(entity.getFichierRef());
        return dto;
    }

    private void applyDtoToEntity(DiagnosticCollectifDto dto, DiagnosticCollectif entity) {
        entity.setDiagnostiqueur(dto.getDiagnostiqueur());
        entity.setTypeDiagnostic(dto.getTypeDiagnostic());
        entity.setRefPv(dto.getRefPv());
        entity.setMontant(dto.getMontant());
        entity.setDateControle(dto.getDateControle());
        entity.setDateEcheance(dto.getDateEcheance());
        entity.setValeur(dto.getValeur());
        entity.setIndicateur(dto.getIndicateur());
        entity.setDescription(dto.getDescription());
        entity.setFichierRef(dto.getFichierRef());
    }
}

