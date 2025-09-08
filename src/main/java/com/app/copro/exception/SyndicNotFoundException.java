package com.app.copro.exception;

public class SyndicNotFoundException extends RuntimeException {

    public SyndicNotFoundException(String message) {
        super(message);
    }

    public SyndicNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SyndicNotFoundException(Long id) {
        super("Syndic avec l'ID " + id + " introuvable");
    }
}