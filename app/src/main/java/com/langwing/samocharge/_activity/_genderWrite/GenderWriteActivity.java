package com.langwing.samocharge._activity._genderWrite;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseBackActivity;
import com.langwing.samocharge._view.ClearWriteEditText;

/**
 * Create By WYJ on 2017/12/6.
 */
public class GenderWriteActivity extends BaseBackActivity implements View.OnClickListener {

    private ClearWriteEditText etGender;

    @Override
    public int getLayoutID() {
        return R.layout.activity_gender_write;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.gender_write);
        String gender = getIntent().getStringExtra("gender");
        etGender = findViewById(R.id.et_gender);
        if (0 != gender.length()) {
            etGender.setText(gender);
        }
        etGender.setSelection(gender.length());
        findViewById(R.id.btn_save).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                String gender = etGender.getText().toString().trim();
                if (0 == gender.length()) {
                    toast("请输入姓名");
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("gender", gender);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
                break;
            default:
                break;
        }
    }
}
