package com.langwing.samocharge._fragment._chargingPile._map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._shake.ShakeActivity;
import com.langwing.samocharge._activity._chargingStationDetails.StationDetailsActivity;
import com.langwing.samocharge._base.BaseMap2dChildFragment;
import com.langwing.samocharge._fragment._chargingPile.Station;
import com.langwing.samocharge._view.CS2DMapView;

import java.util.List;


@SuppressLint("ValidFragment")
public class MapFragment extends BaseMap2dChildFragment {

    private CS2DMapView mapView;
    private AMap map;
    private List<Station> stationList;

    public MapFragment() {
    }

    @SuppressLint("ValidFragment")
    public MapFragment(List<Station> stationList) {
        this.stationList = stationList;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_map;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        mapView = view.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        if (map == null) {
            map = mapView.getMap();
            map.moveCamera(CameraUpdateFactory.zoomTo(13));
        }

        if (ContextCompat.checkSelfPermission(getParentFragment().getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
        } else {
            initMap();
        }
        addMarker(stationList);
        view.findViewById(R.id.iv_shake).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(ShakeActivity.class);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 2) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initMap();
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    boolean isNeverShow = shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION);
                    if (!isNeverShow) {
                        toast("APP内容显示需要位置权限,请开启！");
//                        MPermissionUtils.showTipsDialog(getActivity(), "定位");
                    } else {
                        toast("APP内容显示需要位置权限,请允许！");
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
                    }
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    public void addMarker(List<Station> stationList) {
        if (stationList != null && stationList.size() > 0) {
            for (int position = 0; position < stationList.size(); position++) {
                Station station = stationList.get(position);
                Marker marker = map.addMarker(new MarkerOptions()
                        .anchor(0.5f, 0.5f)
                        .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getParentFragment().getContext().getResources(), R.drawable.station_point)))
                        .position(new LatLng(Double.parseDouble(station.getLat()), Double.parseDouble(station.getLng()))));
                marker.setObject(station);
                marker.setTitle(station.getName());
                marker.setSnippet(station.getAddress());
            }

            map.setOnMarkerClickListener(marker -> {
                Station station = (Station) marker.getObject();
                Intent toItt = new Intent(getParentFragment().getContext(), StationDetailsActivity.class);
                toItt.putExtra("Station", station);
                startActivity(toItt);
                return false;
            });
        }
    }

    public void initMap() {
        MyLocationStyle locationStyle = new MyLocationStyle();
        locationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        locationStyle.showMyLocation(true);
        locationStyle.interval(30000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        locationStyle.strokeColor(Color.TRANSPARENT);
        locationStyle.radiusFillColor(Color.TRANSPARENT);
        map.setMyLocationStyle(locationStyle);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.setMyLocationEnabled(true);
    }

    public void moveCamera(LatLng latLng) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
    }
}
