package com.langwing.samocharge._utils;

import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.langwing.samocharge.ChargingApplication;

import java.io.File;


/**
 * To Change The World
 * 2017/5/8 15:18
 * Created by Mr.Wang
 */

public class ImageLoadUtil {

    /**
     * @param imageView
     * @param imgPath   图片地址
     */
    public static void loadImgURL(ImageView imageView, String imgPath) {
        Glide.with(ChargingApplication.application)
                .load(URL.API + imgPath)
                .into(imageView);
    }

    /**
     * @param imageView
     * @param imgPath
     * @param defaultDrawableId 默认图片
     */
    public static void loadImgURL(ImageView imageView, String imgPath, int defaultDrawableId) {
        Glide.with(ChargingApplication.application)
                .load(URL.API + imgPath)
//                .error(defaultDrawableId)
                .into(imageView);
    }

    public static void loadImgUrl(ImageView imageView, String path) {
        Glide.with(ChargingApplication.application)
                .load(path)
//                .error(defaultDrawableId)
                .into(imageView);
    }

    /**
     * @param imageView
     * @param uri
     */
    public static void loadImgUri(ImageView imageView, Uri uri) {
        Glide.with(ChargingApplication.application)
                .load(uri)
                .into(imageView);
    }

    public static void loadImageFile(ImageView imageView, File file) {
        if (file != null && file.exists()) {
            Glide.with(ChargingApplication.application)
                    .load(file)
                    .into(imageView);
        }
    }
}
