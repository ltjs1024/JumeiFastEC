package com.jm.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;

import com.jm.core.delegates.LatteDelegate;
import com.jm.ec.R;
import com.jm.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 * Created by Administrator on 2018/1/27.
 */

public class SignInDelegate extends LatteDelegate {
    /**
     * UI
     */
    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword;

    @OnClick(R2.id.btn_sign_in)
    public void onClickSignIn() {
        if (checkForm()) {

        }
    }

    private boolean checkForm() {
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("密码不得少于6位");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }

    @OnClick(R2.id.tv_link_sign_up)
    public void onClickLinkSignUp() {
        getSupportDelegate().start(new SignUpDelegate());
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    public void onClickSignInWechat() {

    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
