package com.langwing.samocharge._utils;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.langwing.samocharge.ChargingApplication;
import com.langwing.samocharge._activity._login.LoginActivity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * To Change The World
 * 2016-10-26 12:30
 * Created by Mr.Wang
 */

public class OKHttpUtil {

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//                .cache(new Cache(OAApplication.application.getCacheDir(), 10240 * 1024))
//                .connectTimeout(20, TimeUnit.SECONDS)
//                .readTimeout(20, TimeUnit.SECONDS);
//                .addNetworkInterceptor(new CacheInterceptor());
        httpClient = builder.build();
    }

    private static OkHttpClient httpClient;

    private static String TOKEN;

    public static void setToken(String token) {
        TOKEN = token;
    }

    public static String getToken() {
        if (TOKEN == null) {
            TOKEN = SharePreferenceUtils.readString(Constants.TOKEN, null);
        }
        if (TOKEN == null) {
            Intent ittLogin = new Intent(ChargingApplication.application, LoginActivity.class);
            ittLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ChargingApplication.application.startActivity(ittLogin);
            return "";
        } else {
            return TOKEN;
        }
    }

    public static void get(String url, final GetData getData) {
        DD.dd("get", url);
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Authorization", "Bearer " + getToken())
                .header("Authorization", "Bearer " + getToken())
//                .cacheControl(new CacheControl.Builder().build())
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                requestFailed(e, getData);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                requestSuccess(response, getData);
            }
        });
    }

    public static void post(String url, RequestBody requestBody, final GetData getData) {
        DD.dd("post", url);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + getToken())
                .header("Authorization", "Bearer " + getToken())
                .post(requestBody)
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                requestFailed(e, getData);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                requestSuccess(response, getData);
            }
        });
    }

    public static void put(String url, RequestBody requestBody, final GetData getData) {
        DD.dd("put", url);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + getToken())
                .header("Authorization", "Bearer " + getToken())
                .put(requestBody)
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                requestFailed(e, getData);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                requestSuccess(response, getData);
            }
        });
    }

    public static void delete(String url, final GetData getData) {
        DD.dd("delete", url);
        final Request request = new Request.Builder()
                .url(url)
                .delete()
                .addHeader("Authorization", "Bearer " + getToken())
                .header("Authorization", "Bearer " + getToken())
//                .cacheControl(new CacheControl.Builder().build())
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                requestFailed(e, getData);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                requestSuccess(response, getData);
            }
        });
    }

    public static void postNoToken(String url, RequestBody requestBody, final GetData getData) {
        DD.dd("postNoToken", url);
        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                requestFailed(e, getData);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                requestSuccess(response, getData);
            }
        });
    }

    private static void requestSuccess(Response response, GetData getData) throws IOException {
        if (response.isSuccessful()) {
            String result = response.body().string();
            ReturnData returnData = JSON.parseObject(result, ReturnData.class);
            requestCodeDeal(returnData, getData);
        } else {
            ResponseBody responseBody = response.body();
            String result = responseBody.string();
            if (result != null) {
                ReturnData returnData = JSON.parseObject(result, ReturnData.class);
                requestCodeDeal(returnData, getData);
            } else {
                requestFailed(null, getData);
            }
        }
    }

    private static void requestCodeDeal(ReturnData returnData, GetData getData) {
        switch (returnData.code) {
            case 200:
            case 422:
            case 400:
                giveDataToHandler(returnData, getData);
                break;
            case 401:
                setToken(null);
                getToken();
                break;
            case 500:
                giveDataToHandler(initErrorReturn("服务器内部错误"), getData);
                break;
            default:
                break;
        }
    }

    private static void requestFailed(IOException e, GetData getData) {
        if (!NetJudgeUtil.isNetWork()) {
            giveDataToHandler(initErrorReturn("网络无连接！"), getData);
        } else {
            giveDataToHandler(initErrorReturn("请求失败，请重试！"), getData);
        }
        if (e != null) {
            e.printStackTrace();
        }
    }

    private static ReturnData initErrorReturn(String errorMsg) {
        ReturnData returnData = new ReturnData();
        returnData.setData("");
        returnData.setMessage(errorMsg);
        returnData.setStatus(false);
        return returnData;
    }

    private static void giveDataToHandler(ReturnData returnData, GetData getData) {
        Message msg = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ReturnData", returnData);
        msg.setData(bundle);
        msg.obj = getData;
        uiHandler.sendMessage(msg);
    }

    private static Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            ReturnData returnData = (ReturnData) bundle.getSerializable("ReturnData");
            GetData getData = (GetData) msg.obj;
            getData.GetResponse(returnData);
        }
    };

    public interface GetData {
        void GetResponse(ReturnData returnData);
    }

    public static String getUTF8XMLString(String xml) {
        StringBuffer sb = new StringBuffer();
        sb.append(xml);
        String xmString = "";
        String xmlUTF8 = "";
        try {
            xmString = new String(sb.toString().getBytes("UTF-8"));
            xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return xmlUTF8;
    }

    public static class CacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            Response response1 = response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "max-age=" + 3600 * 24 * 30)   //cache for 30 days
                    .build();
            return response1;
        }
    }
}