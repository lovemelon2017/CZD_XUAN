package com.langwing.samocharge._activity._applyPartner;

import com.langwing.samocharge._base.IBasePresenter;
import com.langwing.samocharge._base.IBaseView;
import com.langwing.samocharge._utils.VerifyCodeTimeDown;

public interface IApplyPartnerContract {

    interface IApplyPartnerView extends IBaseView {
        VerifyCodeTimeDown getVerifyCodeTimeDown();

        void showDetailedLayout();

        void showOtherLayout();

        void setApplyPartnerParameters(ApplyPartnerParameters partnerParameters);
    }

    interface IApplyPartnerPresenter extends IBasePresenter {
        void getAuthCode(String phone);

        void isShowDetailedInfoLayout(String name, String idCard, String phone, String verificationCode);

        void isShowOtherInfoLayout(String detailHomeAddress);

        void getApplyPartnerParameters();
    }
}
