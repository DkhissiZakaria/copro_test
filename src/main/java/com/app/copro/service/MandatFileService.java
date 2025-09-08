package com.app.copro.service;

import com.app.copro.dto.FileResponseDto;
import com.app.copro.exception.MandatNotFoundException;
import com.app.copro.model.Mandat;
import com.app.copro.repository.MandatRepository;
import com.app.copro.util.FileConstants;
import com.app.copro.util.FileManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class MandatFileService {

    @Autowired
    private FileManagerHelper fileManagerHelper;

    @Autowired
    private MandatRepository mandatRepository;

    public String uploadMandatFile(Long syndicId, MultipartFile file) {
        Mandat mandat = mandatRepository.findBySyndicId(syndicId)
                .orElseThrow(() -> new MandatNotFoundException(syndicId));

        String fileRef = fileManagerHelper.uploadEntityDocument(
                file,
                FileConstants.EntityTypes.MANDAT,
                mandat.getId(),
                FileConstants.CommonCategories.CONTRAT,
                "Document de mandat"
        );

        if (fileRef != null) {
            mandat.setFichierRef(fileRef);
            mandatRepository.save(mandat);
        }

        return fileRef;
    }

    public FileResponseDto getMandatFile(Long syndicId) {
        Optional<Mandat> mandatOpt = mandatRepository.findBySyndicId(syndicId);
        return mandatOpt.map(m -> fileManagerHelper.parseFileReference(m.getFichierRef())).orElse(null);
    }
}

