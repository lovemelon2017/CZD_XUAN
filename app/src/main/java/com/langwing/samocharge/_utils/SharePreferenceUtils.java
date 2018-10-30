package com.langwing.samocharge._utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.langwing.samocharge.ChargingApplication;


/**
 * To Change The World
 * 2017/5/4 12:27
 * Created by Mr.Wang
 */
public class SharePreferenceUtils {
    /*
 * String key:键
 * String defaultValue:默认返回值
 *
 * 默认返回String类型,可通过parse解析为需要得到的相应类型
  */
    public static String readString(String key, String defaultValue) {
        SharedPreferences sharedPreferences = ChargingApplication.application.getSharedPreferences("info", Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(key, defaultValue);
        return value;
    }

    /*
   * String key:键
   * String value:从配置文件中读取到的值
   *
   * 默认写入String类型,可通过parse解析把需要传入的值转为String类型
    */
    public static boolean writeString(String key, String value) {
        SharedPreferences sharedPreferences = ChargingApplication.application.getSharedPreferences("info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        boolean isSuccess = editor.commit();
        return isSuccess;
    }


    public static boolean readBoolean(String key, boolean defaultVale) {
        SharedPreferences sharedPreferences = ChargingApplication.application.getSharedPreferences("info", Context.MODE_PRIVATE);
        boolean value = sharedPreferences.getBoolean(key, defaultVale);
        return value;
    }

    public static boolean writeBoolean(String key, boolean value) {
        SharedPreferences sharedPreferences = ChargingApplication.application.getSharedPreferences("info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        boolean isSuccess = editor.commit();
        return isSuccess;
    }

    public static int readInt(String key, int defaultVale) {
        SharedPreferences sharedPreferences = ChargingApplication.application.getSharedPreferences("info", Context.MODE_PRIVATE);
        int value = sharedPreferences.getInt(key, defaultVale);
        return value;
    }

    public static boolean writeInt(String key, int value) {
        SharedPreferences sharedPreferences = ChargingApplication.application.getSharedPreferences("info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        boolean isSuccess = editor.commit();
        return isSuccess;
    }

    /*
    *清除
    * String key:键
    */
    public static boolean clean(String key) {
        SharedPreferences sharedPreferences = ChargingApplication.application.getSharedPreferences("info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        boolean isSuccess = editor.commit();
        return isSuccess;
    }
}
