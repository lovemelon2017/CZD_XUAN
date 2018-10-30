package com.langwing.samocharge._activity._lifeDetails;

import com.langwing.samocharge._base.IBasePresenter;
import com.langwing.samocharge._base.IBaseView;
import com.langwing.samocharge._fragment._dynamic.bean.CommentConfig;
import com.langwing.samocharge._fragment._dynamic.bean.CommentItem;

public interface IWonderfulDetailsContract {

    interface IWonderfulDetailsView extends IBaseView {

        void setComment(int circlePosition, CommentItem newItem);

        void showEditTextBody();
    }

    interface IWonderfulDetailsPresenter extends IBasePresenter {

        void addNewComment(String comment);

        void replyComment(String comment, CommentConfig commentConfig);

        void showEditTextBody();
    }
}
