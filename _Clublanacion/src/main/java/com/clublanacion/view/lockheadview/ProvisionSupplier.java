package com.clublanacion.view.lockheadview;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;

import com.clublanacion.async.AsyncGetBanner.ILocationProvisioner;
import com.clublanacion.global.Functions;

import java.lang.ref.WeakReference;
import java.util.List;

public class ProvisionSupplier implements ILocationProvisioner {

    private final WeakReference<Context> context;
    private LocationManager locationManager;
    private LocationProvider provider;
    private Criteria criteria;

    public ProvisionSupplier(Context context) {
        this.context = new WeakReference<Context>(context);
        this.setCriteria();
    }

    @Override
    public Location getPosition() {
        Functions.logD("Consultando geoposicionamiento");
        locationManager = (LocationManager) context.get()
                .getSystemService(Context.LOCATION_SERVICE);

        List<String> listaProviders = locationManager.getAllProviders();

        for (String nombre : listaProviders) {
            Functions.logD("Provider: " + nombre);
            provider = locationManager.getProvider(nombre);

            Functions.logD("precision: " + provider.getAccuracy());
            Functions.logD("obtieneAltitud: " + provider.supportsAltitude());
            Functions
                    .logD("consumoRecursos: " + provider.getPowerRequirement());
        }

        String bestProvider = locationManager.getBestProvider(criteria, false);
        Functions.logD("bestProvider: " + bestProvider);

        Location location = locationManager
                .getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location == null && bestProvider != LocationManager.GPS_PROVIDER) {
            location = locationManager
                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }

        if (location != null) {
            Functions.logD("lat:" + location.getLatitude() + " long: "
                    + location.getLongitude());
        }

        return location;
    }

    private void setCriteria() {
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.POWER_LOW);
        criteria.setAltitudeRequired(false);
    }
}
