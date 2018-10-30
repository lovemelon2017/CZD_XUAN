package com.langwing.samocharge._activity._login;

import com.langwing.samocharge._utils.OKHttpUtil;
import com.langwing.samocharge._utils.URL;

import okhttp3.RequestBody;

/**
 * Created by WYJ
 * on 2017/9/11.
 */

public class LoginModel {

    public void login(RequestBody requestBody, OKHttpUtil.GetData getData) {
        OKHttpUtil.postNoToken(URL.LOGIN, requestBody, getData);
    }

    public void getAuthCode(RequestBody requestBody, OKHttpUtil.GetData getData) {
        OKHttpUtil.postNoToken(URL.GET_AUTH_CODE, requestBody, getData);
    }
}
