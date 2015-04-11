package com.clublanacion.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.clublanacion.global.Constants;

public class ScreenStateReceiver extends BroadcastReceiver {

    private BlockTouchScreenService service;
    public static boolean state;
    public ScreenStateReceiver() {
    }

    public ScreenStateReceiver(BlockTouchScreenService service) {
        this.service = service;
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        String action = intent.getAction();
        try {
            if (action != null && action.equals(Intent.ACTION_SCREEN_OFF)) {

                if (!InOutCallReceiver.willLockAgain()
                        && !InOutCallReceiver.isOutgoing()) {
                    ScreenStateReceiver.state = false;
                    context.sendBroadcast(new Intent(Constants.RECEIVER_KILL));

					/*
                     * Fix to reload the image when screen is going off and on
					 * repeatedly, if it was blocked at screen off, unlock and
					 * return to lock
					 */
                    if (BlockTouchScreenService.isBlockTouch()) {
                        context.sendBroadcast(new Intent(
                                BlockTouchScreenService.RECEIVER_UNLOCK));
                    }
                    context.sendBroadcast(new Intent(
                            BlockTouchScreenService.RECEIVER_LOCK));
                }
            } else {

                if (service != null) {
                    service.setScreenOn();
                } else {
                    if (BlockTouchScreenService.isBlockTouch()) {
                        context.sendBroadcast(new Intent(
                                BlockTouchScreenService.RECEIVER_UNLOCK));
                    }
                }

                ScreenStateReceiver.state = true;
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        context.sendBroadcast(new Intent(
                                Constants.RECEIVER_PLAY));
                    }
                });
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}