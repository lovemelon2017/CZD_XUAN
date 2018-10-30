package com.langwing.samocharge._activity._album;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseRecyclerViewAdapter;
import com.langwing.samocharge._utils.ImageLoadUtil;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends BaseRecyclerViewAdapter<AlbumAdapter.ViewHolder> {

    private Context context;
    private List<Photo> photoList;
    private List<Photo> selectPhotoList = new ArrayList<>();

    public AlbumAdapter(Context context, List<Photo> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    @Override
    public void bindDate(ViewHolder holder, int position) {
        Photo photo = photoList.get(position);
        ImageLoadUtil.loadImgURL(holder.ivPhoto, photo.getPath());
        holder.cbSelect.setTag(photo);
        holder.cbSelect.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Photo selectPhoto = (Photo) buttonView.getTag();
            if (isChecked) {
                if (!selectPhotoList.contains(selectPhoto)) {
                    selectPhoto.setChecked(true);
                    selectPhotoList.add(selectPhoto);
                }
            } else {
                if (selectPhotoList.contains(selectPhoto)) {
                    selectPhoto.setChecked(false);
                    selectPhotoList.remove(selectPhoto);
                }
            }
        });

        holder.cbSelect.setChecked(selectPhotoList.contains(photo));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView ivPhoto;
        CheckBox cbSelect;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
            cbSelect = itemView.findViewById(R.id.cb_select);
        }
    }
}
