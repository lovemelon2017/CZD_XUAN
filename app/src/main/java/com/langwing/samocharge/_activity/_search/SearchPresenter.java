package com.langwing.samocharge._activity._search;

import com.alibaba.fastjson.JSON;
import com.langwing.samocharge._base.BasePresenter;
import com.langwing.samocharge._fragment._chargingPile.Station;

import java.util.List;

public class SearchPresenter extends BasePresenter implements ISearchContract.ISearchPresenter {

    private ISearchContract.ISearchView searchView;
    private final ISearchContract.SearchModel searchModel;

    public SearchPresenter(ISearchContract.ISearchView searchView) {
        super(searchView);
        this.searchView = searchView;
        searchModel = new ISearchContract.SearchModel();
    }

    @Override
    public void getSearchDetails(String data) {
        if (data.length() == 0) {
            toast("请输入名称");
        } else {
            showWaitDialog("");
            searchModel.getSearchDertails(data, returnData -> {
                dismissWaitDialog();
                if (returnData.status) {
                    List<Station> stationList = JSON.parseArray(returnData.data, Station.class);
                    if (stationList.size() > 0) {
                        searchView.initRvChargingPile(stationList);
                    } else {
                        toast("搜索结果不存在");
                    }
                } else {
                    toast(returnData.message);
                }
            });
        }
    }
}
