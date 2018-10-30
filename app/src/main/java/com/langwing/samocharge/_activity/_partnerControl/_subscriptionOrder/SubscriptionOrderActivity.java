package com.langwing.samocharge._activity._partnerControl._subscriptionOrder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._uploadVoucher.UploadVoucherActivity;
import com.langwing.samocharge._base.BaseBackActivity;

public class SubscriptionOrderActivity extends BaseBackActivity {


    @Override
    public int getLayoutID() {
        return R.layout.activity_subscription_order;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("认购订单");
        findViewById(R.id.btn_upload_voucher).setOnClickListener(
                v -> toActivity(UploadVoucherActivity.class));
    }
}
