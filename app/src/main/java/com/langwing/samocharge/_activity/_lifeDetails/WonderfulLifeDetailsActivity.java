package com.langwing.samocharge._activity._lifeDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.langwing.samocharge.R;
import com.langwing.samocharge._base.BaseBackActivity;
import com.langwing.samocharge._fragment._dynamic.bean.CircleItem;
import com.langwing.samocharge._fragment._dynamic.bean.CommentItem;
import com.langwing.samocharge._fragment._dynamic.bean.User;
import com.langwing.samocharge._utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class WonderfulLifeDetailsActivity extends BaseBackActivity implements View.OnClickListener, IWonderfulDetailsContract.IWonderfulDetailsView {

    private LinearLayout llEdit;
    private AppCompatEditText etComment;
    private AppCompatImageView ivSend;
    private WonderfulDetailsPresenter presenter;
    private WonderfulDetailsAdapter adapter;
    private boolean isReply = false;
    private LinearLayoutManager manager;
    private RecyclerView rvDetails;
    private int clickPosition;
    private List<CommentItem> commentItemList;

    @Override
    public int getLayoutID() {
        return R.layout.activity_wonderful_life_details;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle(R.string.details);
        CircleItem circleItem = (CircleItem) getIntent().getSerializableExtra("circleItem");
        commentItemList = circleItem.getComments();

        llEdit = findViewById(R.id.ll_edit_comment);
        etComment = findViewById(R.id.et_comment);
        ivSend = findViewById(R.id.iv_send);
        ivSend.setOnClickListener(this);
        rvDetails = findViewById(R.id.rv_details);
        manager = new LinearLayoutManager(this);
        rvDetails.setLayoutManager(manager);
        adapter = new WonderfulDetailsAdapter(this, circleItem);
        rvDetails.setAdapter(adapter);
        presenter = new WonderfulDetailsPresenter(this);
        adapter.setPresenter(presenter);
        adapter.setOnItemClickListener((view, position) -> {
            showEditTextBody();
            clickPosition = position;
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_send: // 添加新的评论 或 回复评论
                String comment = etComment.getText().toString().trim();
                if (comment.length() > 0) {

                    if (isReply) {
                        CommentItem replyCommentItem = new CommentItem();
                        replyCommentItem.setUser(new User("3", "马云", ""));
                        replyCommentItem.setContent(comment);
                        replyCommentItem.setToReplyUser(new User("2", "历史", ""));

                        CommentItem commentItem = commentItemList.get(clickPosition);
                        List<CommentItem> replyCommentItemList = commentItem.getReplyCommentItemList();
                        if (replyCommentItemList == null || replyCommentItemList.size() == 0) {
                            replyCommentItemList = new ArrayList<>();
                            commentItem.setReplyCommentItemList(replyCommentItemList);
                        }
                        replyCommentItemList.add(replyCommentItem);
                        adapter.notifyDataSetChanged();

                        etComment.setText("");
                        isReply = false;
                    } else {
                        // 添加新的评论
                        presenter.addNewComment(comment);
                    }
                    CommonUtils.hideSoftInput(this, etComment);
                } else {
                    toast("评论内容不能为空");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setComment(int circlePosition, CommentItem newItem) {
        if (newItem != null) {
            commentItemList.add(newItem);
            adapter.notifyDataSetChanged();
            manager.scrollToPositionWithOffset(commentItemList.size(), 0);
        }
        etComment.setText("");
    }

    @Override
    public void showEditTextBody() {
        isReply = true;
        etComment.requestFocus();
        CommonUtils.showSoftInput(this, etComment);
    }
}
