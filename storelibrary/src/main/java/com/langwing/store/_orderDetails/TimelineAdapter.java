package com.langwing.store._orderDetails;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.langwing.store.BaseRecyclerViewAdapter;
import com.langwing.store.R;
import com.langwing.store._view.TimelineView;

import java.util.List;

/**
 * Created by WYJ on 2017/12/21.
 */

public class TimelineAdapter extends BaseRecyclerViewAdapter<TimelineAdapter.ViewHolder> {

    private Context context;
    private List<String> dataList;

    public TimelineAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public void bindDate(ViewHolder holder, int position) {
        if (position == 0) {

            return;
        }
        String desc = dataList.get(position-1);
        if (position == 1) {
            holder.timelineView.setMarker(ContextCompat.getDrawable(context, R.drawable.marker));
        } else {
            holder.timelineView.setMarker(ContextCompat.getDrawable(context, R.drawable.gray_circle));
        }
        holder.tvOrderStatus.setText(desc);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(context).inflate(R.layout.item_goods_header, parent, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_timeline, parent, false);
        }
        return new ViewHolder(view, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        }
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

    @Override
    public int getItemCount() {
        return null == dataList ? 1 : dataList.size() + 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tvOrderStatus;
        TimelineView timelineView;

        ViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == 0) {

            } else {
                tvOrderStatus = itemView.findViewById(R.id.tv_order_status);
                timelineView = itemView.findViewById(R.id.time_marker);
                timelineView.initLine(viewType);
            }
        }
    }

}
