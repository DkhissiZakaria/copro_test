package com.app.copro.exception;

public class ProjetNotFoundException extends RuntimeException {

    public ProjetNotFoundException(String message) {
        super(message);
    }

    public ProjetNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjetNotFoundException(Long id) {
        super("Projet avec l'ID " + id + " introuvable");
    }
}