package com.langwing.samocharge._activity._setting;

import com.langwing.samocharge._base.IBasePresenter;
import com.langwing.samocharge._base.IBaseView;
import com.langwing.samocharge._utils.OKHttpUtil;
import com.langwing.samocharge._utils.URL;

import okhttp3.FormBody;

public interface ISettingContract {

    interface ISettingView extends IBaseView{

    }

    interface ISettingPresenter extends IBasePresenter{
        void logOut();
    }

    class SettingModel {
        void logOut(OKHttpUtil.GetData getData) {
            OKHttpUtil.post(URL.API + "/auth/logout", new FormBody.Builder().build(), getData);
        }
    }
}
