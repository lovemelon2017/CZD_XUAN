package com.langwing.samocharge._activity._recharge;

import com.langwing.samocharge._base.IBasePresenter;
import com.langwing.samocharge._base.IBaseView;

import java.util.List;

/**
 * Created by Wyj
 * on 2017/11/16.
 */

public interface IRechargeContract {

    interface IRechargeView extends IBaseView{
        void initRvAmount(List<String> stringList);
    }

    interface IRechargePresenter extends IBasePresenter{

    }
}
