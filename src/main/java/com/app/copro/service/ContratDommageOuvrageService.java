package com.app.copro.service;

import com.app.copro.dto.ContratDommageOuvrageDto;
import com.app.copro.exception.ContratDommageOuvrageNotFoundException;
import com.app.copro.model.Carnet;
import com.app.copro.model.ContratDommageOuvrage;
import com.app.copro.model.TravailImportant;
import com.app.copro.repository.CarnetRepository;
import com.app.copro.repository.ContratDommageOuvrageRepository;
import com.app.copro.repository.TravailImportantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContratDommageOuvrageService {

    private final ContratDommageOuvrageRepository repository;
    private final CarnetRepository carnetRepository;
    private final TravailImportantRepository travailRepository;

    public ContratDommageOuvrageService(ContratDommageOuvrageRepository repository,
                                        CarnetRepository carnetRepository,
                                        TravailImportantRepository travailRepository) {
        this.repository = repository;
        this.carnetRepository = carnetRepository;
        this.travailRepository = travailRepository;
    }

    @Transactional
    public ContratDommageOuvrageDto create(Long carnetId, ContratDommageOuvrageDto dto) {
        Carnet carnet = carnetRepository.findById(carnetId)
                .orElseThrow(() -> new RuntimeException("Carnet not found"));
        ContratDommageOuvrage entity = new ContratDommageOuvrage();
        applyDtoToEntity(dto, entity);
        entity.setCarnet(carnet);
        ContratDommageOuvrage saved = repository.save(entity);
        return mapToDto(saved);
    }

    public List<ContratDommageOuvrageDto> getByCarnet(Long carnetId) {
        return repository.findByCarnetId(carnetId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ContratDommageOuvrageDto getById(Long id) {
        ContratDommageOuvrage entity = repository.findById(id)
                .orElseThrow(() -> new ContratDommageOuvrageNotFoundException(id));
        return mapToDto(entity);
    }

    @Transactional
    public ContratDommageOuvrageDto update(Long id, ContratDommageOuvrageDto dto) {
        ContratDommageOuvrage entity = repository.findById(id)
                .orElseThrow(() -> new ContratDommageOuvrageNotFoundException(id));
        applyDtoToEntity(dto, entity);
        ContratDommageOuvrage saved = repository.save(entity);
        return mapToDto(saved);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ContratDommageOuvrageNotFoundException(id);
        }
        repository.deleteById(id);
    }

    private ContratDommageOuvrageDto mapToDto(ContratDommageOuvrage entity) {
        ContratDommageOuvrageDto dto = new ContratDommageOuvrageDto();
        dto.setId(entity.getId());
        dto.setContrat(entity.getContrat());
        dto.setAssureur(entity.getAssureur());
        dto.setCompagnie(entity.getCompagnie());
        dto.setNumeroContrat(entity.getNumeroContrat());
        dto.setNumeroPolice(entity.getNumeroPolice());
        dto.setDateEffet(entity.getDateEffet());
        dto.setDateFin(entity.getDateFin());
        dto.setTravailId(entity.getTravail() != null ? entity.getTravail().getId() : null);
        dto.setSouscripteur(entity.getSouscripteur());
        return dto;
    }

    private void applyDtoToEntity(ContratDommageOuvrageDto dto, ContratDommageOuvrage entity) {
        entity.setContrat(dto.getContrat());
        entity.setAssureur(dto.getAssureur());
        entity.setCompagnie(dto.getCompagnie());
        entity.setNumeroContrat(dto.getNumeroContrat());
        entity.setNumeroPolice(dto.getNumeroPolice());
        entity.setDateEffet(dto.getDateEffet());
        entity.setDateFin(dto.getDateFin());
        entity.setSouscripteur(dto.getSouscripteur());
        if (dto.getTravailId() != null) {
            TravailImportant travail = travailRepository.findById(dto.getTravailId())
                    .orElse(null);
            entity.setTravail(travail);
        } else {
            entity.setTravail(null);
        }
    }
}
