package com.langwing.samocharge._view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.langwing.samocharge.R;

/**
 * Created by Administrator on 2017/12/6.
 */

public class ClearWriteEditText extends AppCompatEditText implements View.OnFocusChangeListener, TextWatcher {

    private Drawable clearDrawable;

    public ClearWriteEditText(Context context) {
        this(context, null);
    }

    public ClearWriteEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearWriteEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        clearDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.clear, null);
        clearDrawable.setBounds(0, 0, clearDrawable.getIntrinsicWidth(), clearDrawable.getIntrinsicHeight());
        setClearIconVisible(false);
        this.setOnFocusChangeListener(this);
        this.addTextChangedListener(this);
    }

    private void setClearIconVisible(boolean visible) {
        Drawable right = visible ? clearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
        setClearIconVisible(charSequence.length() > 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != getCompoundDrawables()[2]) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                boolean touchable = event.getX() > (getWidth() - getPaddingRight() - clearDrawable.getIntrinsicWidth())
                        && (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) {
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
