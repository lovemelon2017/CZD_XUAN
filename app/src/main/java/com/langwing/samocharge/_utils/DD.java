package com.langwing.samocharge._utils;


import android.util.Log;

import com.langwing.samocharge.BuildConfig;


/**
 * To Change The World
 * 2017-04-06 17:41
 * Created by Mr.Wang
 */

public class DD {
    public static void dd(String tag, String info) {
        if ("debug".equals(BuildConfig.BUILD_TYPE)) {
            Log.e("==" + tag + "==", "---message:" + info);
        }
    }
}
