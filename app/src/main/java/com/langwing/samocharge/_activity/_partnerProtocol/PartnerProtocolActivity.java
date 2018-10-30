package com.langwing.samocharge._activity._partnerProtocol;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._applyPartner.ApplyPartnerActivity;
import com.langwing.samocharge._base.BaseBackActivity;
import com.langwing.samocharge._utils.OKHttpUtil;
import com.langwing.samocharge._utils.URL;

import java.util.HashMap;
import java.util.Map;

public class PartnerProtocolActivity extends BaseBackActivity implements View.OnClickListener {

    @Override
    public int getLayoutID() {
        return R.layout.activity_partner_protocol;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("萨默合伙人协议");
        findViewById(R.id.btn_read_protocol).setOnClickListener(this);
//        initWebView(URL.PARTNER_PROTOCOL);
    }

    private void initWebView(String url) {
        showWaitDialog("正在加载");
        WebView webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setHorizontalScrollBarEnabled(false);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webView.setInitialScale(70);
        webView.setHorizontalScrollbarOverlay(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//使用缓存
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

        webSettings.setDefaultTextEncodingName("UTF-8");
        webView.setWebViewClient(new MyWebViewClient());
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Authorization", "Bearer " + OKHttpUtil.getToken());
        webView.loadUrl(url, headerMap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_read_protocol:
                toActivity(ApplyPartnerActivity.class);
                finish();
                break;
            default:
                break;
        }
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
            dismissWaitDialog();
        }
    }
}