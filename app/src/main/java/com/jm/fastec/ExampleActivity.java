package com.jm.fastec;

import com.jm.core.activities.ProxyActivity;
import com.jm.core.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
