package com.app.copro.exception;

public class ContratAssuranceNotFoundException extends RuntimeException {
    public ContratAssuranceNotFoundException(Long id) {
        super("Contrat d'assurance not found with id " + id);
    }
}
