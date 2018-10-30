package com.langwing.samocharge._fragment._mine;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ImageView;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._accountCertified.AccountCertifiedActivity;
import com.langwing.samocharge._activity._personalCenter.PersonalCenterActivity;
import com.langwing.samocharge._activity._setting.SettingActivity;
import com.langwing.samocharge._activity._chargingRecord.ChargingRecordActivity;
import com.langwing.samocharge._activity._coupon.CouponActivity;
import com.langwing.samocharge._activity._myChargingStation.MyCharginStationActivity;
import com.langwing.samocharge._activity._myCollection.MyCollectActivity;
import com.langwing.samocharge._activity._personalBalance.PersonalBalanceActivity;
import com.langwing.samocharge._activity._personalInfo.PersonalInfoActivity;
import com.langwing.samocharge._activity._stationNews.StationNewsActivity;
import com.langwing.samocharge._base.BaseFragment;
import com.langwing.samocharge._utils.Constants;
import com.langwing.samocharge._utils.ImageLoadUtil;
import com.langwing.samocharge._utils.SharePreferenceUtils;

/**
 * Create By WYJ on 2017/12/7.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener, IMineContract.IMineView {

    private IMineContract.IMinePresenter presenter;
    private AppCompatTextView tvGrade;
    private AppCompatTextView tvName;
    private ImageView ivAvatar;
    private AppCompatTextView tvPerfectInfo;
    private AppCompatTextView tvVerified;
    private AppCompatTextView tvUnreadQty;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.ll_personal_balance).setOnClickListener(this);
        view.findViewById(R.id.ll_coupon).setOnClickListener(this);
        view.findViewById(R.id.ll_station_news).setOnClickListener(this);
        view.findViewById(R.id.ll_charging_record).setOnClickListener(this);
        view.findViewById(R.id.tv_my_charging_station).setOnClickListener(this);
        view.findViewById(R.id.tv_collection).setOnClickListener(this);
        view.findViewById(R.id.tv_personal_center).setOnClickListener(this);
        view.findViewById(R.id.rl_consumer_hotline).setOnClickListener(this);
        view.findViewById(R.id.tv_setting).setOnClickListener(this);
        tvName = view.findViewById(R.id.tv_name);
        tvGrade = view.findViewById(R.id.tv_grade);
        ivAvatar = view.findViewById(R.id.iv_avatar);
        tvPerfectInfo = view.findViewById(R.id.tv_perfect_info);
        tvVerified = view.findViewById(R.id.tv_verified);
        tvPerfectInfo.setOnClickListener(this);
        tvVerified.setOnClickListener(this);
        tvUnreadQty = view.findViewById(R.id.tv_unread_qty);
        presenter = new MinePresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_personal_balance:
                toActivity(PersonalBalanceActivity.class);
                break;
            case R.id.ll_coupon:
                toActivity(CouponActivity.class);
                break;
            case R.id.ll_station_news:
                toActivity(StationNewsActivity.class);
                break;
            case R.id.ll_charging_record:
                toActivity(ChargingRecordActivity.class);
                break;
            case R.id.tv_my_charging_station:
                toActivity(MyCharginStationActivity.class);
                break;
            case R.id.tv_collection:
                toActivity(MyCollectActivity.class);
                break;
            case R.id.tv_personal_center:
                Intent intentPersonal = new Intent(getActivity(), PersonalCenterActivity.class);
                startActivityForResult(intentPersonal, 1);
                break;
            case R.id.rl_consumer_hotline:
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    callPhone();
                }
                break;
            case R.id.tv_setting:
                toActivity(SettingActivity.class);
                break;
            case R.id.tv_perfect_info:
                Intent intent = new Intent(getActivity(), PersonalInfoActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_verified:
                Intent intentVerified = new Intent(getActivity(), AccountCertifiedActivity.class);
                startActivityForResult(intentVerified, 1);
                break;
            default:
                break;
        }
    }

    @Override
    public void bindMeInfo(Me me) {
        if (me != null) {
            String name = me.getName();
            int userLevel = me.getUser_level();
            String avatar = me.getAvatar();
            String realNameStatus = me.getReal_name_status();
            String profileFullStatus = me.getProfile_full_status();
            int isProfileFull = me.getIs_profile_full();
            if (avatar.length() > 0) {
                ImageLoadUtil.loadImgURL(ivAvatar, me.getAvatar());
            }
            if (name.length() > 0) {
                tvName.setText(me.getName());
            }
            tvGrade.setText("LV." + userLevel);
            if (realNameStatus.length() > 0) {
                tvVerified.setText(realNameStatus);
            }
            if (profileFullStatus.length() > 0) {
                tvPerfectInfo.setText(profileFullStatus);
            }
            if (isProfileFull == 0) {
                tvPerfectInfo.setVisibility(View.VISIBLE);
            }
            SharePreferenceUtils.writeString(Constants.PHONE, me.getMobile());
        }
    }

    @Override
    public void bindUnreadQty(int qty) {
        tvUnreadQty.setText(String.valueOf(qty));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhone();
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    boolean isNeverShow = shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE);
                    if (!isNeverShow) {
                        toast("拨打电话需要通话权限,请开启！");
                    } else {
                        toast("拨打电话需要通话权限,请允许！");
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
                    }
                }
            }
        }
    }

    private void callPhone() {
        Intent tellIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "400-886-5160"));
        startActivity(tellIntent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            presenter.getMe();
        }
    }
}