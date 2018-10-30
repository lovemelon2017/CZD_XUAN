package com.langwing.samocharge._activity._selectCity;

import com.langwing.samocharge._base.IBasePresenter;
import com.langwing.samocharge._base.IBaseView;
import com.langwing.samocharge._utils.OKHttpUtil;
import com.langwing.samocharge._utils.URL;

import java.util.List;

public interface ISelectCityContract {

    interface ISelectCityView extends IBaseView{
        void initRvCity(List<City.CitiesBean> citiesBeanList);
    }

    interface ISelectCityPresenter extends IBasePresenter{
        void getCity();
    }

    class SelectCityModel{
        void getCity(OKHttpUtil.GetData getData) {
            OKHttpUtil.get(URL.API + "/station/city", getData);
        }
    }
}
