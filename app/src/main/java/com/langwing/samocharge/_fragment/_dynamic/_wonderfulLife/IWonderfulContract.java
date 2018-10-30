package com.langwing.samocharge._fragment._dynamic._wonderfulLife;

import com.langwing.samocharge._base.IBasePresenter;
import com.langwing.samocharge._base.IBaseView;
import com.langwing.samocharge._fragment._dynamic.bean.CircleItem;
import com.langwing.samocharge._fragment._dynamic.bean.CommentConfig;
import com.langwing.samocharge._fragment._dynamic.bean.CommentItem;

import java.util.List;

public interface IWonderfulContract {

    interface IWonderfulView extends IBaseView {

        void initData(List<CircleItem> circleItemList);

        void updateEditTextBodyVisible(int visibility, CommentConfig commentConfig);

        void addComment(int circlePosition, CommentItem newItem);
    }

    interface IWonderfulPresenter extends IBasePresenter {

        void loadData();

        void showEditTextBody(CommentConfig commentConfig);

        void addComment(String comment, CommentConfig commentConfig);
    }
}
