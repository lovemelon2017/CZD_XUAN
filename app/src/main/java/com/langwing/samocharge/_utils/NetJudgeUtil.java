package com.langwing.samocharge._utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.langwing.samocharge.ChargingApplication;


/**
 * 2016-06-18 12:28
 * Created by Mr.Wang
 */
public class NetJudgeUtil {

    /**
     * 是否有网络
     *
     * @return
     */
    public static boolean isNetWork() {
        ConnectivityManager manager = (ConnectivityManager) ChargingApplication.application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isAvailable();
    }

    /**
     * 是否有可用WiFi
     *
     * @return
     */
    public static boolean isWifiWork() {
        ConnectivityManager manager = (ConnectivityManager) ChargingApplication.application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return info != null && info.isConnected();
    }

    /**
     * 是否有可用的移动网络
     *
     * @return
     */
    public static boolean isMobileWork() {
        ConnectivityManager manager = (ConnectivityManager) ChargingApplication.application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return info != null && info.isConnected();
    }

    /**
     * 那种类型的网络在线
     * -1为其他移动网络在线
     * 1为WiFi在线
     * 0为其他在线 例如和电脑共享网络
     *
     * @return
     */
    public static int getConnetType() {
        ConnectivityManager manager = (ConnectivityManager) ChargingApplication.application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            switch (info.getType()) {
                case ConnectivityManager.TYPE_MOBILE:
                    return -1;
                case ConnectivityManager.TYPE_WIFI:
                    return 1;
                default:
                    return 0;
            }
        }
        return -1;
    }

}
