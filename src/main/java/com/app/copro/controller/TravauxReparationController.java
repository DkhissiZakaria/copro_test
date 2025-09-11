package com.app.copro.controller;

import com.app.copro.dto.FileResponseDto;
import com.app.copro.dto.TravauxReparationDto;
import com.app.copro.service.TravauxReparationFileService;
import com.app.copro.service.TravauxReparationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TravauxReparationController {

    private final TravauxReparationService service;
    private final TravauxReparationFileService fileService;

    public TravauxReparationController(TravauxReparationService service, TravauxReparationFileService fileService) {
        this.service = service;
        this.fileService = fileService;
    }

    @PostMapping("/equipements/{equipementId}/travaux-reparations")
    public ResponseEntity<TravauxReparationDto> create(@PathVariable Long equipementId,
                                                       @Valid @RequestBody TravauxReparationDto dto) {
        TravauxReparationDto created = service.create(equipementId, dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping(value = "/equipements/{equipementId}/travaux-reparations/with-files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TravauxReparationDto> createWithFiles(@PathVariable Long equipementId,
                                                                @RequestPart("travaux") @Valid TravauxReparationDto dto,
                                                                @RequestPart(value = "devis", required = false) MultipartFile devis,
                                                                @RequestPart(value = "facture", required = false) MultipartFile facture,
                                                                @RequestPart(value = "photoAvant", required = false) MultipartFile photoAvant,
                                                                @RequestPart(value = "photoApres", required = false) MultipartFile photoApres) {
        TravauxReparationDto created = service.create(equipementId, dto);
        if (devis != null && !devis.isEmpty()) {
            String ref = fileService.uploadFile(created.getId(), devis, "devis");
            created.setDevisRef(ref);
        }
        if (facture != null && !facture.isEmpty()) {
            String ref = fileService.uploadFile(created.getId(), facture, "facture");
            created.setFactureRef(ref);
        }
        if (photoAvant != null && !photoAvant.isEmpty()) {
            String ref = fileService.uploadFile(created.getId(), photoAvant, "photoAvant");
            created.setPhotoAvantRef(ref);
        }
        if (photoApres != null && !photoApres.isEmpty()) {
            String ref = fileService.uploadFile(created.getId(), photoApres, "photoApres");
            created.setPhotoApresRef(ref);
        }
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/equipements/{equipementId}/travaux-reparations")
    public ResponseEntity<List<TravauxReparationDto>> getByEquipement(@PathVariable Long equipementId) {
        List<TravauxReparationDto> list = service.getByEquipement(equipementId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/travaux-reparations/{id}")
    public ResponseEntity<TravauxReparationDto> getById(@PathVariable Long id) {
        TravauxReparationDto dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/travaux-reparations/{id}")
    public ResponseEntity<TravauxReparationDto> update(@PathVariable Long id,
                                                       @Valid @RequestBody TravauxReparationDto dto) {
        TravauxReparationDto updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PutMapping(value = "/travaux-reparations/{id}/with-files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TravauxReparationDto> updateWithFiles(@PathVariable Long id,
                                                                @RequestPart("travaux") @Valid TravauxReparationDto dto,
                                                                @RequestPart(value = "devis", required = false) MultipartFile devis,
                                                                @RequestPart(value = "facture", required = false) MultipartFile facture,
                                                                @RequestPart(value = "photoAvant", required = false) MultipartFile photoAvant,
                                                                @RequestPart(value = "photoApres", required = false) MultipartFile photoApres) {
        TravauxReparationDto updated = service.update(id, dto);
        if (devis != null && !devis.isEmpty()) {
            String ref = fileService.uploadFile(updated.getId(), devis, "devis");
            updated.setDevisRef(ref);
        }
        if (facture != null && !facture.isEmpty()) {
            String ref = fileService.uploadFile(updated.getId(), facture, "facture");
            updated.setFactureRef(ref);
        }
        if (photoAvant != null && !photoAvant.isEmpty()) {
            String ref = fileService.uploadFile(updated.getId(), photoAvant, "photoAvant");
            updated.setPhotoAvantRef(ref);
        }
        if (photoApres != null && !photoApres.isEmpty()) {
            String ref = fileService.uploadFile(updated.getId(), photoApres, "photoApres");
            updated.setPhotoApresRef(ref);
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/travaux-reparations/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/travaux-reparations/{id}/file")
    public ResponseEntity<String> uploadFile(@PathVariable Long id,
                                             @RequestParam("type") String type,
                                             @RequestParam("file") MultipartFile file) {
        String ref = fileService.uploadFile(id, file, type);
        return ref != null ? ResponseEntity.ok(ref) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/travaux-reparations/{id}/file")
    public ResponseEntity<FileResponseDto> getFile(@PathVariable Long id,
                                                   @RequestParam("type") String type) {
        FileResponseDto file = fileService.getFile(id, type);
        return file != null ? ResponseEntity.ok(file) : ResponseEntity.notFound().build();
    }
}
