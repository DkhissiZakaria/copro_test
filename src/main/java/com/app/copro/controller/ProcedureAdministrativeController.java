package com.app.copro.controller;

import com.app.copro.dto.FileResponseDto;
import com.app.copro.dto.ProcedureAdministrativeDto;
import com.app.copro.service.ProcedureAdministrativeFileService;
import com.app.copro.service.ProcedureAdministrativeService;
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
public class ProcedureAdministrativeController {

    private final ProcedureAdministrativeService service;
    private final ProcedureAdministrativeFileService fileService;

    public ProcedureAdministrativeController(ProcedureAdministrativeService service,
                                             ProcedureAdministrativeFileService fileService) {
        this.service = service;
        this.fileService = fileService;
    }

    @PostMapping("/carnets/{carnetId}/procedures-administratives")
    public ResponseEntity<ProcedureAdministrativeDto> create(@PathVariable Long carnetId,
                                                             @Valid @RequestBody ProcedureAdministrativeDto dto) {
        ProcedureAdministrativeDto created = service.create(carnetId, dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping(value = "/carnets/{carnetId}/procedures-administratives/with-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProcedureAdministrativeDto> createWithFile(@PathVariable Long carnetId,
                                                                     @RequestPart("procedure") @Valid ProcedureAdministrativeDto dto,
                                                                     @RequestPart(value = "file", required = false) MultipartFile file) {
        ProcedureAdministrativeDto created = service.create(carnetId, dto);
        if (file != null && !file.isEmpty()) {
            String ref = fileService.uploadFile(created.getId(), file);
            created.setFichierRef(ref);
        }
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/carnets/{carnetId}/procedures-administratives")
    public ResponseEntity<List<ProcedureAdministrativeDto>> getByCarnet(@PathVariable Long carnetId) {
        List<ProcedureAdministrativeDto> list = service.getByCarnet(carnetId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/procedures-administratives/{id}")
    public ResponseEntity<ProcedureAdministrativeDto> getById(@PathVariable Long id) {
        ProcedureAdministrativeDto dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/procedures-administratives/{id}")
    public ResponseEntity<ProcedureAdministrativeDto> update(@PathVariable Long id,
                                                             @Valid @RequestBody ProcedureAdministrativeDto dto) {
        ProcedureAdministrativeDto updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PutMapping(value = "/procedures-administratives/{id}/with-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProcedureAdministrativeDto> updateWithFile(@PathVariable Long id,
                                                                     @RequestPart("procedure") @Valid ProcedureAdministrativeDto dto,
                                                                     @RequestPart(value = "file", required = false) MultipartFile file) {
        ProcedureAdministrativeDto updated = service.update(id, dto);
        if (file != null && !file.isEmpty()) {
            String ref = fileService.uploadFile(updated.getId(), file);
            updated.setFichierRef(ref);
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/procedures-administratives/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/procedures-administratives/{id}/file")
    public ResponseEntity<String> uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        String ref = fileService.uploadFile(id, file);
        return ref != null ? ResponseEntity.ok(ref) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/procedures-administratives/{id}/file")
    public ResponseEntity<FileResponseDto> getFile(@PathVariable Long id) {
        FileResponseDto file = fileService.getFile(id);
        return file != null ? ResponseEntity.ok(file) : ResponseEntity.notFound().build();
    }
}
