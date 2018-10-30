package com.langwing.samocharge._activity._recharge;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseRecyclerViewAdapter;
import com.langwing.samocharge._utils.Constants;

import java.util.List;

/**
 * Created by Wyj
 * on 2017/11/16.
 */

public class AmountAdapter extends BaseRecyclerViewAdapter<AmountAdapter.ViewHolder> {

    private Context context;
    private List<String> stringList;
    private int selectPosition = -1;

    public AmountAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_amount, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void bindDate(ViewHolder holder, int position) {
        String smoothBeans = stringList.get(position);
        int index = smoothBeans.lastIndexOf("畅通豆");
        holder.tvMoothBeans.setText(smoothBeans);
        holder.tvAmount.setText(String.valueOf(Constants.RMB) + smoothBeans.substring(0, index));

        if (selectPosition == position) {
            holder.llAmount.setBackgroundResource(R.drawable.bg_yellow_fa_frame);
        } else {
            holder.llAmount.setBackgroundResource(R.drawable.bg_gray_f2_frame);
        }
    }

    public void setSelectPosition(int position) {
        this.selectPosition = position;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llAmount;
        AppCompatTextView tvMoothBeans;
        AppCompatTextView tvAmount;

        public ViewHolder(View itemView) {
            super(itemView);
            llAmount = itemView.findViewById(R.id.ll_amount);
            tvMoothBeans = itemView.findViewById(R.id.tv_smooth_beans);
            tvAmount = itemView.findViewById(R.id.tv_amount);
        }
    }
}
