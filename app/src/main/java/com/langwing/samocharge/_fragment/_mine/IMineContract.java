package com.langwing.samocharge._fragment._mine;

import com.langwing.samocharge._base.IBasePresenter;
import com.langwing.samocharge._base.IBaseView;
import com.langwing.samocharge._utils.OKHttpUtil;
import com.langwing.samocharge._utils.URL;

/**
 * To Change The World
 * 2018-04-04 15:12
 * Created by Mr.Wang
 */
public interface IMineContract {
    interface IMineView extends IBaseView {
        void bindMeInfo(Me me);

        void bindUnreadQty(int qty);
    }

    interface IMinePresenter extends IBasePresenter {
        void getMe();

        void logOut();

        void getUnreadMessageQty();
    }

    class MineModel {
        void getMe(OKHttpUtil.GetData getData) {
            OKHttpUtil.get(URL.API + "/me", getData);
        }

        void logOut(OKHttpUtil.GetData getData) {
            OKHttpUtil.get(URL.API + "/auth/logout", getData);
        }

        void getUnreadMessageQty(OKHttpUtil.GetData getData) {
            OKHttpUtil.get(URL.API + "/my/message/unread/qty", getData);
        }
    }
}
