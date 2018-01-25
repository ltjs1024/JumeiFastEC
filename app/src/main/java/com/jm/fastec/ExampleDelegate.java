package com.jm.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
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
                .url("http://wap.faxingw.cn/wapapp.php?g=User&m=text&a=getlist")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.e("TAG", response);
                        Toast.makeText(Latte.getApplicationContext(), "获取成功！", Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.e("TAG", "onFailure");
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Log.e("TAG", code + "--->" + msg);
                    }
                })
                .build()
                .get();

    }
}
