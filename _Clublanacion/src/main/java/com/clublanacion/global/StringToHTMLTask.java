package com.clublanacion.global;

import android.os.AsyncTask;
import android.text.Html;
import android.text.Spanned;

public class StringToHTMLTask extends AsyncTask<String, Void, Spanned> {

    private FromHTMLCallBackListener mListener;

    public void setListener(FromHTMLCallBackListener listener) {
        this.mListener = listener;
    }

    @Override
    protected Spanned doInBackground(String... params) {
        return Html.fromHtml((String) params[0]);
    }

    @Override
    protected void onPostExecute(Spanned result) {
        mListener.callback(result);
    }

}
