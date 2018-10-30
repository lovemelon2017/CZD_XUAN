package com.langwing.samocharge._fragment._chargingPile;

import android.support.v4.app.FragmentTransaction;

import com.alibaba.fastjson.JSON;
import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BasePresenter;
import com.langwing.samocharge._fragment._chargingPile._list.ListFragment;
import com.langwing.samocharge._fragment._chargingPile._map.MapFragment;
import com.langwing.samocharge._utils.DD;

import java.util.List;

/**
 * Created by WYJ
 * on 2017/9/6.
 */

public class StationPresenter extends BasePresenter implements IStationContract.IChargingPilePresenter {

    private IStationContract.IChargingPileView chargingPileView;
    private IStationContract.ChargingPileModel model;
    private int fragmentFlag = 0;
    public MapFragment mapFragment;
    private ListFragment listFragment;

    StationPresenter(IStationContract.IChargingPileView iChargingPileView) {
        super(iChargingPileView);
        chargingPileView = iChargingPileView;
        model = new IStationContract.ChargingPileModel();
        getChargingPile();
    }

    @Override
    public void changeFragment(FragmentTransaction fragmentTransaction) {
        switch (fragmentFlag) {
            case 0:
                if (!listFragment.isHidden()) {
                    fragmentTransaction.hide(listFragment);
                }
                if (mapFragment.isAdded()) {
                    fragmentTransaction.show(mapFragment);
                } else {
                    fragmentTransaction.add(R.id.fl_container, mapFragment);
                }
                fragmentFlag = 1;
                break;
            case 1:
                if (!mapFragment.isHidden()) {
                    fragmentTransaction.hide(mapFragment);
                }
                if (listFragment.isAdded()) {
                    fragmentTransaction.show(listFragment);
                } else {
                    fragmentTransaction.add(R.id.fl_container, listFragment);
                    fragmentTransaction.show(listFragment);
                }
                fragmentFlag = 0;
                break;
            default:
                break;
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void getChargingPile() {
        showWaitDialog("");
        model.getChargingPile(returnData -> {
            DD.dd("getChargingPile", returnData.data);
            dismissWaitDialog();
            if (returnData.status) {
                List<Station> stationList = JSON.parseArray(returnData.data, Station.class);
                mapFragment = new MapFragment(stationList);
                listFragment = new ListFragment(stationList);
                changeFragment(chargingPileView.getFragmentTransaction());
            } else {
                toast(returnData.message);
            }
        });
    }

    @Override
    public void getChargingPile(int cityId) {
        showWaitDialog("");
        model.getChargingPile(cityId, returnData -> {
            dismissWaitDialog();
            if (returnData.status) {
                List<Station> stationList = JSON.parseArray(returnData.data, Station.class);
                if (listFragment != null) {
                    DD.dd("getChargingPile", "listFragment is not null");
                    listFragment.setStationList(stationList);
                } else {
                    DD.dd("getChargingPile", "listFragment is null");
                    mapFragment = new MapFragment(stationList);
                    listFragment = new ListFragment(stationList);
                    changeFragment(chargingPileView.getFragmentTransaction());
                }
            } else {
                toast(returnData.message);
            }
        });
    }
}
