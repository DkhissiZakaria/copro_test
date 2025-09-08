package com.app.copro.service;

import com.app.copro.dto.MandatDto;
import com.app.copro.exception.MandatNotFoundException;
import com.app.copro.exception.SyndicNotFoundException;
import com.app.copro.model.Mandat;
import com.app.copro.model.Syndic;
import com.app.copro.repository.MandatRepository;
import com.app.copro.repository.SyndicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MandatService {

    private final MandatRepository mandatRepository;
    private final SyndicRepository syndicRepository;

    @Autowired
    public MandatService(MandatRepository mandatRepository, SyndicRepository syndicRepository) {
        this.mandatRepository = mandatRepository;
        this.syndicRepository = syndicRepository;
    }

    @Transactional
    public MandatDto createMandat(Long syndicId, MandatDto dto) {
        Syndic syndic = syndicRepository.findById(syndicId)
                .orElseThrow(() -> new SyndicNotFoundException(syndicId));

        if (mandatRepository.findBySyndicId(syndicId).isPresent()) {
            throw new IllegalStateException("Mandat already exists for this syndic");
        }

        Mandat mandat = new Mandat();
        applyDtoToEntity(dto, mandat);
        mandat.setSyndic(syndic);
        Mandat saved = mandatRepository.save(mandat);
        return mapToDto(saved);
    }

    public MandatDto getMandatBySyndic(Long syndicId) {
        Mandat mandat = mandatRepository.findBySyndicId(syndicId)
                .orElseThrow(() -> new MandatNotFoundException(syndicId));
        return mapToDto(mandat);
    }

    @Transactional
    public MandatDto updateMandat(Long syndicId, MandatDto dto) {
        Mandat mandat = mandatRepository.findBySyndicId(syndicId)
                .orElseThrow(() -> new MandatNotFoundException(syndicId));
        applyDtoToEntity(dto, mandat);
        Mandat saved = mandatRepository.save(mandat);
        return mapToDto(saved);
    }

    private MandatDto mapToDto(Mandat mandat) {
        MandatDto dto = new MandatDto();
        dto.setId(mandat.getId());
        dto.setNumero(mandat.getNumero());
        dto.setDateDebut(mandat.getDateDebut());
        dto.setDateFin(mandat.getDateFin());
        dto.setDateSignature(mandat.getDateSignature());
        dto.setTypeMandat(mandat.getTypeMandat());
        dto.setAdministrateurProvisoire(mandat.getAdministrateurProvisoire());
        dto.setAdresse(mandat.getAdresse());
        dto.setComplement(mandat.getComplement());
        dto.setCodePostal(mandat.getCodePostal());
        dto.setVille(mandat.getVille());
        dto.setFichierRef(mandat.getFichierRef());
        return dto;
    }

    private void applyDtoToEntity(MandatDto dto, Mandat mandat) {
        mandat.setNumero(dto.getNumero());
        mandat.setDateDebut(dto.getDateDebut());
        mandat.setDateFin(dto.getDateFin());
        mandat.setDateSignature(dto.getDateSignature());
        mandat.setTypeMandat(dto.getTypeMandat());
        mandat.setAdministrateurProvisoire(dto.getAdministrateurProvisoire() != null ? dto.getAdministrateurProvisoire() : false);
        mandat.setAdresse(dto.getAdresse());
        mandat.setComplement(dto.getComplement());
        mandat.setCodePostal(dto.getCodePostal());
        mandat.setVille(dto.getVille());
        mandat.setFichierRef(dto.getFichierRef());
    }
}
