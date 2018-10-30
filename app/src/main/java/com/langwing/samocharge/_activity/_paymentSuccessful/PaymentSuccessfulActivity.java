package com.langwing.samocharge._activity._paymentSuccessful;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._chargingStatus.ChargingStatusActivity;
import com.langwing.samocharge._base.BaseBackActivity;

public class PaymentSuccessfulActivity extends BaseBackActivity implements View.OnClickListener {

    @Override
    public int getLayoutID() {
        return R.layout.activity_payment_successful;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("支付成功");
        findViewById(R.id.btn_charging).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_charging:
                toActivity(ChargingStatusActivity.class);
                finish();
                break;
            default:
                break;
        }
    }
}
