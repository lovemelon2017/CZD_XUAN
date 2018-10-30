package com.langwing.samocharge._fragment._charging;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._recharge.RechargeActivity;
import com.langwing.samocharge._base.BaseFragment;
import com.langwing.samocharge._utils.MPermissionUtils;
import com.langwing.samocharge._view.WaterRippleView;

public class ChargingFragment extends BaseFragment implements View.OnClickListener, IChargingContract.IChargingView {

    private AppCompatTextView tvBalance;
    private AlertDialog alertDialog;
    private IChargingContract.IChargingPresenter chargingPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_charging;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.tv_recharge).setOnClickListener(this);
        tvBalance = view.findViewById(R.id.tv_balance);
        WaterRippleView waterRippleView = view.findViewById(R.id.water_ripple_view);
        waterRippleView.setRefreshTime(350);
        waterRippleView.setOnClickListener(this);

        chargingPresenter = new ChargingPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_recharge:
                startActivity(new Intent(getActivity(), RechargeActivity.class));
                break;
            case R.id.water_ripple_view:
                String balance = tvBalance.getText().toString().trim();
                if (balance.length() > 0 || Integer.parseInt(balance) > 0) {
                    chargingPresenter.requestCamera(this);
                } else {
                    showRechargeDialog();
                }
                break;
            case R.id.tv_go_recharge:
                startActivity(new Intent(getActivity(), RechargeActivity.class));
                alertDialog.dismiss();
                break;
            case R.id.tv_cancel:
                alertDialog.dismiss();
                break;
            default:
                break;
        }
    }

    private void showRechargeDialog() {
        if (null == alertDialog) {
            initRechargeDialog();
        }
        alertDialog.show();
    }

    private void initRechargeDialog() {
        alertDialog = new AlertDialog.Builder(getActivity(), R.style.RechargeDialogStyle).create();
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_recharge, null);
        view.findViewById(R.id.tv_go_recharge).setOnClickListener(this);
        view.findViewById(R.id.tv_cancel).setOnClickListener(this);
        alertDialog.setView(view);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
