package com.langwing.samocharge._activity._lifeDetails;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._imagePager.ImagePagerActivity;
import com.langwing.samocharge._fragment._dynamic.bean.CircleItem;
import com.langwing.samocharge._fragment._dynamic.bean.CommentItem;
import com.langwing.samocharge._fragment._dynamic.bean.FavortItem;
import com.langwing.samocharge._fragment._dynamic.bean.PhotoInfo;
import com.langwing.samocharge._fragment._dynamic.bean.User;
import com.langwing.samocharge._view._circle.MultiImageView;
import com.langwing.samocharge._view._circle.PraiseListView;

import java.util.ArrayList;
import java.util.List;

public class WonderfulDetailsAdapter extends RecyclerView.Adapter<WonderfulDetailsAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private CircleItem circleItem;
    private final List<CommentItem> comments;
    private WonderfulDetailsPresenter presenter;

    public WonderfulDetailsAdapter(Context context, CircleItem circleItem) {
        this.context = context;
        this.circleItem = circleItem;
        comments = circleItem.getComments();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (1 == viewType) {
            view = LayoutInflater.from(context).inflate(R.layout.item_life_details_title, parent, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item__life_details, parent, false);
            view.setOnClickListener(this);
        }
        return new ViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (0 == position) {
            User user = circleItem.getUser();
            String content = circleItem.getContent();
            String createTime = circleItem.getCreateTime();
            List<PhotoInfo> photos = circleItem.getPhotos();
            List<FavortItem> favorters = circleItem.getFavorters();
            holder.tvName.setText(user.getName());
            holder.tvTime.setText(createTime);
            holder.tvContent.setText(content);

            if (photos != null && photos.size() > 0) {
                holder.multiImageView.setList(photos);
                holder.multiImageView.setOnItemClickListener((view, position1) -> {
                    ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(view.getMeasuredWidth(), view.getMeasuredHeight());

                    List<String> photoUrls = new ArrayList<>();
                    for (PhotoInfo photoInfo : photos) {
                        photoUrls.add(photoInfo.url);
                    }
                    ImagePagerActivity.startImagePagerActivity(context, photoUrls, position1, imageSize);
                });
                holder.multiImageView.setVisibility(View.VISIBLE);
            } else {
                holder.multiImageView.setVisibility(View.GONE);
            }

            if (favorters != null && favorters.size() > 0) {
                holder.praiseListView.setDatas(favorters);
                holder.praiseListView.setVisibility(View.VISIBLE);
            } else {
                holder.praiseListView.setVisibility(View.GONE);
            }

        } else {
            // 评论区
            int clickPosition = position - 1;
            holder.itemView.setTag(clickPosition);
            CommentItem commentItem = comments.get(clickPosition);
            String content = commentItem.getContent();
            User user = commentItem.getUser();
            if (user != null) {
                holder.tvCommentName.setText(user.getName());
            }
            holder.tvCommentContent.setText(content);

            // 评论回复
            List<CommentItem> replyCommentItemList = commentItem.getReplyCommentItemList();
            ReplyAdapter replyAdapter = new ReplyAdapter(context);
            holder.rvReply.setAdapter(replyAdapter);
            replyAdapter.setData(replyCommentItemList);
            replyAdapter.setOnItemClickListener((view, position12) -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(view, clickPosition);
                }
                presenter.showEditTextBody();
            });
        }
    }

    @Override
    public int getItemCount() {
        return comments.size() > 0 ? comments.size() + 1 : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (onItemClickListener != null) {
            onItemClickListener.onClick(v, position);
        }
    }

    public void setPresenter(WonderfulDetailsPresenter presenter) {
        this.presenter = presenter;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        // 头部
        AppCompatImageView ivHead;
        AppCompatTextView tvName;
        AppCompatTextView tvTime;
        AppCompatTextView tvContent;
        MultiImageView multiImageView;
        PraiseListView praiseListView;

        // 评论
        AppCompatImageView ivCommentHead;
        AppCompatTextView tvCommentName;
        AppCompatTextView tvCommentTime;
        AppCompatTextView tvCommentContent;
        RecyclerView rvReply;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            switch (viewType) {
                case 1:
                    ivHead = itemView.findViewById(R.id.iv_head);
                    tvName = itemView.findViewById(R.id.tv_name);
                    tvTime = itemView.findViewById(R.id.tv_time);
                    tvContent = itemView.findViewById(R.id.tv_content);
                    multiImageView = itemView.findViewById(R.id.multi_image_view);
                    praiseListView = itemView.findViewById(R.id.praise_list_view);
                    break;
                default:
                    ivCommentHead = itemView.findViewById(R.id.iv_head);
                    tvCommentName = itemView.findViewById(R.id.tv_name);
                    tvCommentTime = itemView.findViewById(R.id.tv_time);
                    tvCommentContent = itemView.findViewById(R.id.tv_comment);
                    rvReply = itemView.findViewById(R.id.rv_reply);
                    rvReply.setLayoutManager(new LinearLayoutManager(context));
                    break;
            }
        }
    }

    interface onItemClickListener {
        void onClick(View view, int position);
    }

    onItemClickListener onItemClickListener;

    public void setOnItemClickListener(WonderfulDetailsAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
