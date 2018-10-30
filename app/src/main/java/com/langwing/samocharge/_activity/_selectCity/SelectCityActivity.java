package com.langwing.samocharge._activity._selectCity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseBackActivity;

import java.util.List;

public class SelectCityActivity extends BaseBackActivity implements ISelectCityContract.ISelectCityView {

    private ISelectCityContract.ISelectCityPresenter presenter;
    private RecyclerView rvCity;

    @Override
    public int getLayoutID() {
        return R.layout.activity_select_city;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("选择城市");
        rvCity = findViewById(R.id.rv_select_city);
        rvCity.setLayoutManager(new LinearLayoutManager(this));
        presenter = new SelectCityPresenter(this);
        presenter.getCity();
    }

    @Override
    public void initRvCity(List<City.CitiesBean> citiesBeanList) {
        CityAdapter adapter = new CityAdapter(this,citiesBeanList);
        rvCity.setAdapter(adapter);
        adapter.setOnItemClickListener((view, position) -> {
            City.CitiesBean citiesBean = citiesBeanList.get(position);
            Intent intent = new Intent();
            intent.putExtra("data", citiesBean);
            setResult(Activity.RESULT_OK,intent);
            finish();
        });
    }
}
