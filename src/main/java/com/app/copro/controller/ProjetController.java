package com.app.copro.controller;

import com.app.copro.dto.CreateProjetDto;
import com.app.copro.dto.ProjetResponseDto;
import com.app.copro.service.ProjetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projets")
@CrossOrigin(origins = "*")
public class ProjetController {

    private final ProjetService projetService;

    @Autowired
    public ProjetController(ProjetService projetService) {
        this.projetService = projetService;
    }

    @PostMapping
    public ResponseEntity<ProjetResponseDto> createProjet(@Valid @RequestBody CreateProjetDto createProjetDto) {
        ProjetResponseDto responseDto = projetService.createProjet(createProjetDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProjetResponseDto>> getAllProjets() {
        List<ProjetResponseDto> projets = projetService.getAllProjets();
        return ResponseEntity.ok(projets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetResponseDto> getProjetById(@PathVariable Long id) {
        ProjetResponseDto projet = projetService.getProjetById(id);
        return ResponseEntity.ok(projet);
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<ProjetResponseDto> getProjetByNom(@PathVariable String nom) {
        ProjetResponseDto projet = projetService.getProjetByNom(nom);
        return ResponseEntity.ok(projet);
    }

    @GetMapping("/makeplan/{idMakePlan}")
    public ResponseEntity<ProjetResponseDto> getProjetByIdMakePlan(@PathVariable Long idMakePlan) {
        ProjetResponseDto projet = projetService.getProjetByIdMakePlan(idMakePlan);
        return ResponseEntity.ok(projet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjet(@PathVariable Long id) {
        projetService.deleteProjet(id);
        return ResponseEntity.noContent().build();
    }
}