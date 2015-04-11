package com.clublanacion.exception;

public class UnkownHostException extends OfflineException {

    /**
     *
     */
    private static final long serialVersionUID = 553810155164769804L;

	/*
     * Deberiamos guardar la tarea pendiente en una sharedpreference y poolearla
	 * en un proceso asincrono
	 */

    public UnkownHostException() {
        super();
    }

    public UnkownHostException(String message) {
        super(message);
    }

    public UnkownHostException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnkownHostException(Throwable cause) {
        super(cause);
    }
}
