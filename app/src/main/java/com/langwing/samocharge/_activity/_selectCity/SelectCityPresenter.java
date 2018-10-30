package com.langwing.samocharge._activity._selectCity;

import com.alibaba.fastjson.JSON;
import com.langwing.samocharge._base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class SelectCityPresenter extends BasePresenter implements ISelectCityContract.ISelectCityPresenter {

    private ISelectCityContract.ISelectCityView selectCityView;
    private ISelectCityContract.SelectCityModel selectCityModel;

    public SelectCityPresenter(ISelectCityContract.ISelectCityView selectCityView) {
        super(selectCityView);
        this.selectCityView = selectCityView;
        selectCityModel = new ISelectCityContract.SelectCityModel();
    }

    @Override
    public void getCity() {
        showWaitDialog("");
        selectCityModel.getCity(returnData -> {
            dismissWaitDialog();
            if (returnData.status) {
                List<City> provinceList = JSON.parseArray(returnData.data, City.class);
                List<City.CitiesBean> citiesBeanList = new ArrayList<>();
                for (int i = 0; i < provinceList.size(); i++) {
                    String provinceName = provinceList.get(i).getName();
                    int provinceId = provinceList.get(i).getId();
                    List<City.CitiesBean> cities = provinceList.get(i).getCities();
                    for (int j = 0; j < cities.size(); j++) {
                        City.CitiesBean citiesBean = cities.get(j);
                        citiesBean.setProvinceId(provinceId);
                        citiesBean.setProvinceName(provinceName);
                        if (j == 0) {
                            citiesBean.setFirtCityInProvince(true);
                        }
                        citiesBeanList.add(citiesBean);
                    }
                }
                selectCityView.initRvCity(citiesBeanList);
            } else {
                toast(returnData.message);
                activityFinish();
            }
        });
    }
}
