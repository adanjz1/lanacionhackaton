package com.clublanacion.apis;

//import com.google.gson.Gson;

import android.util.Log;

import com.clublanacion.entity.BannerEntity;
import com.clublanacion.exception.OfflineException;
import com.clublanacion.global.Constants;
import com.clublanacion.global.Functions;
import com.clublanacion.util.JSONParser;
import com.clublanacion.util.PreferencesHelper;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BannerApis {
    public BannerEntity getBanner(String latitude, String longitude,PreferencesHelper helper) {
        Functions.logI("BannerEntity getBanner");
        BannerEntity entity = null;
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("lat", latitude));
        params.add(new BasicNameValuePair("long", longitude));
        params.add(new BasicNameValuePair("tarjeta", String.valueOf(helper.getTarjeta())));
        params.add(new BasicNameValuePair("porcentaje", String.valueOf(helper.getPorcentaje())));
        params.add(new BasicNameValuePair("gastronomia", String.valueOf(helper.getCheckGastronomia())));
        params.add(new BasicNameValuePair("entretenimiento", String.valueOf(helper.getCheckEntretenimiento())));
        params.add(new BasicNameValuePair("turismo", String.valueOf(helper.getCheckTurismo())));
        params.add(new BasicNameValuePair("moda", String.valueOf(helper.getCheckModa())));
        params.add(new BasicNameValuePair("masCategorias", String.valueOf(helper.getCheckMasCategorias())));
        params.add(new BasicNameValuePair("cuidadoPersonal", String.valueOf(helper.getCheckCuidadoPersonal())));





        JSONParser jsonParser = new JSONParser();
        //Gson gson = new Gson();

        JSONObject json;
        try {
            json = jsonParser.getJSONFromUrl(Constants.API_WS, 5000, params);
            Functions.logI("json: " + json.toString());
            if (!json.isNull("success") && json.getBoolean("success")) {
                String bannerId = json.getString("bannerId");
                String banner = json.getString("html").replace("\\", "");
                String linkbtn = json.getString("link").replace("\\", "");

                entity = new BannerEntity(bannerId, banner, linkbtn);

            }
        } catch (OfflineException e) {
            Log.e("Error", "BannerApis::getBanner()" + e.getMessage()); //Deberia reintentar e ir a offline
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return entity;
    }
}
