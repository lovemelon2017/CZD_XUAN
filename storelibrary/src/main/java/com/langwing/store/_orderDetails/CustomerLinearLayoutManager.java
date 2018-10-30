package com.langwing.store._orderDetails;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

import java.util.TreeMap;

/**
 * Created by WYJ on 2017/12/21.
 */

public class CustomerLinearLayoutManager extends LinearLayoutManager {

    private boolean isScrollEnable = true;

    public CustomerLinearLayoutManager(Context context) {
        super(context);
    }

    public CustomerLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public CustomerLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setScrollEnable(boolean scrollEnable) {
        this.isScrollEnable = scrollEnable;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnable && super.canScrollVertically();
    }
}
