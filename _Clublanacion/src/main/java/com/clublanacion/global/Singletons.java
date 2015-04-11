package com.clublanacion.global;

public class Singletons {
    private static final Object synchObj = new Object();
    private static boolean onScroll = false;

    public static boolean isOnScroll() {
        boolean result;
        synchronized (synchObj) {
            result = onScroll;
        }
        return result;
    }

    public static void setOnScroll(boolean _onScroll) {
        synchronized (synchObj) {
            onScroll = _onScroll;
        }
    }
}
