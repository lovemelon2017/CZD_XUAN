package com.langwing.samocharge._activity._main;

import android.support.v4.app.Fragment;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BasePresenter;
import com.langwing.samocharge._fragment._chargingPile.StationFragment;
import com.langwing.samocharge._fragment._dynamic.DynamicFragment;
import com.langwing.samocharge._fragment._mine.MineFragment;
import com.langwing.samocharge._fragment._serve.ServeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYJ
 * on 2017/8/8.
 */
public class MainPresenter extends BasePresenter implements IMainContract.IMainPresenter {
    private IMainContract.IMainView mainView;

    MainPresenter(IMainContract.IMainView iMainView) {
        super(iMainView);
        mainView = iMainView;
        initFragmentList();
    }

    @Override
    public void initFragmentList() {
        List<Fragment> fragmentList = new ArrayList<>(4);
        fragmentList.add(new StationFragment());
        fragmentList.add(new ServeFragment());
        fragmentList.add(new DynamicFragment());
        fragmentList.add(new MineFragment());
        mainView.setViewPagerAdapter(fragmentList);
    }

    @Override
    public void setCurrentItem(int checkId) {
        switch (checkId) {
            case R.id.rb_charging_pile:
                mainView.setCurrentItem(0);
                break;
            case R.id.rb_service:
                mainView.setCurrentItem(1);
                break;
            case R.id.rb_dynamic:
                mainView.setCurrentItem(2);
                break;
            case R.id.rb_mine:
                mainView.setCurrentItem(3);
                break;
            default:
                break;
        }
    }

    @Override
    public void setCheckedStateForView(int position) {
        switch (position) {
            case 0:
                mainView.setCheckId(R.id.rb_charging_pile);
                break;
            case 1:
                mainView.setCheckId(R.id.rb_service);
                break;
            case 2:
                mainView.setCheckId(R.id.rb_dynamic);
                break;
            case 3:
                mainView.setCheckId(R.id.rb_mine);
                break;
            default:
                break;
        }
    }
}