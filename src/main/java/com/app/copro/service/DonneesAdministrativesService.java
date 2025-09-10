package com.app.copro.service;

import com.app.copro.dto.DonneesAdministrativesDto;
import com.app.copro.model.Carnet;
import com.app.copro.model.DonneesAdministratives;
import com.app.copro.repository.CarnetRepository;
import com.app.copro.repository.DonneesAdministrativesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DonneesAdministrativesService {

    private final DonneesAdministrativesRepository repository;
    private final CarnetRepository carnetRepository;

    public DonneesAdministrativesService(DonneesAdministrativesRepository repository, CarnetRepository carnetRepository) {
        this.repository = repository;
        this.carnetRepository = carnetRepository;
    }

    @Transactional
    public DonneesAdministrativesDto create(Long carnetId, DonneesAdministrativesDto dto) {
        Carnet carnet = carnetRepository.findById(carnetId)
                .orElseThrow(() -> new RuntimeException("Carnet not found"));

        if (repository.findByCarnetId(carnetId).isPresent()) {
            throw new IllegalStateException("Administrative data already exists for this carnet");
        }

        DonneesAdministratives entity = new DonneesAdministratives();
        applyDtoToEntity(dto, entity);
        entity.setCarnet(carnet);
        DonneesAdministratives saved = repository.save(entity);
        return mapToDto(saved);
    }

    public DonneesAdministrativesDto getByCarnet(Long carnetId) {
        DonneesAdministratives entity = repository.findByCarnetId(carnetId)
                .orElseThrow(() -> new RuntimeException("Administrative data not found for carnet " + carnetId));
        return mapToDto(entity);
    }

    @Transactional
    public DonneesAdministrativesDto update(Long carnetId, DonneesAdministrativesDto dto) {
        DonneesAdministratives entity = repository.findByCarnetId(carnetId)
                .orElseThrow(() -> new RuntimeException("Administrative data not found for carnet " + carnetId));
        applyDtoToEntity(dto, entity);
        DonneesAdministratives saved = repository.save(entity);
        return mapToDto(saved);
    }

    private DonneesAdministrativesDto mapToDto(DonneesAdministratives entity) {
        DonneesAdministrativesDto dto = new DonneesAdministrativesDto();
        dto.setId(entity.getId());
        dto.setAdressePrincipaleNumeroRue(entity.getAdressePrincipaleNumeroRue());
        dto.setAdressePrincipaleComplement(entity.getAdressePrincipaleComplement());
        dto.setAdressePrincipaleCodePostal(entity.getAdressePrincipaleCodePostal());
        dto.setAdressePrincipaleVille(entity.getAdressePrincipaleVille());
        dto.setAdressePrincipalePays(entity.getAdressePrincipalePays());
        dto.setAdresseSecondaireNumeroRue(entity.getAdresseSecondaireNumeroRue());
        dto.setAdresseSecondaireComplement(entity.getAdresseSecondaireComplement());
        dto.setAdresseSecondaireCodePostal(entity.getAdresseSecondaireCodePostal());
        dto.setAdresseSecondaireVille(entity.getAdresseSecondaireVille());
        dto.setAdresseSecondairePays(entity.getAdresseSecondairePays());
        dto.setAutreAdresseSecondaireNumeroRue(entity.getAutreAdresseSecondaireNumeroRue());
        dto.setAutreAdresseSecondaireComplement(entity.getAutreAdresseSecondaireComplement());
        dto.setAutreAdresseSecondaireCodePostal(entity.getAutreAdresseSecondaireCodePostal());
        dto.setAutreAdresseSecondaireVille(entity.getAutreAdresseSecondaireVille());
        dto.setAutreAdresseSecondairePays(entity.getAutreAdresseSecondairePays());
        dto.setDateReglementCopropriete(entity.getDateReglementCopropriete());
        dto.setDateDerniereModificationReglementCopropriete(entity.getDateDerniereModificationReglementCopropriete());
        dto.setResidenceService(entity.getResidenceService());
        dto.setSiret(entity.getSiret());
        dto.setSyndicatCooperatif(entity.getSyndicatCooperatif());
        dto.setSyndicatPrincipal(entity.getSyndicatPrincipal());
        dto.setNumeroImmatriculationCoproprietePrincipale(entity.getNumeroImmatriculationCoproprietePrincipale());
        dto.setNbASL(entity.getNbASL());
        dto.setNbAFUL(entity.getNbAFUL());
        dto.setNbUnionSyndical(entity.getNbUnionSyndical());
        return dto;
    }

    private void applyDtoToEntity(DonneesAdministrativesDto dto, DonneesAdministratives entity) {
        entity.setAdressePrincipaleNumeroRue(dto.getAdressePrincipaleNumeroRue());
        entity.setAdressePrincipaleComplement(dto.getAdressePrincipaleComplement());
        entity.setAdressePrincipaleCodePostal(dto.getAdressePrincipaleCodePostal());
        entity.setAdressePrincipaleVille(dto.getAdressePrincipaleVille());
        entity.setAdressePrincipalePays(dto.getAdressePrincipalePays());
        entity.setAdresseSecondaireNumeroRue(dto.getAdresseSecondaireNumeroRue());
        entity.setAdresseSecondaireComplement(dto.getAdresseSecondaireComplement());
        entity.setAdresseSecondaireCodePostal(dto.getAdresseSecondaireCodePostal());
        entity.setAdresseSecondaireVille(dto.getAdresseSecondaireVille());
        entity.setAdresseSecondairePays(dto.getAdresseSecondairePays());
        entity.setAutreAdresseSecondaireNumeroRue(dto.getAutreAdresseSecondaireNumeroRue());
        entity.setAutreAdresseSecondaireComplement(dto.getAutreAdresseSecondaireComplement());
        entity.setAutreAdresseSecondaireCodePostal(dto.getAutreAdresseSecondaireCodePostal());
        entity.setAutreAdresseSecondaireVille(dto.getAutreAdresseSecondaireVille());
        entity.setAutreAdresseSecondairePays(dto.getAutreAdresseSecondairePays());
        entity.setDateReglementCopropriete(dto.getDateReglementCopropriete());
        entity.setDateDerniereModificationReglementCopropriete(dto.getDateDerniereModificationReglementCopropriete());
        entity.setResidenceService(dto.getResidenceService());
        entity.setSiret(dto.getSiret());
        entity.setSyndicatCooperatif(dto.getSyndicatCooperatif());
        entity.setSyndicatPrincipal(dto.getSyndicatPrincipal());
        entity.setNumeroImmatriculationCoproprietePrincipale(dto.getNumeroImmatriculationCoproprietePrincipale());
        entity.setNbASL(dto.getNbASL());
        entity.setNbAFUL(dto.getNbAFUL());
        entity.setNbUnionSyndical(dto.getNbUnionSyndical());
    }
}
