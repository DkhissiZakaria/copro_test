package com.app.copro.service;

import com.app.copro.dto.FileResponseDto;
import com.app.copro.exception.DiagnosticCollectifNotFoundException;
import com.app.copro.model.DiagnosticCollectif;
import com.app.copro.repository.DiagnosticCollectifRepository;
import com.app.copro.util.FileConstants;
import com.app.copro.util.FileManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class DiagnosticCollectifFileService {

    @Autowired
    private FileManagerHelper fileManagerHelper;

    @Autowired
    private DiagnosticCollectifRepository repository;

    public String uploadFile(Long diagnosticId, MultipartFile file) {
        DiagnosticCollectif diagnostic = repository.findById(diagnosticId)
                .orElseThrow(() -> new DiagnosticCollectifNotFoundException(diagnosticId));

        String fileRef = fileManagerHelper.uploadEntityDocument(
                file,
                FileConstants.EntityTypes.DIAGNOSTIC_COLLECTIF,
                diagnostic.getId(),
                FileConstants.CommonCategories.RAPPORT,
                "Rapport diagnostic collectif"
        );

        if (fileRef != null) {
            diagnostic.setFichierRef(fileRef);
            repository.save(diagnostic);
        }

        return fileRef;
    }

    public FileResponseDto getFile(Long diagnosticId) {
        Optional<DiagnosticCollectif> opt = repository.findById(diagnosticId);
        return opt.map(d -> fileManagerHelper.parseFileReference(d.getFichierRef())).orElse(null);
    }
}

