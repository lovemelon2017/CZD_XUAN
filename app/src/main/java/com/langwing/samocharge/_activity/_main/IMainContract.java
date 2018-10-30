package com.langwing.samocharge._activity._main;


import android.support.v4.app.Fragment;

import com.langwing.samocharge._base.IBasePresenter;
import com.langwing.samocharge._base.IBaseView;

import java.util.List;

/**
 * Created by WYJ
 * on 2017/8/11.
 */

public interface IMainContract {

    interface IMainView extends IBaseView {

        void setCurrentItem(int item);

        void setCheckId(int id);

        void setViewPagerAdapter(List<Fragment> fragmentList);

        void toChargingScan();
    }

    interface IMainPresenter extends IBasePresenter {
        void initFragmentList();

        void setCurrentItem(int checkId);

        void setCheckedStateForView(int position);
    }
}
