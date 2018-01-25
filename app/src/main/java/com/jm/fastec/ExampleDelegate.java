package com.jm.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jm.core.delegates.LatteDelegate;

/**
 * Created by Administrator on 2018/1/25.
 */

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
