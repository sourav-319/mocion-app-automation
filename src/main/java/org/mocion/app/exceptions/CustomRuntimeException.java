package org.mocion.app.exceptions;

import java.net.MalformedURLException;

public class CustomRuntimeException extends RuntimeException {
    public CustomRuntimeException(MalformedURLException message) {
        super(message);
    }

    public CustomRuntimeException(String message) {
        super(message);
    }

    public CustomRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
