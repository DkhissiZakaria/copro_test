package com.app.copro.service;

import com.app.copro.dto.FileResponseDto;
import com.app.copro.exception.TravauxReparationNotFoundException;
import com.app.copro.model.TravauxReparation;
import com.app.copro.repository.TravauxReparationRepository;
import com.app.copro.util.FileConstants;
import com.app.copro.util.FileManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class TravauxReparationFileService {

    @Autowired
    private FileManagerHelper fileManagerHelper;

    @Autowired
    private TravauxReparationRepository repository;

    public String uploadFile(Long travauxId, MultipartFile file, String type) {
        TravauxReparation travaux = repository.findById(travauxId)
                .orElseThrow(() -> new TravauxReparationNotFoundException(travauxId));

        String category;
        String description;
        switch (type) {
            case "devis" -> {
                category = FileConstants.CommonCategories.DOCUMENT;
                description = "Devis travaux";
            }
            case "facture" -> {
                category = FileConstants.CommonCategories.FACTURE;
                description = "Facture travaux";
            }
            case "photoAvant" -> {
                category = FileConstants.CommonCategories.IMAGE;
                description = "Photo avant travaux";
            }
            case "photoApres" -> {
                category = FileConstants.CommonCategories.IMAGE;
                description = "Photo apres travaux";
            }
            default -> {
                return null;
            }
        }

        String ref = fileManagerHelper.uploadEntityDocument(
                file,
                FileConstants.EntityTypes.TRAVAUX_REPARATION,
                travaux.getId(),
                category,
                description
        );

        if (ref != null) {
            switch (type) {
                case "devis" -> travaux.setDevisRef(ref);
                case "facture" -> travaux.setFactureRef(ref);
                case "photoAvant" -> travaux.setPhotoAvantRef(ref);
                case "photoApres" -> travaux.setPhotoApresRef(ref);
            }
            repository.save(travaux);
        }

        return ref;
    }

    public FileResponseDto getFile(Long travauxId, String type) {
        Optional<TravauxReparation> opt = repository.findById(travauxId);
        if (opt.isEmpty()) {
            return null;
        }
        TravauxReparation travaux = opt.get();
        String ref;
        switch (type) {
            case "devis" -> ref = travaux.getDevisRef();
            case "facture" -> ref = travaux.getFactureRef();
            case "photoAvant" -> ref = travaux.getPhotoAvantRef();
            case "photoApres" -> ref = travaux.getPhotoApresRef();
            default -> {
                return null;
            }
        }
        return fileManagerHelper.parseFileReference(ref);
    }
}
