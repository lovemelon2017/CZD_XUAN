package com.langwing.samocharge._utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.widget.ImageView;

import com.langwing.samocharge.BuildConfig;
import com.langwing.samocharge._base.BaseActivity;

import java.io.File;

/**
 * To Change The World
 * 2017-10-16 17:01
 * Created by Mr.Wang
 */

public class TakePhotoUtil {
    private File cameraFile;
    private ImageView imageView;

    public File getCameraFile() {
        return cameraFile;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TakePhotoUtil(ImageView imageView) {
        this.imageView = imageView;
        cameraFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/moduleImage/" + System.currentTimeMillis() + ".jpg");
        cameraFile.getParentFile().mkdirs();
    }

    public void takePhoto(Context context) {
        Intent camItt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= 24) {
            Uri photoUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", cameraFile);
            camItt.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            camItt.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            camItt.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraFile));
        }
        ((BaseActivity) context).startActivityForResult(camItt, Constants.TAKE_PHOTO_INTENT_CODE);
    }

    public void albumSelection(Context context){
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        ((BaseActivity) context).startActivityForResult(intent, Constants.ALBUM_SELECTION_INTENT_CODE);
    }
}
