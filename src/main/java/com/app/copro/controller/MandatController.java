package com.app.copro.controller;

import com.app.copro.dto.FileResponseDto;
import com.app.copro.dto.MandatDto;
import com.app.copro.service.MandatFileService;
import com.app.copro.service.MandatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/syndics/{syndicId}/mandat")
@CrossOrigin(origins = "*")
public class MandatController {

    private final MandatService mandatService;

    @Autowired
    private MandatFileService mandatFileService;

    @Autowired
    public MandatController(MandatService mandatService) {
        this.mandatService = mandatService;
    }

    @PostMapping
    public ResponseEntity<MandatDto> createMandat(@PathVariable Long syndicId,
                                                  @Valid @RequestBody MandatDto mandatDto) {
        MandatDto created = mandatService.createMandat(syndicId, mandatDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping(value = "/with-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MandatDto> createMandatWithFile(@PathVariable Long syndicId,
                                                          @RequestPart("mandat") @Valid MandatDto mandatDto,
                                                          @RequestPart(value = "file", required = false) MultipartFile file) {
        MandatDto created = mandatService.createMandat(syndicId, mandatDto);
        if (file != null && !file.isEmpty()) {
            String ref = mandatFileService.uploadMandatFile(syndicId, file);
            created.setFichierRef(ref);
        }
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<MandatDto> getMandat(@PathVariable Long syndicId) {
        MandatDto dto = mandatService.getMandatBySyndic(syndicId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity<MandatDto> updateMandat(@PathVariable Long syndicId,
                                                  @Valid @RequestBody MandatDto mandatDto) {
        MandatDto updated = mandatService.updateMandat(syndicId, mandatDto);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/file")
    public ResponseEntity<String> uploadMandatFile(@PathVariable Long syndicId,
                                                   @RequestParam("file") MultipartFile file) {
        String ref = mandatFileService.uploadMandatFile(syndicId, file);
        return ref != null ? ResponseEntity.ok(ref) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/file")
    public ResponseEntity<FileResponseDto> getMandatFile(@PathVariable Long syndicId) {
        FileResponseDto file = mandatFileService.getMandatFile(syndicId);
        return file != null ? ResponseEntity.ok(file) : ResponseEntity.notFound().build();
    }
}
