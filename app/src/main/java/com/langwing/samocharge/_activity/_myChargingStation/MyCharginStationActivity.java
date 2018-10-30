package com.langwing.samocharge._activity._myChargingStation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._intoNet.IntoNetActivity;
import com.langwing.samocharge._base.BaseBackActivity;


public class MyCharginStationActivity extends BaseBackActivity implements View.OnClickListener {

    @Override
    public int getLayoutID() {
        return R.layout.activity_my_chargin_station;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("我的充电桩");
        findViewById(R.id.tv_carring_piles_into_network).setOnClickListener(this);
        findViewById(R.id.tv_put_into_the_net).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, IntoNetActivity.class);
        switch (v.getId()) {
            case R.id.tv_carring_piles_into_network:
                intent.putExtra("title", "携桩入网");
                intent.putExtra("image", R.drawable.personal_network);
                break;
            case R.id.tv_put_into_the_net:
                intent.putExtra("title", "认桩入网");
                intent.putExtra("image", R.drawable.personal_admission);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
