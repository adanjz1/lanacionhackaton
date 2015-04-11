package com.clublanacion.exception;

public class OfflineException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1100485244465256748L;

    public OfflineException() {
        super();
    }

    public OfflineException(String message) {
        super(message);
    }

    public OfflineException(String message, Throwable cause) {
        super(message, cause);
    }

    public OfflineException(Throwable cause) {
        super(cause);
    }

}
