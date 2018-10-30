package com.langwing.samocharge._activity._personalBalance;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._recharge.RechargeActivity;
import com.langwing.samocharge._activity._transactionRecord.TransactionRecordActivity;
import com.langwing.samocharge._base.BaseBackActivity;

/**
 * Create By WYJ on 2017/12/7.
 */
public class PersonalBalanceActivity extends BaseBackActivity implements View.OnClickListener {

    @Override
    public int getLayoutID() {
        return R.layout.activity_personal_balance;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.personal_balance);
        findViewById(R.id.tv_recharge).setVisibility(View.VISIBLE);
        findViewById(R.id.tv_recharge).setOnClickListener(this);
        AppCompatTextView tvTransactionDetails = findViewById(R.id.tv_right);
        tvTransactionDetails.setText("交易明细");
        tvTransactionDetails.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                toActivity(TransactionRecordActivity.class);
                break;
            case R.id.tv_recharge:
                Intent intent = new Intent(this, RechargeActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
