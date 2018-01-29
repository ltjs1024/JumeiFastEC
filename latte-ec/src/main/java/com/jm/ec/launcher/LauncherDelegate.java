package com.jm.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.jm.core.app.AccountManager;
import com.jm.core.app.IUserChecker;
import com.jm.core.delegates.LatteDelegate;
import com.jm.core.util.storage.LattePreference;
import com.jm.ec.R;
import com.jm.ec.R2;
import com.jm.ui.launcher.ILauncherListener;
import com.jm.ui.launcher.LauncherScrollDelegate;
import com.jm.ui.launcher.OnLauncherFinishTag;
import com.jm.ui.launcher.ScrollLauncherTag;

import java.text.MessageFormat;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 启动页
 * Created by ltjs1024 on 2018/1/26.
 */

public class LauncherDelegate extends LatteDelegate implements ITimeListener {
    /**
     * Data
     */
    private static int mCount = 5;
    private Timer mTimer;
    private ILauncherListener mILauncherListener = null;

    /**
     * UI
     */
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer;

    @OnClick(R2.id.tv_launcher_timer)
    public void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    private void initTimer() {
        mTimer = new Timer();
        TimerTask timerTask = new BaseTimerTask(this);
        mTimer.schedule(timerTask, 0, 1000);
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });

    }

    private void checkIsShowScroll() {
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            //检查用户是否已经登录
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            this.mILauncherListener = (ILauncherListener) activity;
        }
    }
}
