package com.langwing.samocharge._activity._chargingStationDetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseRecyclerViewAdapter;

import java.util.List;


/**
 * Created by WYJ
 * on 2017/9/7.
 */

class StationAdapter extends BaseRecyclerViewAdapter<StationAdapter.ViewHolder> {

    private Context context;
    private StationDetails stationDetails;
    private List<StationDetails.ChargersBean> pileList;

    StationAdapter(Context context, StationDetails stationDetails) {
        this.context = context;
        this.stationDetails = stationDetails;
        pileList = stationDetails.getChargers();
    }

    @Override
    public void bindDate(ViewHolder holder, int position) {
        if (position == 0) {
            StationDetails.StationBean stationBean = stationDetails.getStation();
            holder.tvName.setText(stationBean.getName());
            holder.tvAddress.setText(stationBean.getAddress());
            holder.tvPrice.setText(String.format("￥ %s畅通豆 / kwh", stationBean.getPrice_kwh()));
        } else {
            StationDetails.ChargersBean pile = pileList.get(position - 1);
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
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        if (viewType == 1) {
            view = inflater.inflate(R.layout.item_charging_station_details_title, parent, false);
        } else {
            view = inflater.inflate(R.layout.item_charging_station_details, parent, false);
        }
        return new ViewHolder(view, viewType);
    }

    @Override
    public int getItemCount() {
        return stationDetails.getChargers().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 1 : 2;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvName, tvPrice, tvAddress;
        private TextView tvCode, tvStatus, tvChargingType, tvSubscribe;
        private ImageView ivStatus;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            switch (viewType) {
                case 1:
                    tvName = itemView.findViewById(R.id.tv_name);
                    tvPrice = itemView.findViewById(R.id.tv_price);
                    tvAddress = itemView.findViewById(R.id.tv_address);
                    break;
                case 2:
                    ivStatus = itemView.findViewById(R.id.iv_icon);
                    tvChargingType = itemView.findViewById(R.id.tv_charging_type);
                    tvCode = itemView.findViewById(R.id.tv_code);
                    tvStatus = itemView.findViewById(R.id.tv_status);
                    tvSubscribe = itemView.findViewById(R.id.tv_subscribe);
                    tvSubscribe.setOnClickListener(this);
                    break;
            }
        }

        @Override
        public void onClick(View v) {
            ((StationDetailsActivity) context).showReservationDialog(stationDetails, 1);
        }
    }
}
