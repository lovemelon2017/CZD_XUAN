package com.langwing.samocharge._base;

import java.io.Serializable;

/**
 * To Change The World
 * 2016-10-06 15:55
 * Created by Mr.Wang
 */

public interface IBasePresenter {
    void activityFinish();

    void toast(String msg);

    void showWaitDialog(String msg);

    void dismissWaitDialog();

    void toActivity(Class className);

    void toActivity(Class className, String key, String value);

    void toActivity(Class className, String key, Serializable value);
}
