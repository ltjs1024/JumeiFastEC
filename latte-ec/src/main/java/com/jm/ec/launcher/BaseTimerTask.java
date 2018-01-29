package com.jm.ec.launcher;

import java.util.TimerTask;

/**
 * BaseTimerTask
 * Created by ltjs1024 on 2018/1/27.
 */

public class BaseTimerTask extends TimerTask {
    private ITimeListener mTimeListener = null;

    public BaseTimerTask(ITimeListener timeListener) {
        this.mTimeListener = timeListener;
    }

    @Override
    public void run() {
        if (mTimeListener != null) {
            mTimeListener.onTimer();
        }
    }
}
