package com.langwing.samocharge._activity._scanCodeDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._chargingStatus.ChargingStatusActivity;
import com.langwing.samocharge._activity._recharge.AmountAdapter;
import com.langwing.samocharge._base.BaseBackActivity;

import java.util.ArrayList;
import java.util.List;

public class ScanCodeDetailsActivity extends BaseBackActivity implements IScanCodeDetailsContract.IScanCodeDetailsView, View.OnClickListener {

    private List<String> amountList = new ArrayList<>();
    private List<String> electricityList = new ArrayList<>();
    private RecyclerView rvAmount;
    private RecyclerView rvElectricity;

    @Override
    public int getLayoutID() {
        return R.layout.activity_scan_code_details;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        initAmount();
        initElectricity();
        rvAmount = findViewById(R.id.iv_amount);
        rvAmount.setLayoutManager(new GridLayoutManager(this, 3));
        rvElectricity = findViewById(R.id.rv_electricity);
        rvElectricity.setLayoutManager(new GridLayoutManager(this, 3));
        findViewById(R.id.btn_start_charging).setOnClickListener(this);
        initRvAmount();
        initRvElectricity();
    }

    private void initElectricity() {
        electricityList.add("50%");
        electricityList.add("60%");
        electricityList.add("80%");
        electricityList.add("100%");
    }

    private void initAmount() {
        amountList.add("5元");
        amountList.add("15元");
        amountList.add("30元");
        amountList.add("50元");
        amountList.add("100元");
    }

    @Override
    public void initRvAmount() {
        AmountAdapter amountAdapter = new AmountAdapter(this, amountList);
        rvAmount.setAdapter(amountAdapter);
        amountAdapter.setOnItemClickListener((view, position) -> amountAdapter.setSelectPosition(position));
    }

    @Override
    public void initRvElectricity() {
        ElectricityAdapter electricityAdapter = new ElectricityAdapter(this, electricityList);
        rvElectricity.setAdapter(electricityAdapter);
        electricityAdapter.setOnItemClickListener((view, position) -> electricityAdapter.setSelectPosition(position));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_charging:
                toActivity(ChargingStatusActivity.class);
                break;
            default:
                break;
        }
    }
}