package com.langwing.samocharge._activity._instructions;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseBackActivity;

public class InstructionsActivity extends BaseBackActivity {

    @Override
    public int getLayoutID() {
        return R.layout.activity_instructions;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.instructions);
    }
}
