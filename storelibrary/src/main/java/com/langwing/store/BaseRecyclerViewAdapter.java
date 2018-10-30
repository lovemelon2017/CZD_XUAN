package com.langwing.store;

import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * To Change The World
 * 2016-08-05 14:01
 * Created by Mr.Wang
 */
public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements View.OnClickListener {

    private ItemClickListener itemClickListener;

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        if (itemClickListener != null) {
            itemClickListener.onItemClickListener(v, (Integer) v.getTag());
        }
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);
        bindDate(holder, position);
    }

    public abstract void bindDate(VH holder, int position);

    public interface ItemClickListener {
        void onItemClickListener(View view, int position);
    }

}
