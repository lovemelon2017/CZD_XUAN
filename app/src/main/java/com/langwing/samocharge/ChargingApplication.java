package com.langwing.samocharge;

import android.app.Application;

/**
 * Created by WYJ
 * on 2017/9/6.
 */

public class ChargingApplication extends Application {

    public static ChargingApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = ChargingApplication.this;
    }
}
