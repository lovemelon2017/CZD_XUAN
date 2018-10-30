package com.langwing.samocharge._utils;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * To Change The World
 * 2017-05-15 10:31
 * Created by Mr.Wang
 */

public class VerifyCodeTimeDown extends CountDownTimer {

    private Button btGetVerifyCode;

    public VerifyCodeTimeDown(long millisInFuture, long countDownInterval, Button btGetVerifyCode) {
        super(millisInFuture, countDownInterval);
        this.btGetVerifyCode = btGetVerifyCode;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        btGetVerifyCode.setText(millisUntilFinished / 1000 + "秒");
    }

    @Override
    public void onFinish() {
        btGetVerifyCode.setText("重新获取");
        btGetVerifyCode.setClickable(true);
    }

    public void startNow() {
        btGetVerifyCode.setClickable(false);
        start();
    }
}

