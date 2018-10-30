package com.langwing.samocharge._fragment._serve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._applyForPile.ApplyForPileActivity;
import com.langwing.samocharge._activity._collaborateWithNet.CollaborateWithNetActivity;
import com.langwing.samocharge._activity._development.DevelpmentActivity;
import com.langwing.samocharge._activity._partnerControl.PartnerControlActivity;
import com.langwing.samocharge._activity._partnerProtocol.PartnerProtocolActivity;
import com.langwing.samocharge._activity._web.WebActivity;
import com.langwing.samocharge._base.BaseFragment;
import com.langwing.samocharge._utils.Constants;
import com.langwing.samocharge._utils.URL;


public class ServeFragment extends BaseFragment implements View.OnClickListener {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_service;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.tv_become_partner).setOnClickListener(this);
        view.findViewById(R.id.tv_apply_pile).setOnClickListener(this);
        view.findViewById(R.id.tv_into_network).setOnClickListener(this);
        view.findViewById(R.id.tv_partner_advisory).setOnClickListener(this);
        view.findViewById(R.id.tv_car_model).setOnClickListener(this);
        view.findViewById(R.id.tv_auto_finance).setOnClickListener(this);
        view.findViewById(R.id.tv_business_plan).setOnClickListener(this);
        view.findViewById(R.id.tv_rent_purchase).setOnClickListener(this);
        view.findViewById(R.id.tv_driving).setOnClickListener(this);
        view.findViewById(R.id.tv_car_insurance_purchase).setOnClickListener(this);
        view.findViewById(R.id.tv_service).setOnClickListener(this);
        view.findViewById(R.id.tv_illegal_inquiry).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_become_partner:
                toActivity(PartnerProtocolActivity.class);
                break;
            case R.id.tv_apply_pile:
                toActivity(ApplyForPileActivity.class);
                break;
            case R.id.tv_into_network:
                toActivity(CollaborateWithNetActivity.class);
                break;
            case R.id.tv_partner_advisory:
                toActivity(PartnerControlActivity.class);
                break;
            case R.id.tv_car_model:
                toActivity(DevelpmentActivity.class, "name", "热销车型");
                break;
            case R.id.tv_auto_finance:
                toActivity(DevelpmentActivity.class, "name", "金融服务");
                break;
            case R.id.tv_business_plan:
                toActivity(DevelpmentActivity.class, "name", "企业方案");
                break;
            case R.id.tv_rent_purchase:
                toActivity(DevelpmentActivity.class, "name", "以租代购");
                break;
            case R.id.tv_driving:
                toActivity(DevelpmentActivity.class, "name", "代驾");
                break;
            case R.id.tv_car_insurance_purchase:
                toActivity(DevelpmentActivity.class, "name", "车险购买");
                break;
            case R.id.tv_service:
                toActivity(DevelpmentActivity.class, "name", "保养与维修");
                break;
            case R.id.tv_illegal_inquiry:
                toActivity(DevelpmentActivity.class, "name", "违章查询");
                break;
            default:
                break;
        }
    }
}
