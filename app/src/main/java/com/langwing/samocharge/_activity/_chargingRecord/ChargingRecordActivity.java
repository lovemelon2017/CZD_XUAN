package com.langwing.samocharge._activity._chargingRecord;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseBackActivity;

/**
 * Create By WYJ on 2017/12/7.
 */
public class ChargingRecordActivity extends BaseBackActivity {

    @Override
    public int getLayoutID() {
        return R.layout.activity_charging_record;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.chargint_record);
        RecyclerView rvChargingRecord = findViewById(R.id.rv_charging_record);
        rvChargingRecord.setLayoutManager(new LinearLayoutManager(this));
        ChargingRecordAdapter chargingRecordAdapter = new ChargingRecordAdapter(this);
//        rvChargingRecord.setAdapter(chargingRecordAdapter );
    }
}
