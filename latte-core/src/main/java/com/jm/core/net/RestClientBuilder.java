package com.jm.core.net;

import android.content.Context;

import com.jm.core.net.callback.IError;
import com.jm.core.net.callback.IFailure;
import com.jm.core.net.callback.IRequest;
import com.jm.core.net.callback.ISuccess;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/1/25.
 */

public class RestClientBuilder {
    private WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private String mUrl;
    private ResponseBody mBody;
    private ISuccess mSuccess;
    private IError mError;
    private IFailure mFailure;
    private IRequest mRequest;
    private File mFile;
    private String mDir;
    private String mExtension;
    private String mName;
    private IRequest mIRequest;
    private Context mContext;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        this.PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        this.PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RestClientBuilder dir(String dir) {
        this.mDir = dir;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = ResponseBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mSuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mError = iError;
        return this;
    }


    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mDir, mExtension, mName, mBody, mSuccess, mError,
                mFailure, mRequest, mFile, mContext);
    }

}
