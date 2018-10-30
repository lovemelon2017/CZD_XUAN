package com.langwing.samocharge._activity._personalInfo;

import com.langwing.samocharge._base.IBasePresenter;
import com.langwing.samocharge._base.IBaseView;
import com.langwing.samocharge._fragment._mine.Me;
import com.langwing.samocharge._utils.OKHttpUtil;
import com.langwing.samocharge._utils.URL;

import java.io.File;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * To Change The World
 * 2018-04-09 14:58
 * Created by Mr.Wang
 */
public interface IPersonalInfoContract {
    interface IPersonalInfoView extends IBaseView {
        void bindMeInfo(Me me);

        void setResult();
    }

    interface IPersonalInfoPresenter extends IBasePresenter {
        void getMe();

        void uploadAccountInfo(String nickName, int cbId, String birthDate, File avatarFile);
    }

    class PersonalInfoModel {
        void getMe(OKHttpUtil.GetData getData) {
            OKHttpUtil.get(URL.API + "/me", getData);
        }


        void uploadAccountInfo(RequestBody requestBody, OKHttpUtil.GetData getData) {
            OKHttpUtil.post(URL.API + "/my/profile", requestBody, getData);
        }
    }
}
