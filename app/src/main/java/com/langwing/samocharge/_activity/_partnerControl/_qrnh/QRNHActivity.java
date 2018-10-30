package com.langwing.samocharge._activity._partnerControl._qrnh;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseBackActivity;

public class QRNHActivity extends BaseBackActivity {

    @Override
    public int getLayoutID() {
        return R.layout.activity_qrnh;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("七日年华");
    }
}
