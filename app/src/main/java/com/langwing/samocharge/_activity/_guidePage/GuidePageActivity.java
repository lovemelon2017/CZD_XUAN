package com.langwing.samocharge._activity._guidePage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._login.LoginActivity;
import com.langwing.samocharge._base.BaseActivity;
import com.langwing.samocharge._base.BaseBackActivity;
import com.langwing.samocharge._base.BaseRecyclerViewAdapter;

public class GuidePageActivity extends BaseActivity implements BaseRecyclerViewAdapter.ItemClickListener {

    @Override
    public int getLayoutID() {
        return R.layout.activity_guide_page;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        RecyclerView rvGuide = findViewById(R.id.rvGuide);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false);
        rvGuide.setLayoutManager(manager);
        GuidePageAdapter adapter = new GuidePageAdapter(this);
        rvGuide.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rvGuide);
    }

    @Override
    public void onItemClickListener(View view, int position) {
        if (position == 2) {
            toActivity(LoginActivity.class);
        }
    }
}
