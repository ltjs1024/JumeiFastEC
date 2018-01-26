package com.jm.fastec;

import com.jm.core.activities.ProxyActivity;
import com.jm.core.delegates.LatteDelegate;
import com.jm.ec.launcher.LauncherDelegate;

public class ExampleActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
