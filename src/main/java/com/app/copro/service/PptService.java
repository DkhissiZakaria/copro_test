package com.app.copro.service;

import com.app.copro.dto.PptDto;
import com.app.copro.model.Carnet;
import com.app.copro.model.Ppt;
import com.app.copro.repository.CarnetRepository;
import com.app.copro.repository.PptRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PptService {

    private final PptRepository repository;
    private final CarnetRepository carnetRepository;

    public PptService(PptRepository repository, CarnetRepository carnetRepository) {
        this.repository = repository;
        this.carnetRepository = carnetRepository;
    }

    @Transactional
    public PptDto create(Long carnetId, PptDto dto) {
        Carnet carnet = carnetRepository.findById(carnetId)
                .orElseThrow(() -> new RuntimeException("Carnet not found"));

        if (repository.findByCarnetId(carnetId).isPresent()) {
            throw new IllegalStateException("PPT already exists for this carnet");
        }

        Ppt entity = new Ppt();
        applyDtoToEntity(dto, entity);
        entity.setCarnet(carnet);
        Ppt saved = repository.save(entity);
        return mapToDto(saved);
    }

    public PptDto getByCarnet(Long carnetId) {
        Ppt entity = repository.findByCarnetId(carnetId)
                .orElseThrow(() -> new RuntimeException("PPT not found for carnet " + carnetId));
        return mapToDto(entity);
    }

    @Transactional
    public PptDto update(Long carnetId, PptDto dto) {
        Ppt entity = repository.findByCarnetId(carnetId)
                .orElseThrow(() -> new RuntimeException("PPT not found for carnet " + carnetId));
        applyDtoToEntity(dto, entity);
        Ppt saved = repository.save(entity);
        return mapToDto(saved);
    }

    private PptDto mapToDto(Ppt entity) {
        PptDto dto = new PptDto();
        dto.setId(entity.getId());
        dto.setNatureTravaux(entity.getNatureTravaux());
        dto.setDateProjet(entity.getDateProjet());
        dto.setDetailProjet(entity.getDetailProjet());
        dto.setFichierRef(entity.getFichierRef());
        return dto;
    }

    private void applyDtoToEntity(PptDto dto, Ppt entity) {
        entity.setNatureTravaux(dto.getNatureTravaux());
        entity.setDateProjet(dto.getDateProjet());
        entity.setDetailProjet(dto.getDetailProjet());
        entity.setFichierRef(dto.getFichierRef());
    }
}
