package com.langwing.store;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * To Change The World
 * 2017-12-28 15:12
 * Created by Mr.Wang
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        initTitle();
        initView(savedInstanceState);
    }

    private void initTitle() {
        View titleView = findViewById(R.id.title);
        titleView.setBackgroundColor(getResources().getColor(Constants.titleBackgroundColor));
        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setTextColor(getResources().getColor(Constants.titleTextColor));
        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setImageResource(Constants.backIcon);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public abstract void initView(Bundle savedInstanceState);

    public abstract int getLayoutID();
}
