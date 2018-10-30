package com.langwing.store._homepage;


import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.langwing.store.BaseRecyclerViewAdapter;
import com.langwing.store.Constants;
import com.langwing.store.DensityUtil;
import com.langwing.store.R;
import com.langwing.store._goodsDetails.GoodsDetailsActivity;
import com.langwing.store._sign.SignActivity;


/**
 * To Change The World
 * 2017/12/20 11:52
 * Created by Mr.Wang
 */
public class HomepageFragment extends Fragment {

    public boolean showBackIcon = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        initView(view, savedInstanceState);
        return view;
    }

    private void initView(View view, Bundle savedInstanceState) {
        View titleView = view.findViewById(R.id.title);
        titleView.setBackgroundColor(getResources().getColor(Constants.titleBackgroundColor));
        TextView tvTitle = view.findViewById(R.id.tv_title);
        tvTitle.setTextColor(getResources().getColor(Constants.titleTextColor));
        ImageView ivBack = view.findViewById(R.id.iv_back);
        ivBack.setVisibility(showBackIcon ? View.VISIBLE : View.GONE);
        ivBack.setImageResource(Constants.backIcon);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        initRvGoods(view);
        TextView tvSign = view.findViewById(R.id.tv_sign);
        tvSign.setTextColor(getResources().getColor(Constants.titleTextColor));
        tvSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ittSign = new Intent(getContext(), SignActivity.class);
                startActivity(ittSign);
            }
        });
    }

    private void initRvGoods(View view) {
        RecyclerView rvGoods = view.findViewById(R.id.rv_goods);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvGoods.setLayoutManager(layoutManager);
        GoodsAdapter adapter = new GoodsAdapter(getContext());
        rvGoods.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Intent ittDetails = new Intent(getContext(), GoodsDetailsActivity.class);
                startActivity(ittDetails);
            }
        });
        rvGoods.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
                outRect.bottom = DensityUtil.dip2px(getContext(), 9);
                if (itemPosition != 0) {
                    if (view.getLeft() < view.getRight()) {
                        outRect.left = DensityUtil.dip2px(getContext(), 9);
                        outRect.right = DensityUtil.dip2px(getContext(), 2);
                    } else {
                        outRect.left = DensityUtil.dip2px(getContext(), 2);
                        outRect.right = DensityUtil.dip2px(getContext(), 9);
                    }
                }
            }
        });
    }
}