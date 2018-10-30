package com.langwing.samocharge._activity._register;

import com.langwing.samocharge._utils.OKHttpUtil;
import com.langwing.samocharge._utils.URL;

import okhttp3.RequestBody;

/**
 * Created by WYJ
 * on 2017/9/11.
 */

public class RegisterModel {

    public void getAuthCode(RequestBody requestBody, OKHttpUtil.GetData getData) {
        OKHttpUtil.postNoToken(URL.GET_AUTH_CODE, requestBody, getData);
    }

    public void register(RequestBody requestBody, OKHttpUtil.GetData getData) {
        OKHttpUtil.postNoToken(URL.REGISTER, requestBody, getData);
    }
}
