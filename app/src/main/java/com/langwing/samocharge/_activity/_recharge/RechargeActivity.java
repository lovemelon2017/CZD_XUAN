package com.langwing.samocharge._activity._recharge;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._chargingScan.Pile;
import com.langwing.samocharge._activity._chargingStatus.ChargingStatusActivity;
import com.langwing.samocharge._activity._paymentSuccessful.PaymentSuccessfulActivity;
import com.langwing.samocharge._base.BaseBackActivity;

import java.util.ArrayList;
import java.util.List;

public class RechargeActivity extends BaseBackActivity implements IRechargeContract.IRechargeView, View.OnClickListener {

    private List<String> amountList = new ArrayList<>();
    private RecyclerView rvAmount;
    private GridLayoutManager manager;
    private Pile pile;

    @Override
    public int getLayoutID() {
        return R.layout.activity_recharge;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.account_reload);
        initAmount();
        Intent fromItt = getIntent();
        pile = (Pile) fromItt.getSerializableExtra("Pile");
        if (pile != null) {
            AppCompatTextView tvJump = findViewById(R.id.tv_right);
            tvJump.setVisibility(View.VISIBLE);
            tvJump.setText("直接充电");
            tvJump.setOnClickListener(this);
            findViewById(R.id.ll_balance).setVisibility(View.VISIBLE);
        }
        rvAmount = findViewById(R.id.recyclerView);
        manager = new GridLayoutManager(this, 3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position % 4 == 3) {
                    return 3;
                } else {
                    return 1;
                }
            }
        });

        rvAmount.setLayoutManager(manager);
        findViewById(R.id.tv_wechat_Pay).setOnClickListener(this);
        findViewById(R.id.tv_alipay).setOnClickListener(this);
        findViewById(R.id.tv_union_pay).setOnClickListener(this);
        initRvAmount(amountList);
    }

    private void initAmount() {
        amountList.add("50畅通豆");
        amountList.add("100畅通豆");
        amountList.add("200畅通豆");
        amountList.add("500畅通豆");
        amountList.add("1000畅通豆");
        amountList.add("2000畅通豆");
        amountList.add("5000畅通豆");
        amountList.add("10000畅通豆");
    }

    @Override
    public void initRvAmount(List<String> stringList) {
        final AmountAdapter adapter = new AmountAdapter(this, stringList);
        rvAmount.setAdapter(adapter);
        adapter.setOnItemClickListener((view, position) -> adapter.setSelectPosition(position));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                toActivity(ChargingStatusActivity.class, "Pile", pile);
                finish();
                break;
            case R.id.tv_wechat_Pay:
            case R.id.tv_alipay:
            case R.id.tv_union_pay:
                toActivity(PaymentSuccessfulActivity.class);
                finish();
                break;
            default:
                break;
        }
    }
}
