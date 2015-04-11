package com.clublanacion.global;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

//import com.clublanacion.LoginActivity;
import com.clublanacion.MainActivity;
import com.clublanacion.R;
import com.clublanacion.util.NetworkType;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Functions {
    private static final String TAG = "Club lanacion";
    private static Toast mToast;

    public static void logE(String msg) {
        Log.e(TAG, msg);
    }

    public static void logI(String msg) {
        Log.i(TAG, msg);
    }

    public static void logV(String msg) {
        Log.v(TAG, msg);
    }


    public static void logD(String msg) {
        Log.d(TAG, msg);
    }

    public static void gotoUrl(Context context, String url) {
        Functions.logI("[gotoUrl] " + url);
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static boolean checkNetwork(Context con) {
        ConnectivityManager conMgr = (ConnectivityManager) con
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        // mobile
        State mobile = conMgr.getNetworkInfo(0) != null ? conMgr
                .getNetworkInfo(0).getState() : State.UNKNOWN;
        // wifi
        State wifi = conMgr.getNetworkInfo(1) != null ? conMgr
                .getNetworkInfo(1).getState() : State.UNKNOWN;
        boolean state = mobile == NetworkInfo.State.CONNECTED
                || mobile == NetworkInfo.State.CONNECTING
                || wifi == NetworkInfo.State.CONNECTED
                || wifi == NetworkInfo.State.CONNECTING;

        return state;
    }


    public static void vibrate(Context con) {
        Vibrator v = (Vibrator) con.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(50);
    }



}
