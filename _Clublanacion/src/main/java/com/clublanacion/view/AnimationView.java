package com.clublanacion.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.widget.ImageView;

import com.clublanacion.R;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class AnimationView extends ImageView {
    private static final boolean DECODE_STREAM = true;
    int width = 0;
    int height = 0;
    private Movie mMovie;
    private long mMovieStart;
    public AnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        width = ((WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getWidth();

        height = ((WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getHeight();

        setFocusable(true);
        InputStream is = context.getResources().openRawResource(
                R.drawable.loading_icon);
        if (DECODE_STREAM) {
            mMovie = Movie.decodeStream(is);
        } else {
            byte[] array = streamToBytes(is);
            mMovie = Movie.decodeByteArray(array, 0, array.length);
        }
    }

    private static byte[] streamToBytes(InputStream is) {
        ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = is.read(buffer)) >= 0) {
                os.write(buffer, 0, len);
            }
        } catch (java.io.IOException e) {
        }
        return os.toByteArray();
    }

    @Override
    public void onDraw(Canvas canvas) {
        long now = android.os.SystemClock.uptimeMillis();
        if (mMovieStart == 0) {
            // first time
            mMovieStart = now;
        }
        if (mMovie != null) {
            int dur = mMovie.duration();

            if (dur == 0) {
                dur = 3000;
            }

            int relTime = (int) ((now - mMovieStart) % dur);
            mMovie.setTime(relTime);
            mMovie.draw(canvas, 0, 0);
            invalidate();
        }
    }
}