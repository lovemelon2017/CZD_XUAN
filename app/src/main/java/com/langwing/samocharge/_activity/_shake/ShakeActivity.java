package com.langwing.samocharge._activity._shake;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.amap.api.navi.model.NaviLatLng;
import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._navigation.NavigationActivity;
import com.langwing.samocharge._base.BaseBackActivity;
import com.langwing.samocharge._utils.DD;
import com.langwing.samocharge._utils.ShakeUtils;
import com.wang.zxinglibrary.zXing.BeepManager;

public class ShakeActivity extends BaseBackActivity implements ShakeUtils.OnShakeListener, Animation.AnimationListener {

    @Override
    public int getLayoutID() {
        return R.layout.activity_shake;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("摇一摇");
        beepManager = new BeepManager(this, R.raw.shake);
        ivTop = findViewById(R.id.iv_top);
        ivBottom = findViewById(R.id.iv_bottom);
        ivCenter = findViewById(R.id.iv_center);
        shakeUtils = new ShakeUtils(this);
        shakeUtils.setOnShakeListener(this);
    }

    private ShakeUtils shakeUtils;
    private BeepManager beepManager;
    private ImageView ivTop, ivBottom, ivCenter;

    @Override
    public void onShake() {
        DD.dd("onShake", "onShake");
        Animation animTop = AnimationUtils.loadAnimation(this, R.anim.shake_to_top);
        ivTop.startAnimation(animTop);
        Animation animBottom = AnimationUtils.loadAnimation(this, R.anim.shake_to_bottom);
        ivBottom.startAnimation(animBottom);
        animTop.setAnimationListener(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        beepManager.playBeepSoundAndVibrate();
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        showToNavigationDialog();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    AlertDialog dialogToNavigation;

    private void showToNavigationDialog() {
        if (dialogToNavigation == null) {
            dialogToNavigation = new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("是否导航至最近的充电桩？")
                    .setPositiveButton("确定", (dialogInterface, i) -> {
                        NaviLatLng startNavLatLng = new NaviLatLng(34.760807, 113.706049);
                        NaviLatLng endNavLatLng = new NaviLatLng(34.790983, 113.667671);
                        Intent ittNav = new Intent(this, NavigationActivity.class);
                        ittNav.putExtra("startNavLatLng", startNavLatLng);
                        ittNav.putExtra("endNavLatLng", endNavLatLng);
                        startActivity(ittNav);
                    })
                    .setNegativeButton("取消", null)
                    .create();
            dialogToNavigation.setOnDismissListener(dialogInterface -> shakeUtils.onResume());
        }
        dialogToNavigation.show();
        shakeUtils.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        shakeUtils.onResume();
        beepManager.updatePrefs();
    }

    @Override
    protected void onPause() {
        super.onPause();
        shakeUtils.onPause();
        beepManager.close();
    }
}
