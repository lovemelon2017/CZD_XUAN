package com.langwing.samocharge._activity._chargingStationDetails;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.navi.model.NaviLatLng;
import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._navigation.NavigationActivity;
import com.langwing.samocharge._base.BaseRecyclerViewAdapter;
import com.langwing.samocharge._utils.DD;
import com.langwing.samocharge._utils.ImageLoadUtil;
import com.langwing.samocharge._utils.ToastUtil;

import java.util.List;


/**
 * Created by WYJ
 * on 2017/9/7.
 */

class StationAdapter1 extends BaseRecyclerViewAdapter<StationAdapter1.ViewHolder> {

    private Context context;
    private StationDetails stationDetails;
    private List<StationDetails.ChargersBean> pileList;

    private boolean isPileOpen = false;
    private boolean isCommentOpen = false;
    public Bundle bundle;

    StationAdapter1(Context context, Bundle bundle, StationDetails stationDetails) {
        this.context = context;
        this.bundle = bundle;
        this.stationDetails = stationDetails;
        pileList = stationDetails.getChargers();
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void bindDate(ViewHolder holder, int position) {
        if (position == 0) {
            StationDetails.StationBean stationBean = stationDetails.getStation();
//            AMap aMap = mapView.getMap();
//            LatLng latLng = new LatLng(Double.parseDouble(stationBean.getLat()), Double.parseDouble(stationBean.getLng()));
//            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, 10, 30, 30));
//            aMap.moveCamera(cameraUpdate);
//            aMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.station_pile))).title(stationBean.getName()));
            String mapUrl = "http://restapi.amap.com/v3/staticmap?location=" + stationBean.getLng() + "," + stationBean.getLat() + "&zoom=15&size=1024*1024&markers=large,0xff0000,A:" + stationBean.getLng() + "," + stationBean.getLat() + "&labels=" + stationBean.getName() + ",2,0,16,0xFFFFFF,0x008000:" + stationBean.getLng() + "," + stationBean.getLat() + "&key=b4c4d7d875472e9f9f1d10450da7a5c5";
            ImageLoadUtil.loadImgUrl(holder.ivMap, mapUrl);
            DD.dd("mapUrl", mapUrl);
            holder.tvName.setText(stationBean.getName());
            holder.tvAddress.setText(stationBean.getAddress());
            holder.tvPrice.setText(String.format("￥ %s畅通豆 / kwh", stationBean.getPrice_kwh()));
            holder.tvPile.setText(String.format("%d个桩  |  %s  |  空闲 %d", stationBean.getQty_of_charger(), stationBean.getCharger_type(), stationBean.getAvailable_qty_of_charger()));
            return;
        }
        if (getItemViewType(position) == 1) {
            StationDetails.ChargersBean pile = pileList.get(position - 1);
            initPile(holder, pile);
        } else if (getItemViewType(position) == 2) {
            holder.tvPileOpen.setText(isPileOpen ? "收起更多" : "展开更多");
            Drawable drawableRight = context.getResources().getDrawable(isPileOpen ? R.drawable.arrow_close : R.drawable.arrow_open);
            drawableRight.setBounds(0, 0, drawableRight.getMinimumWidth(), drawableRight.getMinimumHeight());
            holder.tvPileOpen.setCompoundDrawables(null, null, drawableRight, null);
        } else if (getItemViewType(position) == 3) {
        } else if (getItemViewType(position) == 4) {
            holder.tvCommentOpen.setText(isCommentOpen ? "收起更多" : "展开更多");
            Drawable drawableRight = context.getResources().getDrawable(isCommentOpen ? R.drawable.arrow_close : R.drawable.arrow_open);
            drawableRight.setBounds(0, 0, drawableRight.getMinimumWidth(), drawableRight.getMinimumHeight());
            holder.tvCommentOpen.setCompoundDrawables(null, null, drawableRight, null);
        } else if (getItemViewType(position) == 5) {
        }
    }

    private void initPile(ViewHolder holder, StationDetails.ChargersBean pile) {
        holder.tvCode.setText(pile.getCode());
        holder.tvChargingType.setText(pile.getType());
        holder.tvStatus.setText(pile.getCharger_status());
        if (pile.getIs_online() == 0) {
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.red_f4));
            holder.ivStatus.setImageResource(R.drawable.stake_red);
        } else {
            if (pile.getStatus() == 0) {
                holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.green_00));
                holder.ivStatus.setImageResource(R.drawable.stake_green);
            } else if (pile.getStatus() > 1 && pile.getStatus() < 4) {
                holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.yello_ff));
                holder.ivStatus.setImageResource(R.drawable.stake_yellow);
            } else {
                holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.red_f4));
                holder.ivStatus.setImageResource(R.drawable.stake_red);
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = null;
        if (viewType == 0) {
            view = inflater.inflate(R.layout.item_station_details_map, parent, false);
        } else if (viewType == 1) {
            view = inflater.inflate(R.layout.item_station_details_pile, parent, false);
        } else if (viewType == 2) {
            view = inflater.inflate(R.layout.item_station_details_pile_open, parent, false);
            return new ViewHolder(view, viewType);
        } else if (viewType == 3) {
            view = inflater.inflate(R.layout.item_station_details_comment, parent, false);
            return new ViewHolder(view, viewType);
        } else if (viewType == 4) {
            view = inflater.inflate(R.layout.item_station_details_comment_open, parent, false);
            return new ViewHolder(view, viewType);
        } else if (viewType == 5) {
            view = inflater.inflate(R.layout.item_station_details_new_comment, parent, false);
            return new ViewHolder(view, viewType);
        }
        return new ViewHolder(view, viewType);
    }

    @Override
    public int getItemCount() {
        if (isPileOpen) {
            return 5 + pileList.size();
        } else if (isCommentOpen) {
            return 6 + 10;
        } else {
            return 7;
        }
//        return stationDetails.getChargers().size() + 1;
    }

    /*
     * 0：头布局
     * 1:pile item
     * 2:pile 展开更多 收起更多
     * 3:comment item
     * 4:comment 展开更多 收起更多
     * 5:new comment
     */
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        }
        if (isPileOpen) {
            if (position <= pileList.size()) {
                return 1;
            } else if (position == pileList.size() + 1) {
                return 2;
            } else if (position == pileList.size() + 2) {
                return 5;
            } else if (position == pileList.size() + 3) {
                return 3;
            } else if (position == pileList.size() + 4) {
                return 4;
            }
        } else if (isCommentOpen) {
            if (position <= 2) {
                return 1;
            } else if (position == 3) {
                return 2;
            } else if (position == 4) {
                return 5;
            } else if (position == getItemCount() - 1) {
                return 4;
            } else {
                return 3;
            }
        } else {
            if (position <= 2) {
                return 1;
            } else if (position == 3) {
                return 2;
            } else if (position == 4) {
                return 5;
            } else if (position == 5) {
                return 3;
            } else {
                return 4;
            }
        }
        DD.dd("getItemViewType", "viewType 0");
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvName, tvPile, tvPrice, tvAddress;
        private ImageView ivNavigation;
        private ImageView ivMap;

        private TextView tvCode, tvStatus, tvChargingType, tvSubscribe;
        private ImageView ivStatus;

        private TextView tvCommentOpen, tvPileOpen;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            switch (viewType) {
                case 0://头布局
                    ivMap = itemView.findViewById(R.id.iv_map);
                    tvName = itemView.findViewById(R.id.tv_name);
                    tvPile = itemView.findViewById(R.id.tv_pile);
                    tvPrice = itemView.findViewById(R.id.tv_price);
                    tvAddress = itemView.findViewById(R.id.tv_address);
                    ivNavigation = itemView.findViewById(R.id.iv_navigation);
                    ivNavigation.setOnClickListener(this);
                    break;
                case 1://1:pile item
                    ivStatus = itemView.findViewById(R.id.iv_icon);
                    tvChargingType = itemView.findViewById(R.id.tv_charging_type);
                    tvCode = itemView.findViewById(R.id.tv_code);
                    tvStatus = itemView.findViewById(R.id.tv_status);
                    tvSubscribe = itemView.findViewById(R.id.tv_subscribe);
                    tvSubscribe.setOnClickListener(this);
                    break;
                case 2:// 2:pile 展开更多 收起更多
                    itemView.findViewById(R.id.rl_pile_open).setOnClickListener(this);
                    tvPileOpen = itemView.findViewById(R.id.tv_pile_open);
                    break;
                case 3://3:comment item

                    break;
                case 4://4:comment 展开更多 收起更多
                    itemView.findViewById(R.id.rl_comment_open).setOnClickListener(this);
                    tvCommentOpen = itemView.findViewById(R.id.tv_comment_open);
                    break;
                case 5://5:new comment
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_navigation:
                    NaviLatLng startNavLatLng = new NaviLatLng(34.760769, 113.706143);
                    NaviLatLng endNavLatLng = new NaviLatLng(Double.parseDouble(stationDetails.getStation().getLat()), Double.parseDouble(stationDetails.getStation().getLng()));
                    Intent intent = new Intent(context, NavigationActivity.class);
                    intent.putExtra("startNavLatLng", startNavLatLng);
                    intent.putExtra("endNavLatLng", endNavLatLng);
                    context.startActivity(intent);
                    break;
                case R.id.rl_comment_open:
                    if (isCommentOpen) {
                        isCommentOpen = false;
                        notifyItemRangeRemoved(6, 9);
                    } else {
                        isCommentOpen = true;
                        notifyItemRangeInserted(6, 9);
                    }
                    isPileOpen = false;
                    notifyItemChanged(4);
                    notifyItemChanged(getItemCount());
                    break;
                case R.id.rl_pile_open:
                    if (isPileOpen) {
                        isPileOpen = false;
                        notifyItemRangeRemoved(3, pileList.size() - 2);
                    } else {
                        isPileOpen = true;
                        notifyItemRangeInserted(3, pileList.size() - 2);
                    }
                    isCommentOpen = false;
                    notifyItemChanged(1 + pileList.size());
                    notifyItemChanged(getItemCount());
                    break;
                case R.id.tv_subscribe:
                    new AlertDialog.Builder(context)
                            .setTitle("预约")
                            .setMessage("确定预约后将为您保留1小时，请尽快前往充电！")
                            .setPositiveButton("确定", (dialogInterface, i) -> {
                                ToastUtil.toast(context, "预约成功，请在规定时间内到达！");
                            })
                            .setNegativeButton("取消", null)
                            .create()
                            .show();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        bindDate(holder, position);
    }
}
