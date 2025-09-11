package com.app.copro.controller;

import com.app.copro.dto.ContratMaintenanceDto;
import com.app.copro.dto.FileResponseDto;
import com.app.copro.service.ContratMaintenanceFileService;
import com.app.copro.service.ContratMaintenanceService;
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
public class ContratMaintenanceController {

    private final ContratMaintenanceService service;
    private final ContratMaintenanceFileService fileService;

    public ContratMaintenanceController(ContratMaintenanceService service, ContratMaintenanceFileService fileService) {
        this.service = service;
        this.fileService = fileService;
    }

    @PostMapping("/equipements/{equipementId}/contrats-maintenance")
    public ResponseEntity<ContratMaintenanceDto> create(@PathVariable Long equipementId,
                                                         @Valid @RequestBody ContratMaintenanceDto dto) {
        ContratMaintenanceDto created = service.create(equipementId, dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping(value = "/equipements/{equipementId}/contrats-maintenance/with-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ContratMaintenanceDto> createWithFile(@PathVariable Long equipementId,
                                                                 @RequestPart("contrat") @Valid ContratMaintenanceDto dto,
                                                                 @RequestPart(value = "file", required = false) MultipartFile file) {
        ContratMaintenanceDto created = service.create(equipementId, dto);
        if (file != null && !file.isEmpty()) {
            String ref = fileService.uploadFile(created.getId(), file);
            created.setFichierRef(ref);
        }
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/equipements/{equipementId}/contrats-maintenance")
    public ResponseEntity<List<ContratMaintenanceDto>> getByEquipement(@PathVariable Long equipementId) {
        List<ContratMaintenanceDto> list = service.getByEquipement(equipementId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/contrats-maintenance/{id}")
    public ResponseEntity<ContratMaintenanceDto> getById(@PathVariable Long id) {
        ContratMaintenanceDto dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/contrats-maintenance/{id}")
    public ResponseEntity<ContratMaintenanceDto> update(@PathVariable Long id,
                                                         @Valid @RequestBody ContratMaintenanceDto dto) {
        ContratMaintenanceDto updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PutMapping(value = "/contrats-maintenance/{id}/with-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ContratMaintenanceDto> updateWithFile(@PathVariable Long id,
                                                                 @RequestPart("contrat") @Valid ContratMaintenanceDto dto,
                                                                 @RequestPart(value = "file", required = false) MultipartFile file) {
        ContratMaintenanceDto updated = service.update(id, dto);
        if (file != null && !file.isEmpty()) {
            String ref = fileService.uploadFile(updated.getId(), file);
            updated.setFichierRef(ref);
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/contrats-maintenance/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/contrats-maintenance/{id}/file")
    public ResponseEntity<String> uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        String ref = fileService.uploadFile(id, file);
        return ref != null ? ResponseEntity.ok(ref) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/contrats-maintenance/{id}/file")
    public ResponseEntity<FileResponseDto> getFile(@PathVariable Long id) {
        FileResponseDto file = fileService.getFile(id);
        return file != null ? ResponseEntity.ok(file) : ResponseEntity.notFound().build();
    }
}
