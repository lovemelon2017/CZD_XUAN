package com.langwing.samocharge._base;

import android.support.annotation.StringRes;

import java.io.Serializable;

/**
 * To Change The World
 * 2016-09-06 13:48
 * Created by Mr.Wang
 */
public interface IBaseView {
    void toast(String msg);

    void toast(@StringRes int stringId);

    void showWaitDialog(String msg);

    void dismissWaitDialog();

    void activityFinish();

    void toActivity(Class className);

    void toActivity(Class className, String key, String value);

    void toActivity(Class className, String key, Serializable value);
}
