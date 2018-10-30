package com.langwing.samocharge._fragment._chargingPile._list;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSON;
import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseFragment;
import com.langwing.samocharge._fragment._chargingPile.Station;
import com.langwing.samocharge._utils.DD;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("ValidFragment")
public class ListFragment extends BaseFragment implements IListContract.IListView, RadioGroup.OnCheckedChangeListener {

    private IListContract.IListPresenter presenter;
    private RecyclerView rvChargingPile;
    private List<Station> stationList;
    private ListAdapter stationAdapter;

    public ListFragment() {
    }

    @SuppressLint("ValidFragment")
    public ListFragment(List<Station> stationList) {
        this.stationList = stationList;
    }

    public void setStationList(List<Station> stations) {
        DD.dd("setStationList", JSON.toJSONString(stationList));
        if (stationAdapter == null) {
            stationList = new ArrayList<>(stations);
        } else {
            stationList.clear();
            stationList.addAll(stations);
            stationAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        rvChargingPile = view.findViewById(R.id.rv_station);
        presenter = new ListPresenter(this);
        initRvChargingPile(stationList);
        RadioGroup radioGroup = view.findViewById(R.id.rg_station_type);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void initRvChargingPile(List<Station> stationList) {
        rvChargingPile.setLayoutManager(new LinearLayoutManager(getActivity()));
        stationAdapter = new ListAdapter(getParentFragment().getContext(), stationList);
        rvChargingPile.setAdapter(stationAdapter);
        stationAdapter.setSort(1);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_nearest:
                stationAdapter.setSort(1);
                break;
            case R.id.rb_free:
                stationAdapter.setSort(2);
                break;
            default:
                break;
        }
    }
}