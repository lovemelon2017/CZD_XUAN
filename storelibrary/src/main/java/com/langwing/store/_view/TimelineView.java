package com.langwing.store._view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.langwing.store.R;
import com.langwing.store._utils.DensityUtil;


public class TimelineView extends View {

    private Drawable marker;
    private Drawable startLine;
    private Drawable endLine;
    private int markerSize;
    private int lineSize;
    private int lineOrientation;
    private int linePadding;
    private boolean markerInCenter;

    private Rect bounds;
    private Context context;

    public TimelineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.timeline_style);
        marker = typedArray.getDrawable(R.styleable.timeline_style_marker);
        startLine = typedArray.getDrawable(R.styleable.timeline_style_line);
        endLine = typedArray.getDrawable(R.styleable.timeline_style_line);
        markerSize = typedArray.getDimensionPixelSize(R.styleable.timeline_style_markerSize, DensityUtil.dip2px(context, 20));
        lineSize = typedArray.getDimensionPixelSize(R.styleable.timeline_style_lineSize, DensityUtil.dip2px(context, 2));
        lineOrientation = typedArray.getInt(R.styleable.timeline_style_lineOrientation, 1);
        linePadding = typedArray.getDimensionPixelSize(R.styleable.timeline_style_linePadding, 0);
        markerInCenter = typedArray.getBoolean(R.styleable.timeline_style_markerInCenter, true);
        typedArray.recycle();

        if (marker == null) {
            marker = ContextCompat.getDrawable(context, R.drawable.marker);
        }

        if (startLine == null && endLine == null) {
            startLine = new ColorDrawable(ContextCompat.getColor(context, android.R.color.darker_gray));
            endLine = new ColorDrawable(ContextCompat.getColor(context, android.R.color.darker_gray));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int w = markerSize + getPaddingLeft() + getPaddingRight();
        int h = markerSize + getPaddingTop() + getPaddingBottom();

        int widthSize = resolveSizeAndState(w, widthMeasureSpec, 0);
        int heightSize = resolveSizeAndState(h, heightMeasureSpec, 0);

        setMeasuredDimension(widthSize, heightSize);
        initDrawable();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initDrawable();
    }

    private void initDrawable() {
        int pLeft = getPaddingLeft();
        int pRight = getPaddingRight();
        int pTop = getPaddingTop();
        int pBottom = getPaddingBottom();

        // 当前自定义 View 的宽高
        int width = getWidth();
        int height = getHeight();

        int cWidth = width - pLeft - pRight;// Circle width
        int cHeight = height - pTop - pBottom;

        int markSize = Math.min(markerSize, Math.min(cWidth, cHeight));

        if (markerInCenter) {

            if (marker != null) {
                marker.setBounds((width / 2) - (markSize / 2), (height / 2) - (markSize / 2), (width / 2) + (markSize / 2), (height / 2) + (markSize / 2));
                bounds = marker.getBounds();
            }

        } else {

            if (marker != null) {
                marker.setBounds(pLeft, pTop, pLeft + markSize, pTop + markSize);
                bounds = marker.getBounds();
            }
        }

        int centerX = bounds.centerX();
        int lineLeft = centerX - (lineSize >> 1);

        if (lineOrientation == 0) {

            if (startLine != null) {
                startLine.setBounds(0, pTop + (bounds.height() / 2), bounds.left - linePadding, (bounds.height() / 2) + pTop + lineSize);
            }

            if (endLine != null) {
                endLine.setBounds(bounds.right + linePadding, pTop + (bounds.height() / 2), width, (bounds.height() / 2) + pTop + lineSize);
            }
        } else {

            if (startLine != null) {
                startLine.setBounds(lineLeft, 0, lineSize + lineLeft, bounds.top - linePadding);
            }

            if (endLine != null) {
                endLine.setBounds(lineLeft, bounds.bottom + linePadding, lineSize + lineLeft, height);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (marker != null) {
            marker.draw(canvas);
        }

        if (startLine != null) {
            startLine.draw(canvas);
        }

        if (endLine != null) {
            endLine.draw(canvas);
        }
    }

    public void setMarker(Drawable marker) {
        this.marker = marker;
        initDrawable();
    }

    public void setMarker(Drawable marker, int color) {
        this.marker = marker;
        this.marker.setColorFilter(color, PorterDuff.Mode.SRC);
        initDrawable();
    }

    public void setMarkerColor(int color) {
        marker.setColorFilter(color, PorterDuff.Mode.SRC);
        initDrawable();
    }

    public void setStartLine(int color, int viewType) {
        startLine = new ColorDrawable(color);
        initLine(viewType);
    }

    public void setEndLine(int color, int viewType) {
        endLine = new ColorDrawable(color);
        initLine(viewType);
    }

    public void setMarkerSize(int markerSize) {
        this.markerSize = markerSize;
        initDrawable();
    }

    public void setLineSize(int lineSize) {
        this.lineSize = lineSize;
        initDrawable();
    }

    public void setLinePadding(int padding) {
        linePadding = padding;
        initDrawable();
    }

    private void setStartLine(Drawable startLine) {
        this.startLine = startLine;
        initDrawable();
    }

    private void setEndLine(Drawable endLine) {
        this.endLine = endLine;
        initDrawable();
    }

    public void initLine(int viewType) {
        if (viewType == LineType.BEGIN) {
            setStartLine(null);
        } else if (viewType == LineType.END) {
            setEndLine(null);
        } else if (viewType == LineType.ONLYONE) {
            setStartLine(null);
            setEndLine(null);
        }
        initDrawable();
    }

    public class LineType {
        public static final int NORMAL = 4;
        public static final int BEGIN = 1;
        public static final int END = 2;
        public static final int ONLYONE = 3;
    }

    public static int getTimeLineViewType(int position, int totalSize) {
        if (totalSize == 1) {
            return LineType.ONLYONE;
        } else if (position == 0) {
            return LineType.BEGIN;
        } else if (position == totalSize - 1) {
            return LineType.END;
        } else {
            return LineType.NORMAL;
        }
    }
}
