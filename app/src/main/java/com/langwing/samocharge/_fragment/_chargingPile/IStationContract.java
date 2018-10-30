package com.langwing.samocharge._fragment._chargingPile;


import android.support.v4.app.FragmentTransaction;

import com.langwing.samocharge._base.IBasePresenter;
import com.langwing.samocharge._base.IBaseView;
import com.langwing.samocharge._utils.OKHttpUtil;
import com.langwing.samocharge._utils.URL;

/**
 * Created by WYJ
 * on 2017/9/6.
 */

public interface IStationContract {

    interface IChargingPileView extends IBaseView {
        FragmentTransaction getFragmentTransaction();
    }

    interface IChargingPilePresenter extends IBasePresenter {
        void changeFragment(FragmentTransaction fragmentTransaction);

        void getChargingPile();

        void getChargingPile(int cityId);
    }

    class ChargingPileModel {
        void getChargingPile(OKHttpUtil.GetData getData) {
            OKHttpUtil.get(URL.API + "/station/geo-list", getData);
        }

        void getChargingPile(int cityId, OKHttpUtil.GetData getData) {
            OKHttpUtil.get(URL.API + "/station/geo-list?area_id=" + cityId, getData);
        }
    }
}
