package com.langwing.store._orderDetails;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.langwing.store.BaseActivity;
import com.langwing.store.R;

import java.util.ArrayList;
import java.util.List;

public class GoodsOrderDetailsActivity extends BaseActivity implements View.OnClickListener {

    private List<String> orderStatusList = new ArrayList<>();

    @Override
    public void initView(Bundle savedInstanceState) {
        initData();
        RecyclerView rvTimeline = findViewById(R.id.rv_time_line);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvTimeline.setLayoutManager(manager);
        TimelineAdapter adapter = new TimelineAdapter(this, orderStatusList);
        rvTimeline.setAdapter(adapter);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_goods_order_details;
    }

    private void initData() {
        orderStatusList.add("您的订单快递小哥正在派送，请保持电话畅通");
        orderStatusList.add("您的订单已到达三门峡陕县集中部");
        orderStatusList.add("您的订单已到达三门峡");
        orderStatusList.add("您的订单有郑州邮政局揽收，正在发往三门峡的路上");
        orderStatusList.add("您的订单已从仓库发出，请求耐心等待");
        orderStatusList.add("您的订单已提交，正在处理中，请耐心等待");
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.iv_back) {
            finish();
        }
    }
}
