package com.langwing.samocharge._fragment._dynamic._companyDynamic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.langwing.samocharge.R;

import java.util.List;

public class CompanyDynamicAdapter extends RecyclerView.Adapter<CompanyDynamicAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<CompanyDynamic> companyDynamicList;

    public CompanyDynamicAdapter(Context context, List<CompanyDynamic> companyDynamicList) {
        this.context = context;
        this.companyDynamicList = companyDynamicList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (1 == viewType) {
            view = LayoutInflater.from(context).inflate(R.layout.item_company_dynamic_title, parent, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_company_dynamic, parent, false);
            view.setOnClickListener(this);
        }
        return new ViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(position - 1);
    }

    @Override
    public int getItemCount() {
        return companyDynamicList.size() > 0 ? companyDynamicList.size() + 1 : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (onItemClickListener != null) {
            onItemClickListener.onClick(position);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view, int viewType) {
            super(view);
        }
    }

    interface onItemClickListener {
        void onClick(int position);
    }

    onItemClickListener onItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
