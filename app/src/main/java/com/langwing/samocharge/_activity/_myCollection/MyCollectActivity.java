package com.langwing.samocharge._activity._myCollection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._chargingStationDetails.StationDetailsActivity;
import com.langwing.samocharge._base.BaseBackActivity;
import com.langwing.samocharge._base.BaseRecyclerViewAdapter;
import com.langwing.samocharge._fragment._chargingPile.Station;
import com.langwing.samocharge._fragment._chargingPile._list.ListAdapter;

import java.util.List;

public class MyCollectActivity extends BaseBackActivity implements IMyCollectContract.IMyCollectView {

    @Override
    public int getLayoutID() {
        return R.layout.activity_my_collect;
    }

    private IMyCollectContract.IMyCollectPresenter presenter;
    private RecyclerView rvCollectStation;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("我的收藏");
        rvCollectStation = findViewById(R.id.rv_collection);
        presenter = new MyCollectPresenter(this);
        presenter.getCollect();
    }

    @Override
    public void initCollectRV(List<Station> stationList) {
        if (stationList != null && stationList.size() != 0) {
            findViewById(R.id.iv_none).setVisibility(View.GONE);
            findViewById(R.id.tv_none).setVisibility(View.GONE);
        }
        rvCollectStation.setLayoutManager(new LinearLayoutManager(this));
        ListAdapter stationAdapter = new ListAdapter(this, stationList);
        rvCollectStation.setAdapter(stationAdapter);
        stationAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Station station = stationList.get(position);
                Intent intent = new Intent(MyCollectActivity.this, StationDetailsActivity.class);
                intent.putExtra("Station", station);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.getCollect();
    }
}
