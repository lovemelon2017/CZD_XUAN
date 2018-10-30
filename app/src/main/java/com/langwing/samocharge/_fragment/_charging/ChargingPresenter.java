package com.langwing.samocharge._fragment._charging;

import android.Manifest;
import android.support.v4.app.Fragment;

import com.langwing.samocharge._activity._chargingScan.ChargingScanActivity;
import com.langwing.samocharge._base.BasePresenter;
import com.langwing.samocharge._utils.Constants;
import com.langwing.samocharge._utils.MPermissionUtils;

/**
 * Created by Wyj
 * on 2017/11/21.
 */

public class ChargingPresenter extends BasePresenter implements IChargingContract.IChargingPresenter {

    private IChargingContract.IChargingView iChargingView;

    public ChargingPresenter(IChargingContract.IChargingView iChargingView) {
        super(iChargingView);
        this.iChargingView = iChargingView;
    }

    @Override
    public void requestCamera(Fragment fragment) {
        MPermissionUtils.requestPermissionsResult(fragment, Constants.CAMERA_PERMISSION_REQUEST_CODE, new String[]{Manifest.permission.CAMERA}, new MPermissionUtils.OnPermissionListener() {
            @Override
            public void onPermissionGranted() {
                iChargingView.toActivity(ChargingScanActivity.class);
            }

            @Override
            public void onPermissionDenied() {
                MPermissionUtils.showTipsDialog(fragment.getActivity(), "相机");
            }
        });
    }
}
