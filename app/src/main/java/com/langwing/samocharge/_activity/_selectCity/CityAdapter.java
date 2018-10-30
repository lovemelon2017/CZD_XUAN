package com.langwing.samocharge._activity._selectCity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseRecyclerViewAdapter;

import java.util.List;

public class CityAdapter extends BaseRecyclerViewAdapter<CityAdapter.ViewHolder> {

    private Context context;
    private List<City.CitiesBean> citiesBeanList;

    public CityAdapter(Context context, List<City.CitiesBean> citiesBeanList) {
        this.context = context;
        this.citiesBeanList = citiesBeanList;
    }

    @Override
    public void bindDate(ViewHolder holder, int position) {
        City.CitiesBean citiesBean = citiesBeanList.get(position);
        boolean firtCityInProvince = citiesBean.isFirtCityInProvince();
        holder.tvCityName.setText(citiesBean.getCity_name());
        holder.tvProvinceName.setText(citiesBean.getProvinceName());
        if (firtCityInProvince) {
            holder.tvProvinceName.setVisibility(View.VISIBLE);
            holder.view.setVisibility(View.GONE);
        } else {
            holder.tvProvinceName.setVisibility(View.GONE);
            holder.view.setVisibility(View.VISIBLE);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_city, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return citiesBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tvProvinceName;
        AppCompatTextView tvCityName;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            tvProvinceName = itemView.findViewById(R.id.tv_provinceName);
            tvCityName = itemView.findViewById(R.id.tv_cityName);
            view = itemView.findViewById(R.id.line);
        }
    }
}
