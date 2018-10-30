package com.langwing.samocharge._activity._accountCertified;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.langwing.samocharge.BuildConfig;
import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._personalInfo.SelectAvatarDialog;
import com.langwing.samocharge._base.BaseBackActivity;
import com.langwing.samocharge._fragment._mine.Me;
import com.langwing.samocharge._utils.Constants;
import com.langwing.samocharge._utils.ImageLoadUtil;
import com.langwing.samocharge._utils.PhotoCompressUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AccountCertifiedActivity extends BaseBackActivity implements View.OnClickListener, IAccountCertifiedContract.IAccountCertifiedView {

    private AppCompatEditText etName;
    private AppCompatEditText etIdCard;
    private IAccountCertifiedContract.IAccountCertifiedPresenter presenter;
    private AppCompatImageView ivIdCardFront;
    private AppCompatImageView ivIdCardBack;
    private Map<String, File> imageMap = new HashMap<>();
    private final int IDCARD_FRONT_ALBUM = 0x001;
    private final int IDCARD_FRONT_CAMERA = 0x002;
    private final int IDCARD_BACK_ALBUM = 0x003;
    private final int IDCARD_BACK_CAMERA = 0x004;
    private SelectAvatarDialog selectAvatarDialog;

    @Override
    public int getLayoutID() {
        return R.layout.activity_account_certified;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("实名认证");
        etName = findViewById(R.id.et_name);
        etIdCard = findViewById(R.id.et_id_card);
        ivIdCardFront = findViewById(R.id.iv_idcard_front);
        ivIdCardBack = findViewById(R.id.iv_idcard_back);
        ivIdCardFront.setOnClickListener(this);
        ivIdCardBack.setOnClickListener(this);
        findViewById(R.id.bt_upload).setOnClickListener(this);
        presenter = new AccountCertifiedPresenter(this);
        presenter.getMe();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_upload:
                String name = etName.getText().toString().trim();
                String idCard = etIdCard.getText().toString().trim();
                presenter.uploadIdentifyInfo(name, idCard, imageMap);
                break;
            case R.id.iv_idcard_front:
                selectAvatarDialog = new SelectAvatarDialog();
                selectAvatarDialog.show(getSupportFragmentManager(), "IdCardFront");
                selectAvatarDialog.setImageView(ivIdCardFront);
                break;
            case R.id.iv_idcard_back:
                selectAvatarDialog = new SelectAvatarDialog();
                selectAvatarDialog.show(getSupportFragmentManager(), "IdCardBack");
                selectAvatarDialog.setImageView(ivIdCardBack);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int id = selectAvatarDialog.getImageView().getId();
        switch (requestCode) {
            case Constants.ALBUM_SELECTION_INTENT_CODE:
                if (R.id.iv_idcard_front == id) {
                    if (resultCode == Activity.RESULT_OK && null != data) {
                        getImageByAlbum(data, IDCARD_FRONT_ALBUM);
                    }
                } else {
                    if (resultCode == Activity.RESULT_OK && null != data) {
                        getImageByAlbum(data, IDCARD_BACK_ALBUM);
                    }
                }
                break;
            case Constants.TAKE_PHOTO_INTENT_CODE:
                if (R.id.iv_idcard_front == id) {
                    getImageByCamera(IDCARD_FRONT_CAMERA);
                } else {
                    getImageByCamera(IDCARD_BACK_CAMERA);
                }
                break;
            default:
                break;
        }
    }

    private void getImageByCamera(int what) {
        Uri cameraUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            cameraUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileProvider", selectAvatarDialog.takePhotoUtil.getCameraFile());
        } else {
            cameraUri = Uri.fromFile(selectAvatarDialog.takePhotoUtil.getCameraFile());
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
                        message.what = what;
                        handler.sendMessage(message);
                    }).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void getImageByAlbum(Intent data, int what) {
        Uri uri = data.getData();
        if (null != uri) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                if (null != bitmap) {
                    showWaitDialog("正在处理");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            File albumFile = PhotoCompressUtil.compressBitmap(bitmap);
                            Message message = Message.obtain();
                            message.what = what;
                            message.obj = albumFile;
                            handler.sendMessage(message);
                        }
                    }).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Bundle extras = data.getExtras();
            if (null != extras) {
                Bitmap bitmap = extras.getParcelable("data");
                if (null != bitmap) {
                    showWaitDialog("正在处理");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            File albumFile = PhotoCompressUtil.compressBitmap(bitmap);
                            Message message = Message.obtain();
                            message.what = what;
                            message.obj = albumFile;
                            handler.sendMessage(message);
                        }
                    }).start();
                }
            }
        }
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            dismissWaitDialog();
            File file = (File) msg.obj;
            switch (msg.what) {
                case IDCARD_FRONT_ALBUM:
                case IDCARD_FRONT_CAMERA:
                    imageMap.put("id_card_f", file);
                    ImageLoadUtil.loadImageFile(ivIdCardFront, file);
                    break;
                case IDCARD_BACK_ALBUM:
                case IDCARD_BACK_CAMERA:
                    imageMap.put("id_card_b", file);
                    ImageLoadUtil.loadImageFile(ivIdCardBack, file);
                    break;
                default:
                    break;
            }
            return false;
        }
    });

    @Override
    public void bindMeInfo(Me me) {
        if (null != me) {
            String name = me.getReal_name();
            String idCardNumber = me.getId_card_number();
            String idCardPathF = me.getId_card_path_f();
            String idCardPathB = me.getId_card_path_b();
            if (name.length() > 0) {
                etName.setText(name);
                etName.setSelection(name.length());
            }
            if (idCardNumber.length() > 0) {
                etIdCard.setText(idCardNumber);
                etIdCard.setSelection(idCardNumber.length());
            }
            if (idCardPathF.length() > 0) {
                ImageLoadUtil.loadImgURL(ivIdCardFront, idCardPathF);
            }
            if (idCardPathB.length() > 0) {
                ImageLoadUtil.loadImgURL(ivIdCardBack, idCardPathB);
            }
        }
    }

    @Override
    public void setResult() {
        setResult(Activity.RESULT_OK, new Intent());
        finish();
    }
}
