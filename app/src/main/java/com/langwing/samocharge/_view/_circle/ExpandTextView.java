package com.langwing.samocharge._view._circle;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.langwing.samocharge.R;


public class ExpandTextView extends LinearLayout {
    public static final int DEFAULT_MAX_LINES = 3;
    private TextView tvContent;
    private TextView tvPlus;
    private int showLines;
    private boolean isExpand;
    private Context context;

    public ExpandTextView(Context context) {
        this(context, null);
    }

    public ExpandTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initAttrs(attrs);
        initView();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ExpandTextView, 0, 0);
        try {
            showLines = typedArray.getInt(R.styleable.ExpandTextView_showLines, DEFAULT_MAX_LINES);
        } finally {
            typedArray.recycle();
        }
    }

    private void initView() {
        setOrientation(LinearLayout.VERTICAL);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_magic_text, this);
        tvContent = findViewById(R.id.tv_content);
        if (showLines > 0) {
            tvContent.setMaxLines(showLines);
        }

        tvPlus = findViewById(R.id.tv_plus);
        tvPlus.setOnClickListener(view -> {
            String textStr = tvPlus.getText().toString().trim();
            if ("全文".equals(textStr)) {
                tvContent.setMaxLines(Integer.MAX_VALUE);
                tvPlus.setText("收起");
                setExpand(true);
            } else {
                tvContent.setMaxLines(showLines);
                tvPlus.setText("全文");
                setExpand(false);
            }
            //通知外部状态已变更
            if (expandStatusListener != null) {
                expandStatusListener.statusChange(isExpand());
            }
        });
    }

    public void setText(final CharSequence content) {
        tvContent.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                // 避免重复监听
                tvContent.getViewTreeObserver().removeOnPreDrawListener(this);

                int linCount = tvContent.getLineCount();
                if (linCount > showLines) {

                    if (isExpand) {
                        tvContent.setMaxLines(Integer.MAX_VALUE);
                        tvPlus.setText("收起");
                    } else {
                        tvContent.setMaxLines(showLines);
                        tvPlus.setText("全文");
                    }
                    tvPlus.setVisibility(View.VISIBLE);
                } else {
                    tvPlus.setVisibility(View.GONE);
                }

                return true;
            }


        });
        tvContent.setText(content);
    }

    public void setExpand(boolean isExpand) {
        this.isExpand = isExpand;
    }

    public boolean isExpand() {
        return this.isExpand;
    }

    private ExpandStatusListener expandStatusListener;

    public void setExpandStatusListener(ExpandStatusListener listener) {
        this.expandStatusListener = listener;
    }

    public interface ExpandStatusListener {

        void statusChange(boolean isExpand);
    }

}
