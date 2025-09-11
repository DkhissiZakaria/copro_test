package com.app.copro.exception;

public class TravauxReparationNotFoundException extends RuntimeException {
    public TravauxReparationNotFoundException(Long id) {
        super("Travaux reparation not found with id " + id);
    }
}
