package com.jm.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.blankj.utilcode.util.ToastUtils;
import com.jm.core.activities.ProxyActivity;
import com.jm.core.delegates.LatteDelegate;
import com.jm.core.util.log.LatteLogger;
import com.jm.ec.launcher.LauncherDelegate;
import com.jm.ec.sign.ISignListener;
import com.jm.ec.sign.SignInDelegate;
import com.jm.ui.launcher.ILauncherListener;
import com.jm.ui.launcher.OnLauncherFinishTag;

import me.yokeyword.fragmentation.ISupportFragment;

public class ExampleActivity extends ProxyActivity implements ISignListener, ILauncherListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        ToastUtils.showShort("登录成功");
    }

    @Override
    public void onSignUpSuccess() {
        ToastUtils.showShort("注册成功");
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                getSupportDelegate().startWithPop(new ExampleDelegate());
                break;
            case NOT_SIGNED:
                LatteLogger.e("ExampleActivity", "NOT_SIGNED");
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
