package com.langwing.samocharge._activity._partnerControl;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._partnerControl._details.ProfitDetailsActivity;
import com.langwing.samocharge._activity._partnerControl._qrnh.QRNHActivity;
import com.langwing.samocharge._activity._partnerControl._subscriptionOrder.SubscriptionOrderActivity;
import com.langwing.samocharge._activity._partnerControl._total.TotalProfitActivity;
import com.langwing.samocharge._activity._smoothBeansUse.SmoothBeansUseActivity;
import com.langwing.samocharge._base.BaseBackActivity;
import com.langwing.samocharge._utils.DensityUtil;
import com.langwing.samocharge._utils.URL;

public class PartnerControlActivity extends BaseBackActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private RadioButton rb60;
    private RadioButton rb120;
    private WebView webView;

    @Override
    public int getLayoutID() {
        return R.layout.activity_partner_control;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("合伙人控制台");
        ImageView ivDetails = findViewById(R.id.iv_right);
        ivDetails.setVisibility(View.VISIBLE);
        ivDetails.setImageResource(R.drawable.profit_details);
        ivDetails.setOnClickListener(this);
        findViewById(R.id.tv_total_profit).setOnClickListener(this);
        findViewById(R.id.tv_qrnh).setOnClickListener(this);
        findViewById(R.id.btn_buy).setOnClickListener(this);
        findViewById(R.id.tv_more).setOnClickListener(this);
//        initWebView(URL.LINE_CHAT);
        showBuyDialog();
    }

    private void showBuyDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this, R.style.CustomDialogStyle).create();
        dialog.setCanceledOnTouchOutside(false);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_buy, null, false);
        view.setOnClickListener(v -> {
            toActivity(SmoothBeansUseActivity.class);
            dialog.dismiss();
        });
        Window window = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        window.setGravity(Gravity.TOP);
        layoutParams.y = DensityUtil.dip2px(this, 150);
        window.setAttributes(layoutParams);
        dialog.setView(view);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_right:
                toActivity(ProfitDetailsActivity.class);
                break;
            case R.id.tv_total_profit:
            case R.id.tv_more:
                toActivity(TotalProfitActivity.class);
                break;
            case R.id.tv_qrnh:
                toActivity(QRNHActivity.class);
                break;
            case R.id.btn_buy:
                showBuyDialog2();
                break;
            default:
                break;
        }
    }

    private void showBuyDialog2() {
        AlertDialog dialog = new AlertDialog.Builder(this, R.style.CustomDialogStyle).create();
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_buy_dou_copy, null, false);
        view.findViewById(R.id.iv_fork).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.tv_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                toActivity(SubscriptionOrderActivity.class);
            }
        });
        rb60 = view.findViewById(R.id.rb_60);
        rb120 = view.findViewById(R.id.rb_120);
        rb60.setOnCheckedChangeListener(this);
        rb120.setOnCheckedChangeListener(this);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        window.setAttributes(layoutParams);
        dialog.setView(view, -10, 0, -10, 0);
        dialog.show();
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

    private void initWebView(String url) {
        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);

        // 设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); // 将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        // 缩放操作
        webSettings.setSupportZoom(false); // 支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(false); // 设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); // 隐藏原生的缩放控件

        // 缓存
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 编码
        webSettings.setDefaultTextEncodingName("UTF-8");
        // 自动加载图片
        webSettings.setLoadsImagesAutomatically(true);

        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setHorizontalScrollbarOverlay(true);
        webView.setVerticalScrollBarEnabled(false);
        webView.setVerticalScrollbarOverlay(true);
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl(url);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.trim().startsWith("tel")) {//特殊情况tel，调用系统的拨号软件拨号【<a href="tel:1111111111">1111111111</a>】
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            } else {
                view.loadUrl(url);
            }
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            webView.loadUrl("javascript:document.body.style.paddingLeft=\"8%\"; void 0");
        }
    }
}
