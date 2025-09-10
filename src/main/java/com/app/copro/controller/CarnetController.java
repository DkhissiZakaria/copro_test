package com.app.copro.controller;

import com.app.copro.model.Carnet;
import com.app.copro.service.CarnetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carnets")
@CrossOrigin(origins = "*")
public class CarnetController {

    private final CarnetService carnetService;

    public CarnetController(CarnetService carnetService) {
        this.carnetService = carnetService;
    }

    @PostMapping
    public ResponseEntity<Carnet> createCarnet() {
        Carnet carnet = carnetService.createCarnet();
        return new ResponseEntity<>(carnet, HttpStatus.CREATED);
    }

    @GetMapping("/by-makeplan/{idMakePlan}")
    public ResponseEntity<List<Carnet>> getCarnetsByIdProjetMakePlan(@PathVariable Long idMakePlan) {
        List<Carnet> carnets = carnetService.getCarnetsByIdProjetMakePlan(idMakePlan);
        return ResponseEntity.ok(carnets);
    }

    @GetMapping("/active")
    public ResponseEntity<Carnet> getCarnetForActiveSyndic() {
        Carnet carnet = carnetService.getCarnetForActiveSyndic();
        return ResponseEntity.ok(carnet);
    }

    @GetMapping("/first-active-id/by-makeplan/{idMakePlan}")
    public ResponseEntity<Long> getFirstCarnetIdForActiveSyndic(@PathVariable Long idMakePlan) {
        Long carnetId = carnetService.getFirstCarnetIdForActiveSyndicByIdMakePlan(idMakePlan);
        return ResponseEntity.ok(carnetId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carnet> updateCarnet(@PathVariable Long id, @RequestBody Carnet updatedCarnet) {
        Carnet carnet = carnetService.updateCarnet(id, updatedCarnet);
        return ResponseEntity.ok(carnet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarnet(@PathVariable Long id) {
        carnetService.deleteCarnet(id);
        return ResponseEntity.noContent().build();
    }
}
