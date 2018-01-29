package com.jm.ec.sign;

/**
 * 登录、注册回调
 * Created by ltjs1024 on 2018/1/29.
 */

public interface ISignListener {

    /**
     * 登录成功回调
     */
    void onSignInSuccess();

    /**
     * 注册成功回调
     */
    void onSignUpSuccess();


}
