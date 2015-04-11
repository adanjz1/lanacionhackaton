package com.clublanacion.global;

import android.content.Context;
import android.graphics.Typeface;

public class GlobalFont {
    private Context mContext;

    public GlobalFont(Context context) {
        mContext = context;
    }

    public Typeface getFont() {
        return Typeface.createFromAsset(mContext.getAssets(),
                "HelveticaNeueLTPro-Lt.ttf");
    }
}
