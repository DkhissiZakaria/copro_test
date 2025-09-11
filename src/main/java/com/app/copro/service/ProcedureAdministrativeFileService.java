package com.app.copro.service;

import com.app.copro.dto.FileResponseDto;
import com.app.copro.exception.ProcedureAdministrativeNotFoundException;
import com.app.copro.model.ProcedureAdministrative;
import com.app.copro.repository.ProcedureAdministrativeRepository;
import com.app.copro.util.FileConstants;
import com.app.copro.util.FileManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class ProcedureAdministrativeFileService {

    @Autowired
    private FileManagerHelper fileManagerHelper;

    @Autowired
    private ProcedureAdministrativeRepository repository;

    public String uploadFile(Long procedureId, MultipartFile file) {
        ProcedureAdministrative procedure = repository.findById(procedureId)
                .orElseThrow(() -> new ProcedureAdministrativeNotFoundException(procedureId));

        String fileRef = fileManagerHelper.uploadEntityDocument(
                file,
                FileConstants.EntityTypes.PROCEDURE_ADMINISTRATIVE,
                procedure.getId(),
                FileConstants.CommonCategories.DOCUMENT,
                "Document procédure administrative"
        );

        if (fileRef != null) {
            procedure.setFichierRef(fileRef);
            repository.save(procedure);
        }
        return fileRef;
    }

    public FileResponseDto getFile(Long procedureId) {
        Optional<ProcedureAdministrative> opt = repository.findById(procedureId);
        return opt.map(p -> fileManagerHelper.parseFileReference(p.getFichierRef())).orElse(null);
    }
}
