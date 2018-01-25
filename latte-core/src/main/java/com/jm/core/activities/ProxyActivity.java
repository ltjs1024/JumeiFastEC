package com.jm.core.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.jm.core.R;
import com.jm.core.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * 单Activity基类
 * Created by ltjs1024 on 2018/1/25.
 */

public abstract class ProxyActivity extends SupportActivity {

    public abstract LatteDelegate setRootDelegate();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initContainer(savedInstanceState);

    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout frameLayout = new ContentFrameLayout(this);
        frameLayout.setId(R.id.delegate_container);
        setContentView(frameLayout);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }


}
