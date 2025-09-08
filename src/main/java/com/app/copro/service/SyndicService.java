package com.app.copro.service;

import com.app.copro.dto.CreateSyndicDto;
import com.app.copro.dto.SyndicResponseDto;
import com.app.copro.dto.UpdateSyndicDto;
import com.app.copro.exception.ProjetNotFoundException;
import com.app.copro.exception.SyndicCreationException;
import com.app.copro.exception.SyndicNotFoundException;
import com.app.copro.model.Projet;
import com.app.copro.model.Syndic;
import com.app.copro.repository.ProjetRepository;
import com.app.copro.repository.SyndicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SyndicService {

    private static final Logger logger = LoggerFactory.getLogger(SyndicService.class);

    private final SyndicRepository syndicRepository;
    private final ProjetRepository projetRepository;

    @Autowired
    public SyndicService(SyndicRepository syndicRepository, ProjetRepository projetRepository) {
        this.syndicRepository = syndicRepository;
        this.projetRepository = projetRepository;
    }

    @Transactional
    public SyndicResponseDto createSyndic(CreateSyndicDto createSyndicDto) {
        logger.info("Tentative de création d'un syndic avec idMakePlan: {}", createSyndicDto.getIdMakePlan());
        
        if (createSyndicDto.getIdMakePlan() == null) {
            logger.error("idMakePlan est null");
            throw new IllegalArgumentException("idMakePlan ne peut pas être null");
        }

        Projet projet = projetRepository.findByIdMakePlan(createSyndicDto.getIdMakePlan())
                .orElseThrow(() -> {
                    logger.error("Projet non trouvé avec idMakePlan: {}", createSyndicDto.getIdMakePlan());
                    return new ProjetNotFoundException("Projet avec idMakePlan '" + createSyndicDto.getIdMakePlan() + "' introuvable");
                });
        
        logger.info("Projet trouvé: {}", projet.getNom());

        Syndic syndic = new Syndic();
        syndic.setNom(createSyndicDto.getNom());
        syndic.setEmail(createSyndicDto.getEmail());
        syndic.setTelephone(createSyndicDto.getTelephone());
        syndic.setTelecopie(createSyndicDto.getTelecopie());
        syndic.setWeb(createSyndicDto.getWeb());
        syndic.setAdresseNumeroRue(createSyndicDto.getAdresseNumeroRue());
        syndic.setAdresseComplement(createSyndicDto.getAdresseComplement());
        syndic.setAdresseCodePostal(createSyndicDto.getAdresseCodePostal());
        syndic.setAdresseVille(createSyndicDto.getAdresseVille());
        syndic.setAdresseRegion(createSyndicDto.getAdresseRegion());
        syndic.setAdressePays(createSyndicDto.getAdressePays());
        syndic.setSiret(createSyndicDto.getSiret());
        syndic.setApe(createSyndicDto.getApe());
        syndic.setCarteProfessionnelle(createSyndicDto.getCarteProfessionnelle());
        syndic.setCapital(createSyndicDto.getCapital());
        syndic.setLogoCoordonneesRef(createSyndicDto.getLogoCoordonneesPath());
        syndic.setLogoSimpleRef(createSyndicDto.getLogoSimplePath());
        syndic.setPointeFinanciere(createSyndicDto.getPointeFinanciere());
        syndic.setSocieteGarant(createSyndicDto.getSocieteGarant());
        syndic.setNumeroTeleDeclarant(createSyndicDto.getNumeroTeleDeclarant());
        syndic.setMailTeleDeclarant(createSyndicDto.getMailTeleDeclarant());
        syndic.setDescription(createSyndicDto.getDescription());
        syndic.setDocCarteProfessionnelleRef(createSyndicDto.getDocCarteProfessionnellePath());
        syndic.setDocAssuranceRcRef(createSyndicDto.getDocAssuranceRcPath());
        syndic.setDocGarantieFinanciereRef(createSyndicDto.getDocGarantieFinancierePath());
        syndic.setDocTamponSignatureRef(createSyndicDto.getDocTamponSignaturePath());
        boolean isActive = createSyndicDto.getIsActive() != null ? createSyndicDto.getIsActive() : true;
        syndic.setIsActive(isActive);
        syndic.setProjet(projet);

        // Si le nouveau syndic est actif, désactiver tous les autres syndics du même projet
        if (isActive) {
            logger.info("Désactivation de tous les autres syndics pour le projet idMakePlan: {}", createSyndicDto.getIdMakePlan());
            syndicRepository.deactivateAllSyndicsByProjetIdMakePlan(createSyndicDto.getIdMakePlan());
        }
        // Temporairement commenté pour diagnostic
        // projet.getSyndics().add(syndic);

        try {
            logger.info("Tentative de sauvegarde du syndic: {}", syndic.getNom());
            Syndic saved = syndicRepository.save(syndic);
            logger.info("Syndic sauvegardé avec succès, ID: {}", saved.getId());
            return mapToResponseDto(saved);
        } catch (DataAccessException ex) {
            logger.error("Erreur DataAccessException lors de la création du syndic", ex);
            throw new SyndicCreationException("Erreur lors de la création du syndic", ex);
        } catch (Exception ex) {
            logger.error("Erreur générale lors de la création du syndic", ex);
            throw new SyndicCreationException("Erreur inattendue lors de la création du syndic", ex);
        }
    }

    private SyndicResponseDto mapToResponseDto(Syndic syndic) {
        SyndicResponseDto dto = new SyndicResponseDto();
        dto.setId(syndic.getId());
        dto.setNom(syndic.getNom());
        dto.setEmail(syndic.getEmail());
        dto.setTelephone(syndic.getTelephone());
        dto.setTelecopie(syndic.getTelecopie());
        dto.setWeb(syndic.getWeb());
        dto.setAdresseNumeroRue(syndic.getAdresseNumeroRue());
        dto.setAdresseComplement(syndic.getAdresseComplement());
        dto.setAdresseCodePostal(syndic.getAdresseCodePostal());
        dto.setAdresseVille(syndic.getAdresseVille());
        dto.setAdresseRegion(syndic.getAdresseRegion());
        dto.setAdressePays(syndic.getAdressePays());
        dto.setSiret(syndic.getSiret());
        dto.setApe(syndic.getApe());
        dto.setCarteProfessionnelle(syndic.getCarteProfessionnelle());
        dto.setCapital(syndic.getCapital());
        dto.setLogoCoordonneesPath(syndic.getLogoCoordonneesRef());
        dto.setLogoSimplePath(syndic.getLogoSimpleRef());
        dto.setPointeFinanciere(syndic.getPointeFinanciere());
        dto.setSocieteGarant(syndic.getSocieteGarant());
        dto.setNumeroTeleDeclarant(syndic.getNumeroTeleDeclarant());
        dto.setMailTeleDeclarant(syndic.getMailTeleDeclarant());
        dto.setDescription(syndic.getDescription());
        dto.setDocCarteProfessionnellePath(syndic.getDocCarteProfessionnelleRef());
        dto.setDocAssuranceRcPath(syndic.getDocAssuranceRcRef());
        dto.setDocGarantieFinancierePath(syndic.getDocGarantieFinanciereRef());
        dto.setDocTamponSignaturePath(syndic.getDocTamponSignatureRef());
        dto.setProjetId(syndic.getProjet() != null ? syndic.getProjet().getId() : null);
        dto.setIsActive(syndic.getIsActive());
        return dto;
    }

    public SyndicResponseDto getSyndicById(Long id) {
        Syndic syndic = syndicRepository.findById(id)
                .orElseThrow(() -> new SyndicNotFoundException(id));
        return mapToResponseDto(syndic);
    }

    public SyndicResponseDto updateSyndic(Long id, UpdateSyndicDto updateSyndicDto) {
        Syndic syndic = syndicRepository.findById(id)
                .orElseThrow(() -> new SyndicNotFoundException(id));

        syndic.setNom(updateSyndicDto.getNom());
        syndic.setEmail(updateSyndicDto.getEmail());
        syndic.setTelephone(updateSyndicDto.getTelephone());
        syndic.setTelecopie(updateSyndicDto.getTelecopie());
        syndic.setWeb(updateSyndicDto.getWeb());
        syndic.setAdresseNumeroRue(updateSyndicDto.getAdresseNumeroRue());
        syndic.setAdresseComplement(updateSyndicDto.getAdresseComplement());
        syndic.setAdresseCodePostal(updateSyndicDto.getAdresseCodePostal());
        syndic.setAdresseVille(updateSyndicDto.getAdresseVille());
        syndic.setAdresseRegion(updateSyndicDto.getAdresseRegion());
        syndic.setAdressePays(updateSyndicDto.getAdressePays());
        syndic.setSiret(updateSyndicDto.getSiret());
        syndic.setApe(updateSyndicDto.getApe());
        syndic.setCarteProfessionnelle(updateSyndicDto.getCarteProfessionnelle());
        syndic.setCapital(updateSyndicDto.getCapital());
        syndic.setLogoCoordonneesRef(updateSyndicDto.getLogoCoordonneesPath());
        syndic.setLogoSimpleRef(updateSyndicDto.getLogoSimplePath());
        syndic.setPointeFinanciere(updateSyndicDto.getPointeFinanciere());
        syndic.setSocieteGarant(updateSyndicDto.getSocieteGarant());
        syndic.setNumeroTeleDeclarant(updateSyndicDto.getNumeroTeleDeclarant());
        syndic.setMailTeleDeclarant(updateSyndicDto.getMailTeleDeclarant());
        syndic.setDescription(updateSyndicDto.getDescription());
        syndic.setDocCarteProfessionnelleRef(updateSyndicDto.getDocCarteProfessionnellePath());
        syndic.setDocAssuranceRcRef(updateSyndicDto.getDocAssuranceRcPath());
        syndic.setDocGarantieFinanciereRef(updateSyndicDto.getDocGarantieFinancierePath());
        syndic.setDocTamponSignatureRef(updateSyndicDto.getDocTamponSignaturePath());
        syndic.setIsActive(updateSyndicDto.getIsActive() != null ? updateSyndicDto.getIsActive() : syndic.getIsActive());

        try {
            Syndic updated = syndicRepository.save(syndic);
            return mapToResponseDto(updated);
        } catch (DataAccessException ex) {
            throw new SyndicCreationException("Erreur lors de la mise à jour du syndic", ex);
        }
    }

    public List<SyndicResponseDto> getSyndicsByIdMakePlan(Long idMakePlan) {
        try {
            // Vérifier que le projet existe
            projetRepository.findByIdMakePlan(idMakePlan)
                    .orElseThrow(() -> new ProjetNotFoundException("Projet avec idMakePlan '" + idMakePlan + "' introuvable"));

            // Récupérer les syndics par idMakePlan
            List<Syndic> syndics = syndicRepository.findByProjetIdMakePlan(idMakePlan);
            logger.info("Trouvé {} syndics pour idMakePlan {}", syndics.size(), idMakePlan);
            
            return syndics.stream()
                    .map(syndic -> {
                        try {
                            return mapToResponseDto(syndic); //
                        } catch (Exception e) {
                            logger.error("Erreur lors du mapping du syndic ID {}: {}", syndic.getId(), e.getMessage(), e);
                            throw new SyndicCreationException("Erreur lors du mapping du syndic", e);
                        }
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Erreur dans getSyndicsByIdMakePlan pour idMakePlan {}: {}", idMakePlan, e.getMessage(), e);
            throw e;
        }
    }

    public SyndicResponseDto        getActiveSyndicByIdMakePlan(Long idMakePlan) {
        try {
            // Vérifier que le projet existe
            projetRepository.findByIdMakePlan(idMakePlan)
                    .orElseThrow(() -> new ProjetNotFoundException("Projet avec idMakePlan '" + idMakePlan + "' introuvable"));

            // Récupérer le syndic actif
            List<Syndic> activeSyndics = syndicRepository.findActiveSyndicsByProjetIdMakePlan(idMakePlan);
            
            if (activeSyndics.isEmpty()) {
                throw new SyndicNotFoundException("Aucun syndic actif trouvé pour idMakePlan: " + idMakePlan);
            }
            
            if (activeSyndics.size() > 1) {
                logger.warn("Plusieurs syndics actifs trouvés pour idMakePlan: {}. Retour du premier.", idMakePlan);
            }
            
            Syndic activeSyndic = activeSyndics.get(0);
            logger.info("Syndic actif trouvé: {} pour idMakePlan: {}", activeSyndic.getNom(), idMakePlan);
            
            return mapToResponseDto(activeSyndic);
        } catch (Exception e) {
            logger.error("Erreur dans getActiveSyndicByIdMakePlan pour idMakePlan {}: {}", idMakePlan, e.getMessage(), e);
            throw e;
        }
    }
}
