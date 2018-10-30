package com.langwing.samocharge._activity._chargingStatus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._chargingScan.Pile;
import com.langwing.samocharge._base.BaseBackActivity;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class ChargingStatusActivity extends BaseBackActivity implements View.OnClickListener, IChargingStatusContract.IChargingStatusView {

    @Override
    public int getLayoutID() {
        return R.layout.activity_charging_status;
    }

    private TextView tvNotice;
    private Button btCharge;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.charging);
        pile = (Pile) getIntent().getSerializableExtra("Pile");
        findViewById(R.id.bt_charging).setOnClickListener(this);
        presenter = new ChargingStatusPresenter(this);
        btCharge = findViewById(R.id.bt_charging);
        btCharge.setText("开始充电");
        tvNotice = findViewById(R.id.tv_notice);
        TextView tvName = findViewById(R.id.tv_name);
        tvName.setText(pile.getInfo().getStation().getName());
        presenter = new ChargingStatusPresenter(this);
    }

    Pile pile;
    private IChargingStatusContract.IChargingStatusPresenter presenter;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_charging:
                RequestBody requestBody = new FormBody.Builder()
                        .add("token", pile.getInfo().getCharger().getToken())
                        .add("pay_method", "use-balance")
                        .build();
                presenter.startCharge(pile.getInfo().getStation().getId(), requestBody);
//                toActivity(OrderDetailsActivity.class);
//                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void startCharge() {
        tvNotice.setText("正在加速充电中,请耐心等待...");
        btCharge.setText("结束充电");
    }
}