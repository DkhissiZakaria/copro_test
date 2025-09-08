package com.app.copro.exception;

public class MandatNotFoundException extends RuntimeException {
    public MandatNotFoundException(Long syndicId) {
        super("Mandat not found for syndic id " + syndicId);
    }
}
