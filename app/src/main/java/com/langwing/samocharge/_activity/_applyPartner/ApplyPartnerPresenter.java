package com.langwing.samocharge._activity._applyPartner;

import com.alibaba.fastjson.JSON;
import com.langwing.samocharge._base.BasePresenter;
import com.langwing.samocharge._utils.OKHttpUtil;
import com.langwing.samocharge._utils.ReturnData;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class ApplyPartnerPresenter extends BasePresenter implements IApplyPartnerContract.IApplyPartnerPresenter {

    private IApplyPartnerContract.IApplyPartnerView applyPartnerView;
    private ApplyPartnerModel model;

    public ApplyPartnerPresenter(IApplyPartnerContract.IApplyPartnerView applyPartnerView) {
        super(applyPartnerView);
        this.applyPartnerView = applyPartnerView;
        model = new ApplyPartnerModel();
    }

    @Override
    public void getAuthCode(String phone) {
        if (11 != phone.length()) {
            toast("请正确填写手机号！");
        } else {
            applyPartnerView.getVerifyCodeTimeDown().startNow();
            RequestBody requestBody = new FormBody.Builder()
                    .add("mobile", phone)
                    .add("request_for", "business_partner_apply")
                    .build();
            showWaitDialog("");
            model.getAuthCode(requestBody, returnData -> {
                dismissWaitDialog();
                if (returnData.status) {
                    toast("验证码已发送");
                    applyPartnerView.getVerifyCodeTimeDown().startNow();
                } else {
                    toast(returnData.message);
                }

            });
        }
    }

    @Override
    public void isShowDetailedInfoLayout(String name, String idCard, String phone, String verificationCode) {
        if (0 == name.length() || 18 != idCard.length() || 11 != phone.length() || 0 == verificationCode.length()) {
            toast("请完善基本信息");
        } else {
            applyPartnerView.showDetailedLayout();
        }
    }

    @Override
    public void isShowOtherInfoLayout(String detailHomeAddress) {
//        if (0 == detailHomeAddress.length()) {
//            toast("请完善详细信息");
//        } else {
        applyPartnerView.showOtherLayout();
//        }
    }

    @Override
    public void getApplyPartnerParameters() {
        model.getApplyParameters(returnData -> {
            if (returnData.status) {
                ApplyPartnerParameters partnerParameters = JSON.parseObject(returnData.data, ApplyPartnerParameters.class);
                applyPartnerView.setApplyPartnerParameters(partnerParameters);
            } else {
                toast(returnData.message);
            }
        });
    }
}
