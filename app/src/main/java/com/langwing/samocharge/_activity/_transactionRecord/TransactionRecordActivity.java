package com.langwing.samocharge._activity._transactionRecord;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseBackActivity;

/**
 * Create By WYJ on 2017/12/7.
 */
public class TransactionRecordActivity extends BaseBackActivity {

    @Override
    public int getLayoutID() {
        return R.layout.activity_transaction_record;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.transaction_record);

        RecyclerView rvRecord = findViewById(R.id.rv_transaction_record);
        rvRecord.setLayoutManager(new LinearLayoutManager(this));
//        rvRecord.setAdapter(new TransactionRecordAdapter(this));
    }
}
