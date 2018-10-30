package com.langwing.store._goodsDetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.langwing.store.BaseActivity;
import com.langwing.store.R;
import com.langwing.store._orderPurchaseDetails.OrderPurchaseDetailsActivity;

public class GoodsDetailsActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public void initView(Bundle savedInstanceState) {
        findViewById(R.id.tv_buy).setOnClickListener(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_goods_details;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_back) {
            finish();
        } else if (id == R.id.tv_buy) {
            Intent ittDetails = new Intent(this, OrderPurchaseDetailsActivity.class);
            startActivity(ittDetails);
        }
    }
}
