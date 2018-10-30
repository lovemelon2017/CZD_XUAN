package com.langwing.samocharge._activity._watchBigPicture;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bumptech.glide.Glide;
import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseActivity;
import com.langwing.samocharge._base.BaseBackActivity;
import com.langwing.samocharge._utils.DD;
import com.langwing.samocharge._view._photoView.PhotoView;

public class WatchPictureActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public int getLayoutID() {
        return R.layout.activity_watch_picture;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        findViewById(R.id.iv_back).setOnClickListener(this);
        String data = getIntent().getStringExtra("data");
        if (data != null) {
            Uri uri = Uri.parse(data);
            if (uri != null) {
                PhotoView photoView = findViewById(R.id.iv_big_picture);
                Glide.with(this)
                        .load(uri)
                        .thumbnail(0.1f)
                        .into(photoView);
            }
        } else {
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
