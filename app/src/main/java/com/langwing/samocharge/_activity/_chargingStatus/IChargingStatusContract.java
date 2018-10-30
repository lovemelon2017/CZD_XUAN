package com.langwing.samocharge._activity._chargingStatus;

import com.langwing.samocharge._base.IBasePresenter;
import com.langwing.samocharge._base.IBaseView;
import com.langwing.samocharge._utils.OKHttpUtil;
import com.langwing.samocharge._utils.URL;

import okhttp3.RequestBody;

/**
 * To Change The World
 * 2018-04-10 16:10
 * Created by Mr.Wang
 */
public interface IChargingStatusContract {
    interface IChargingStatusView extends IBaseView {
        void startCharge();
    }

    interface IChargingStatusPresenter extends IBasePresenter {
        void startCharge(int stationId, RequestBody requestBodyl);
    }

    class ChargingStatusModel {
        void startCharge(int stationId, RequestBody requestBody, OKHttpUtil.GetData getData) {
            OKHttpUtil.post(URL.API + "/station/" + stationId + "/charge/start", requestBody, getData);
        }
    }
}
