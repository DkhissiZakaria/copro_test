package com.app.copro.service;

import com.app.copro.dto.FileResponseDto;
import com.app.copro.exception.TravailImportantNotFoundException;
import com.app.copro.model.TravailImportant;
import com.app.copro.repository.TravailImportantRepository;
import com.app.copro.util.FileConstants;
import com.app.copro.util.FileManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class TravailImportantFileService {

    @Autowired
    private FileManagerHelper fileManagerHelper;

    @Autowired
    private TravailImportantRepository repository;

    public String uploadImage(Long travailId, MultipartFile file, int index) {
        TravailImportant travail = repository.findById(travailId)
                .orElseThrow(() -> new TravailImportantNotFoundException(travailId));

        String fileRef = fileManagerHelper.uploadEntityDocument(
                file,
                FileConstants.EntityTypes.TRAVAIL_IMPORTANT,
                travail.getId(),
                FileConstants.CommonCategories.IMAGE,
                "Image " + index + " travail important"
        );

        if (fileRef != null) {
            switch (index) {
                case 1 -> travail.setImage1Ref(fileRef);
                case 2 -> travail.setImage2Ref(fileRef);
                case 3 -> travail.setImage3Ref(fileRef);
            }
            repository.save(travail);
        }

        return fileRef;
    }

    public FileResponseDto getImage(Long travailId, int index) {
        Optional<TravailImportant> opt = repository.findById(travailId);
        if (opt.isEmpty()) {
            return null;
        }
        TravailImportant t = opt.get();
        String ref = switch (index) {
            case 1 -> t.getImage1Ref();
            case 2 -> t.getImage2Ref();
            case 3 -> t.getImage3Ref();
            default -> null;
        };
        return fileManagerHelper.parseFileReference(ref);
    }
}

