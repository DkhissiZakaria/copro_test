package com.app.copro.controller;

import com.app.copro.dto.ContratAssuranceDto;
import com.app.copro.dto.FileResponseDto;
import com.app.copro.service.ContratAssuranceFileService;
import com.app.copro.service.ContratAssuranceService;
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
public class ContratAssuranceController {

    private final ContratAssuranceService service;
    private final ContratAssuranceFileService fileService;

    public ContratAssuranceController(ContratAssuranceService service, ContratAssuranceFileService fileService) {
        this.service = service;
        this.fileService = fileService;
    }

    @PostMapping("/carnets/{carnetId}/contrats-assurance")
    public ResponseEntity<ContratAssuranceDto> create(@PathVariable Long carnetId,
                                                       @Valid @RequestBody ContratAssuranceDto dto) {
        ContratAssuranceDto created = service.create(carnetId, dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping(value = "/carnets/{carnetId}/contrats-assurance/with-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ContratAssuranceDto> createWithFile(@PathVariable Long carnetId,
                                                              @RequestPart("contrat") @Valid ContratAssuranceDto dto,
                                                              @RequestPart(value = "file", required = false) MultipartFile file) {
        ContratAssuranceDto created = service.create(carnetId, dto);
        if (file != null && !file.isEmpty()) {
            String ref = fileService.uploadFile(created.getId(), file);
            created.setFichierRef(ref);
        }
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/carnets/{carnetId}/contrats-assurance")
    public ResponseEntity<List<ContratAssuranceDto>> getByCarnet(@PathVariable Long carnetId) {
        List<ContratAssuranceDto> list = service.getByCarnet(carnetId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/contrats-assurance/{id}")
    public ResponseEntity<ContratAssuranceDto> getById(@PathVariable Long id) {
        ContratAssuranceDto dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/contrats-assurance/{id}")
    public ResponseEntity<ContratAssuranceDto> update(@PathVariable Long id,
                                                      @Valid @RequestBody ContratAssuranceDto dto) {
        ContratAssuranceDto updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PutMapping(value = "/contrats-assurance/{id}/with-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ContratAssuranceDto> updateWithFile(@PathVariable Long id,
                                                              @RequestPart("contrat") @Valid ContratAssuranceDto dto,
                                                              @RequestPart(value = "file", required = false) MultipartFile file) {
        ContratAssuranceDto updated = service.update(id, dto);
        if (file != null && !file.isEmpty()) {
            String ref = fileService.uploadFile(updated.getId(), file);
            updated.setFichierRef(ref);
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/contrats-assurance/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/contrats-assurance/{id}/file")
    public ResponseEntity<String> uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        String ref = fileService.uploadFile(id, file);
        return ref != null ? ResponseEntity.ok(ref) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/contrats-assurance/{id}/file")
    public ResponseEntity<FileResponseDto> getFile(@PathVariable Long id) {
        FileResponseDto file = fileService.getFile(id);
        return file != null ? ResponseEntity.ok(file) : ResponseEntity.notFound().build();
    }
}
