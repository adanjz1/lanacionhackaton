package com.clublanacion.global;

import android.content.Context;

public class InputForRawType {

    private Context context;
    private int resourceID;
    public InputForRawType(Context context, int resourceID) {
        this.context = context;
        this.resourceID = resourceID;
    }

    public Context getContext() {
        return context;
    }

    public int getResourceID() {
        return resourceID;
    }
}
