package com.app.copro.exception;

public class TravailImportantNotFoundException extends RuntimeException {
    public TravailImportantNotFoundException(Long id) {
        super("Travail important not found with id " + id);
    }
}

