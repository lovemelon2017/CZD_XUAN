package com.langwing.samocharge._activity._lifeDetails;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseRecyclerViewAdapter;
import com.langwing.samocharge._fragment._dynamic.bean.CommentItem;

import java.util.List;

public class ReplyAdapter extends BaseRecyclerViewAdapter<ReplyAdapter.ViewHolder> {

    private Context context;
    private List<CommentItem> replyList;

    public ReplyAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<CommentItem> replyList) {
        this.replyList = replyList;
    }

    @Override
    public void bindDate(ViewHolder holder, int position) {
        CommentItem commentItem = replyList.get(position);
        holder.tvReplyContent.setText(commentItem.getContent());
        holder.tvReplyName.setText(commentItem.getToReplyUser().getName());
        holder.tvCurrentName.setText(commentItem.getUser().getName());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reply, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return replyList == null ? 0 : replyList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tvReplyName;
        AppCompatTextView tvCurrentName;
        AppCompatTextView tvReplyContent;

        public ViewHolder(View itemView) {
            super(itemView);
            tvReplyName = itemView.findViewById(R.id.tv_reply_name);
            tvCurrentName = itemView.findViewById(R.id.tv_current_name);
            tvReplyContent = itemView.findViewById(R.id.tv_reply_content);
        }
    }
}
