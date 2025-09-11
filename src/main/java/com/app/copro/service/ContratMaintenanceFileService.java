package com.app.copro.service;

import com.app.copro.dto.FileResponseDto;
import com.app.copro.exception.ContratMaintenanceNotFoundException;
import com.app.copro.model.ContratMaintenance;
import com.app.copro.repository.ContratMaintenanceRepository;
import com.app.copro.util.FileConstants;
import com.app.copro.util.FileManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class ContratMaintenanceFileService {

    @Autowired
    private FileManagerHelper fileManagerHelper;

    @Autowired
    private ContratMaintenanceRepository repository;

    public String uploadFile(Long contratId, MultipartFile file) {
        ContratMaintenance contrat = repository.findById(contratId)
                .orElseThrow(() -> new ContratMaintenanceNotFoundException(contratId));

        String fileRef = fileManagerHelper.uploadEntityDocument(
                file,
                FileConstants.EntityTypes.CONTRAT_MAINTENANCE,
                contrat.getId(),
                FileConstants.CommonCategories.CONTRAT,
                "Document contrat maintenance"
        );

        if (fileRef != null) {
            contrat.setFichierRef(fileRef);
            repository.save(contrat);
        }

        return fileRef;
    }

    public FileResponseDto getFile(Long contratId) {
        Optional<ContratMaintenance> contratOpt = repository.findById(contratId);
        return contratOpt.map(c -> fileManagerHelper.parseFileReference(c.getFichierRef())).orElse(null);
    }
}
