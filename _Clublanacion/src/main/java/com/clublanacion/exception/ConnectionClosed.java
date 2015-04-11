package com.clublanacion.exception;

public class ConnectionClosed extends OfflineException {

    /**
     *
     */
    private static final long serialVersionUID = 553810155164769804L;

	/*
     * Deberiamos guardar la tarea pendiente en una sharedpreference y poolearla
	 * en un proceso asincrono
	 */

    public ConnectionClosed() {
        super();
    }

    public ConnectionClosed(String message) {
        super(message);
    }

    public ConnectionClosed(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionClosed(Throwable cause) {
        super(cause);
    }
}
