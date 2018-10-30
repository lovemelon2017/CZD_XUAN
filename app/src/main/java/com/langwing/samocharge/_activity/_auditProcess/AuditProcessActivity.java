package com.langwing.samocharge._activity._auditProcess;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseBackActivity;

public class AuditProcessActivity extends BaseBackActivity {

    @Override
    public int getLayoutID() {
        return R.layout.activity_audit_process;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.audit_process);
    }
}
