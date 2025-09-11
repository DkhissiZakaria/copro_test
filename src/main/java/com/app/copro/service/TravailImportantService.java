package com.app.copro.service;

import com.app.copro.dto.TravailImportantDto;
import com.app.copro.exception.TravailImportantNotFoundException;
import com.app.copro.model.Carnet;
import com.app.copro.model.TravailImportant;
import com.app.copro.repository.CarnetRepository;
import com.app.copro.repository.TravailImportantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravailImportantService {

    private final TravailImportantRepository repository;
    private final CarnetRepository carnetRepository;

    public TravailImportantService(TravailImportantRepository repository, CarnetRepository carnetRepository) {
        this.repository = repository;
        this.carnetRepository = carnetRepository;
    }

    @Transactional
    public TravailImportantDto create(Long carnetId, TravailImportantDto dto) {
        Carnet carnet = carnetRepository.findById(carnetId)
                .orElseThrow(() -> new RuntimeException("Carnet not found"));
        TravailImportant entity = new TravailImportant();
        applyDtoToEntity(dto, entity);
        entity.setCarnet(carnet);
        TravailImportant saved = repository.save(entity);
        return mapToDto(saved);
    }

    public List<TravailImportantDto> getByCarnet(Long carnetId) {
        return repository.findByCarnetId(carnetId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public TravailImportantDto getById(Long id) {
        TravailImportant entity = repository.findById(id)
                .orElseThrow(() -> new TravailImportantNotFoundException(id));
        return mapToDto(entity);
    }

    @Transactional
    public TravailImportantDto update(Long id, TravailImportantDto dto) {
        TravailImportant entity = repository.findById(id)
                .orElseThrow(() -> new TravailImportantNotFoundException(id));
        applyDtoToEntity(dto, entity);
        TravailImportant saved = repository.save(entity);
        return mapToDto(saved);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new TravailImportantNotFoundException(id);
        }
        repository.deleteById(id);
    }

    private TravailImportantDto mapToDto(TravailImportant entity) {
        TravailImportantDto dto = new TravailImportantDto();
        dto.setId(entity.getId());
        dto.setNomTravaux(entity.getNomTravaux());
        dto.setDateDebut(entity.getDateDebut());
        dto.setDateFin(entity.getDateFin());
        dto.setAssembleeGenerale(entity.getAssembleeGenerale());
        dto.setCleCharges(entity.getCleCharges());
        dto.setMontantTravaux(entity.getMontantTravaux());
        dto.setNombreEcheances(entity.getNombreEcheances());
        dto.setDetails(entity.getDetails());
        dto.setImage1Ref(entity.getImage1Ref());
        dto.setImage2Ref(entity.getImage2Ref());
        dto.setImage3Ref(entity.getImage3Ref());
        return dto;
    }

    private void applyDtoToEntity(TravailImportantDto dto, TravailImportant entity) {
        entity.setNomTravaux(dto.getNomTravaux());
        entity.setDateDebut(dto.getDateDebut());
        entity.setDateFin(dto.getDateFin());
        entity.setAssembleeGenerale(dto.getAssembleeGenerale());
        entity.setCleCharges(dto.getCleCharges());
        entity.setMontantTravaux(dto.getMontantTravaux());
        entity.setNombreEcheances(dto.getNombreEcheances());
        entity.setDetails(dto.getDetails());
        entity.setImage1Ref(dto.getImage1Ref());
        entity.setImage2Ref(dto.getImage2Ref());
        entity.setImage3Ref(dto.getImage3Ref());
    }
}

