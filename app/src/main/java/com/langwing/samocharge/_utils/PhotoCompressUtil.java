package com.langwing.samocharge._utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * To Change The World
 * 2017-10-18 14:24
 * Created by Mr.Wang
 */

public class PhotoCompressUtil {

    public static File compressImage(File imgFile) {
        Bitmap bitmap = getBitmap(imgFile.getPath());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 500) { // 循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            options -= 10;// 每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            long length = baos.toByteArray().length;
        }
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/moduleImage/" + System.currentTimeMillis() + ".jpg");
        file.getParentFile().mkdirs();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            try {
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        recycleBitmap(bitmap);
        return file;
    }

    public static File compressBitmap(Bitmap bitmap) {
        if (null != bitmap) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
            int options = 100;
            while (baos.toByteArray().length / 1024 > 500) { // 循环判断如果压缩后图片是否大于500kb,大于继续压缩
                baos.reset();// 重置baos即清空baos
                options -= 10;// 每次都减少10
                bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
                long length = baos.toByteArray().length;
            }
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/moduleImage/" + System.currentTimeMillis() + ".jpg");
            file.getParentFile().mkdirs();
            try {
                FileOutputStream fos = new FileOutputStream(file);
                try {
                    fos.write(baos.toByteArray());
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {

                e.printStackTrace();
            }
            recycleBitmap(bitmap);
            return file;
        }else {
            return null;
        }
    }

    private static Bitmap getBitmap(String imgPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = false;
        newOpts.inPurgeable = true;
        newOpts.inInputShareable = true;
        // Do not compress
        newOpts.inSampleSize = 1;
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(imgPath, newOpts);
    }

    private static void recycleBitmap(Bitmap bitmap) {
        if (null != bitmap && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }
}
