package com.langwing.samocharge._activity._partnerControl._details;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseBackActivity;

public class ProfitDetailsActivity extends BaseBackActivity {


    @Override
    public int getLayoutID() {
        return R.layout.activity_profit_details;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("明细");
    }
}
