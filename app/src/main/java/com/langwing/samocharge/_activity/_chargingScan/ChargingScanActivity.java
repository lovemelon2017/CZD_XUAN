package com.langwing.samocharge._activity._chargingScan;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._instructions.InstructionsActivity;
import com.langwing.samocharge._activity._recharge.RechargeActivity;
import com.langwing.samocharge._utils.ReturnData;
import com.langwing.samocharge._utils.DD;
import com.langwing.samocharge._utils.OKHttpUtil;
import com.langwing.samocharge._utils.ToastUtil;
import com.langwing.samocharge._utils.URL;
import com.wang.zxinglibrary.zXing.CaptureActivity;
import com.wang.zxinglibrary.zXing.ViewfinderView;

public class ChargingScanActivity extends CaptureActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private AppCompatTextView tvOpenFlashLight;
    private LinearLayout llInputNumber;
    private ViewfinderView viewfinderView;
    private AppCompatTextView tvTitle;
    private RadioGroup rgScan;
    private boolean isFlashlightOpen = false;
    private AppCompatTextView tvPrompt;
    private EditText etCode;

    @Override
    public int layoutID() {
        return R.layout.activity_charging_scan;
    }

    @Override
    public void initView() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        tvTitle = findViewById(R.id.tv_title);
        tvPrompt = findViewById(R.id.tv_prompt);
        llInputNumber = findViewById(R.id.ll_input_number);
        viewfinderView = findViewById(R.id.viewfinder_view);
        findViewById(R.id.btn_ok).setOnClickListener(this);
        rgScan = findViewById(R.id.rg_scan);
        rgScan.setOnCheckedChangeListener(this);
        tvOpenFlashLight = findViewById(R.id.tv_open_flashlight);
        tvOpenFlashLight.setOnClickListener(this);
        etCode = findViewById(R.id.et_input_number);
        findViewById(R.id.btn_ok).setOnClickListener(this);
    }

    @Override
    public int surfaceViewID() {
        return R.id.preview_view;
    }

    @Override
    public int viewFindViewID() {
        return R.id.viewfinder_view;
    }

    @Override
    public void handleQrContent(String qrContent) {
        freshDialog("");
        OKHttpUtil.get(URL.API + "/scan-code?c=" + qrContent, new OKHttpUtil.GetData() {
            @Override
            public void GetResponse(ReturnData returnData) {
                DD.dd("handleQrContent", qrContent);
                dialog.dismiss();
                if (returnData.status) {
                    Pile pile = JSON.parseObject(returnData.data, Pile.class);
                    Pile.InfoBean.ChargerBean chargerBean = pile.getInfo().getCharger();
                    if (chargerBean.getIs_online() == 1 && chargerBean.getStatus() == 0) {
                        Intent intent = new Intent(ChargingScanActivity.this, RechargeActivity.class);
                        intent.putExtra("Pile", pile);
                        startActivity(intent);
                        finish();
                    } else {
                        ToastUtil.toast(ChargingScanActivity.this, "当前充电状态为：" + chargerBean.getCharger_status() + "，请尝试其他充电桩。");
                    }
                } else {
                    ToastUtil.toast(ChargingScanActivity.this, returnData.message);
                }
                restartPreviewAfterDelay(1500);
            }
        });

//        Intent intent = new Intent(this, RechargeActivity.class);
//        intent.putExtra("qrCode", qrContent);
//        startActivity(intent);
//        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_ok:
                String code = etCode.getText().toString().trim();
                if (code.length() == 0) {
                    ToastUtil.toast(this, "请填写终端号码！");
                } else {
                    handleQrContent(code);
                }
                break;
            case R.id.tv_open_flashlight:
                if (isFlashlightOpen) {
                    cameraManager.setTorch(false);
                    tvOpenFlashLight.setText(R.string.open_flash_light);
                } else {
                    cameraManager.setTorch(true);
                    tvOpenFlashLight.setText(R.string.close_flash_light);
                }
                isFlashlightOpen = !isFlashlightOpen;
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
        switch (checkId) {
            case R.id.rb_scan:
                tvTitle.setText("扫描充电");
                llInputNumber.setVisibility(View.GONE);
                viewfinderView.setVisibility(View.VISIBLE);
                tvPrompt.setVisibility(View.VISIBLE);
                break;
            case R.id.rb_input_number:
                tvTitle.setText("输入终端号码");
                llInputNumber.setVisibility(View.VISIBLE);
                viewfinderView.setVisibility(View.GONE);
                tvPrompt.setVisibility(View.GONE);
                break;
            case R.id.rb_instructions:
                startActivity(new Intent(this, InstructionsActivity.class));
                rgScan.check(R.id.rb_scan);
                break;
            default:
                break;
        }
    }

    private Dialog dialog;

    private void freshDialog(String msg) {
        dialog = new Dialog(this, R.style.FreshWaitDialogStyle);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_fresh, null);
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
}
