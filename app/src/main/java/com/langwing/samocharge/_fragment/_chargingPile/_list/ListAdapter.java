package com.langwing.samocharge._fragment._chargingPile._list;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.amap.api.navi.model.NaviLatLng;
import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._chargingStationDetails.StationDetailsActivity;
import com.langwing.samocharge._activity._navigation.NavigationActivity;
import com.langwing.samocharge._base.BaseRecyclerViewAdapter;
import com.langwing.samocharge._fragment._chargingPile.Station;
import com.langwing.samocharge._utils.ToastUtil;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by WYJ
 * on 2017/9/7.
 */

public class ListAdapter extends BaseRecyclerViewAdapter<ListAdapter.ViewHolder> implements AMapLocationListener {

    private Context context;
    private List<Station> stationList;
    private NaviLatLng startLarLng;
    private int sortType = 0;//0 不排序 1 距离， 2 空闲

    public ListAdapter(Context context, List<Station> stationList) {
        this.context = context;
        this.stationList = stationList;
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            startLocation();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_station, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void bindDate(ViewHolder holder, int position) {
        Station station = stationList.get(position);
        holder.tvName.setText(station.getName());
        holder.tvPile.setText(String.format("%d/%d", station.getAvailable_qty_of_charger(), station.getQty_of_charger()));
        holder.tvPile.setText(String.format("%d个桩  |  %s  |  空闲 %d", station.getQty_of_charger(), station.getCharger_type(), station.getAvailable_qty_of_charger()));
        holder.tvAddress.setText(station.getAddress());
        holder.tvDetails.setTag(station);
        if (startLarLng != null) {
            NaviLatLng endLatlng = new NaviLatLng(Double.parseDouble(station.getLat()), Double.parseDouble(station.getLng()));
            holder.tvDistance.setText(String.format("%sKM", station.getDistance()));
            holder.tvNavigation.setTag(endLatlng);
        }
    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }

    public void setSort(int sortType) {
        this.sortType = sortType;
        if (sortType == 1) {
            if (startLarLng == null) {
                return;
            }
            Collections.sort(stationList, new Comparator<Station>() {
                @Override
                public int compare(Station station1, Station station2) {
                    return (int) (station1.getDistance() * 10 - station2.getDistance() * 10);
                }
            });
        } else if (sortType == 2) {
            Collections.sort(stationList, new Comparator<Station>() {
                @Override
                public int compare(Station station1, Station station2) {
                    return station2.getAvailable_qty_of_charger() - station1.getAvailable_qty_of_charger();
                }
            });
        }
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private AppCompatTextView tvPile, tvName, tvAddress, tvDistance, tvNavigation, tvDetails;

        public ViewHolder(View itemView) {
            super(itemView);
            tvPile = itemView.findViewById(R.id.tv_pile);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvDistance = itemView.findViewById(R.id.tv_distance);
            tvNavigation = itemView.findViewById(R.id.tv_navigation);
            tvDetails = itemView.findViewById(R.id.tv_details);
            tvDetails.setOnClickListener(this);
            tvNavigation.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_navigation:
                    if (startLarLng == null) {
                        ToastUtil.toast(context, "定位失败，无法导航！");
                    } else {
                        NaviLatLng endLatLng = (NaviLatLng) view.getTag();
                        Intent ittNav = new Intent(context, NavigationActivity.class);
                        ittNav.putExtra("startNavLatLng", startLarLng);
                        ittNav.putExtra("endNavLatLng", endLatLng);
                        context.startActivity(ittNav);
                    }
                    break;
                case R.id.tv_details:
                    Station station = (Station) view.getTag();
                    Intent intent = new Intent(context, StationDetailsActivity.class);
                    intent.putExtra("Station", station);
                    context.startActivity(intent);
                    break;
            }

        }
    }

    private float getDistance(NaviLatLng startLatLng, NaviLatLng endLatLng) {
        if (startLatLng == null) {
            return 0;
        } else {
            float distance = AMapUtils.calculateLineDistance(new LatLng(startLatLng.getLatitude(), startLatLng.getLongitude()),
                    new LatLng(endLatLng.getLatitude(), endLatLng.getLongitude()));
            distance = distance / 1000;
            DecimalFormat decimalFormat = new DecimalFormat(".#");
            distance = Float.parseFloat(decimalFormat.format(distance));
            return distance;
        }
    }

    private void startLocation() {
        AMapLocationClient locationClient;
        AMapLocationClientOption mLocationOption = null;
        locationClient = new AMapLocationClient(context);
        mLocationOption = new AMapLocationClientOption();
        locationClient.setLocationListener(this);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(2000);
        locationClient.setLocationOption(mLocationOption);
        locationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                amapLocation.getLatitude();//获取纬度
                amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                NaviLatLng startLatLng = new NaviLatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
                this.startLarLng = startLatLng;
                for (Station station : stationList) {
                    NaviLatLng endLatlng = new NaviLatLng(Double.parseDouble(station.getLat()), Double.parseDouble(station.getLng()));
                    station.setDistance(getDistance(startLatLng, endLatlng));
                }
                setSort(sortType);
            }
        }
    }
}