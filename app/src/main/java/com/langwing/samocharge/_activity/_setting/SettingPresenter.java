package com.langwing.samocharge._activity._setting;

import com.alibaba.fastjson.JSON;
import com.langwing.samocharge._activity._login.LoginActivity;
import com.langwing.samocharge._base.BasePresenter;
import com.langwing.samocharge._utils.ReturnData;
import com.langwing.samocharge._utils.DD;
import com.langwing.samocharge._utils.OKHttpUtil;

public class SettingPresenter extends BasePresenter implements ISettingContract.ISettingPresenter {

    private ISettingContract.SettingModel model;
    private ISettingContract.ISettingView settingView;

    public SettingPresenter(ISettingContract.ISettingView settingView) {
        super(settingView);
        this.settingView = settingView;
        model = new ISettingContract.SettingModel();
    }

    @Override
    public void logOut() {
        showWaitDialog("");
        model.logOut(returnData -> {
            DD.dd("LOGOUT", JSON.toJSONString(returnData));
            dismissWaitDialog();
            if (returnData.status) {
               toActivity(LoginActivity.class);
               activityFinish();
            } else {
                toast(returnData.message);
            }
        });
    }
}
