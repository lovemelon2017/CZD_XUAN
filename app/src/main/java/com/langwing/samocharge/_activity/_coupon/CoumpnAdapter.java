package com.langwing.samocharge._activity._coupon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseRecyclerViewAdapter;

public class CoumpnAdapter extends BaseRecyclerViewAdapter<CoumpnAdapter.ViewHolder> {

    private Context context;
    private int[] images;

    public CoumpnAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void bindDate(ViewHolder holder, int position) {
        holder.ivCoupon.setImageResource(images[position]);
    }

    public void setData(int[] images) {
        this.images = images;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_coupon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView ivCoupon;

        public ViewHolder(View itemView) {
            super(itemView);
            ivCoupon = itemView.findViewById(R.id.iv_coupon);
        }
    }
}
