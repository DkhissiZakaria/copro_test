package com.app.copro.exception;

public class ProcedureAdministrativeNotFoundException extends RuntimeException {
    public ProcedureAdministrativeNotFoundException(Long id) {
        super("Procédure administrative not found with id " + id);
    }
}
