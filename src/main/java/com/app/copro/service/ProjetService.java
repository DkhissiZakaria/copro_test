package com.app.copro.service;

import com.app.copro.dto.CreateProjetDto;
import com.app.copro.dto.ProjetResponseDto;
import com.app.copro.exception.ProjetAlreadyExistsException;
import com.app.copro.exception.ProjetNotFoundException;
import com.app.copro.model.Projet;
import com.app.copro.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjetService {

    private final ProjetRepository projetRepository;

    @Autowired
    public ProjetService(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    public ProjetResponseDto createProjet(CreateProjetDto createProjetDto) {
        if (projetRepository.existsByNom(createProjetDto.getNom())) {
            throw new ProjetAlreadyExistsException(createProjetDto.getNom());
        }

        Projet projet = new Projet();
        projet.setNom(createProjetDto.getNom());
        projet.setIdMakePlan(createProjetDto.getIdMakePlan());

        Projet savedProjet = projetRepository.save(projet);
        return mapToResponseDto(savedProjet);
    }

    public List<ProjetResponseDto> getAllProjets() {
        return projetRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public ProjetResponseDto getProjetById(Long id) {
        Projet projet = projetRepository.findById(id)
                .orElseThrow(() -> new ProjetNotFoundException(id));
        return mapToResponseDto(projet);
    }

    public ProjetResponseDto getProjetByNom(String nom) {
        Projet projet = projetRepository.findByNom(nom)
                .orElseThrow(() -> new ProjetNotFoundException("Projet avec le nom '" + nom + "' introuvable"));
        return mapToResponseDto(projet);
    }

    public ProjetResponseDto getProjetByIdMakePlan(Long idMakePlan) {
        Projet projet = projetRepository.findByIdMakePlan(idMakePlan)
                .orElseThrow(() -> new ProjetNotFoundException("Projet avec idMakePlan '" + idMakePlan + "' introuvable"));
        return mapToResponseDto(projet);
    }

    public void deleteProjet(Long id) {
        if (!projetRepository.existsById(id)) {
            throw new ProjetNotFoundException(id);
        }
        projetRepository.deleteById(id);
    }

    private ProjetResponseDto mapToResponseDto(Projet projet) {
        return new ProjetResponseDto(projet.getId(), projet.getNom(), projet.getIdMakePlan());
    }
}