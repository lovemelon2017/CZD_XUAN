package com.langwing.samocharge._activity._login;

import com.langwing.samocharge._activity._main.MainActivity;
import com.langwing.samocharge._base.BasePresenter;
import com.langwing.samocharge._utils.Constants;
import com.langwing.samocharge._utils.DD;
import com.langwing.samocharge._utils.SharePreferenceUtils;

import org.json.JSONException;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by WYJ
 * on 2017/9/11.
 */

public class LoginPresenter extends BasePresenter implements ILoginContract.ILoginPresenter {

    private ILoginContract.ILoginView loginView;
    private final LoginModel loginModel;

    LoginPresenter(ILoginContract.ILoginView iLoginView) {
        super(iLoginView);
        loginView = iLoginView;
        loginModel = new LoginModel();
    }

    @Override
    public void login(String phone, String authCode) {
        if (11 != phone.length()) {
            toast("请输入正确的手机号");
        } else if (0 == authCode.length()) {
            toast("请输入验证码");
        } else {
            showWaitDialog("正在登录");
            RequestBody requestBody = new FormBody.Builder()
                    .add("mobile", phone)
                    .add("verify_code", authCode)
                    .build();
            loginModel.login(requestBody, returnData -> {
                dismissWaitDialog();
                if (returnData.status) {
                    try {
                        org.json.JSONObject jsonObject = new org.json.JSONObject(returnData.data);
                        String token = jsonObject.getString("token");
                        SharePreferenceUtils.writeString(Constants.TOKEN, token);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    loginView.toActivity(MainActivity.class);
                    activityFinish();
                } else {
                    toast(returnData.message);
                }
            });
        }
    }

    @Override
    public void getAuthCode(String mobile) {
        if (mobile.length() != 11) {
            toast("请正确填写手机号！");
            return;
        }
        RequestBody requestBody = new FormBody.Builder()
                .add("mobile", mobile)
                .add("request_for", "app_login")
                .build();
        showWaitDialog("");
        loginModel.getAuthCode(requestBody, returnData -> {
            dismissWaitDialog();
            DD.dd("getAuthCode", returnData.data);
            if (returnData.status) {
                loginView.getVerifyCodeTimeDown().startNow();
                toast("验证码已发送");
            } else {
                toast(returnData.message);
            }
//                switch (returnData.status) {
//                    case 1:
////----------------------测试版本，验证码自动填入-------------------------------------------------------
//                        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(returnData.data);
//                        String code = String.valueOf(jsonObject.getInteger("code"));
//                        if (!code.equals("null")) {
////                            loginView.setVerifyCode(code);
//                        }
////--------------------------------------------------------------------------------------------------
//                        loginView.getVerifyCodeTimeDown().startNow();
//                        toast(returnData.message);
//                        break;
//                    default:
//                        toast(returnData.message);
//                        break;
//                }
        });
    }

}
