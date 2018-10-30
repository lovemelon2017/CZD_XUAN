package com.langwing.samocharge._fragment._chargingPile._list;

import com.amap.api.navi.model.NaviLatLng;
import com.langwing.samocharge._base.IBasePresenter;
import com.langwing.samocharge._base.IBaseView;
import com.langwing.samocharge._fragment._chargingPile.Station;

import java.util.List;

/**
 * Created by WYJ
 * on 2017/9/7.
 */

public interface IListContract {

    interface IListView extends IBaseView {

        void initRvChargingPile(List<Station> stationList);
    }

    interface IListPresenter extends IBasePresenter {

        String getDistance(NaviLatLng startLatLng, NaviLatLng endLatLng);
    }

}
