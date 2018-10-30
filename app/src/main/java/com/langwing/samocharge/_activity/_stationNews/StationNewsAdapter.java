package com.langwing.samocharge._activity._stationNews;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseRecyclerViewAdapter;

import java.util.List;


/**
 * Created by WYJ on 2018/3/9.
 */

public class StationNewsAdapter extends BaseRecyclerViewAdapter<StationNewsAdapter.ViewHolder> {

    private Context context;
    private List<Message> messageList;

    public StationNewsAdapter(Context context, List<Message> messageList) {
        this.context = context;
        this.messageList = messageList;
    }

    @Override
    public void bindDate(ViewHolder holder, int position) {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (1 == viewType) {
            view = LayoutInflater.from(context).inflate(R.layout.item_message_normal, parent, false);
        } else if (2 == viewType) {
            view = LayoutInflater.from(context).inflate(R.layout.item_message_payment, parent, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_message_charging, parent, false);
        }
        return new ViewHolder(view, viewType);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messageList.get(position);
        String type = message.getType();
        if ("normal".equals(type)) {
            return 1; // 通用模版
        } else if ("payment".equals(type)) {
            return 2; // 支付模版
        } else {
            return 3; // 充电订单
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView tvNoraml, tvPayment, tvOrder;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            switch (viewType) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        }
    }

}
