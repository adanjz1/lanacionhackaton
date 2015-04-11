package com.clublanacion.view.lockheadview.states;

import com.clublanacion.view.lockheadview.LockHeadView;

public class ScreenOnState implements LockScreenState {

    public ScreenOnState(LockHeadView view) {
        if (view.getState() instanceof ResourceLoaded) {

        }

        if (view.getState() instanceof OfflineState) {

        }
    }

}
