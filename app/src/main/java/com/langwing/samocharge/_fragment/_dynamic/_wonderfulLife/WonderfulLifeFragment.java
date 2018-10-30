package com.langwing.samocharge._fragment._dynamic._wonderfulLife;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.langwing.samocharge.R;
import com.langwing.samocharge._activity._lifeDetails.WonderfulLifeDetailsActivity;
import com.langwing.samocharge._base.BaseFragment;
import com.langwing.samocharge._base.BaseRecyclerViewAdapter;
import com.langwing.samocharge._fragment._dynamic.bean.CircleItem;
import com.langwing.samocharge._fragment._dynamic.bean.CommentConfig;
import com.langwing.samocharge._fragment._dynamic.bean.CommentItem;
import com.langwing.samocharge._utils.CommonUtils;
import com.langwing.samocharge._utils.DensityUtil;
import com.langwing.samocharge._view._circle.CommentListView;

import java.util.List;

public class WonderfulLifeFragment extends BaseFragment implements IWonderfulContract.IWonderfulView, View.OnClickListener {

    private IWonderfulContract.IWonderfulPresenter presenter;
    private WonderfulLifeAdapter adapter;
    private LinearLayout llEditComment;
    private AppCompatEditText etComment;
    private CommentConfig commentConfig;
    private LinearLayoutManager manager;
    private int selectCircleItemH;
    private int selectCommentItemOffset;
    private RelativeLayout rlBody;
    private int currentKeyboardH;
    private int screenHeight;
    private int editTextBodyHeight;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_wonderful_life;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        rlBody = view.findViewById(R.id.rl_body);
        RecyclerView rvWonderfulLife = view.findViewById(R.id.rv_wonderful_life);
        manager = new LinearLayoutManager(getActivity());
        rvWonderfulLife.setLayoutManager(manager);
        llEditComment = view.findViewById(R.id.ll_edit_comment);
        etComment = view.findViewById(R.id.et_comment);
        AppCompatImageView ivSend = view.findViewById(R.id.iv_send);
        ivSend.setOnClickListener(this);
        adapter = new WonderfulLifeAdapter(getActivity());
        rvWonderfulLife.setAdapter(adapter);
        presenter = new WonderfulPresenter(this);
        adapter.setPresenter(presenter);
        presenter.loadData();

    }

    @Override
    public void initData(List<CircleItem> circleItemList) {
        adapter.setDatas(circleItemList);
        adapter.setOnItemClickListener((view, position) -> {
            CircleItem circleItem = circleItemList.get(position);
            toActivity(WonderfulLifeDetailsActivity.class, "circleItem", circleItem);
        });
    }

    @Override
    public void updateEditTextBodyVisible(int visibility, CommentConfig commentConfig) {
        this.commentConfig = commentConfig;
        llEditComment.setVisibility(visibility);

        measureCircleItemHighAndCommentItemOffset(commentConfig);

        if (View.VISIBLE == visibility) {
            etComment.requestFocus();
            // 弹出键盘
            CommonUtils.showSoftInput(getActivity(), etComment);
        } else if (View.GONE == visibility) {
            // 隐藏键盘
            CommonUtils.hideSoftInput(getActivity(), etComment);
        }
    }

    @Override
    public void addComment(int circlePosition, CommentItem newItem) {
        if (newItem != null) {
            CircleItem item = adapter.getDatas().get(circlePosition);
            item.getComments().add(newItem);
            adapter.notifyDataSetChanged();
        }
        etComment.setText("");
    }

    private void measureCircleItemHighAndCommentItemOffset(CommentConfig commentConfig) {
        if (commentConfig == null) {
            return;
        }
        int firstPosition = manager.findFirstVisibleItemPosition();
        // 只能返回当前可见区域（列表可滚动）的子项
        View selectCircleItem = manager.getChildAt(commentConfig.circlePosition - firstPosition);

        if (selectCircleItem != null) {
            selectCircleItemH = selectCircleItem.getHeight();
        }

        if (commentConfig.commentType == CommentConfig.Type.REPLY) {
            //回复评论的情况
            CommentListView commentLv = (CommentListView) selectCircleItem.findViewById(R.id.comment_list_view);
            if (commentLv != null) {
                //找到要回复的评论view,计算出该view距离所属动态底部的距离
                View selectCommentItem = commentLv.getChildAt(commentConfig.commentPosition);
                if (selectCommentItem != null) {
                    //选择的commentItem距选择的CircleItem底部的距离
                    selectCommentItemOffset = 0;
                    View parentView = selectCommentItem;
                    do {
                        int subItemBottom = parentView.getBottom();
                        parentView = (View) parentView.getParent();
                        if (parentView != null) {
                            selectCommentItemOffset += (parentView.getHeight() - subItemBottom);
                        }
                    } while (parentView != null && parentView != selectCircleItem);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_send:
                String comment = etComment.getText().toString().trim();
                if (presenter != null) {
                    if (comment.length() > 0) {
                        presenter.addComment(comment, commentConfig);
                        updateEditTextBodyVisible(View.GONE, null);
                    } else {
                        toast("评论内容不能为空");
                    }
                }
                break;
            default:
                break;
        }
    }
}
