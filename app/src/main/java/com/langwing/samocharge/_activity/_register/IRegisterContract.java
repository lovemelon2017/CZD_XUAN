package com.langwing.samocharge._activity._register;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

import com.langwing.samocharge._base.IBasePresenter;
import com.langwing.samocharge._base.IBaseView;

/**
 * Created by WYJ
 * on 2017/9/11.
 */

public interface IRegisterContract {

    interface IRegisterView extends IBaseView {

    }

    interface IRegisterPresenter extends IBasePresenter {


        void getAuthCode(String phone);

        void changeAuthCodeBtn(Context context, AppCompatButton button);

        void register(String phone, String pwd, String authCode);

    }
}
