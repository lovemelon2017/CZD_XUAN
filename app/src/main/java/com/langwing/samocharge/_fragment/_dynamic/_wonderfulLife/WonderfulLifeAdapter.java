package com.langwing.samocharge._fragment._dynamic._wonderfulLife;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._imagePager.ImagePagerActivity;
import com.langwing.samocharge._base.BaseRecyclerViewAdapter;
import com.langwing.samocharge._fragment._dynamic.bean.CircleItem;
import com.langwing.samocharge._fragment._dynamic.bean.CommentConfig;
import com.langwing.samocharge._fragment._dynamic.bean.CommentItem;
import com.langwing.samocharge._fragment._dynamic.bean.FavortItem;
import com.langwing.samocharge._fragment._dynamic.bean.PhotoInfo;
import com.langwing.samocharge._fragment._dynamic.bean.User;
import com.langwing.samocharge._view._circle.CommentListView;
import com.langwing.samocharge._view._circle.ExpandTextView;
import com.langwing.samocharge._view._circle.MultiImageView;
import com.langwing.samocharge._view._circle.PraiseListView;

import java.util.ArrayList;
import java.util.List;

public class WonderfulLifeAdapter extends BaseRecyclerViewAdapter<WonderfulLifeAdapter.ViewHolder> {

    private Context context;
    private List<CircleItem> circleItemList;
    private IWonderfulContract.IWonderfulPresenter presenter;

    public WonderfulLifeAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<CircleItem> circleItemList) {
        this.circleItemList = circleItemList;
        notifyDataSetChanged();
    }

    public List<CircleItem> getDatas() {
        return this.circleItemList;
    }

    public void setPresenter(IWonderfulContract.IWonderfulPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wonderful_life, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void bindDate(ViewHolder holder, int circlePosition) {
        CircleItem circleItem = circleItemList.get(circlePosition);
        User user = circleItem.getUser();
        String name = user.getName();
        String headUrl = user.getHeadUrl();
        String content = circleItem.getContent();
        String createTime = circleItem.getCreateTime();
        List<FavortItem> favorters = circleItem.getFavorters();
        List<PhotoInfo> photos = circleItem.getPhotos();
        List<CommentItem> comments = circleItem.getComments();
        boolean hasFavort = circleItem.hasFavort();
        boolean hasComment = circleItem.hasComment();

        Glide.with(context)
                .load(headUrl)
                .into(holder.ivHead);
        holder.tvName.setText(name);
        holder.tvTime.setText(createTime);
        if (content != null && content.length() > 0) {
            holder.tvExpand.setExpand(circleItem.isExpand());
            holder.tvExpand.setExpandStatusListener(isExpand -> {
                circleItem.setExpand(isExpand);
            });
            holder.tvExpand.setText(content);
            holder.tvExpand.setVisibility(View.VISIBLE);
        } else {
            holder.tvExpand.setVisibility(View.GONE);
        }

        // 评论和点赞
        if (hasFavort || hasComment) {
            // 处理点赞列表
            if (hasFavort) {
                holder.praiseListView.setDatas(favorters);
                holder.praiseListView.setVisibility(View.VISIBLE);
            } else {
                holder.praiseListView.setVisibility(View.GONE);
            }

            // 处理评论列表
            if (hasComment) {
                holder.commentListView.setOnItemClickListener(position1 -> {
                    CommentItem commentItem = comments.get(position1);
                    if (DatasUtil.curUser.getId().equals(commentItem.getUser().getId())) {
                    } else {
                        if (presenter != null) {
//                            CommentConfig commentConfig = new CommentConfig();
//                            commentConfig.circlePosition = circlePosition;
//                            commentConfig.commentPosition = position1;
//                            commentConfig.commentType = CommentConfig.Type.REPLY;
//                            commentConfig.replyUser = commentItem.getUser();
//                            presenter.showEditTextBody(commentConfig);
                        }
                    }
                });

                holder.commentListView.setDatas(comments);
                holder.commentListView.setVisibility(View.VISIBLE);
            } else {
                holder.commentListView.setVisibility(View.GONE);
            }

            holder.llFavorComment.setVisibility(View.VISIBLE);
        } else {
            holder.llFavorComment.setVisibility(View.GONE);
        }
        holder.viewLine.setVisibility(hasFavort && hasComment ? View.VISIBLE : View.GONE);
        // 处理图片
        if (photos != null && photos.size() > 0) {
            holder.ivMulti.setList(photos);
            holder.ivMulti.setOnItemClickListener((view, position) -> {
                ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(view.getMeasuredWidth(), view.getMeasuredHeight());

                List<String> photoUrls = new ArrayList<>();
                for (PhotoInfo photoInfo : photos) {
                    photoUrls.add(photoInfo.url);
                }
                ImagePagerActivity.startImagePagerActivity(context, photoUrls, position, imageSize);
            });
            holder.ivMulti.setVisibility(View.VISIBLE);
        } else {
            holder.ivMulti.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return circleItemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        AppCompatImageView ivHead;
        AppCompatTextView tvName;
        AppCompatTextView tvTime;
        ExpandTextView tvExpand;
        MultiImageView ivMulti;
        LinearLayout llFavorComment;
        PraiseListView praiseListView;
        CommentListView commentListView;
        View viewLine;
        AppCompatTextView tvAddComment;
        AppCompatTextView tvPraise;

        public ViewHolder(View itemView) {
            super(itemView);
            ivHead = itemView.findViewById(R.id.iv_head);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvExpand = itemView.findViewById(R.id.tv_expand);
            ivMulti = itemView.findViewById(R.id.multi_image_view);
            llFavorComment = itemView.findViewById(R.id.ll_favor_comment);
            praiseListView = itemView.findViewById(R.id.praise_list_view);
            commentListView = itemView.findViewById(R.id.comment_list_view);
            viewLine = itemView.findViewById(R.id.view_line);
            tvAddComment = itemView.findViewById(R.id.tv_add_comment);
            tvPraise = itemView.findViewById(R.id.tv_praise);

            tvAddComment.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_add_comment:
//                    if (presenter != null) {
//                        CommentConfig config = new CommentConfig();
//                        config.circlePosition = getAdapterPosition();
//                        config.commentType = CommentConfig.Type.PUBLIC;
//                        presenter.showEditTextBody(config);
//                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

}
