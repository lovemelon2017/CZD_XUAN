package com.langwing.samocharge._fragment._dynamic;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RadioGroup;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._editIdea.EditIdeaActivity;
import com.langwing.samocharge._base.BaseFragment;
import com.langwing.samocharge._fragment._dynamic._companyDynamic.CompanyDynamicFragment;
import com.langwing.samocharge._fragment._dynamic._wonderfulLife.WonderfulLifeFragment;
import com.langwing.samocharge._utils.DD;


public class DynamicFragment extends BaseFragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private CompanyDynamicFragment companyDynamicFragment;
    private WonderfulLifeFragment wonderfulLifeFragment;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_dynamic;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        wonderfulLifeFragment = new WonderfulLifeFragment();
        companyDynamicFragment = new CompanyDynamicFragment();
        view.findViewById(R.id.iv_add).setOnClickListener(this);
        RadioGroup rgDynamic = view.findViewById(R.id.rg_dynamic);
        rgDynamic.setOnCheckedChangeListener(this);
        getChildFragmentManager().beginTransaction().add(R.id.fl_container,companyDynamicFragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add:
                toActivity(EditIdeaActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        switch (checkedId) {
            case R.id.rb_company_dynamic:
                if (!wonderfulLifeFragment.isHidden()) {
                    fragmentTransaction.hide(wonderfulLifeFragment);
                }
                if (companyDynamicFragment.isAdded()) {
                    fragmentTransaction.show(companyDynamicFragment);
                } else {
                    fragmentTransaction.add(R.id.fl_container, companyDynamicFragment);
                    fragmentTransaction.show(companyDynamicFragment);
                }
                break;
            case R.id.rb_wonderful_life:
                if (!companyDynamicFragment.isHidden()) {
                    fragmentTransaction.hide(companyDynamicFragment);
                }
                if (wonderfulLifeFragment.isAdded()) {
                    fragmentTransaction.show(wonderfulLifeFragment);
                } else {
                    fragmentTransaction.add(R.id.fl_container, wonderfulLifeFragment);
                    fragmentTransaction.show(wonderfulLifeFragment);
                }
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }
}
