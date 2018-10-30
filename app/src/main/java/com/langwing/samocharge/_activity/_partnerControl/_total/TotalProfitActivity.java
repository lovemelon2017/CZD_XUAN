package com.langwing.samocharge._activity._partnerControl._total;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseBackActivity;

public class TotalProfitActivity extends BaseBackActivity {

    @Override
    public int getLayoutID() {
        return R.layout.activity_total_profit;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("累计收益");
    }
}
