package com.app.copro.exception;

public class ContratMaintenanceNotFoundException extends RuntimeException {
    public ContratMaintenanceNotFoundException(Long id) {
        super("Contrat maintenance not found with id " + id);
    }
}
