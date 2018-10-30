package com.langwing.samocharge._activity._guidePage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.model.LatLng;
import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseRecyclerViewAdapter;


public class GuidePageAdapter extends BaseRecyclerViewAdapter<GuidePageAdapter.ViewHolder> {

    private int[] images = new int[]{R.drawable.guide_one, R.drawable.guide_two, R.drawable.guide_three};
    private Context context;

    public GuidePageAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_guide, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void bindDate(ViewHolder holder, int position) {
        holder.ivGuide.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView ivGuide;

        public ViewHolder(View itemView) {
            super(itemView);
            ivGuide = itemView.findViewById(R.id.iv_guide);
        }
    }
}
