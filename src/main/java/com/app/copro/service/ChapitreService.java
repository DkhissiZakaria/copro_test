package com.app.copro.service;

import com.app.copro.dto.ChapitreDto;
import com.app.copro.exception.ChapitreNotFoundException;
import com.app.copro.model.Carnet;
import com.app.copro.model.Chapitre;
import com.app.copro.repository.CarnetRepository;
import com.app.copro.repository.ChapitreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChapitreService {

    private final ChapitreRepository repository;
    private final CarnetRepository carnetRepository;

    public ChapitreService(ChapitreRepository repository, CarnetRepository carnetRepository) {
        this.repository = repository;
        this.carnetRepository = carnetRepository;
    }

    @Transactional
    public ChapitreDto create(Long carnetId, ChapitreDto dto) {
        Carnet carnet = carnetRepository.findById(carnetId)
                .orElseThrow(() -> new RuntimeException("Carnet not found"));
        Chapitre entity = new Chapitre();
        applyDtoToEntity(dto, entity);
        entity.setCarnet(carnet);
        Chapitre saved = repository.save(entity);
        return mapToDto(saved);
    }

    public List<ChapitreDto> getByCarnet(Long carnetId) {
        return repository.findByCarnetId(carnetId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ChapitreDto getById(Long id) {
        Chapitre entity = repository.findById(id)
                .orElseThrow(() -> new ChapitreNotFoundException(id));
        return mapToDto(entity);
    }

    @Transactional
    public ChapitreDto update(Long id, ChapitreDto dto) {
        Chapitre entity = repository.findById(id)
                .orElseThrow(() -> new ChapitreNotFoundException(id));
        applyDtoToEntity(dto, entity);
        Chapitre saved = repository.save(entity);
        return mapToDto(saved);
    }

    private ChapitreDto mapToDto(Chapitre entity) {
        ChapitreDto dto = new ChapitreDto();
        dto.setId(entity.getId());
        dto.setIdBatiment(entity.getIdBatiment());
        dto.setNom(entity.getNom());
        dto.setAdresse(entity.getAdresse());
        return dto;
    }

    private void applyDtoToEntity(ChapitreDto dto, Chapitre entity) {
        entity.setIdBatiment(dto.getIdBatiment());
        entity.setNom(dto.getNom());
        entity.setAdresse(dto.getAdresse());
    }
}

