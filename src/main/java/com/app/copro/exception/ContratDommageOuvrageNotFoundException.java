package com.app.copro.exception;

public class ContratDommageOuvrageNotFoundException extends RuntimeException {
    public ContratDommageOuvrageNotFoundException(Long id) {
        super("Contrat dommage-ouvrage not found with id " + id);
    }
}
