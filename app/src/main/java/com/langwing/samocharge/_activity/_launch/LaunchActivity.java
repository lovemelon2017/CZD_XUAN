package com.langwing.samocharge._activity._launch;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._guidePage.GuidePageActivity;
import com.langwing.samocharge._activity._login.LoginActivity;
import com.langwing.samocharge._activity._main.MainActivity;
import com.langwing.samocharge._base.BaseActivity;
import com.langwing.samocharge._utils.Constants;
import com.langwing.samocharge._utils.SharePreferenceUtils;

/**
 * Created by WYJ
 * on 2017/9/6.
 */

public class LaunchActivity extends BaseActivity {
    @Override
    public int getLayoutID() {
        return R.layout.activity_launch;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        SplashHandler splashHandler = new SplashHandler();
        String token = SharePreferenceUtils.readString(Constants.TOKEN, null);
        Intent intent = new Intent(this, token == null ? GuidePageActivity.class : MainActivity.class);
        Message message = Message.obtain();
        message.obj = intent;
        splashHandler.sendMessageDelayed(message, 2000);
    }

    @SuppressLint("HandlerLeak")
    class SplashHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent intent = (Intent) msg.obj;
            startActivity(intent);
            finish();
        }
    }
}
