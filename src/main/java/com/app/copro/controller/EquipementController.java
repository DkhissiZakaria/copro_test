package com.app.copro.controller;

import com.app.copro.dto.EquipementDto;
import com.app.copro.service.EquipementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EquipementController {

    private final EquipementService service;

    public EquipementController(EquipementService service) {
        this.service = service;
    }

    @PostMapping("/chapitres/{chapitreId}/equipements")
    public ResponseEntity<EquipementDto> create(@PathVariable Long chapitreId,
                                                @Valid @RequestBody EquipementDto dto) {
        EquipementDto created = service.create(chapitreId, dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/chapitres/{chapitreId}/equipements")
    public ResponseEntity<List<EquipementDto>> getByChapitre(@PathVariable Long chapitreId) {
        List<EquipementDto> list = service.getByChapitre(chapitreId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/equipements/{id}")
    public ResponseEntity<EquipementDto> getById(@PathVariable Long id) {
        EquipementDto dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/equipements/{id}")
    public ResponseEntity<EquipementDto> update(@PathVariable Long id,
                                                @Valid @RequestBody EquipementDto dto) {
        EquipementDto updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }
}
