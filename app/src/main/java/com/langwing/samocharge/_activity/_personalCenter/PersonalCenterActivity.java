package com.langwing.samocharge._activity._personalCenter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._accountCertified.AccountCertifiedActivity;
import com.langwing.samocharge._activity._personalInfo.PersonalInfoActivity;
import com.langwing.samocharge._base.BaseBackActivity;

public class PersonalCenterActivity extends BaseBackActivity implements View.OnClickListener {

    @Override
    public int getLayoutID() {
        return R.layout.activity_personal_center;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.persone_center);
        findViewById(R.id.tv_personal_info).setOnClickListener(this);
        findViewById(R.id.tv_verified).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_personal_info:
                Intent intentInfo = new Intent(this, PersonalInfoActivity.class);
                startActivityForResult(intentInfo, 1);
                break;
            case R.id.tv_verified:
                Intent intent = new Intent(this, AccountCertifiedActivity.class);
                startActivityForResult(intent, 1);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            setResult(Activity.RESULT_OK, new Intent());
        }
    }
}
