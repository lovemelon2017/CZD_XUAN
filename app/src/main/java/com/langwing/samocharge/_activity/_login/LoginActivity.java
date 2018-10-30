package com.langwing.samocharge._activity._login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._web.WebActivity;
import com.langwing.samocharge._base.BaseActivity;
import com.langwing.samocharge._utils.Constants;
import com.langwing.samocharge._utils.SharePreferenceUtils;
import com.langwing.samocharge._utils.URL;
import com.langwing.samocharge._utils.VerifyCodeTimeDown;


public class LoginActivity extends BaseActivity implements View.OnClickListener, ILoginContract.ILoginView {

    private ILoginContract.ILoginPresenter loginPresenter;
    private AppCompatEditText etMobile;
    private AppCompatEditText etAuthCode;
    private AppCompatButton btGetAuthCode;

    @Override
    public int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        SharePreferenceUtils.clean(Constants.TOKEN);
        etMobile = findViewById(R.id.et_phone);
        etAuthCode = findViewById(R.id.et_verification_code);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.iv_qq).setOnClickListener(this);
        findViewById(R.id.iv_wechat).setOnClickListener(this);
        findViewById(R.id.iv_zfb).setOnClickListener(this);
        btGetAuthCode = findViewById(R.id.btn_get_verification_code);
        btGetAuthCode.setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.tv_agreement).setOnClickListener(this);
        loginPresenter = new LoginPresenter(this);
        TextView tvAgreement = findViewById(R.id.tv_agreement);
        String remark = "未注册自动注册，登录即表示同意";
        String agreement = "《萨默用户协议》";
        SpannableString spannableString = new SpannableString(remark + agreement);
        LinkedSpan urlSpan = new LinkedSpan(URL.API + "/page/user-agreement");
        spannableString.setSpan(urlSpan, remark.length(), remark.length() + agreement.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvAgreement.setText(spannableString);
        tvAgreement.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.btn_get_verification_code:
                String strMobile = etMobile.getText().toString().trim();
                loginPresenter.getAuthCode(strMobile);
                break;
            case R.id.btn_login:
                strMobile = etMobile.getText().toString().trim();
                String authCode = etAuthCode.getText().toString().trim();
                loginPresenter.login(strMobile, authCode);
                break;
            case R.id.tv_agreement:

                break;
            case R.id.iv_qq:
                break;
            case R.id.iv_wechat:
                break;
            case R.id.iv_zfb:
                break;
            default:
                break;
        }
    }

    VerifyCodeTimeDown verifyCodeTimeDown;

    @Override
    public VerifyCodeTimeDown getVerifyCodeTimeDown() {
        if (verifyCodeTimeDown == null) {
            verifyCodeTimeDown = new VerifyCodeTimeDown(60000, 1000, btGetAuthCode);
        }
        return verifyCodeTimeDown;
    }

    @Override
    public void onBackPressed() {

    }

    private void loginByZfb() {

    }

    private void loginByQQ() {
    }

    private void loginByWechat() {
    }

    @SuppressLint("ParcelCreator")
    private class LinkedSpan extends URLSpan {

        LinkedSpan(String url) {
            super(url);
        }

        @Override
        public void onClick(View widget) {
            Intent intent = new Intent(LoginActivity.this, WebActivity.class);
            intent.putExtra(Constants.TITLE, "萨默用户协议");
            intent.putExtra(Constants.URL, getURL());
            startActivity(intent);
        }
    }

//    public void authV2(View v) {
//        if (TextUtils.isEmpty(PID) || TextUtils.isEmpty(APPID)
//                || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))
//                || TextUtils.isEmpty(TARGET_ID)) {
//            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置PARTNER |APP_ID| RSA_PRIVATE| TARGET_ID")
//                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialoginterface, int i) {
//                        }
//                    }).show();
//            return;
//        }
//
//        /**
//         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
//         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
//         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
//         *
//         * authInfo的获取必须来自服务端；
//         */
//        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
//        Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID, rsa2);
//        String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);
//
//        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
//        String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2);
//        final String authInfo = info + "&" + sign;
//        Runnable authRunnable = new Runnable() {
//
//            @Override
//            public void run() {
//                // 构造AuthTask 对象
//                AuthTask authTask = new AuthTask(LoginActivity.this);
//                // 调用授权接口，获取授权结果
//
//                Map<String, String> result = authTask.authV2(authInfo, true);
//                Message msg = new Message();
//                msg.what = SDK_AUTH_FLAG;
//                msg.obj = result;
//                mHandler.sendMessage(msg);
//            }
//        };
//
//        // 必须异步调用
//        Thread authThread = new Thread(authRunnable);
//        authThread.start();
//    }
}
