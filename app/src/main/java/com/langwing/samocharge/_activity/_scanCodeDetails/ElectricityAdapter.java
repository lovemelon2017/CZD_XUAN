package com.langwing.samocharge._activity._scanCodeDetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wyj
 * on 2017/11/21.
 */

public class ElectricityAdapter extends BaseRecyclerViewAdapter<ElectricityAdapter.ViewHolder> {

    private Context context;
    private List<String> stringList = new ArrayList<>();
    private int selectPosition = -1;

    public ElectricityAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    @Override
    public void bindDate(ViewHolder holder, int position) {
//        holder.cbElectricity.setText(stringList.get(position));
//        if (selectPosition == position) {
//            holder.cbElectricity.setChecked(true);
//        } else {
//            holder.cbElectricity.setChecked(false);
//        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_amount, parent, false);
        return new ViewHolder(view);
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
//        CheckBox cbElectricity;

        public ViewHolder(View itemView) {
            super(itemView);
//            cbElectricity = itemView.findViewById(R.id.cb_amount);
        }
    }
}
