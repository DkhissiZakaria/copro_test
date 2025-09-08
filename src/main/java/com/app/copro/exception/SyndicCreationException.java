package com.app.copro.exception;

public class SyndicCreationException extends RuntimeException {
    public SyndicCreationException(String message) {
        super(message);
    }

    public SyndicCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
