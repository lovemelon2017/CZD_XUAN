package com.langwing.samocharge._base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.langwing.samocharge.R;
import com.langwing.samocharge._utils.ToastUtil;

import java.io.Serializable;


/**
 * To Change The World
 * 2016-09-09 10:58
 * Created by Mr.Wang
 */
public abstract class BaseFragment extends Fragment implements IBaseView {

    private View mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.from(getActivity()).inflate(getLayoutId(), container, false);
        }
        initView(mView, savedInstanceState);
        return mView;
    }


    public abstract int getLayoutId();

    public abstract void initView(View view, Bundle savedInstanceState);

    private Dialog dialog;

    public void freshDialog(Context context, String msg) {
        if (dialog != null) {
            dialog = null;
        }
        dialog = new Dialog(context, R.style.FreshWaitDialogStyle);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_fresh, null);
        if (msg != null) {
            TextView tvMessage =  view.findViewById(R.id.tv_fresh_dialog_message);
            tvMessage.setText(msg);
        }
        ImageView iv =  view.findViewById(R.id.iv_dialog_fresh);
        Animation animation = new RotateAnimation(0, 1080, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(2000);
        animation.setRepeatCount(Animation.INFINITE);
        LinearInterpolator lir = new LinearInterpolator();
        animation.setInterpolator(lir);
        iv.startAnimation(animation);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void toast(String msg) {
        ToastUtil.toast(getContext(), msg);
    }

    @Override
    public void toast(@StringRes int stringId) {
        toast(getString(stringId));
    }

    @Override
    public void showWaitDialog(String msg) {
        freshDialog(getContext(), msg);
    }

    @Override
    public void dismissWaitDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public void activityFinish() {
        getActivity().finish();
    }

    @Override
    public void toActivity(Class className) {
        Intent toItt = new Intent(getContext(), className);
        startActivity(toItt);
    }

    @Override
    public void toActivity(Class className, String key, String value) {
        Intent toItt = new Intent(getContext(), className);
        toItt.putExtra(key, value);
        startActivity(toItt);
    }

    @Override
    public void toActivity(Class className, String key, Serializable value) {
        Intent toItt = new Intent(getContext(), className);
        toItt.putExtra(key, value);
        startActivity(toItt);
    }
}