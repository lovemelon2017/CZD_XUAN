package com.langwing.samocharge._activity._applyForPile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.RadioGroup;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseBackActivity;

public class ApplyForPileActivity extends BaseBackActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private AppCompatImageView ivPile;

    @Override
    public int getLayoutID() {
        return R.layout.activity_apply_for_pile;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("申请建桩");
        ivPile = findViewById(R.id.iv_pile);
        RadioGroup rgPile = findViewById(R.id.rg_pile);
        rgPile.setOnCheckedChangeListener(this);
        findViewById(R.id.tv_consumer_hotline).setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_personal_pile:
                ivPile.setImageResource(R.drawable.personal_pile);
                break;
            case R.id.rb_company_pile:
                ivPile.setImageResource(R.drawable.company_pile);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_consumer_hotline:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    callPhone();
                }
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
                callPhone();
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    boolean isNeverShow = shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE);
                    if (!isNeverShow) {
                        toast("拨打电话需要通话权限,请开启！");
                    } else {
                        toast("拨打电话需要通话权限,请允许！");
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE}, 1);
                    }
                }
            }
        }
    }

    private void callPhone() {
        Intent tellIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "400-886-5160"));
        startActivity(tellIntent);
    }

}
