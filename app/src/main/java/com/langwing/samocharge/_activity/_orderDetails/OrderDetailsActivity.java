package com.langwing.samocharge._activity._orderDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseBackActivity;

public class OrderDetailsActivity extends BaseBackActivity {

    @Override
    public int getLayoutID() {
        return R.layout.activity_order_details;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.order_details);
    }
}
