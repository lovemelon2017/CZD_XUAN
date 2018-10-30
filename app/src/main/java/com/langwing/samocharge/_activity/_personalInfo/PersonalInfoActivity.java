package com.langwing.samocharge._activity._personalInfo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.langwing.samocharge.BuildConfig;
import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseBackActivity;
import com.langwing.samocharge._fragment._mine.Me;
import com.langwing.samocharge._utils.Constants;
import com.langwing.samocharge._utils.ImageLoadUtil;
import com.langwing.samocharge._utils.PhotoCompressUtil;

import java.io.File;

/**
 * @desc: 个人信息界面
 * @author：WYJ
 * @date：2017/12/6
 */
public class PersonalInfoActivity extends BaseBackActivity implements View.OnClickListener, IPersonalInfoContract.IPersonalInfoView, SelectTimeDialog.oneSelectDateListener {

    private IPersonalInfoContract.IPersonalInfoPresenter preseenter;
    private AppCompatTextView tvDateOfBirth;
    private AppCompatEditText etNickName;
    private final int ALBUM = 4;
    private final int CAMERA = 5;
    private ImageView ivAvatar;
    private SelectAvatarDialog selectAvatarDialog;
    private File file;
    private RadioGroup rgGender;
    private AppCompatTextView tvPhone;

    @Override
    public int getLayoutID() {
        return R.layout.activity_personal_info;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.personal_info);
        ivAvatar = findViewById(R.id.iv_avatar);
        etNickName = findViewById(R.id.et_nick_name);
        tvDateOfBirth = findViewById(R.id.tv_date_of_birth);
        tvPhone = findViewById(R.id.tv_phone);
        findViewById(R.id.rl_avatar).setOnClickListener(this);
        findViewById(R.id.rl_nick_name).setOnClickListener(this);
        findViewById(R.id.rl_gender).setOnClickListener(this);
        findViewById(R.id.rl_phone).setOnClickListener(this);
        findViewById(R.id.rl_date_of_birth).setOnClickListener(this);
        findViewById(R.id.bt_save).setOnClickListener(this);
        rgGender = findViewById(R.id.rg_gender);
        preseenter = new PersonalInfoPresenter(this);
        preseenter.getMe();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_avatar:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
                } else {
                    selectAvatarDialog = new SelectAvatarDialog();
                    selectAvatarDialog.show(getSupportFragmentManager(), "SelectAvatarDialog");
                    selectAvatarDialog.setImageView(ivAvatar);
                }
                break;
            case R.id.rl_date_of_birth:
                SelectTimeDialog selectTimeDialog = new SelectTimeDialog();
                selectTimeDialog.show(getSupportFragmentManager(), "selectTime");
                selectTimeDialog.setOneSelectDateListener(this);
                break;
            case R.id.bt_save:
                int cbId = rgGender.getCheckedRadioButtonId();
                String nickName = etNickName.getText().toString().trim();
                String birthDate = tvDateOfBirth.getText().toString().trim();
                preseenter.uploadAccountInfo(nickName, cbId, birthDate, file);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.TAKE_PHOTO_INTENT_CODE:
                Uri cameraUri;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    cameraUri = FileProvider.getUriForFile(PersonalInfoActivity.this, BuildConfig.APPLICATION_ID + ".fileProvider", selectAvatarDialog.takePhotoUtil.getCameraFile());
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
                                message.what = CAMERA;
                                handler.sendMessage(message);
                            }).start();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                break;
            case Constants.ALBUM_SELECTION_INTENT_CODE:
                if (resultCode == Activity.RESULT_OK && null != data) {
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
                                        message.what = ALBUM;
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
                                        message.what = ALBUM;
                                        message.obj = albumFile;
                                        handler.sendMessage(message);
                                    }
                                }).start();
                            }
                        }
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
            file = (File) message.obj;
            switch (message.what) {
                case ALBUM:
                case CAMERA:
                    ImageLoadUtil.loadImageFile(ivAvatar, file);
                    break;
                default:
                    break;
            }
            return false;
        }
    });

    @Override
    public void bindMeInfo(Me me) {
        if (me != null) {
            String avatar = me.getAvatar();
            String nickname = me.getNickname();
            String gender = me.getGender();
            String birthDate = me.getBirth_date();
            String mobile = me.getMobile();
            if (avatar.length() > 0) {
                ImageLoadUtil.loadImgURL(ivAvatar, me.getAvatar());
            }
            if (nickname.length() > 0) {
                etNickName.setText(nickname);
                etNickName.setSelection(nickname.length());
            }
            if (mobile.length() > 0) {
                tvPhone.setText(mobile);
            }
            switch (gender) {
                case "male":
                    rgGender.check(R.id.rb_male);
                    break;
                case "female":
                    rgGender.check(R.id.rb_female);
                    break;
                default:
                    break;
            }
            if (birthDate.length() > 0) {
                tvDateOfBirth.setText(birthDate);
            }
        }
    }

    @Override
    public void setResult() {
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectAvatarDialog = new SelectAvatarDialog();
                selectAvatarDialog.show(getSupportFragmentManager(), "SelectAvatarDialog");
                selectAvatarDialog.setImageView(ivAvatar);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    boolean isNeverShow = shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if (!isNeverShow) {
                        toast("设置头像需要存储权限,请开启！");
                    } else {
                        toast("设置头像需要存储权限,请允许！");
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
                    }
                }
            }
        }
    }

    @Override
    public void setSelectDate(String date) {
        if (date.length() > 0) {
            tvDateOfBirth.setText(date);
        }
    }
}
