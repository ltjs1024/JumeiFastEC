package com.jm.core.app;

/**
 * 用户状态检查
 * Created by ltjs1024 on 2018/1/29.
 */

public interface IUserChecker {

    /**
     * 已登录
     */
    void onSignIn();

    /**
     * 未登录
     */
    void onNotSignIn();
}
