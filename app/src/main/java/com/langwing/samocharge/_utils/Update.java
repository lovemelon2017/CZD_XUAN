package com.langwing.samocharge._utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;

import com.alibaba.fastjson.JSON;
import com.langwing.samocharge.BuildConfig;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;

/**
 * To Change The World
 * 2016/12/6 18:00
 * Created by Mr.Wang
 */
public class Update {
    private Context context;

    public Update(Context context) {
        this.context = context;
        checkNew();
    }

    private Version version;

    private void checkNew() {
        OKHttpUtil.get(URL.API + "/current_app_version/android", new OKHttpUtil.GetData() {
            @Override
            public void GetResponse(ReturnData returnData) {
                if (returnData.status) {
                    version = JSON.parseObject(returnData.data, Version.class);
                    if (version.version > getVersionCode()) {
                        showUpdateDialog();
                    }
                }
            }
        });
    }

    private static final int GET_UNDATAINFO_ERROR = 99;
    private static final int DOWN_ERROR = 98;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GET_UNDATAINFO_ERROR:
                    ToastUtil.toast(context, "获取服务器更新信息失败");
                    break;
                case DOWN_ERROR:
                    ToastUtil.toast(context, "下载新版本失败");
                    break;
            }
        }
    };

    private void showUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("版本升级");
        builder.setMessage("检测到最新版本，请及时更新！");
        //当点确定按钮时从服务器上下载 新的apk 然后安装
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                downLoadApk();
            }
        });
        builder.setNegativeButton("取消", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void downLoadApk() {
        final ProgressDialog pd;    //进度条对话框
        pd = new ProgressDialog(context);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setProgressNumberFormat("%1d MB/%2d MB");
        pd.setMessage("正在下载更新");

        pd.show();
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(version.getUrl(), pd);
                    sleep(2000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    Message msg = new Message();
                    msg.what = GET_UNDATAINFO_ERROR;
                    handler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }

    private float getVersionCode() {
        //获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packInfo.versionCode;
    }

    private boolean isWifiWork() {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (info != null && info.isAvailable()) {
            return true;
        } else {
            return false;
        }
    }

    private static File getFileFromServer(String path, ProgressDialog pd) {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                java.net.URL url = new java.net.URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                //获取到文件的大小
                pd.setMax(conn.getContentLength() / 1024 / 1024 + 1);
                InputStream is = conn.getInputStream();
                File file = new File(Environment.getExternalStorageDirectory(), "samo.apk");
                FileOutputStream fos = new FileOutputStream(file);
                BufferedInputStream bis = new BufferedInputStream(is);
                byte[] buffer = new byte[1024];
                int len;
                int total = 0;
                while ((len = bis.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                    total += len;
                    //获取当前下载量
                    pd.setProgress(total / 1024 / 1024);
                }
                fos.close();
                bis.close();
                is.close();
                return file;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static class Version implements Serializable {

        private static final long serialVersionUID = -6609158833316006862L;
        public int version;
        public String url;

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}