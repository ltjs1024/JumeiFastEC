package com.jm.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.jm.core.delegates.LatteDelegate;
import com.jm.core.net.RestClient;
import com.jm.core.net.callback.IError;
import com.jm.core.net.callback.IFailure;
import com.jm.core.net.callback.ISuccess;
import com.jm.core.util.log.LatteLogger;
import com.jm.ec.R;
import com.jm.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册
 * Created by ltjs1024 on 2018/01/27.
 */

public class SignUpDelegate extends LatteDelegate {
    /**
     * UI
     */
    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null;

    @OnClick(R2.id.btn_sign_up)
    public void onClickSignUp() {
        if (checkForm()) {
            RestClient.builder()
                    .url("http://192.168.0.81/RestServer/api/user_profile.php")
                    .params("name", mName.getText().toString().trim())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LatteLogger.json("USER_PROFILE", response);
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            LatteLogger.e("TAG", "onFailure");
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            LatteLogger.e("TAG", code + "-->" + msg);
                        }
                    })
                    .build()
                    .post();
        }
    }

    private boolean checkForm() {
        String name = mName.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        String phone = mPhone.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String rePassword = mRePassword.getText().toString().trim();

        boolean isPass = true;
        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("密码不得少于6位");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            mRePassword.setError("验证密码错误");
            return isPass;
        } else {
            mRePassword.setError(null);
        }

        return isPass;
    }

    @OnClick(R2.id.tv_link_sign_in)
    public void onClickSignIn() {
        getSupportDelegate().start(new SignInDelegate());
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }
}
