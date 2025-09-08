package com.app.copro.exception;

public class ProjetAlreadyExistsException extends RuntimeException {

    public ProjetAlreadyExistsException(String nom) {
        super("Un projet avec le nom '" + nom + "' existe déjà");
    }

    public ProjetAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}