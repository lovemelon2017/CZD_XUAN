package com.langwing.samocharge._activity._main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._chargingScan.ChargingScanActivity;
import com.langwing.samocharge._base.BaseActivity;
import com.langwing.samocharge._utils.Update;
import com.langwing.samocharge._view.CSRadioGroup;

import java.util.List;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, IMainContract.IMainView,
        ViewPager.OnPageChangeListener, View.OnClickListener {

    private long currentTime;
    private ViewPager viewPager;
    private CSRadioGroup rgMain;
    private MainPresenter mainPresenter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        viewPager = findViewById(R.id.view_pager);
        rgMain = findViewById(R.id.rg_main);
        rgMain.setOnCheckedChangeListener(this);
        findViewById(R.id.iv_scan).setOnClickListener(this);
        mainPresenter = new MainPresenter(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {
                new Update(this);
            }
        }
    }

    @Override
    public void setViewPagerAdapter(List<Fragment> fragmentList) {
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(mainPagerAdapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(fragmentList.size());
        viewPager.setCurrentItem(0);
    }

    @Override
    public void toChargingScan() {
        toActivity(ChargingScanActivity.class);
    }

    @Override
    public void setCurrentItem(int item) {
        viewPager.setCurrentItem(item, false);
    }

    @Override
    public void setCheckId(int id) {
        rgMain.setCheckedStateForView(id);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        mainPresenter.setCurrentItem(checkedId);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mainPresenter.setCheckedStateForView(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - currentTime > 2000) {
                currentTime = System.currentTimeMillis();
                toast("再按一次退出");
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
//                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 2);
//                } else {
                toChargingScan();
//                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                new Update(this);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    boolean isNeverShow = shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if (!isNeverShow) {
                        toast("APP更新需要存储权限,请开启！");
                        activityFinish();
                    } else {
                        toast("APP更新需要存储权限,请允许！");
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }
                }
            }
        } else if (requestCode == 2) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                toChargingScan();
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    boolean isNeverShow = shouldShowRequestPermissionRationale(Manifest.permission.CAMERA);
                    if (!isNeverShow) {
                        toast("扫码需要相机权限,请开启！");
                    } else {
                        toast("扫码需要相机权限,请允许！");
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 2);
                    }
                }
            }
        }
    }
}