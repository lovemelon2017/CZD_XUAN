package com.langwing.samocharge._activity._accountCertified;

import com.alibaba.fastjson.JSON;
import com.langwing.samocharge._base.BasePresenter;
import com.langwing.samocharge._fragment._mine.Me;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AccountCertifiedPresenter extends BasePresenter implements IAccountCertifiedContract.IAccountCertifiedPresenter {

    private IAccountCertifiedContract.IAccountCertifiedView iAccountCertifiedView;
    private IAccountCertifiedContract.AccountCertifiedModel model;

    public AccountCertifiedPresenter(IAccountCertifiedContract.IAccountCertifiedView iAccountCertifiedView) {
        super(iAccountCertifiedView);
        this.iAccountCertifiedView = iAccountCertifiedView;
        model = new IAccountCertifiedContract.AccountCertifiedModel();
    }

    @Override
    public void uploadIdentifyInfo(String name, String idCard, Map<String, File> imageMap) {
        if (idCard.length() != 18) {
            toast("请输入正确的身份证号");
        } else {
            showWaitDialog("正在上传");
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            if (imageMap != null) {
                for (Map.Entry<String, File> entry : imageMap.entrySet()) {
                    builder.addFormDataPart(entry.getKey(), entry.getValue().getName(), RequestBody.create(MediaType.parse("image/png"), entry.getValue()));
                }
            }
            builder.addFormDataPart("real_name", name);
            builder.addFormDataPart("id_card_number", idCard);
            MultipartBody body = builder.build();
            model.uploadIdentifyInfo(body, returnData -> {
                dismissWaitDialog();
                if (returnData.status) {
                    toast(returnData.message);
                    iAccountCertifiedView.setResult();
                } else {
                    toast(returnData.message);
                }
            });
        }
    }

    @Override
    public void getMe() {
        showWaitDialog("");
        model.getMe(returnData -> {
            dismissWaitDialog();
            if (returnData.status) {
                Me me = JSON.parseObject(returnData.data, Me.class);
                iAccountCertifiedView.bindMeInfo(me);
            } else {
                toast(returnData.message);
            }
        });
    }

}
