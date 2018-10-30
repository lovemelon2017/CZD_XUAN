package com.langwing.samocharge._activity._stationNews;

import com.langwing.samocharge._base.BasePresenter;

public class StationNewsPresenter extends BasePresenter implements IStationNewsContract.IStationNewsPresenter {

    private IStationNewsContract.IStationNewsView iStationNewsView;
    private IStationNewsContract.StaionNewsModel model;

    public StationNewsPresenter(IStationNewsContract.IStationNewsView iStationNewsView) {
        super(iStationNewsView);
        this.iStationNewsView = iStationNewsView;
        model = new IStationNewsContract.StaionNewsModel();
    }

    @Override
    public void getNews(String type) {
        showWaitDialog("");
        model.getNews(type, returnData -> {
            dismissWaitDialog();
            if (returnData.status) {

            } else {
                toast(returnData.message);
            }
        });
    }
}
