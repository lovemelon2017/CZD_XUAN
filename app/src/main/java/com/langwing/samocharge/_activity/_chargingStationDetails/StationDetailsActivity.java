package com.langwing.samocharge._activity._chargingStationDetails;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseBackActivity;
import com.langwing.samocharge._base.BaseRecyclerViewAdapter;
import com.langwing.samocharge._fragment._chargingPile.Station;
import com.langwing.samocharge._utils.DD;
import com.langwing.samocharge._utils.DensityUtil;
import com.langwing.samocharge._view.WrapContentLinearLayoutManager;
import com.langwing.samocharge._view._wheel.WheelView;

public class StationDetailsActivity extends BaseBackActivity implements IStationDetailsContract.IStationDetailsView, BaseRecyclerViewAdapter.ItemClickListener, View.OnClickListener {

    private AMap mMap;
    private Station station;
    private AlertDialog reservationDialog;
    private View view;
    private int offsetY;
    private AppCompatTextView tvReservationTime;
    private IStationDetailsContract.IStationDetailsPresenter presenter;
    private StationDetails stationDetails;
    private boolean isCollect = false;
    private ImageView ivCollect;
    private Bundle bundle;

    @Override
    public int getLayoutID() {
        return R.layout.activity_station_details1;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        bundle = savedInstanceState;
        setTitle("充电站");
        station = (Station) getIntent().getSerializableExtra("Station");
        if (station == null) {
            finish();
            return;
        }
        DD.dd("PILE", JSON.toJSONString(station));
        presenter = new StationDetailsPresenter(this);
        presenter.getPileDetails(station.getId());
        ivCollect = findViewById(R.id.iv_right);
        ivCollect.setVisibility(View.VISIBLE);
        ivCollect.setOnClickListener(this);
        isCollect = station.getIs_favorite() == 0;
        setCollect(isCollect);
        findViewById(R.id.tv_call).setOnClickListener(this);
    }

    private StationAdapter1 adapter;

    @Override
    public void initStation(StationDetails stationDetails) {
        this.stationDetails = stationDetails;
        RecyclerView rvStationDetails = findViewById(R.id.recyclerView);
        rvStationDetails.setLayoutManager(new WrapContentLinearLayoutManager(this));
        adapter = new StationAdapter1(this, bundle, stationDetails);
        rvStationDetails.setAdapter(adapter);
    }

    @Override
    public void setCollect(boolean collect) {
        isCollect = collect;
        ivCollect.setImageResource(collect ? R.drawable.collect : R.drawable.collect_no);
    }

    @Override
    public void onItemClickListener(View view, int position) {
        if (position != 0) {
            showReservationDialog(stationDetails, position - 1);
        }
    }

    public void showReservationDialog(StationDetails stationDetails, int position) {
        reservationDialog = new AlertDialog.Builder(this, R.style.FullScreenDialogStyle).create();
        view = LayoutInflater.from(this).inflate(R.layout.dialog_reservation, null);
        view.findViewById(R.id.rl_time_choice).setOnClickListener(this);
        view.findViewById(R.id.btn_reservation).setOnClickListener(this);
        tvReservationTime = view.findViewById(R.id.tv_reservation_time);
        reservationDialog.setView(view);
        AppCompatTextView tvLocation = view.findViewById(R.id.tv_location);
        AppCompatTextView tvAmount = view.findViewById(R.id.tv_amount);
        AppCompatTextView tvCode = view.findViewById(R.id.tv_number);
        ImageView ivStatus = view.findViewById(R.id.iv_charging_icon);
        AppCompatTextView tvChargingType = view.findViewById(R.id.tv_charging_type);
        AppCompatTextView tvStatus = view.findViewById(R.id.tv_status);
        StationDetails.StationBean station = stationDetails.getStation();
        if (null != station) {
            tvLocation.setText(station.getAddress());
            tvAmount.setText(String.format("￥ %s畅通豆 / kwh", station.getPrice_kwh()));
        }
        StationDetails.ChargersBean chargersBean = stationDetails.getChargers().get(position);
        if (null != chargersBean) {
            tvCode.setText(chargersBean.getCode());
            tvStatus.setText(chargersBean.getCharger_status());
            tvChargingType.setText(chargersBean.getType());
            if (chargersBean.getIs_online() == 0) {
                tvStatus.setBackgroundColor(getResources().getColor(R.color.red_f4));
                ivStatus.setImageResource(R.drawable.stake_red);
            } else {
                if (chargersBean.getStatus() == 0) {
                    tvStatus.setBackgroundColor(getResources().getColor(R.color.green_00));
                    ivStatus.setImageResource(R.drawable.stake_green);
                } else if (chargersBean.getStatus() > 1 && chargersBean.getStatus() < 4) {
                    tvStatus.setBackgroundColor(getResources().getColor(R.color.yello_ff));
                    ivStatus.setImageResource(R.drawable.stake_yellow);
                } else {
                    tvStatus.setBackgroundColor(getResources().getColor(R.color.red_f4));
                    ivStatus.setImageResource(R.drawable.stake_red);
                }
            }
        }
        setParams();
        reservationDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_time_choice:
                showTimePicker();
                break;
            case R.id.btn_reservation:
                break;
            case R.id.tv_cancle:
                dialogTimePicker.dismiss();
                break;
            case R.id.tv_select:
                String time = "2018." + (wvMonth.getCurrentItem() + 1) + "." + (wvDay.getCurrentItem() + 1);
                tvReservationTime.setText(time);
                dialogTimePicker.dismiss();
                break;
            case R.id.iv_right:
                collectOrCancel();
                break;
            case R.id.tv_call:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    callPhone();
                }
                break;
            default:
                break;
        }
    }

    private void setParams() {
        Window window = reservationDialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        Rect rect = new Rect();
        View decorView = window.getDecorView();
        decorView.getWindowVisibleDisplayFrame(rect);

        layoutParams.gravity = Gravity.TOP;
        layoutParams.width = displayMetrics.widthPixels;
        layoutParams.y = offsetY;
        decorView.setLayoutParams(layoutParams);
        view.setPadding(0, 0, 0, DensityUtil.dip2px(this, 118));
    }

    @Override
    public void addMarker(Station station) {
        LatLng latLng = new LatLng(Double.parseDouble(station.getLat()), Double.parseDouble(station.getLng()));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, 10, 30, 30));
        mMap.moveCamera(cameraUpdate);
        mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.location))).title(station.getName()));
    }

    private AlertDialog dialogTimePicker;
    private WheelView wvYear, wvMonth, wvDay;

    public void showTimePicker() {
        dialogTimePicker = new AlertDialog.Builder(this, R.style.CustomDialogStyle).create();
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_wheel_selector, null, false);
        view.findViewById(R.id.tv_cancle).setOnClickListener(this);
        view.findViewById(R.id.tv_select).setOnClickListener(this);
        wvYear = view.findViewById(R.id.wheelView1);
        wvMonth = view.findViewById(R.id.wheelView2);
        wvDay = view.findViewById(R.id.wheelView3);

        wvYear.setAdapter(new YearAdapter());
        wvMonth.setAdapter(new MonthAdapter());
        wvDay.setAdapter(new DayAdapter());

        Window window = dialogTimePicker.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.x = 0;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
        dialogTimePicker.setView(view, -10, 0, -10, -10);
        dialogTimePicker.show();
    }

    public void collectOrCancel() {
        if (isCollect) {
            presenter.removeCollect(station.getId());
        } else {
            presenter.addCollect(station.getId());
        }
    }

    private void callPhone() {
        Intent tellIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "400-886-5160"));
        startActivity(tellIntent);
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
}