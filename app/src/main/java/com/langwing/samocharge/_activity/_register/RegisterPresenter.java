package com.langwing.samocharge._activity._register;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.widget.AppCompatButton;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.langwing.samocharge._activity._main.MainActivity;
import com.langwing.samocharge._base.BasePresenter;
import com.langwing.samocharge._utils.ReturnData;
import com.langwing.samocharge._utils.Constants;
import com.langwing.samocharge._utils.OKHttpUtil;
import com.langwing.samocharge._utils.SharePreferenceUtils;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by WYJ
 * on 2017/9/11.
 */

public class RegisterPresenter extends BasePresenter implements IRegisterContract.IRegisterPresenter {

    private IRegisterContract.IRegisterView mIRegisterView;
    private final RegisterModel mRegisterModel;

    RegisterPresenter(IRegisterContract.IRegisterView iRegisterView) {
        super(iRegisterView);
        mIRegisterView = iRegisterView;
        mRegisterModel = new RegisterModel();
    }

    @Override
    public void getAuthCode(String phone) {
        if (11 != phone.length()) {
            toast("请输入正确的手机号");
        } else {
            RequestBody requestBody = new FormBody.Builder()
                    .add("mobile", phone)
                    .build();

            mRegisterModel.getAuthCode(requestBody, new OKHttpUtil.GetData() {
                @Override
                public void GetResponse(ReturnData returnData) {
                    if (returnData.status) {

                        toast("验证码已发送，请注意查收");
                    } else {
                        toast(returnData.message);

                    }
                }
            });
        }
    }

    @Override
    public void changeAuthCodeBtn(final Context context, final AppCompatButton button) {
        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                button.setText(millisUntilFinished / 1000 + "秒");
                button.setEnabled(false);
            }

            public void onFinish() {
                button.setText("重新获取");
                button.setEnabled(true);
            }
        }.start();
    }

    @Override
    public void register(String phone, String pwd, String authCode) {
        if (11 != phone.length()) {
            toast("请输入正确的手机号");
        } else if (0 == pwd.length()) {
            toast("请填写密码");
        } else if (0 == authCode.length()) {
            toast("请填写验证码");
        } else {
            showWaitDialog("正在注册");
            RequestBody requestBody = new FormBody.Builder()
                    .add("mobile", phone)
                    .add("password", pwd)
                    .add("code", authCode)
                    .build();
            mRegisterModel.register(requestBody, new OKHttpUtil.GetData() {
                @Override
                public void GetResponse(ReturnData returnData) {
                    if (returnData.status) {
                        JSONObject jsonObject = JSON.parseObject(returnData.data);
                        String token = jsonObject.getString("token");
                        SharePreferenceUtils.writeString(Constants.TOKEN, token);
                        mIRegisterView.toActivity(MainActivity.class);
                        activityFinish();
                    } else {
                        toast(returnData.message);
                    }
                }
            });
        }
    }
}
