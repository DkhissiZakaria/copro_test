package com.app.copro.controller;

import com.app.copro.dto.PptDto;
import com.app.copro.service.PptFileService;
import com.app.copro.service.PptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PptController {

    private final PptService service;
    private final PptFileService fileService;

    public PptController(PptService service, PptFileService fileService) {
        this.service = service;
        this.fileService = fileService;
    }

    @PostMapping(value = "/carnets/{carnetId}/ppt", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PptDto> create(@PathVariable Long carnetId,
                                         @RequestPart("ppt") PptDto dto,
                                         @RequestPart(value = "file", required = false) MultipartFile file) {
        PptDto created = service.create(carnetId, dto);
        if (file != null && !file.isEmpty()) {
            String ref = fileService.uploadFile(created.getId(), file);
            created.setFichierRef(ref);
        }
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/carnets/{carnetId}/ppt")
    public ResponseEntity<PptDto> get(@PathVariable Long carnetId) {
        PptDto dto = service.getByCarnet(carnetId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/carnets/{carnetId}/ppt", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PptDto> update(@PathVariable Long carnetId,
                                         @RequestPart("ppt") PptDto dto,
                                         @RequestPart(value = "file", required = false) MultipartFile file) {
        PptDto updated = service.update(carnetId, dto);
        if (file != null && !file.isEmpty()) {
            String ref = fileService.uploadFile(updated.getId(), file);
            updated.setFichierRef(ref);
        }
        return ResponseEntity.ok(updated);
    }
}
