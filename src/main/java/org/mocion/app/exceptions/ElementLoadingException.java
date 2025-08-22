package org.mocion.app.exceptions;

public class ElementLoadingException extends RuntimeException {
    public ElementLoadingException(String message) {
        super(message);
    }

    public ElementLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
