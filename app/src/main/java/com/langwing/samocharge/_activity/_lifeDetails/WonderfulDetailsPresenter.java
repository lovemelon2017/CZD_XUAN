package com.langwing.samocharge._activity._lifeDetails;

import com.langwing.samocharge._base.BasePresenter;
import com.langwing.samocharge._fragment._dynamic._wonderfulLife.DatasUtil;
import com.langwing.samocharge._fragment._dynamic.bean.CommentConfig;
import com.langwing.samocharge._fragment._dynamic.bean.CommentItem;

public class WonderfulDetailsPresenter extends BasePresenter implements IWonderfulDetailsContract.IWonderfulDetailsPresenter {

    private IWonderfulDetailsContract.IWonderfulDetailsView iWonderfulDetailsView;

    public WonderfulDetailsPresenter(IWonderfulDetailsContract.IWonderfulDetailsView iWonderfulDetailsView) {
        super(iWonderfulDetailsView);
        this.iWonderfulDetailsView = iWonderfulDetailsView;
    }

    @Override
    public void addNewComment(String comment) {
        CommentItem publicComment = DatasUtil.createPublicComment(comment);
        iWonderfulDetailsView.setComment(0,publicComment);
    }

    @Override
    public void replyComment(String comment, CommentConfig commentConfig) {

    }

    @Override
    public void showEditTextBody() {
        if (iWonderfulDetailsView != null) {
            iWonderfulDetailsView.showEditTextBody();
        }
    }
}
