package com.langwing.samocharge._activity._album;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseBackActivity;

import java.util.ArrayList;
import java.util.List;

public class AlbumActivity extends BaseBackActivity {

    private List<Photo> photoList = new ArrayList<>();
    private AlbumAdapter adapter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_album;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("图片");
        RecyclerView rvAlbum = findViewById(R.id.rv_album);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        rvAlbum.setLayoutManager(manager);
        adapter = new AlbumAdapter(this, photoList);
        rvAlbum.setAdapter(adapter);
        getAllLocalPhotos();
    }

    private void getAllLocalPhotos() {
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            Photo photo;
            while (cursor.moveToNext()) {
                photo = new Photo();
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA));
                String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                photo.setPath(path);
                photo.setName(name);
                photoList.add(photo);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
