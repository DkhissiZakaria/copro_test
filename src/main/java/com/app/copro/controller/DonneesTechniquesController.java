package com.app.copro.controller;

import com.app.copro.dto.DonneesTechniquesDto;
import com.app.copro.service.DonneesTechniquesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carnets/{carnetId}/donnees-techniques")
@CrossOrigin(origins = "*")
public class DonneesTechniquesController {

    private final DonneesTechniquesService service;

    public DonneesTechniquesController(DonneesTechniquesService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DonneesTechniquesDto> create(@PathVariable Long carnetId,
                                                       @RequestBody DonneesTechniquesDto dto) {
        DonneesTechniquesDto created = service.create(carnetId, dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<DonneesTechniquesDto> get(@PathVariable Long carnetId) {
        DonneesTechniquesDto dto = service.getByCarnet(carnetId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity<DonneesTechniquesDto> update(@PathVariable Long carnetId,
                                                       @RequestBody DonneesTechniquesDto dto) {
        DonneesTechniquesDto updated = service.update(carnetId, dto);
        return ResponseEntity.ok(updated);
    }
}

