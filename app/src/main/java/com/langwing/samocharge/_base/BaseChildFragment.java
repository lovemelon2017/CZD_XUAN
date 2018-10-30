package com.langwing.samocharge._base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.langwing.samocharge._utils.ToastUtil;


/**
 * To Change The World
 * 2016-10-09 22:41
 * Created by Mr.Wang
 */

public abstract class BaseChildFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.from(getParentFragment().getContext()).inflate(getLayoutId(), container, false);
        return view;
    }

    @Override
    public void showWaitDialog(String msg) {
        freshDialog(getParentFragment().getContext(), msg);
    }

    @Override
    public void toast(String msg) {
        ToastUtil.toast(getParentFragment().getContext(), msg);
    }
}
