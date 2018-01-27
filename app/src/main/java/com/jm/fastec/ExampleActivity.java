package com.jm.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.jm.core.activities.ProxyActivity;
import com.jm.core.delegates.LatteDelegate;
import com.jm.ec.launcher.LauncherDelegate;
import com.jm.ec.sign.SignUpDelegate;
import com.jm.ui.launcher.LauncherScrollDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new SignUpDelegate();
    }
}
