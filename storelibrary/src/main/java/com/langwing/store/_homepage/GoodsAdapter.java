package com.langwing.store._homepage;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.langwing.store.BaseRecyclerViewAdapter;
import com.langwing.store.R;

/**
 * To Change The World
 * 2017-12-20 16:07
 * Created by Mr.Wang
 */

class GoodsAdapter extends BaseRecyclerViewAdapter<GoodsAdapter.ViewHolder> {

    private Context context;

    GoodsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void bindDate(ViewHolder holder, int position) {
        if (position == 0) {

        } else {
            int goodsPosition = position % 4;
            if (goodsPosition == 0) {
                holder.tvGoods.setText("女士两面休闲背包DH501");
                holder.ivGoods.setImageResource(R.drawable.goods_img2);

            } else if (goodsPosition == 1) {
                holder.tvGoods.setText("哈尔斯500ml不锈钢真空保温杯户外旅行车载子弹头水杯子HB-500AX香槟金");
                holder.ivGoods.setImageResource(R.drawable.goods_img);

            } else if (goodsPosition == 2) {
                holder.tvGoods.setText("飞科电动剃须刀便携式充电");
                holder.ivGoods.setImageResource(R.drawable.goods_img3);

            } else if (goodsPosition == 3) {
                holder.tvGoods.setText("信阳毛尖明前新茶特供");
                holder.ivGoods.setImageResource(R.drawable.goods_img4);

            } else {
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(context).inflate(R.layout.item_goods_header, parent, false);

        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_goods, parent, false);

        }
        return new ViewHolder(view, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivGoods;
        TextView tvGoods, tvNewActivity, tvOldPrice, tvNewPrice;

        ViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == 0) {
            } else {
                ivGoods = itemView.findViewById(R.id.iv_goods);
                tvGoods = itemView.findViewById(R.id.tv_goods);
                tvNewActivity = itemView.findViewById(R.id.tv_new_activity);
                tvOldPrice = itemView.findViewById(R.id.tv_old_price);
                tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                tvNewPrice = itemView.findViewById(R.id.tv_new_price);

            }
        }
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
            params.setFullSpan(true);
        }
    }
}
