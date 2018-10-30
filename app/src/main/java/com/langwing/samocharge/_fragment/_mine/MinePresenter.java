package com.langwing.samocharge._fragment._mine;

import com.alibaba.fastjson.JSON;
import com.langwing.samocharge._base.BasePresenter;
import com.langwing.samocharge._utils.DD;
import com.langwing.samocharge._utils.ReturnData;
import com.langwing.samocharge._utils.OKHttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * To Change The World
 * 2018-04-04 15:15
 * Created by Mr.Wang
 */
public class MinePresenter extends BasePresenter implements IMineContract.IMinePresenter {

    private IMineContract.IMineView mineView;
    private IMineContract.MineModel mineModel;

    MinePresenter(IMineContract.IMineView mineView) {
        super(mineView);
        this.mineView = mineView;
        mineModel = new IMineContract.MineModel();
        getMe();
        getUnreadMessageQty();
    }

    @Override
    public void getMe() {
        mineModel.getMe(returnData -> {
            if (returnData.status) {
                Me me = JSON.parseObject(returnData.data, Me.class);
                mineView.bindMeInfo(me);
            } else {
                toast(returnData.message);
            }
        });
    }

    @Override
    public void logOut() {
        showWaitDialog("");
        mineModel.logOut(new OKHttpUtil.GetData() {
            @Override
            public void GetResponse(ReturnData returnData) {
                dismissWaitDialog();
                if (returnData.status) {

                } else {
                    toast(returnData.message);
                }
            }
        });
    }

    @Override
    public void getUnreadMessageQty() {
        mineModel.getUnreadMessageQty(returnData -> {
            if (returnData.status) {
                try {
                    JSONObject jsonObject = new JSONObject(returnData.data);
                    int qty = jsonObject.optInt("qty");
                    mineView.bindUnreadQty(qty);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
