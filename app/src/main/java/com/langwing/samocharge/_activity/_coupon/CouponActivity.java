package com.langwing.samocharge._activity._coupon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseBackActivity;

public class CouponActivity extends BaseBackActivity implements RadioGroup.OnCheckedChangeListener {

    private int[] imageBeans = new int[]{R.drawable.register_ten, R.drawable.register_twenty, R.drawable.register_fifty};
    //    private int[] iamgeRecharge = new int[]{R.drawable.recharge_gift, R.drawable.bean_fifty, R.drawable.bean_one_hundred,
//            R.drawable.bean_two_hundred, R.drawable.bean_five_hundred};
    private CoumpnAdapter adapter;
    private RelativeLayout rlNoBean;
    private RecyclerView rvCoupon;

    @Override
    public int getLayoutID() {
        return R.layout.activity_coupon;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("畅通豆优惠劵");
        RadioGroup rgCoupon = findViewById(R.id.rg_coupon);
        rgCoupon.setOnCheckedChangeListener(this);
        rvCoupon = findViewById(R.id.rv_coupon);
        rlNoBean = findViewById(R.id.rl_nobean);
        rvCoupon.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CoumpnAdapter(this);
        adapter.setData(imageBeans);
        rvCoupon.setAdapter(adapter);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_bean_coupon:
                adapter.setData(imageBeans);
                rvCoupon.setVisibility(View.VISIBLE);
                rlNoBean.setVisibility(View.GONE);
                break;
            case R.id.rb_recharge:
                rlNoBean.setVisibility(View.VISIBLE);
                rvCoupon.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
}
