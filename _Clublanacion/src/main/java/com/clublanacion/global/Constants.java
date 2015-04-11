package com.clublanacion.global;

import android.os.Environment;

public class Constants {


    public static final String DOMAIN_HOST = "http://www.peimeo.com/";

    public static final String PATH_SDCARD = Environment
            .getExternalStorageDirectory().getAbsolutePath() + "/clublanacion/";

    public static final String HOST = "http://www.peimeo.com/hackathon/";
    public static final String API_WS = HOST + "api.php";



    public static final String PREFS_PORCENTAJE = "50";
    public static final String PREFS_TARJETA = "0";

    public static final String PREFS_GASTRONOMIA = "gastronomia_allowed";
    public static final String PREFS_TURISMO = "turismo_allowed";
    public static final String PREFS_ENTRETENIMIENTO = "entretenimiento_allowed";
    public static final String PREFS_CUIDADOPERSONAL = "cuidadopersonal_allowed";
    public static final String PREFS_MODA = "moda_allowed";
    public static final String PREFS_MASCATEGORIAS = "mascategorias_allowed";


    public static final int TARJETA_CLASICA = 0;
    public static final int TARJETA_PREMIUM = 1;

    public static final String PREFS_NAME = "Club lanacion";
    public static final String PREFS_SHOW_NOTIFICATION_BAR = "show_notification_bar";


    public static final String PREFS_CACHED_BANNER_BANNER = "cached_banner_banner";
    public static final String PREFS_CACHED_BANNER_BANNER_ID = "cached_banner_banner_id";
    public static final String PREFS_CACHED_BANNER_LINK_BTN = "cached_banner_link_btn";


    public static final String RECEIVER_PLAY = "playReceiver";
    public static final String RECEIVER_KILL = "killReceiver";
    public static final String RECEIVER_REMOVE_VIEW = "removeReceiver";

}
