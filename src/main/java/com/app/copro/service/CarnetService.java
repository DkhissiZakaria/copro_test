package com.app.copro.service;

import com.app.copro.model.Carnet;
import com.app.copro.model.Syndic;
import com.app.copro.repository.CarnetRepository;
import com.app.copro.repository.SyndicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarnetService {

    private final CarnetRepository carnetRepository;
    private final SyndicRepository syndicRepository;

    public CarnetService(CarnetRepository carnetRepository, SyndicRepository syndicRepository) {
        this.carnetRepository = carnetRepository;
        this.syndicRepository = syndicRepository;
    }

    public Carnet createCarnet() {
        Syndic activeSyndic = syndicRepository.findByIsActiveTrue()
                .orElseThrow(() -> new RuntimeException("Aucun syndic actif trouvé"));
        Carnet carnet = new Carnet();
        carnet.setSyndic(activeSyndic);
        return carnetRepository.save(carnet);
    }

    public List<Carnet> getCarnetsByIdProjetMakePlan(Long idMakePlan) {
        return carnetRepository.findBySyndic_Projet_IdMakePlan(idMakePlan);
    }

    public Carnet getCarnetForActiveSyndic() {
        return carnetRepository.findBySyndic_IsActiveTrue()
                .orElseThrow(() -> new RuntimeException("Aucun carnet pour le syndic actif"));
    }

    public Carnet updateCarnet(Long id, Carnet updatedCarnet) {
        Carnet existing = carnetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carnet non trouvé"));
        // For now, only syndic reassignment is allowed if provided
        if (updatedCarnet.getSyndic() != null) {
            existing.setSyndic(updatedCarnet.getSyndic());
        }
        return carnetRepository.save(existing);
    }

    public void deleteCarnet(Long id) {
        carnetRepository.deleteById(id);
    }
}
