package com.langwing.samocharge._fragment._chargingPile._list;

import com.amap.api.navi.model.NaviLatLng;
import com.langwing.samocharge._base.BasePresenter;

/**
 * Created by WYJ
 * on 2017/9/7.
 */

public class ListPresenter extends BasePresenter implements IListContract.IListPresenter {

    public ListPresenter(IListContract.IListView iListView) {
        super(iListView);
    }

    @Override
    public String getDistance(NaviLatLng startLatLng, NaviLatLng endLatLng) {
        return null;
    }
}
