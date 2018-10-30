package com.langwing.samocharge._base;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.TextView;

import com.langwing.samocharge.R;


/**
 * To Change The World
 * 2017-04-28 16:06
 * Created by Mr.Wang
 * <p>
 * 所有带返回按钮的布局，该按钮点击事件在此控制
 */

public abstract class BaseBackActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setTitle(@StringRes int titleID) {
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(titleID);
    }

    public void setTitle(String title) {
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(title);
    }
}
