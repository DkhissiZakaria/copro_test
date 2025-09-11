package com.app.copro.controller;

import com.app.copro.dto.ChapitreDto;
import com.app.copro.service.ChapitreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ChapitreController {

    private final ChapitreService service;

    public ChapitreController(ChapitreService service) {
        this.service = service;
    }

    @PostMapping("/carnets/{carnetId}/chapitres")
    public ResponseEntity<ChapitreDto> create(@PathVariable Long carnetId,
                                              @Valid @RequestBody ChapitreDto dto) {
        ChapitreDto created = service.create(carnetId, dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/chapitres/{id}")
    public ResponseEntity<ChapitreDto> update(@PathVariable Long id,
                                              @Valid @RequestBody ChapitreDto dto) {
        ChapitreDto updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/carnets/{carnetId}/chapitres")
    public ResponseEntity<List<ChapitreDto>> getByCarnet(@PathVariable Long carnetId) {
        List<ChapitreDto> list = service.getByCarnet(carnetId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/chapitres/{id}")
    public ResponseEntity<ChapitreDto> getById(@PathVariable Long id) {
        ChapitreDto dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }
}

