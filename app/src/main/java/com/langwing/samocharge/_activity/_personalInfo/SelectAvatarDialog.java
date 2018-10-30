package com.langwing.samocharge._activity._personalInfo;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.langwing.samocharge.R;
import com.langwing.samocharge._utils.DD;
import com.langwing.samocharge._utils.TakePhotoUtil;
import com.langwing.samocharge._utils.ToastUtil;

import java.security.Permission;

/**
 * Create By WYJ on 2017/12/7.
 */
public class SelectAvatarDialog extends DialogFragment implements View.OnClickListener {

    private View view;
    private ImageView ivAvatar;
    public TakePhotoUtil takePhotoUtil;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.SelectAvatarDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (null != dialog) {
            Window window = dialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.dialog_select_avatar, container, false);
        }
        view.findViewById(R.id.tv_take_photo).setOnClickListener(this);
        view.findViewById(R.id.tv_album_selection).setOnClickListener(this);
        view.findViewById(R.id.tv_cancel).setOnClickListener(this);
        return view;

    }

    public void setImageView(ImageView ivAvatar) {
        this.ivAvatar = ivAvatar;
    }

    public ImageView getImageView() {
        return ivAvatar;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_take_photo:
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 102);
                } else {
                    takePhotoUtil = new TakePhotoUtil(ivAvatar);
                    takePhotoUtil.takePhoto(getActivity());
                    dismiss();
                }
                break;
            case R.id.tv_album_selection:
                takePhotoUtil = new TakePhotoUtil(ivAvatar);
                takePhotoUtil.albumSelection(getActivity());
                dismiss();
                break;
            case R.id.tv_cancel:
                dismiss();
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
                takePhotoUtil = new TakePhotoUtil(ivAvatar);
                takePhotoUtil.takePhoto(getActivity());
                dismiss();
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    boolean isNeverShow = shouldShowRequestPermissionRationale(Manifest.permission.CAMERA);
                    if (!isNeverShow) {
                        ToastUtil.toast(getActivity(), "拍照需要相机权限,请开启！");
                    } else {
                        ToastUtil.toast(getActivity(), "拍照需要相机权限,请允许！");
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, 102);
                    }
                }
            }
        }
    }
}
