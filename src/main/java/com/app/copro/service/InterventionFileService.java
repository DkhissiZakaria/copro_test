package com.app.copro.service;

import com.app.copro.dto.FileResponseDto;
import com.app.copro.exception.InterventionNotFoundException;
import com.app.copro.model.Intervention;
import com.app.copro.repository.InterventionRepository;
import com.app.copro.util.FileConstants;
import com.app.copro.util.FileManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class InterventionFileService {

    @Autowired
    private FileManagerHelper fileManagerHelper;

    @Autowired
    private InterventionRepository repository;

    public String uploadFile(Long interventionId, MultipartFile file) {
        Intervention intervention = repository.findById(interventionId)
                .orElseThrow(() -> new InterventionNotFoundException(interventionId));

        String fileRef = fileManagerHelper.uploadEntityDocument(
                file,
                FileConstants.EntityTypes.INTERVENTION,
                intervention.getId(),
                FileConstants.CommonCategories.DOCUMENT,
                "Document intervention"
        );

        if (fileRef != null) {
            intervention.setFichierRef(fileRef);
            repository.save(intervention);
        }

        return fileRef;
    }

    public FileResponseDto getFile(Long interventionId) {
        Optional<Intervention> opt = repository.findById(interventionId);
        return opt.map(i -> fileManagerHelper.parseFileReference(i.getFichierRef())).orElse(null);
    }
}
