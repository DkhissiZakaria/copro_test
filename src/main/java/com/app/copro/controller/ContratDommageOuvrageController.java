package com.app.copro.controller;

import com.app.copro.dto.ContratDommageOuvrageDto;
import com.app.copro.service.ContratDommageOuvrageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ContratDommageOuvrageController {

    private final ContratDommageOuvrageService service;

    public ContratDommageOuvrageController(ContratDommageOuvrageService service) {
        this.service = service;
    }

    @PostMapping("/carnets/{carnetId}/contrats-dommage-ouvrage")
    public ResponseEntity<ContratDommageOuvrageDto> create(@PathVariable Long carnetId,
                                                            @Valid @RequestBody ContratDommageOuvrageDto dto) {
        ContratDommageOuvrageDto created = service.create(carnetId, dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/carnets/{carnetId}/contrats-dommage-ouvrage")
    public ResponseEntity<List<ContratDommageOuvrageDto>> getByCarnet(@PathVariable Long carnetId) {
        List<ContratDommageOuvrageDto> list = service.getByCarnet(carnetId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/contrats-dommage-ouvrage/{id}")
    public ResponseEntity<ContratDommageOuvrageDto> getById(@PathVariable Long id) {
        ContratDommageOuvrageDto dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/contrats-dommage-ouvrage/{id}")
    public ResponseEntity<ContratDommageOuvrageDto> update(@PathVariable Long id,
                                                            @Valid @RequestBody ContratDommageOuvrageDto dto) {
        ContratDommageOuvrageDto updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/contrats-dommage-ouvrage/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
