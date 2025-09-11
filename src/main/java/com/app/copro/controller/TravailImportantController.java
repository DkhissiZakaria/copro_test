package com.app.copro.controller;

import com.app.copro.dto.FileResponseDto;
import com.app.copro.dto.TravailImportantDto;
import com.app.copro.service.TravailImportantFileService;
import com.app.copro.service.TravailImportantService;
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
public class TravailImportantController {

    private final TravailImportantService service;
    private final TravailImportantFileService fileService;

    public TravailImportantController(TravailImportantService service, TravailImportantFileService fileService) {
        this.service = service;
        this.fileService = fileService;
    }

    @PostMapping("/carnets/{carnetId}/travaux-importants")
    public ResponseEntity<TravailImportantDto> create(@PathVariable Long carnetId,
                                                       @Valid @RequestBody TravailImportantDto dto) {
        TravailImportantDto created = service.create(carnetId, dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping(value = "/carnets/{carnetId}/travaux-importants/with-files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TravailImportantDto> createWithFiles(@PathVariable Long carnetId,
                                                               @RequestPart("travail") @Valid TravailImportantDto dto,
                                                               @RequestPart(value = "image1", required = false) MultipartFile image1,
                                                               @RequestPart(value = "image2", required = false) MultipartFile image2,
                                                               @RequestPart(value = "image3", required = false) MultipartFile image3) {
        TravailImportantDto created = service.create(carnetId, dto);
        if (image1 != null && !image1.isEmpty()) {
            String ref = fileService.uploadImage(created.getId(), image1, 1);
            created.setImage1Ref(ref);
        }
        if (image2 != null && !image2.isEmpty()) {
            String ref = fileService.uploadImage(created.getId(), image2, 2);
            created.setImage2Ref(ref);
        }
        if (image3 != null && !image3.isEmpty()) {
            String ref = fileService.uploadImage(created.getId(), image3, 3);
            created.setImage3Ref(ref);
        }
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/carnets/{carnetId}/travaux-importants")
    public ResponseEntity<List<TravailImportantDto>> getByCarnet(@PathVariable Long carnetId) {
        List<TravailImportantDto> list = service.getByCarnet(carnetId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/travaux-importants/{id}")
    public ResponseEntity<TravailImportantDto> getById(@PathVariable Long id) {
        TravailImportantDto dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/travaux-importants/{id}")
    public ResponseEntity<TravailImportantDto> update(@PathVariable Long id,
                                                      @Valid @RequestBody TravailImportantDto dto) {
        TravailImportantDto updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PutMapping(value = "/travaux-importants/{id}/with-files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TravailImportantDto> updateWithFiles(@PathVariable Long id,
                                                               @RequestPart("travail") @Valid TravailImportantDto dto,
                                                               @RequestPart(value = "image1", required = false) MultipartFile image1,
                                                               @RequestPart(value = "image2", required = false) MultipartFile image2,
                                                               @RequestPart(value = "image3", required = false) MultipartFile image3) {
        TravailImportantDto updated = service.update(id, dto);
        if (image1 != null && !image1.isEmpty()) {
            String ref = fileService.uploadImage(updated.getId(), image1, 1);
            updated.setImage1Ref(ref);
        }
        if (image2 != null && !image2.isEmpty()) {
            String ref = fileService.uploadImage(updated.getId(), image2, 2);
            updated.setImage2Ref(ref);
        }
        if (image3 != null && !image3.isEmpty()) {
            String ref = fileService.uploadImage(updated.getId(), image3, 3);
            updated.setImage3Ref(ref);
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/travaux-importants/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/travaux-importants/{id}/images/{index}")
    public ResponseEntity<String> uploadImage(@PathVariable Long id,
                                              @PathVariable int index,
                                              @RequestParam("file") MultipartFile file) {
        String ref = fileService.uploadImage(id, file, index);
        return ref != null ? ResponseEntity.ok(ref) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/travaux-importants/{id}/images/{index}")
    public ResponseEntity<FileResponseDto> getImage(@PathVariable Long id,
                                                    @PathVariable int index) {
        FileResponseDto file = fileService.getImage(id, index);
        return file != null ? ResponseEntity.ok(file) : ResponseEntity.notFound().build();
    }
}

