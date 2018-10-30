package com.langwing.samocharge._activity._aboutUs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._web.WebActivity;
import com.langwing.samocharge._base.BaseBackActivity;
import com.langwing.samocharge._utils.Constants;
import com.langwing.samocharge._utils.URL;

public class AboutUsActivity extends BaseBackActivity implements View.OnClickListener {

    @Override
    public int getLayoutID() {
        return R.layout.activity_about_us;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("关于我们");
        findViewById(R.id.tv_about_us).setOnClickListener(this);
        findViewById(R.id.tv_agreement).setOnClickListener(this);
        findViewById(R.id.tv_private).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_about_us:
                Intent intentAboutUs = new Intent(this, WebActivity.class);
                intentAboutUs.putExtra(Constants.TITLE, "关于我们");
                intentAboutUs.putExtra(Constants.URL, URL.API + "/page/about-us");
                startActivity(intentAboutUs);
                break;
            case R.id.tv_agreement:
                Intent intentAgreement = new Intent(this, WebActivity.class);
                intentAgreement.putExtra(Constants.TITLE, "用户协议");
                intentAgreement.putExtra(Constants.URL, URL.API + "/page/user-agreement");
                startActivity(intentAgreement);
                break;
            case R.id.tv_private:
                Intent intentPrivate = new Intent(this, WebActivity.class);
                intentPrivate.putExtra(Constants.TITLE, "用户协议");
                intentPrivate.putExtra(Constants.URL, URL.API + "/page/policy");
                startActivity(intentPrivate);
                break;
            default:
                break;
        }
    }
}
