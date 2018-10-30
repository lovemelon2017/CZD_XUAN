package com.langwing.samocharge._view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * To Change The World
 * 2016/9/12 15:49
 * Created by Mr.Wang
 */
public class CSRadioGroup extends RadioGroup {

    public CSRadioGroup(Context context) {
        super(context);
    }

    public CSRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void setCheckedStateForView(int viewId) {
        View checkedView = findViewById(viewId);
        if (checkedView != null && checkedView instanceof RadioButton) {
            ((RadioButton) checkedView).setChecked(true);
        }
    }
}
