package com.langwing.samocharge._fragment._dynamic._wonderfulLife;

import android.view.View;

import com.langwing.samocharge._base.BasePresenter;
import com.langwing.samocharge._fragment._dynamic.bean.CircleItem;
import com.langwing.samocharge._fragment._dynamic.bean.CommentConfig;
import com.langwing.samocharge._fragment._dynamic.bean.CommentItem;

import java.util.List;

public class WonderfulPresenter extends BasePresenter implements IWonderfulContract.IWonderfulPresenter {

    private IWonderfulContract.IWonderfulView iWonderfulView;

    public WonderfulPresenter(IWonderfulContract.IWonderfulView iWonderfulView) {
        super(iWonderfulView);
        this.iWonderfulView = iWonderfulView;
    }

    @Override
    public void loadData() {
        List<CircleItem> circleItemList = DatasUtil.createCircleDatas();
        if (iWonderfulView != null) {
            iWonderfulView.initData(circleItemList);
        }
    }

    @Override
    public void showEditTextBody(CommentConfig commentConfig) {
        if (iWonderfulView != null) {
            iWonderfulView.updateEditTextBodyVisible(View.VISIBLE, commentConfig);
        }
    }

    @Override
    public void addComment(String comment, CommentConfig commentConfig) {
        CommentItem newItem = null;
        if (commentConfig.commentType == CommentConfig.Type.PUBLIC) {
            newItem = DatasUtil.createPublicComment(comment); // 创建新建评论
        } else if (commentConfig.commentType == CommentConfig.Type.REPLY) {
            newItem = DatasUtil.createReplyComment(commentConfig.replyUser, comment); // 创建回复评论
        }
        if (iWonderfulView != null) {
            iWonderfulView.addComment(commentConfig.circlePosition, newItem);
        }
    }
}
