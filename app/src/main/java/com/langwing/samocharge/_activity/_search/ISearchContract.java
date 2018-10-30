package com.langwing.samocharge._activity._search;

import com.langwing.samocharge._base.IBasePresenter;
import com.langwing.samocharge._base.IBaseView;
import com.langwing.samocharge._fragment._chargingPile.Station;
import com.langwing.samocharge._utils.OKHttpUtil;
import com.langwing.samocharge._utils.URL;

import java.util.List;

public interface ISearchContract {

    interface ISearchView extends IBaseView {

        void initRvChargingPile(List<Station> stationList);
    }

    interface ISearchPresenter extends IBasePresenter {

        void getSearchDetails(String data);
    }

    class SearchModel {
        public void getSearchDertails(String data, OKHttpUtil.GetData getData) {
            OKHttpUtil.get(URL.API + "/station/geo-list?search=" + data, getData);
        }
    }
}

