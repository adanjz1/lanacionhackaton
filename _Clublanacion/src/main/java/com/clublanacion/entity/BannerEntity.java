package com.clublanacion.entity;

import com.clublanacion.global.Constants;

public class BannerEntity {
    public static final String BANNER_ID = "bannerId";
    public static final String BANNER = "banner";
    public static final String LINKBTN = "linkbtn";

    private String _bannerId;
    private String _banner;
    private String _linkbtn;

    public BannerEntity(String _bannerId, String _banner, String _linkbtn) {
        super();
        this._bannerId = _bannerId;
        this._banner = _banner;
        this._linkbtn = _linkbtn;

    }

    public BannerEntity() {
        super();
    }


    public String get_bannerId() {
        return _bannerId;
    }

    public void set_bannerId(String _bannerId) {
        this._bannerId = _bannerId;
    }

    public String get_banner() {
        return _banner;
    }

    public void set_banner(String _banner) {
        this._banner = _banner;
    }

    public String get_linkbtn() {
        return _linkbtn;
    }

    public void set_linkbtn(String _linkbtn) {
        this._linkbtn = _linkbtn;
    }


}