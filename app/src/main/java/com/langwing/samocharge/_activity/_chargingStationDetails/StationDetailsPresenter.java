package com.langwing.samocharge._activity._chargingStationDetails;

import com.alibaba.fastjson.JSON;
import com.langwing.samocharge._base.BasePresenter;
import com.langwing.samocharge._utils.ReturnData;
import com.langwing.samocharge._utils.DD;
import com.langwing.samocharge._utils.OKHttpUtil;

/**
 * Created by WYJ
 * on 2017/9/7.
 */

public class StationDetailsPresenter extends BasePresenter implements IStationDetailsContract.IStationDetailsPresenter {

    private IStationDetailsContract.IStationDetailsView stationView;
    private IStationDetailsContract.StationDetailsModel model;

    StationDetailsPresenter(IStationDetailsContract.IStationDetailsView stationView) {
        super(stationView);
        this.stationView = stationView;
        model = new IStationDetailsContract.StationDetailsModel();
    }

    @Override
    public void getPileDetails(int pileID) {
        model.getPileDetails(pileID, returnData -> {
            DD.dd("pileDetails", JSON.toJSONString(returnData));
            if (returnData.status) {
                StationDetails stationDetails = JSON.parseObject(returnData.data, StationDetails.class);
                stationView.initStation(stationDetails);
            } else {
                toast(returnData.message);
            }
        });
    }

    @Override
    public void addCollect(int stationID) {
        showWaitDialog("");
        model.addCollect(stationID, new OKHttpUtil.GetData() {
            @Override
            public void GetResponse(ReturnData returnData) {
                dismissWaitDialog();
                if (returnData.status) {
                    toast("收藏成功！");
                    stationView.setCollect(true);
                } else {
                    toast(returnData.message);
                }
            }
        });
    }

    @Override
    public void removeCollect(int stationID) {
        showWaitDialog("");
        model.removeCollect(stationID, new OKHttpUtil.GetData() {
            @Override
            public void GetResponse(ReturnData returnData) {
                dismissWaitDialog();
                if (returnData.status) {
                    toast("取消收藏成功！");
                    stationView.setCollect(false);
                } else {
                    toast(returnData.message);
                }
            }
        });
    }
}
