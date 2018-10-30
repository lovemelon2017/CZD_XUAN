package com.langwing.samocharge._activity._stationNews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseBackActivity;

import java.util.ArrayList;
import java.util.List;


public class StationNewsActivity extends BaseBackActivity {

    private List<Message> messageList = new ArrayList<>();

    @Override
    public int getLayoutID() {
        return R.layout.activity_station_news;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.message);
        initData();
        RecyclerView rvMessage = findViewById(R.id.rv_message);
        rvMessage.setLayoutManager(new LinearLayoutManager(this));
        StationNewsAdapter adapter = new StationNewsAdapter(this, messageList);
        rvMessage.setAdapter(adapter);
    }

    private void initData() {
        Message message;
        for (int i = 0; i < 9; i++) {
            message = new Message();
            if (i % 3 == 0) {
                message.setType("normal");
            } else if (i % 3 == 1) {
                message.setType("payment");
            } else {
                message.setType("order");
            }
            messageList.add(message);
        }
    }
}
