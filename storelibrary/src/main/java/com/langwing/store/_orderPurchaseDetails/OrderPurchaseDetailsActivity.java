package com.langwing.store._orderPurchaseDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.langwing.store.BaseActivity;
import com.langwing.store.BaseRecyclerViewAdapter;
import com.langwing.store.R;
import com.langwing.store._orderDetails.CustomerLinearLayoutManager;
import com.langwing.store._orderDetails.GoodsOrderDetailsActivity;
import com.langwing.store._shippingAddress.ShippingAddressActivity;

public class OrderPurchaseDetailsActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public void initView(Bundle savedInstanceState) {
        RecyclerView rvPaymentMethod = findViewById(R.id.recyclerView);
        CustomerLinearLayoutManager manager = new CustomerLinearLayoutManager(this);
        manager.setScrollEnable(false);
        rvPaymentMethod.setLayoutManager(manager);
        final PaymentMethodAdapter adapter = new PaymentMethodAdapter();
        rvPaymentMethod.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                adapter.setSelectPosition(position);
            }
        });
        findViewById(R.id.tv_edited).setOnClickListener(this);
        findViewById(R.id.tv_update).setOnClickListener(this);
        findViewById(R.id.btn_buy).setOnClickListener(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_order_purchase_details;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (R.id.tv_edited == id) {
            startActivity(new Intent(this, ShippingAddressActivity.class));
        } else if (R.id.tv_update == id) {
            startActivity(new Intent(this, ShippingAddressActivity.class));
        } else if (R.id.btn_buy == id) {
            startActivity(new Intent(this, GoodsOrderDetailsActivity.class));
        }
    }
}
