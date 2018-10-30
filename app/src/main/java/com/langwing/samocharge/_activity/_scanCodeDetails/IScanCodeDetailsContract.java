package com.langwing.samocharge._activity._scanCodeDetails;

import com.langwing.samocharge._base.IBasePresenter;
import com.langwing.samocharge._base.IBaseView;

/**
 * Created by Wyj
 * on 2017/11/21.
 */

public interface IScanCodeDetailsContract {

    interface IScanCodeDetailsView extends IBaseView {

        void initRvAmount();

        void initRvElectricity();
    }

    interface IScanCodeDetailsPresenter extends IBasePresenter {

    }
}
