package com.jm.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jm.core.app.AccountManager;
import com.jm.ec.database.DatabaseManager;
import com.jm.ec.database.UserProfile;

/**
 * 注册、登录处理类
 * Created by ltjs1024 on 2018/1/29.
 */

public class SignHandler {

    /**
     * 登录成功处理
     *
     * @param response
     * @param listener
     */
    public static void onSignIn(String response, ISignListener listener) {
        final JSONObject data = JSON.parseObject(response).getJSONObject("data");
        final long userId = data.getLong("userId");
        final String name = data.getString("name");
        final String avatar = data.getString("avatar");
        final String gender = data.getString("gender");
        final String address = data.getString("address");

        final UserProfile userProfile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(userProfile);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        listener.onSignInSuccess();

    }

    /**
     * 注册成功处理
     *
     * @param response
     * @param listener
     */
    public static void onSignUp(String response, ISignListener listener) {
        final JSONObject data = JSON.parseObject(response).getJSONObject("data");
        final long userId = data.getLong("userId");
        final String name = data.getString("name");
        final String avatar = data.getString("avatar");
        final String gender = data.getString("gender");
        final String address = data.getString("address");

        final UserProfile userProfile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(userProfile);

        //已经注册成功了
        AccountManager.setSignState(true);
        listener.onSignUpSuccess();
    }
}
