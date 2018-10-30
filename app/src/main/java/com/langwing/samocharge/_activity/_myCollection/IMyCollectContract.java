package com.langwing.samocharge._activity._myCollection;

import com.langwing.samocharge._base.IBasePresenter;
import com.langwing.samocharge._base.IBaseView;
import com.langwing.samocharge._fragment._chargingPile.Station;
import com.langwing.samocharge._utils.OKHttpUtil;
import com.langwing.samocharge._utils.URL;

import java.util.List;

/**
 * To Change The World
 * 2018-04-17 16:44
 * Created by Mr.Wang
 */
public interface IMyCollectContract {
    interface IMyCollectView extends IBaseView {
        void initCollectRV(List<Station> stationList);

    }

    interface IMyCollectPresenter extends IBasePresenter {
        void getCollect();
    }

    class MyCollectModel {
        void getCollect(OKHttpUtil.GetData getData) {
            OKHttpUtil.get(URL.API + "/favorite-station", getData);
        }
    }
}
