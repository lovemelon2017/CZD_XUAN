package com.langwing.samocharge._activity._smoothBeansUse;

import android.app.AlertDialog;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._partnerControl._subscriptionOrder.SubscriptionOrderActivity;
import com.langwing.samocharge._base.BaseBackActivity;

public class SmoothBeansUseActivity extends BaseBackActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private RadioButton rb60;
    private RadioButton rb120;
    private AlertDialog buyDialog;

    @Override
    public int getLayoutID() {
        return R.layout.activity_smooth_beans_use;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("畅通豆使用攻略");
        findViewById(R.id.btn_buy).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_buy:
                showBuyDialog();
                break;
            default:
                break;
        }
    }

    private void showBuyDialog() {
        if (buyDialog == null) {
            buyDialog = new AlertDialog.Builder(this, R.style.CustomDialogStyle).create();
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_buy_dou_copy, null, false);
            view.findViewById(R.id.iv_fork).setOnClickListener(v -> buyDialog.dismiss());
            view.findViewById(R.id.tv_next).setOnClickListener(v -> {
                buyDialog.dismiss();
                toActivity(SubscriptionOrderActivity.class);
            });
            rb60 = view.findViewById(R.id.rb_60);
            rb120 = view.findViewById(R.id.rb_120);
            rb60.setOnCheckedChangeListener(this);
            rb120.setOnCheckedChangeListener(this);

            Window window = buyDialog.getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.gravity = Gravity.BOTTOM;
            window.setAttributes(layoutParams);
            buyDialog.setView(view, -10, 0, -10, 0);
        }
        buyDialog.show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.rb_60:
                if (isChecked) {
                    rb120.setChecked(false);
                }
                break;
            case R.id.rb_120:
                if (isChecked) {
                    rb60.setChecked(false);
                }
                break;
            default:
                break;
        }
    }

}
