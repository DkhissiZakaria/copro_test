package com.app.copro.controller;

import com.app.copro.dto.CreateSyndicDto;
import com.app.copro.dto.FileResponseDto;
import com.app.copro.dto.SyndicResponseDto;
import com.app.copro.dto.UpdateSyndicDto;
import com.app.copro.service.SyndicService;
import com.app.copro.service.SyndicFileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/syndics")
@CrossOrigin(origins = "*")
public class SyndicController {

    private final SyndicService syndicService;
    
    @Autowired
    private SyndicFileService syndicFileService;

    @Autowired
    public SyndicController(SyndicService syndicService) {
        this.syndicService = syndicService;
    }

    @PostMapping
    public ResponseEntity<SyndicResponseDto> createSyndic(@Valid @RequestBody CreateSyndicDto createSyndicDto) {
        SyndicResponseDto response = syndicService.createSyndic(createSyndicDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SyndicResponseDto> getSyndicById(@PathVariable Long id) {
        SyndicResponseDto response = syndicService.getSyndicById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SyndicResponseDto> updateSyndic(@PathVariable Long id, @Valid @RequestBody UpdateSyndicDto updateSyndicDto) {
        SyndicResponseDto response = syndicService.updateSyndic(id, updateSyndicDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-makeplan/{idMakePlan}")
    public ResponseEntity<List<SyndicResponseDto>> getSyndicsByIdMakePlan(@PathVariable Long idMakePlan) {
        try {
            List<SyndicResponseDto> response = syndicService.getSyndicsByIdMakePlan(idMakePlan);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Log l'erreur au niveau contrôleur aussi
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/active/by-makeplan/{idMakePlan}")
    public ResponseEntity<SyndicResponseDto> getActiveSyndicByIdMakePlan(@PathVariable Long idMakePlan) {
        try {
            SyndicResponseDto response = syndicService.getActiveSyndicByIdMakePlan(idMakePlan);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ===== Endpoints de gestion des fichiers =====
    
    @PostMapping("/{id}/files/carte-professionnelle")
    public ResponseEntity<String> uploadCarteProfessionnelle(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        String fileRef = syndicFileService.uploadCarteProfessionnelle(id, file);
        return fileRef != null ? ResponseEntity.ok(fileRef) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/{id}/files/assurance-rc")
    public ResponseEntity<String> uploadAssuranceRc(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        String fileRef = syndicFileService.uploadAssuranceRc(id, file);
        return fileRef != null ? ResponseEntity.ok(fileRef) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/{id}/files/garantie-financiere")
    public ResponseEntity<String> uploadGarantieFinanciere(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        String fileRef = syndicFileService.uploadGarantieFinanciere(id, file);
        return fileRef != null ? ResponseEntity.ok(fileRef) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/{id}/files/tampon-signature")
    public ResponseEntity<String> uploadTamponSignature(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        String fileRef = syndicFileService.uploadTamponSignature(id, file);
        return fileRef != null ? ResponseEntity.ok(fileRef) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/{id}/files/logo-coordonnees")
    public ResponseEntity<String> uploadLogoCoordonnees(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        String fileRef = syndicFileService.uploadLogoCoordonnees(id, file);
        return fileRef != null ? ResponseEntity.ok(fileRef) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/{id}/files/logo-simple")
    public ResponseEntity<String> uploadLogoSimple(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        String fileRef = syndicFileService.uploadLogoSimple(id, file);
        return fileRef != null ? ResponseEntity.ok(fileRef) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}/files")
    public ResponseEntity<List<FileResponseDto>> getAllSyndicFiles(@PathVariable Long id) {
        List<FileResponseDto> files = syndicFileService.getAllSyndicFiles(id);
        return ResponseEntity.ok(files);
    }

    @GetMapping("/{id}/files/carte-professionnelle")
    public ResponseEntity<FileResponseDto> getCarteProfessionnelleFile(@PathVariable Long id) {
        FileResponseDto file = syndicFileService.getCarteProfessionnelleFile(id);
        return file != null ? ResponseEntity.ok(file) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/files/assurance-rc")
    public ResponseEntity<FileResponseDto> getAssuranceRcFile(@PathVariable Long id) {
        FileResponseDto file = syndicFileService.getAssuranceRcFile(id);
        return file != null ? ResponseEntity.ok(file) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/files/garantie-financiere")
    public ResponseEntity<FileResponseDto> getGarantieFinanciereFile(@PathVariable Long id) {
        FileResponseDto file = syndicFileService.getGarantieFinanciereFile(id);
        return file != null ? ResponseEntity.ok(file) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/files/tampon-signature")
    public ResponseEntity<FileResponseDto> getTamponSignatureFile(@PathVariable Long id) {
        FileResponseDto file = syndicFileService.getTamponSignatureFile(id);
        return file != null ? ResponseEntity.ok(file) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/files/logo-coordonnees")
    public ResponseEntity<FileResponseDto> getLogoCoordonneesFile(@PathVariable Long id) {
        FileResponseDto file = syndicFileService.getLogoCoordonneesFile(id);
        return file != null ? ResponseEntity.ok(file) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/files/logo-simple")
    public ResponseEntity<FileResponseDto> getLogoSimpleFile(@PathVariable Long id) {
        FileResponseDto file = syndicFileService.getLogoSimpleFile(id);
        return file != null ? ResponseEntity.ok(file) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/files")
    public ResponseEntity<Void> deleteAllSyndicFiles(@PathVariable Long id) {
        boolean deleted = syndicFileService.deleteAllSyndicFiles(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }
}
