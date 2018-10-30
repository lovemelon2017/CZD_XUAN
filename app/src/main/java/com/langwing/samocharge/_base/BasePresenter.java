package com.langwing.samocharge._base;


import java.io.Serializable;

/**
 * To Change The World
 * 2016-10-06 15:54
 * Created by Mr.Wang
 */

public class BasePresenter implements IBasePresenter {
    private IBaseView baseView;

    public BasePresenter(IBaseView baseView) {
        this.baseView = baseView;
    }

    @Override
    public void activityFinish() {
        baseView.activityFinish();
    }

    @Override
    public void toast(String msg) {
        baseView.toast(msg);
    }

    @Override
    public void showWaitDialog(String msg) {
        baseView.showWaitDialog(msg);
    }

    @Override
    public void dismissWaitDialog() {
        baseView.dismissWaitDialog();
    }

    @Override
    public void toActivity(Class className) {
        baseView.toActivity(className);
    }

    @Override
    public void toActivity(Class className, String key, String value) {
        baseView.toActivity(className, key, value);
    }

    @Override
    public void toActivity(Class className, String key, Serializable value) {
        baseView.toActivity(className, key, value);

    }
}
