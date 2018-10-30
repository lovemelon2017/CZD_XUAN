package com.langwing.samocharge._fragment._chargingPile;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._search.SearchActivity;
import com.langwing.samocharge._activity._selectCity.City;
import com.langwing.samocharge._activity._selectCity.SelectCityActivity;
import com.langwing.samocharge._base.BaseFragment;

import java.util.List;


public class StationFragment extends BaseFragment implements View.OnClickListener, IStationContract.IChargingPileView,
        AMapLocationListener, GeocodeSearch.OnGeocodeSearchListener {

    private StationPresenter presenter;
    private AppCompatTextView tvCurrentCity;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_charging_pile;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.iv_station_type).setOnClickListener(this);
        view.findViewById(R.id.tv_city).setOnClickListener(this);
        view.findViewById(R.id.tv_search).setOnClickListener(this);
        tvCurrentCity = view.findViewById(R.id.tv_city);
        presenter = new StationPresenter(this);
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
        } else {
            startLocation();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_station_type:
                presenter.changeFragment(getChildFragmentManager().beginTransaction());
                break;
            case R.id.tv_city:
                Intent intent = new Intent(getActivity(), SelectCityActivity.class);
                startActivityForResult(intent, 0x11);
                break;
            case R.id.tv_search:
                toActivity(SearchActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public FragmentTransaction getFragmentTransaction() {
        return getChildFragmentManager().beginTransaction();
    }

    private void startLocation() {
        AMapLocationClient locationClient;
        AMapLocationClientOption mLocationOption = null;
        locationClient = new AMapLocationClient(getActivity());
        mLocationOption = new AMapLocationClientOption();
        locationClient.setLocationListener(this);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(2000);
        locationClient.setLocationOption(mLocationOption);
        locationClient.startLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 2) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocation();
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    boolean isNeverShow = shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION);
                    if (!isNeverShow) {
                        toast("APP内容显示需要位置权限,请开启！");
                    } else {
                        toast("APP内容显示需要位置权限,请允许！");
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
                    }
                }
            }
        }
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                amapLocation.getLatitude();//获取纬度
                amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                String city = amapLocation.getCity();
                tvCurrentCity.setText(city);

            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x11) {
            if (resultCode == Activity.RESULT_OK && null != data) {
                City.CitiesBean citiesBean = (City.CitiesBean) data.getSerializableExtra("data");
                int cityId = citiesBean.getCity_id();
                tvCurrentCity.setText(citiesBean.getCity_name());
                presenter.getChargingPile(cityId);
                GeocodeSearch geocodeSearch = new GeocodeSearch(getActivity());
                geocodeSearch.setOnGeocodeSearchListener(this);
                GeocodeQuery geocodeQuery = new GeocodeQuery(citiesBean.getCity_name(), String.valueOf(cityId));
                geocodeSearch.getFromLocationNameAsyn(geocodeQuery);
            }
        }
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
        List<GeocodeAddress> geocodeAddressList = geocodeResult.getGeocodeAddressList();
        LatLonPoint latLonPoint = geocodeAddressList.get(0).getLatLonPoint();
        double latitude = latLonPoint.getLatitude();
        double longitude = latLonPoint.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        presenter.mapFragment.moveCamera(latLng);
    }
}
