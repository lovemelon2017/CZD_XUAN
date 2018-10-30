package com.langwing.samocharge._base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.langwing.samocharge.R;
import com.langwing.samocharge._utils.DD;
import com.langwing.samocharge._utils.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * To Change The World
 * 2017-04-18 10:49
 * Created by Mr.Wang
 */

public abstract class BaseActivity extends AppCompatActivity implements com.langwing.samocharge._base.IBaseView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutID() != 0) {
            setContentView(getLayoutID());
        }
        initView(savedInstanceState);
        baseActivityList.add(this);
        DD.dd("activityName", getClass().getSimpleName());
    }

    public List<BaseActivity> baseActivityList = new ArrayList<>();

    public abstract
    @LayoutRes
    int getLayoutID();

    public abstract void initView(@Nullable Bundle savedInstanceState);

    public void toActivity(Class className) {
        startActivity(new Intent(this, className));
    }

    @Override
    public void toActivity(Class className, String key, String value) {
        Intent toItt = new Intent(this, className);
        toItt.putExtra(key, value);
        startActivity(toItt);
    }

    @Override
    public void toActivity(Class className, String key, Serializable value) {
        Intent toItt = new Intent(this, className);
        toItt.putExtra(key, value);
        startActivity(toItt);
    }

    private Dialog dialog;

    private void freshDialog(Context context, String msg) {
        if (dialog != null) {
            dialog = null;
        }
        dialog = new Dialog(context, R.style.FreshWaitDialogStyle);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_fresh, null);
        if (msg != null) {
            TextView tvMessage = view.findViewById(R.id.tv_fresh_dialog_message);
            tvMessage.setText(msg);
        }
        ImageView iv = view.findViewById(R.id.iv_dialog_fresh);
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
        ToastUtil.toast(this, msg);
    }

    @Override
    public void toast(@StringRes int stringId) {
        ToastUtil.toast(this, getString(stringId));
    }

    @Override
    public void showWaitDialog(String msg) {
        freshDialog(this, msg);
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
        this.finish();
    }
}
