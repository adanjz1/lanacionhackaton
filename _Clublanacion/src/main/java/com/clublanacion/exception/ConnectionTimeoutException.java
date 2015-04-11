package com.clublanacion.exception;

public class ConnectionTimeoutException extends OfflineException {

	/* Deberia implementarse algo para que vaya a offline */

    /**
     *
     */
    private static final long serialVersionUID = -4243075072574785268L;

    public ConnectionTimeoutException(String message) {
        super(message);
    }

}
