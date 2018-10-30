package com.langwing.samocharge._fragment._charging;

import android.support.v4.app.Fragment;

import com.langwing.samocharge._base.IBasePresenter;
import com.langwing.samocharge._base.IBaseView;

/**
 * Created by Wyj
 * on 2017/11/20.
 */

public interface IChargingContract {

    interface IChargingView extends IBaseView {
    }

    interface IChargingPresenter extends IBasePresenter {
        void requestCamera(Fragment fragment);
    }
}
