package com.langwing.samocharge._activity._uploadVoucher;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.langwing.samocharge.BuildConfig;
import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._main.MainActivity;
import com.langwing.samocharge._activity._personalInfo.PersonalInfoActivity;
import com.langwing.samocharge._activity._watchBigPicture.WatchPictureActivity;
import com.langwing.samocharge._base.BaseBackActivity;
import com.langwing.samocharge._utils.Constants;
import com.langwing.samocharge._utils.DD;
import com.langwing.samocharge._utils.DensityUtil;
import com.langwing.samocharge._utils.ImageLoadUtil;
import com.langwing.samocharge._utils.PhotoCompressUtil;
import com.langwing.samocharge._utils.TakePhotoUtil;
import com.langwing.samocharge._utils.ToastUtil;

import java.io.File;

public class UploadVoucherActivity extends BaseBackActivity implements View.OnClickListener {

    private AppCompatImageView ivVoucher;
    private TakePhotoUtil takePhotoUtil;
    private AppCompatTextView tvUpload;
    private AppCompatImageView ivClose;
    private Uri cameraUri;
    private FrameLayout flImag;
    private LinearLayout llNotice;
    private AppCompatButton btnNext;
    private LinearLayout llUploadSuccess;
    private AppCompatTextView tvRight;

    @Override
    public int getLayoutID() {
        return R.layout.activity_upload_voucher;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("上传转账凭证");
        tvRight = findViewById(R.id.tv_right);
        tvRight.setText("完成");
        tvRight.setOnClickListener(this);
        ivVoucher = findViewById(R.id.iv_voucher);
        ivVoucher.setOnClickListener(this);
        tvUpload = findViewById(R.id.tv_uplaod);
        ivClose = findViewById(R.id.iv_close);
        ivClose.setOnClickListener(this);
        flImag = findViewById(R.id.fl_iamge);
        llUploadSuccess = findViewById(R.id.ll_upload_succe);
        llNotice = findViewById(R.id.ll_notice);
        btnNext = findViewById(R.id.btn_next);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_voucher:
                if (View.GONE == tvUpload.getVisibility()) {
                    Intent intent = new Intent(this, WatchPictureActivity.class);
                    intent.putExtra("data", cameraUri.toString());
                    startActivity(intent);
                } else {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 102);
                    } else {
                        takePhotoUtil = new TakePhotoUtil(ivVoucher);
                        takePhotoUtil.takePhoto(this);
                    }
                }
                break;
            case R.id.iv_close:
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivVoucher.getLayoutParams();
                params.height = DensityUtil.dip2px(this, 100);
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                params.topMargin = DensityUtil.dip2px(this, 30);
                ivVoucher.setLayoutParams(params);
                ivVoucher.setImageResource(R.drawable.camera);
                tvUpload.setVisibility(View.VISIBLE);
                ivClose.setVisibility(View.GONE);
                break;
            case R.id.btn_next:
                btnNext.setVisibility(View.GONE);
                flImag.setVisibility(View.GONE);
                llNotice.setVisibility(View.GONE);
                llUploadSuccess.setVisibility(View.VISIBLE);
                tvRight.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_right:
                toActivity(MainActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 102) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePhotoUtil = new TakePhotoUtil(ivVoucher);
                takePhotoUtil.takePhoto(this);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    boolean isNeverShow = shouldShowRequestPermissionRationale(Manifest.permission.CAMERA);
                    if (!isNeverShow) {
                        toast("拍照需要相机权限,请开启！");
                    } else {
                        toast("拍照需要相机权限,请允许！");
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 102);
                    }
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.TAKE_PHOTO_INTENT_CODE:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    cameraUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileProvider", takePhotoUtil.getCameraFile());
                } else {
                    cameraUri = Uri.fromFile(takePhotoUtil.getCameraFile());
                }
                if (null != cameraUri) {
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), cameraUri);
                        if (null != bitmap) {
                            showWaitDialog("正在处理");
                            new Thread(() -> {
                                File file = PhotoCompressUtil.compressBitmap(bitmap);
                                Message message = Message.obtain();
                                message.obj = file;
                                message.what = 11;
                                handler.sendMessage(message);
                            }).start();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                break;
            default:
                break;
        }
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            dismissWaitDialog();
            File file = (File) message.obj;
            switch (message.what) {
                case 11:
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivVoucher.getLayoutParams();
                    params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    params.topMargin = 0;
                    ivVoucher.setLayoutParams(params);
                    ImageLoadUtil.loadImageFile(ivVoucher, file);
                    tvUpload.setVisibility(View.GONE);
                    ivClose.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
            return false;
        }
    });
}
