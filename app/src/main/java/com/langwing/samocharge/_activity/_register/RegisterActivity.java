package com.langwing.samocharge._activity._register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseBackActivity;

public class RegisterActivity extends BaseBackActivity implements View.OnClickListener, IRegisterContract.IRegisterView {

    private AppCompatEditText mEtPhone;
    private AppCompatEditText mEtAuthCode;
    private AppCompatEditText mEtPwd;
    private AppCompatButton mBtnGetAuthCode;
    private RegisterPresenter mRegisterPresenter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_register;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        mEtPhone =  findViewById(R.id.et_phone);
        mEtAuthCode =  findViewById(R.id.et_auth_code);
        mEtPwd =  findViewById(R.id.et_pwd);

        mBtnGetAuthCode =  findViewById(R.id.btn_get_auth_code);
        mBtnGetAuthCode.setOnClickListener(this);
        findViewById(R.id.btn_register).setOnClickListener(this);

        mRegisterPresenter = new RegisterPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_auth_code:
                String phone = mEtPhone.getText().toString().trim();
                mRegisterPresenter.getAuthCode(phone);
                mRegisterPresenter.changeAuthCodeBtn(this, mBtnGetAuthCode);
                break;
            case R.id.btn_register:
                String mobile = mEtPhone.getText().toString().trim();
                String pwd = mEtPwd.getText().toString().trim();
                String authCode = mEtAuthCode.getText().toString().trim();
                mRegisterPresenter.register(mobile, pwd, authCode);
                break;

        }
    }

}
