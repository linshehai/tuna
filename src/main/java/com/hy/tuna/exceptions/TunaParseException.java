package com.hy.tuna.exceptions;

public class TunaParseException extends TunaException {

    public TunaParseException(String message) {
        super(message);
    }

    public TunaParseException(String message, String message1) {
        super(message, message1);
    }

    public TunaParseException(String message, Throwable cause, String message1) {
        super(message, cause, message1);
    }

    public TunaParseException(Throwable cause, String message) {
        super(cause, message);
    }

    public TunaParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1) {
        super(message, cause, enableSuppression, writableStackTrace, message1);
    }
}
