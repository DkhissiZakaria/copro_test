package com.app.copro.service;

import com.app.copro.dto.FileResponseDto;
import com.app.copro.model.Ppt;
import com.app.copro.repository.PptRepository;
import com.app.copro.util.FileConstants;
import com.app.copro.util.FileManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class PptFileService {

    @Autowired
    private FileManagerHelper fileManagerHelper;

    @Autowired
    private PptRepository repository;

    public String uploadFile(Long pptId, MultipartFile file) {
        Ppt ppt = repository.findById(pptId)
                .orElseThrow(() -> new RuntimeException("PPT not found: " + pptId));

        String fileRef = fileManagerHelper.uploadEntityDocument(
                file,
                FileConstants.EntityTypes.PPT,
                ppt.getId(),
                FileConstants.CommonCategories.DOCUMENT,
                "Document PPT"
        );

        if (fileRef != null) {
            ppt.setFichierRef(fileRef);
            repository.save(ppt);
        }
        return fileRef;
    }

    public FileResponseDto getFile(Long pptId) {
        Optional<Ppt> opt = repository.findById(pptId);
        return opt.map(p -> fileManagerHelper.parseFileReference(p.getFichierRef())).orElse(null);
    }
}
