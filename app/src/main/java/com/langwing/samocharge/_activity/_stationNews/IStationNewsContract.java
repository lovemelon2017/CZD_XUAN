package com.langwing.samocharge._activity._stationNews;

import com.langwing.samocharge._base.IBasePresenter;
import com.langwing.samocharge._base.IBaseView;
import com.langwing.samocharge._utils.OKHttpUtil;
import com.langwing.samocharge._utils.URL;

import java.util.List;

public interface IStationNewsContract {

    interface IStationNewsView extends IBaseView {
        void initRvMessage(List<Message> messageList);
    }

    interface IStationNewsPresenter extends IBasePresenter {
        void getNews(String type);
    }

    class StaionNewsModel {
        public void getNews(String type, OKHttpUtil.GetData getData) {
            OKHttpUtil.get(URL.API + "/my/message?type=" + type, getData);
        }
    }
}
