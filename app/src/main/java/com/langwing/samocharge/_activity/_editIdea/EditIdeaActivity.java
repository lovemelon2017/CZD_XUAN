package com.langwing.samocharge._activity._editIdea;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._album.AlbumActivity;
import com.langwing.samocharge._base.BaseBackActivity;

public class EditIdeaActivity extends BaseBackActivity implements View.OnClickListener {

    @Override
    public int getLayoutID() {
        return R.layout.activity_edit_idea;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("写想法");
        AppCompatTextView tvSend = findViewById(R.id.tv_right);
        tvSend.setVisibility(View.VISIBLE);
        tvSend.setText("发布");
        findViewById(R.id.tv_album).setOnClickListener(this);
        findViewById(R.id.tv_take_photo).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_album:
                toActivity(AlbumActivity.class);
                break;
            case R.id.tv_take_photo:
                break;
            default:
                break;
        }
    }
}
