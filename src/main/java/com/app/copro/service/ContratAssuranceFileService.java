package com.app.copro.service;

import com.app.copro.dto.FileResponseDto;
import com.app.copro.exception.ContratAssuranceNotFoundException;
import com.app.copro.model.ContratAssurance;
import com.app.copro.repository.ContratAssuranceRepository;
import com.app.copro.util.FileConstants;
import com.app.copro.util.FileManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class ContratAssuranceFileService {

    @Autowired
    private FileManagerHelper fileManagerHelper;

    @Autowired
    private ContratAssuranceRepository repository;

    public String uploadFile(Long contratId, MultipartFile file) {
        ContratAssurance contrat = repository.findById(contratId)
                .orElseThrow(() -> new ContratAssuranceNotFoundException(contratId));

        String fileRef = fileManagerHelper.uploadEntityDocument(
                file,
                FileConstants.EntityTypes.CONTRAT_ASSURANCE,
                contrat.getId(),
                FileConstants.CommonCategories.CONTRAT,
                "Document contrat assurance"
        );

        if (fileRef != null) {
            contrat.setFichierRef(fileRef);
            repository.save(contrat);
        }

        return fileRef;
    }

    public FileResponseDto getFile(Long contratId) {
        Optional<ContratAssurance> contratOpt = repository.findById(contratId);
        return contratOpt.map(c -> fileManagerHelper.parseFileReference(c.getFichierRef())).orElse(null);
    }
}
