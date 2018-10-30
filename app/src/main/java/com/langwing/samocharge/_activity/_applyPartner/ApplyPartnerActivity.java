package com.langwing.samocharge._activity._applyPartner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._auditProcess.AuditProcessActivity;
import com.langwing.samocharge._base.BaseBackActivity;
import com.langwing.samocharge._utils.Constants;
import com.langwing.samocharge._utils.DD;
import com.langwing.samocharge._utils.SharePreferenceUtils;
import com.langwing.samocharge._utils.VerifyCodeTimeDown;

import java.util.List;

public class ApplyPartnerActivity extends BaseBackActivity implements View.OnClickListener,
        IApplyPartnerContract.IApplyPartnerView {

    private TextView tvBaseInfo;
    private AppCompatTextView tvDetailedInfo;
    private AppCompatTextView tvOtherInfo;
    private View hline1;
    private View hline2;
    private RelativeLayout rlBaseInfo;
    private RelativeLayout rlDetailedInfo;
    private RelativeLayout rlOtherInfo;
    private AppCompatButton btnNext;
    private IApplyPartnerContract.IApplyPartnerPresenter presenter;
    private VerifyCodeTimeDown verifyCodeTimeDown;
    private AppCompatButton btnGetVerificationCode;
    private AppCompatEditText etName;
    private AppCompatEditText etIdCard;
    private AppCompatEditText etVerificationCode;
    private AppCompatEditText etDetailHomeAddress;
    private AppCompatTextView tvHomeAddress;
    private AppCompatTextView tvEducation;
    private AlertDialog educationDialog;
    private String[] educations;
    private AlertDialog promptDialog;

    @Override
    public int getLayoutID() {
        return R.layout.activity_apply_partner;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("合伙人申请填写");
        tvBaseInfo = findViewById(R.id.tv_base_info);
        tvDetailedInfo = findViewById(R.id.tv_detailed_info);
        tvOtherInfo = findViewById(R.id.tv_other_info);
        hline1 = findViewById(R.id.line_h1);
        hline2 = findViewById(R.id.line_h2);
        rlBaseInfo = findViewById(R.id.rl_base_info);
        rlDetailedInfo = findViewById(R.id.rl_detailed_info);
        rlOtherInfo = findViewById(R.id.rl_other_info);
        btnNext = findViewById(R.id.btn_next);
        btnNext.setOnClickListener(this);

        // 基本信息
        etName = findViewById(R.id.et_name);
        etIdCard = findViewById(R.id.et_id_card);
        AppCompatTextView tvPhone = findViewById(R.id.tv_phone);
        String phone = SharePreferenceUtils.readString(Constants.PHONE, "");
        tvPhone.setText(phone);
        etVerificationCode = findViewById(R.id.et_verification_code);
        btnGetVerificationCode = findViewById(R.id.btn_verification_code);
        btnGetVerificationCode.setOnClickListener(this);

        // 详细信息
        tvHomeAddress = findViewById(R.id.tv_home_address);
        etDetailHomeAddress = findViewById(R.id.et_detail_home_address);
        tvEducation = findViewById(R.id.tv_education);
        tvEducation.setOnClickListener(this);
        AppCompatEditText etIncome = findViewById(R.id.et_income);

        presenter = new ApplyPartnerPresenter(this);
        presenter.getApplyPartnerParameters();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_verification_code:
//                String phone = etPhone.getText().toString().trim();
//                presenter.getAuthCode(phone);
                break;
            case R.id.btn_next:
                if (View.VISIBLE == rlBaseInfo.getVisibility()) {
                    String name = etName.getText().toString().trim();
                    String idCard = etIdCard.getText().toString().trim();
//                    String mobile = etPhone.getText().toString().trim();
                    String vericaicationCode = etVerificationCode.getText().toString().trim();
//                    presenter.isShowDetailedInfoLayout(name, idCard, mobile, vericaicationCode);
                    showDetailedLayout();
                } else if (View.VISIBLE == rlDetailedInfo.getVisibility()) {
                    String detailHomeAddress = etDetailHomeAddress.getText().toString().trim();
                    presenter.isShowOtherInfoLayout(detailHomeAddress);
                } else if (View.VISIBLE == rlOtherInfo.getVisibility()) {
                    if (promptDialog == null) {
                        View view = LayoutInflater.from(this).inflate(R.layout.dialog_apply_prompt, null, false);
                        view.findViewById(R.id.btn_ok).setOnClickListener(this);
                        promptDialog = new AlertDialog.Builder(this)
                                .setView(view)
                                .create();
                    }
                    promptDialog.show();
                }
                break;
            case R.id.tv_education:
                if (educationDialog == null) {
                    educationDialog = new AlertDialog.Builder(this)
                            .setSingleChoiceItems(educations, -1, (dialog, which) -> {
                                tvEducation.setText(educations[which]);
                                dialog.dismiss();
                            }).create();
                }
                educationDialog.show();
                break;
            case R.id.btn_ok:
                toActivity(AuditProcessActivity.class);
                promptDialog.dismiss();
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public VerifyCodeTimeDown getVerifyCodeTimeDown() {
        if (verifyCodeTimeDown == null) {
            verifyCodeTimeDown = new VerifyCodeTimeDown(60000, 1000, btnGetVerificationCode);
        }
        return verifyCodeTimeDown;
    }

    @Override
    public void showDetailedLayout() {
        tvBaseInfo.setCompoundDrawablesWithIntrinsicBounds(null,
                getResources().getDrawable(R.drawable.apply_right),
                null, null);
        hline1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        tvDetailedInfo.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        tvDetailedInfo.setCompoundDrawablesWithIntrinsicBounds(null,
                getResources().getDrawable(R.drawable.two_select),
                null, null);
        rlBaseInfo.setVisibility(View.GONE);
        rlDetailedInfo.setVisibility(View.VISIBLE);
        rlOtherInfo.setVisibility(View.GONE);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) btnNext.getLayoutParams();
        params.addRule(RelativeLayout.BELOW, R.id.rl_detailed_info);
    }

    @Override
    public void showOtherLayout() {
        tvDetailedInfo.setCompoundDrawablesWithIntrinsicBounds(null,
                getResources().getDrawable(R.drawable.apply_right),
                null, null);
        tvOtherInfo.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        tvOtherInfo.setCompoundDrawablesWithIntrinsicBounds(null,
                getResources().getDrawable(R.drawable.three_select),
                null, null);
        hline2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        rlOtherInfo.setVisibility(View.VISIBLE);
        rlBaseInfo.setVisibility(View.GONE);
        rlDetailedInfo.setVisibility(View.GONE);
        btnNext.setText("提交");
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) btnNext.getLayoutParams();
        params.addRule(RelativeLayout.BELOW, R.id.rl_other_info);
    }

    @Override
    public void setApplyPartnerParameters(ApplyPartnerParameters partnerParameters) {
        // 学历
        List<ApplyPartnerParameters.EduLevelBean> eduLevelList = partnerParameters.getEdu_level();
        educations = new String[eduLevelList.size()];
        for (int i = 0; i < eduLevelList.size(); i++) {
            educations[i] = eduLevelList.get(i).getName();
        }
    }

}
