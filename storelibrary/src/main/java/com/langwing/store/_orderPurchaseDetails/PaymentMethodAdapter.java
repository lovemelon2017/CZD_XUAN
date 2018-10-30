package com.langwing.store._orderPurchaseDetails;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.langwing.store.BaseRecyclerViewAdapter;
import com.langwing.store.R;

/**
 * Created by WYJ on 2017/12/21.
 */

public class PaymentMethodAdapter extends BaseRecyclerViewAdapter<PaymentMethodAdapter.ViewHolder> {

    private String[] paymentMethod = new String[]{"微信支付", "支付宝支付", "银联支付"};
    private int[] images = new int[]{R.drawable.wechatpay, R.drawable.alipay, R.drawable.unionpay};
    private int selectPosition = 0;

    @Override
    public void bindDate(ViewHolder holder, int position) {
        holder.tvPayment.setText(paymentMethod[position]);
        holder.ivPayment.setBackgroundResource(images[position]);
        if (selectPosition == position) {
            holder.ivCheck.setVisibility(View.VISIBLE);
        } else {
            holder.ivCheck.setVisibility(View.GONE);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment_method, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return paymentMethod.length;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView ivPayment;
        AppCompatTextView tvPayment;
        AppCompatImageView ivCheck;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPayment = itemView.findViewById(R.id.iv_payment);
            tvPayment = itemView.findViewById(R.id.tv_payment);
            ivCheck = itemView.findViewById(R.id.iv_check);
        }
    }
}
