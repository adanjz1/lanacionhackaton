package com.clublanacion.global;

import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoadFromRawResourceTask extends
        AsyncTask<InputForRawType, Void, Spanned> {

    private FromRAWCallBackListener mListener;

    public void setListener(FromRAWCallBackListener listener) {
        mListener = listener;
    }

    @Override
    protected Spanned doInBackground(InputForRawType... params) {

        InputForRawType inputParameters = (InputForRawType) params[0];

        Context ctx = inputParameters.getContext();
        int resourceID = inputParameters.getResourceID();

        StringBuilder contents = new StringBuilder();
        String sep = System.getProperty("line.separator");

        try {
            InputStream is = ctx.getResources().openRawResource(resourceID);

            BufferedReader input = new BufferedReader(new InputStreamReader(is,
                    "UTF-8"), 1024 * 8);
            try {
                String line = null;
                while ((line = input.readLine()) != null) {
                    contents.append(line);
                    contents.append(sep);
                }
            } finally {
                input.close();
            }
        } catch (FileNotFoundException ex) {

            return null;
        } catch (IOException ex) {

            return null;
        }

        return Html.fromHtml(contents.toString());
    }

    @Override
    protected void onPostExecute(Spanned spanned) {
        mListener.callback(spanned);
    }
}