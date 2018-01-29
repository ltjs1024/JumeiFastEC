package com.jm.ui.launcher;

/**
 * Created by ltjs1024 on 2018/1/29.
 */

public interface ILauncherListener {

    /**
     * 启动页或引导页结束时回调
     *
     * @param tag
     */
    void onLauncherFinish(OnLauncherFinishTag tag);
}
