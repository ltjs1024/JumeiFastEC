package com.jm.core.net.callback;

import android.os.Handler;

import com.jm.core.app.ConfigKeys;
import com.jm.core.app.Latte;
import com.jm.core.net.RestCreator;
import com.jm.core.ui.loader.LatteLoader;
import com.jm.core.ui.loader.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 网络请求结果回调
 * Created by ltjs1024 on 2018/1/25.
 */

public class RequestCallback implements Callback<String> {
    private static final Handler HANDLER = Latte.getHandler();

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;

    public RequestCallback(IRequest request,
                           ISuccess success,
                           IFailure failure,
                           IError error,
                           LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }

        onRequestFinish();

    }

    private void onRequestFinish() {
        final long delayed = Latte.getConfiguration(ConfigKeys.LOADER_DELAYED);
        if (LOADER_STYLE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    RestCreator.getParams().clear();
                    LatteLoader.stopLoading();
                }
            }, delayed);
        }

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }

        onRequestFinish();
    }
}
