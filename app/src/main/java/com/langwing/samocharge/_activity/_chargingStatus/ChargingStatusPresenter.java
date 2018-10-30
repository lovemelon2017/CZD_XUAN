package com.langwing.samocharge._activity._chargingStatus;


import com.langwing.samocharge._base.BasePresenter;
import com.langwing.samocharge._utils.ReturnData;
import com.langwing.samocharge._utils.OKHttpUtil;

import okhttp3.RequestBody;

/**
 * To Change The World
 * 2018-04-10 16:13
 * Created by Mr.Wang
 */
class ChargingStatusPresenter extends BasePresenter implements IChargingStatusContract.IChargingStatusPresenter {
    private IChargingStatusContract.IChargingStatusView chargingStatusView;
    IChargingStatusContract.ChargingStatusModel model;

    ChargingStatusPresenter(IChargingStatusContract.IChargingStatusView chargingStatusView) {
        super(chargingStatusView);
        this.chargingStatusView = chargingStatusView;
        model = new IChargingStatusContract.ChargingStatusModel();
    }

    @Override
    public void startCharge(int stationId, RequestBody requestBody) {
        showWaitDialog("");
        model.startCharge(stationId, requestBody, new OKHttpUtil.GetData() {
            @Override
            public void GetResponse(ReturnData returnData) {
                dismissWaitDialog();
                if (returnData.status) {

                } else {
                    toast(returnData.message);
                }
            }
        });
    }
}
