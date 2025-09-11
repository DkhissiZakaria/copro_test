package com.app.copro.controller;

import com.app.copro.dto.FileResponseDto;
import com.app.copro.dto.InterventionDto;
import com.app.copro.service.InterventionFileService;
import com.app.copro.service.InterventionService;
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
public class InterventionController {

    private final InterventionService service;
    private final InterventionFileService fileService;

    public InterventionController(InterventionService service, InterventionFileService fileService) {
        this.service = service;
        this.fileService = fileService;
    }

    @PostMapping("/equipements/{equipementId}/interventions")
    public ResponseEntity<InterventionDto> create(@PathVariable Long equipementId,
                                                  @Valid @RequestBody InterventionDto dto) {
        InterventionDto created = service.create(equipementId, dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping(value = "/equipements/{equipementId}/interventions/with-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<InterventionDto> createWithFile(@PathVariable Long equipementId,
                                                          @RequestPart("intervention") @Valid InterventionDto dto,
                                                          @RequestPart(value = "file", required = false) MultipartFile file) {
        InterventionDto created = service.create(equipementId, dto);
        if (file != null && !file.isEmpty()) {
            String ref = fileService.uploadFile(created.getId(), file);
            created.setFichierRef(ref);
        }
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/equipements/{equipementId}/interventions")
    public ResponseEntity<List<InterventionDto>> getByEquipement(@PathVariable Long equipementId) {
        List<InterventionDto> list = service.getByEquipement(equipementId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/interventions/{id}")
    public ResponseEntity<InterventionDto> getById(@PathVariable Long id) {
        InterventionDto dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/interventions/{id}")
    public ResponseEntity<InterventionDto> update(@PathVariable Long id,
                                                  @Valid @RequestBody InterventionDto dto) {
        InterventionDto updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PutMapping(value = "/interventions/{id}/with-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<InterventionDto> updateWithFile(@PathVariable Long id,
                                                          @RequestPart("intervention") @Valid InterventionDto dto,
                                                          @RequestPart(value = "file", required = false) MultipartFile file) {
        InterventionDto updated = service.update(id, dto);
        if (file != null && !file.isEmpty()) {
            String ref = fileService.uploadFile(updated.getId(), file);
            updated.setFichierRef(ref);
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/interventions/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/interventions/{id}/file")
    public ResponseEntity<String> uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        String ref = fileService.uploadFile(id, file);
        return ref != null ? ResponseEntity.ok(ref) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/interventions/{id}/file")
    public ResponseEntity<FileResponseDto> getFile(@PathVariable Long id) {
        FileResponseDto file = fileService.getFile(id);
        return file != null ? ResponseEntity.ok(file) : ResponseEntity.notFound().build();
    }
}
