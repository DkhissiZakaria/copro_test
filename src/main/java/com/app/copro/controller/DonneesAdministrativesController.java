package com.app.copro.controller;

import com.app.copro.dto.DonneesAdministrativesDto;
import com.app.copro.service.DonneesAdministrativesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carnets/{carnetId}/donnees-administratives")
@CrossOrigin(origins = "*")
public class DonneesAdministrativesController {

    private final DonneesAdministrativesService service;

    public DonneesAdministrativesController(DonneesAdministrativesService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DonneesAdministrativesDto> create(@PathVariable Long carnetId,
                                                            @RequestBody DonneesAdministrativesDto dto) {
        DonneesAdministrativesDto created = service.create(carnetId, dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<DonneesAdministrativesDto> get(@PathVariable Long carnetId) {
        DonneesAdministrativesDto dto = service.getByCarnet(carnetId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity<DonneesAdministrativesDto> update(@PathVariable Long carnetId,
                                                            @RequestBody DonneesAdministrativesDto dto) {
        DonneesAdministrativesDto updated = service.update(carnetId, dto);
        return ResponseEntity.ok(updated);
    }
}
