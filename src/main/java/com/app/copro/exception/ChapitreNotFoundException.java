package com.app.copro.exception;

public class ChapitreNotFoundException extends RuntimeException {
    public ChapitreNotFoundException(Long id) {
        super("Chapitre not found with id " + id);
    }
}

