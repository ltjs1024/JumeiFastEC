package com.jm.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jm.core.app.Latte;
import com.jm.core.delegates.LatteDelegate;
import com.jm.core.net.RestClient;
import com.jm.core.net.callback.IError;
import com.jm.core.net.callback.IFailure;
import com.jm.core.net.callback.ISuccess;

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
        testRetrofit();
    }

    private void testRetrofit() {
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.e("TAG", response);
                        ToastUtils.showShort(response);
                    }
                })
                .build()
                .get();

    }
}
