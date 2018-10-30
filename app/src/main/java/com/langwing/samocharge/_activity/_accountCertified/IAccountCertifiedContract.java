package com.langwing.samocharge._activity._accountCertified;

import android.widget.ImageView;

import com.langwing.samocharge._base.IBasePresenter;
import com.langwing.samocharge._base.IBaseView;
import com.langwing.samocharge._fragment._mine.Me;
import com.langwing.samocharge._utils.OKHttpUtil;
import com.langwing.samocharge._utils.URL;

import java.io.File;
import java.util.Map;

import okhttp3.RequestBody;

public interface IAccountCertifiedContract {

    interface IAccountCertifiedView extends IBaseView {
//        void takePhoto(ImageView imageView);

        void bindMeInfo(Me me);

        void setResult();
    }

    interface IAccountCertifiedPresenter extends IBasePresenter {
        void uploadIdentifyInfo(String etName, String idCard, Map<String, File> imageMap);

        void getMe();
    }

    class AccountCertifiedModel {
        void uploadIdentifyInfo(RequestBody requestBody, OKHttpUtil.GetData getData) {
            OKHttpUtil.post(URL.API + "/my/profile", requestBody, getData);
        }

        void getMe(OKHttpUtil.GetData getData) {
            OKHttpUtil.get(URL.API + "/me", getData);
        }
    }
}
