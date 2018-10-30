package com.langwing.samocharge._activity._applyPartner;

import com.langwing.samocharge._utils.OKHttpUtil;
import com.langwing.samocharge._utils.URL;

import okhttp3.RequestBody;

public class ApplyPartnerModel {

    public void getAuthCode(RequestBody requestBody, OKHttpUtil.GetData getData) {
        OKHttpUtil.postNoToken(URL.GET_AUTH_CODE, requestBody, getData);
    }

    public void getApplyParameters(OKHttpUtil.GetData getData) {
        OKHttpUtil.get(URL.APPLY_PARTNER_PARAMETERS, getData);
    }
}
