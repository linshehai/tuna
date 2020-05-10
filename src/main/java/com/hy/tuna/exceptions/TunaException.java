package com.hy.tuna.exceptions;

public class TunaException extends Exception {

    protected String message;
    public TunaException(String message){
        super(message);
        this.message = message;
    }

    public TunaException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    public TunaException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    public TunaException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }

    public TunaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
    }

}
