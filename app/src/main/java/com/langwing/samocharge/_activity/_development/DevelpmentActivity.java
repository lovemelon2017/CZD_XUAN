package com.langwing.samocharge._activity._development;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseBackActivity;

public class DevelpmentActivity extends BaseBackActivity {

    @Override
    public int getLayoutID() {
        return R.layout.activity_develpment;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        String name = getIntent().getStringExtra("name");
        setTitle(name);
    }
}
