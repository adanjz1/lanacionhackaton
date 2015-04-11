package com.clublanacion.util;

public final class NetworkType {   // final class!!

    public static final NetworkType unkownType = new NetworkType();
    public static final NetworkType lowSpeed = new NetworkType();
    public static final NetworkType highSpeed = new NetworkType();
    public static final NetworkType ultraHighSpeed = new NetworkType();
    private NetworkType() {
    }         // private constructor!!
}