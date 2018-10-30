package com.langwing.samocharge._activity._personalInfo;

import com.alibaba.fastjson.JSON;
import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._login.LoginActivity;
import com.langwing.samocharge._base.BasePresenter;
import com.langwing.samocharge._utils.ReturnData;
import com.langwing.samocharge._fragment._mine.Me;
import com.langwing.samocharge._utils.DD;
import com.langwing.samocharge._utils.OKHttpUtil;

import java.io.File;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * To Change The World
 * 2018-04-09 15:25
 * Created by Mr.Wang
 */
class PersonalInfoPresenter extends BasePresenter implements IPersonalInfoContract.IPersonalInfoPresenter {
    private IPersonalInfoContract.IPersonalInfoView personalInfoView;
    private IPersonalInfoContract.PersonalInfoModel model;

    PersonalInfoPresenter(IPersonalInfoContract.IPersonalInfoView personalInfoView) {
        super(personalInfoView);
        this.personalInfoView = personalInfoView;
        model = new IPersonalInfoContract.PersonalInfoModel();
    }

    @Override
    public void getMe() {
        model.getMe(returnData -> {
            if (returnData.status) {
                Me me = JSON.parseObject(returnData.data, Me.class);
                personalInfoView.bindMeInfo(me);
            } else {
                toast(returnData.message);
            }
        });
    }

    @Override
    public void uploadAccountInfo(String nickName, int cbId, String birthDate, File avatarFile) {
        String sex;
        switch (cbId) {
            case R.id.rb_male:
                sex = "male";
                break;
            case R.id.rb_female:
                sex = "female";
                break;
            default:
                sex = "unknown";
                break;
        }
        showWaitDialog("正在提交");
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (avatarFile != null) {
            builder.addFormDataPart("avatar_file", avatarFile.getName(), RequestBody.create(MediaType.parse("image/png"), avatarFile));
        }
        builder.addFormDataPart("nickname", nickName);
        builder.addFormDataPart("gender", sex);
        builder.addFormDataPart("birth_date", birthDate);
        MultipartBody body = builder.build();
        model.uploadAccountInfo(body, returnData -> {
            dismissWaitDialog();
            if (returnData.status) {
                toast(returnData.message);
                personalInfoView.setResult();
            } else {
                toast(returnData.message);
            }
        });
    }
}
