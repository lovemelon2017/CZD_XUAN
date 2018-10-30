package com.langwing.samocharge._utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.langwing.samocharge.R;


/**
 * Created by Mr.Wang on 2015/12/29.
 */
public class ToastUtil {
    public static Toast toast ;

    public static void toast(Context context, String msg) {
        if (toast!=null){
            toast = null;
        }
        toast=new Toast(context);
        View view = LayoutInflater.from(context).inflate(R.layout.toast_ui, null);
        TextView tvToast = (TextView) view.findViewById(R.id.tv_toast);
        tvToast.setText(msg);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void longToast(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        } else {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
