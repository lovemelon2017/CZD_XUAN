package com.langwing.store._view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.langwing.store.R;


/**
 * 商品数量 + - 按钮
 */
public class AmountView extends LinearLayout implements View.OnClickListener {

    private ImageButton btnIncrease;
    private ImageButton btnDecrease;
    private TextView tvAmount;
    private int count = 1;

    public AmountView(Context context) {
        this(context, null);
    }

    public AmountView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_amount, this);
        btnIncrease = findViewById(R.id.btn_increase);
        btnDecrease = findViewById(R.id.btn_decrease);
        tvAmount = findViewById(R.id.tv_amount);
        btnIncrease.setOnClickListener(this);
        btnDecrease.setOnClickListener(this);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.AmountView);
        int btnWidth = typedArray.getDimensionPixelSize(R.styleable.AmountView_btnWidth, LayoutParams.WRAP_CONTENT);
        int tvWidth = typedArray.getDimensionPixelSize(R.styleable.AmountView_tvWidth, 80);
        int tvTextSize = typedArray.getDimensionPixelSize(R.styleable.AmountView_tvTextSize, 0);
        typedArray.recycle();

        LayoutParams params = new LayoutParams(btnWidth, LayoutParams.MATCH_PARENT);
        btnIncrease.setLayoutParams(params);
        btnDecrease.setLayoutParams(params);
        LayoutParams layoutParams = new LayoutParams(tvWidth, LayoutParams.MATCH_PARENT);
        tvAmount.setLayoutParams(layoutParams);
        if (tvTextSize != 0) {
            tvAmount.setTextSize(tvTextSize);
        }

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_increase) {
            count++;
            tvAmount.setText(String.valueOf(count));
            if (OnAmountChangeListener != null) {
                OnAmountChangeListener.getAmount(count);
            }

        } else if (i == R.id.btn_decrease) {
            if (count > 1) {
                count--;
                tvAmount.setText(String.valueOf(count));
                if (OnAmountChangeListener != null) {
                    OnAmountChangeListener.getAmount(count);
                }
            }

        }
    }

    private interface OnAmountChangeListener {
        void getAmount(int amount);
    }

    private OnAmountChangeListener OnAmountChangeListener;

    public void setOnAmountChangeListener(OnAmountChangeListener listener) {
        this.OnAmountChangeListener = listener;
    }

    public void setCount(int count) {
        this.count = count;
        tvAmount.setText(String.valueOf(this.count));
    }

}
