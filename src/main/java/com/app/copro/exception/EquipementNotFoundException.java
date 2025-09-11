package com.app.copro.exception;

public class EquipementNotFoundException extends RuntimeException {
    public EquipementNotFoundException(Long id) {
        super("Equipement not found with id " + id);
    }
}
