package com.langwing.samocharge._activity._login;

import com.langwing.samocharge._base.IBasePresenter;
import com.langwing.samocharge._base.IBaseView;
import com.langwing.samocharge._utils.VerifyCodeTimeDown;

/**
 * Created by WYJ
 * on 2017/9/11.
 */

public interface ILoginContract {

    interface ILoginView extends IBaseView {
        VerifyCodeTimeDown getVerifyCodeTimeDown();
    }

    interface ILoginPresenter extends IBasePresenter {
        void login(String phone, String authCode);

        void getAuthCode(String phone);
    }
}
