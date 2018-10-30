package com.langwing.samocharge._fragment._dynamic._companyDynamic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class CompanyDynamicFragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_company_dynamic;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        List<CompanyDynamic> companyDynamicList = new ArrayList<>();
        companyDynamicList.add(new CompanyDynamic());
        RecyclerView rvCompanyDynamic = view.findViewById(R.id.rv_company_dynamic);
        rvCompanyDynamic.setLayoutManager(new LinearLayoutManager(getActivity()));
        CompanyDynamicAdapter adapter = new CompanyDynamicAdapter(getActivity(), companyDynamicList);
        rvCompanyDynamic.setAdapter(adapter);
//        adapter.setOnItemClickListener(position -> toast("点击了：" + position));
    }

}
