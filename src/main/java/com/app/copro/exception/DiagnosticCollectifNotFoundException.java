package com.app.copro.exception;

public class DiagnosticCollectifNotFoundException extends RuntimeException {
    public DiagnosticCollectifNotFoundException(Long id) {
        super("Diagnostic collectif not found with id " + id);
    }
}

