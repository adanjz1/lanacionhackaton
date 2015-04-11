package com.clublanacion.async;

import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;

import com.clublanacion.MainActivity;
import com.clublanacion.apis.BannerApis;
import com.clublanacion.entity.BannerEntity;
import com.clublanacion.global.Functions;
import com.clublanacion.util.PreferencesHelper;
public class AsyncGetBanner extends AsyncTask<PreferencesHelper, Void, Void> {
    private ILoadBanner listener;
    private ILocationProvisioner locationProvisioner;
    private BannerEntity bannerEntity;

    public AsyncGetBanner(ILoadBanner listener, ILocationProvisioner locationProvisioner) {
        super();
        this.listener = listener;
        this.locationProvisioner = locationProvisioner;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Functions.logI("AsyncGetBanner Cancel 1");
    }

    @Override
    protected void onCancelled(Void result) {
        super.onCancelled(result);
        Functions.logI("AsyncGetBanner Cancel 2");
    }

    @Override
    protected Void doInBackground(PreferencesHelper... params) {
        Functions.logI("getBannerData doInBackground");
        Location location = locationProvisioner.getPosition();
        String latitude = "";
        String longitude = "";
        if (location != null) {
            latitude = Double.toString(location.getLatitude());
            longitude = Double.toString(location.getLongitude());
        }
        bannerEntity = new BannerApis().getBanner(latitude, longitude,params[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        Functions.logI("getBannerData onPostExecute" + isCancelled());
        listener.onReturnBannerEntity(bannerEntity);
    }

    public interface ILoadBanner {
        public void onReturnBannerEntity(BannerEntity entity);
    }

    public interface ILocationProvisioner {
        public Location getPosition();
    }
}