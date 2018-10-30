package com.langwing.samocharge._activity._myCollection;

import com.alibaba.fastjson.JSON;
import com.langwing.samocharge._base.BasePresenter;
import com.langwing.samocharge._utils.ReturnData;
import com.langwing.samocharge._fragment._chargingPile.Station;
import com.langwing.samocharge._utils.OKHttpUtil;

import java.util.List;

/**
 * To Change The World
 * 2018-04-17 16:48
 * Created by Mr.Wang
 */
public class MyCollectPresenter extends BasePresenter implements IMyCollectContract.IMyCollectPresenter {
    private IMyCollectContract.IMyCollectView myCollectView;
    private IMyCollectContract.MyCollectModel model;

    MyCollectPresenter(IMyCollectContract.IMyCollectView myCollectView) {
        super(myCollectView);
        this.myCollectView = myCollectView;
        model = new IMyCollectContract.MyCollectModel();
    }

    @Override
    public void getCollect() {
        showWaitDialog("");
        model.getCollect(new OKHttpUtil.GetData() {
            @Override
            public void GetResponse(ReturnData returnData) {
                dismissWaitDialog();
                if (returnData.status) {
                    List<Station> stationList = JSON.parseArray(returnData.data, Station.class);
                    myCollectView.initCollectRV(stationList);
                } else {
                    toast(returnData.message);
                }
            }
        });
    }
}
