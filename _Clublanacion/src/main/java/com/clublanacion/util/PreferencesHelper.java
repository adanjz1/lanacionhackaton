package com.clublanacion.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.clublanacion.entity.BannerEntity;
import com.clublanacion.global.Constants;

public class PreferencesHelper {

    Context context;
    SharedPreferences myPrefs;
    Editor myEditors;

    public PreferencesHelper(Context _con) {
        this.context = _con;

        myPrefs = context.getSharedPreferences(Constants.PREFS_NAME,
                Context.MODE_MULTI_PROCESS);

        myEditors = myPrefs.edit();
    }

    public void updatePreferences() {
        // Must be called on a separate thread from UI
        myPrefs = context.getSharedPreferences(Constants.PREFS_NAME,
                Context.MODE_MULTI_PROCESS);
    }



    public boolean getShowNotificationBar() {
        return myPrefs.getBoolean(Constants.PREFS_SHOW_NOTIFICATION_BAR, true);
    }






    public void setPorcentaje(int porcentaje) {
        myEditors.putInt(Constants.PREFS_PORCENTAJE, porcentaje);
        myEditors.apply();
    }
    public int getPorcentaje(){
        return myPrefs.getInt(Constants.PREFS_PORCENTAJE,50);
    }
    public void setTarjeta(int tarjeta) {
        myEditors.putInt(Constants.PREFS_TARJETA, tarjeta);
        myEditors.apply();
    }
    public int getTarjeta(){
        return myPrefs.getInt(Constants.PREFS_TARJETA,Constants.TARJETA_CLASICA);
    }


    public void setCheckGastronomia(boolean value) {
        myEditors.putBoolean(Constants.PREFS_GASTRONOMIA, value);
        myEditors.apply();
    }
    public boolean getCheckGastronomia(){
        return myPrefs.getBoolean(Constants.PREFS_GASTRONOMIA,false);
    }

    public void setCheckTurismo(boolean value) {
        myEditors.putBoolean(Constants.PREFS_TURISMO,value);
        myEditors.apply();
    }
    public boolean getCheckTurismo(){
        return myPrefs.getBoolean(Constants.PREFS_TURISMO,false);
    }


    public void setCheckEntretenimiento(boolean value) {
        myEditors.putBoolean(Constants.PREFS_ENTRETENIMIENTO, value);
        myEditors.apply();
    }
    public boolean getCheckEntretenimiento(){
        return myPrefs.getBoolean(Constants.PREFS_ENTRETENIMIENTO,false);
    }

    public void setCheckCuidadoPersonal(boolean value) {
        myEditors.putBoolean(Constants.PREFS_CUIDADOPERSONAL,value);
        myEditors.apply();
    }
    public boolean getCheckCuidadoPersonal(){
        return myPrefs.getBoolean(Constants.PREFS_CUIDADOPERSONAL,false);
    }

    public void setCheckModa(boolean value) {
        myEditors.putBoolean(Constants.PREFS_MODA, value);
        myEditors.apply();
    }
    public boolean getCheckModa(){
        return myPrefs.getBoolean(Constants.PREFS_MODA,false);
    }

    public void setCheckMasCategorias(boolean value) {
        myEditors.putBoolean(Constants.PREFS_MASCATEGORIAS,value);
        myEditors.apply();
    }
    public boolean getCheckMasCategorias(){
        return myPrefs.getBoolean(Constants.PREFS_MASCATEGORIAS,false);
    }



    public BannerEntity getCachedBanner() {

        String banner = myPrefs.getString(Constants.PREFS_CACHED_BANNER_BANNER,
                "");
        String banner_id = myPrefs.getString(
                Constants.PREFS_CACHED_BANNER_BANNER_ID, "");

        String link_btn = myPrefs.getString(
                Constants.PREFS_CACHED_BANNER_LINK_BTN, "");


        return new BannerEntity(banner_id, banner, link_btn);
    }

    public void setCachedBanner(BannerEntity bannerEntity) {
        if (bannerEntity != null) {
            myEditors.putString(Constants.PREFS_CACHED_BANNER_BANNER,
                    bannerEntity.get_banner());
            myEditors.putString(Constants.PREFS_CACHED_BANNER_BANNER_ID,
                    bannerEntity.get_bannerId());

            myEditors.putString(Constants.PREFS_CACHED_BANNER_LINK_BTN,
                    bannerEntity.get_linkbtn());

            myEditors.apply();
        }
    }


}
