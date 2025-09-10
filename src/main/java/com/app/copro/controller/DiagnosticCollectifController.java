package com.app.copro.controller;

import com.app.copro.dto.DiagnosticCollectifDto;
import com.app.copro.dto.FileResponseDto;
import com.app.copro.service.DiagnosticCollectifFileService;
import com.app.copro.service.DiagnosticCollectifService;
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
public class DiagnosticCollectifController {

    private final DiagnosticCollectifService service;
    private final DiagnosticCollectifFileService fileService;

    public DiagnosticCollectifController(DiagnosticCollectifService service,
                                         DiagnosticCollectifFileService fileService) {
        this.service = service;
        this.fileService = fileService;
    }

    @PostMapping("/carnets/{carnetId}/diagnostics-collectifs")
    public ResponseEntity<DiagnosticCollectifDto> create(@PathVariable Long carnetId,
                                                          @Valid @RequestBody DiagnosticCollectifDto dto) {
        DiagnosticCollectifDto created = service.create(carnetId, dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping(value = "/carnets/{carnetId}/diagnostics-collectifs/with-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DiagnosticCollectifDto> createWithFile(@PathVariable Long carnetId,
                                                                 @RequestPart("diagnostic") @Valid DiagnosticCollectifDto dto,
                                                                 @RequestPart(value = "file", required = false) MultipartFile file) {
        DiagnosticCollectifDto created = service.create(carnetId, dto);
        if (file != null && !file.isEmpty()) {
            String ref = fileService.uploadFile(created.getId(), file);
            created.setFichierRef(ref);
        }
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/carnets/{carnetId}/diagnostics-collectifs")
    public ResponseEntity<List<DiagnosticCollectifDto>> getByCarnet(@PathVariable Long carnetId) {
        List<DiagnosticCollectifDto> list = service.getByCarnet(carnetId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/diagnostics-collectifs/{id}")
    public ResponseEntity<DiagnosticCollectifDto> getById(@PathVariable Long id) {
        DiagnosticCollectifDto dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/diagnostics-collectifs/{id}")
    public ResponseEntity<DiagnosticCollectifDto> update(@PathVariable Long id,
                                                          @Valid @RequestBody DiagnosticCollectifDto dto) {
        DiagnosticCollectifDto updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PutMapping(value = "/diagnostics-collectifs/{id}/with-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DiagnosticCollectifDto> updateWithFile(@PathVariable Long id,
                                                                 @RequestPart("diagnostic") @Valid DiagnosticCollectifDto dto,
                                                                 @RequestPart(value = "file", required = false) MultipartFile file) {
        DiagnosticCollectifDto updated = service.update(id, dto);
        if (file != null && !file.isEmpty()) {
            String ref = fileService.uploadFile(updated.getId(), file);
            updated.setFichierRef(ref);
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/diagnostics-collectifs/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/diagnostics-collectifs/{id}/file")
    public ResponseEntity<String> uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        String ref = fileService.uploadFile(id, file);
        return ref != null ? ResponseEntity.ok(ref) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/diagnostics-collectifs/{id}/file")
    public ResponseEntity<FileResponseDto> getFile(@PathVariable Long id) {
        FileResponseDto file = fileService.getFile(id);
        return file != null ? ResponseEntity.ok(file) : ResponseEntity.notFound().build();
    }
}

