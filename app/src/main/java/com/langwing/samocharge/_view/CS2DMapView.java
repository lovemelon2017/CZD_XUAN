package com.langwing.samocharge._view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.TextureMapView;


/**
 * To Change The World
 * 2016/10/10 17:48
 * Created by Mr.Wang
 */
public class CS2DMapView extends TextureMapView implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {

    GestureDetector gestureDetector;

    public CS2DMapView(Context context) {
        super(context);
        if (gestureDetector == null) {
            gestureDetector = new GestureDetector(context, this);
            gestureDetector.setOnDoubleTapListener(this);
        }
    }

    public CS2DMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (gestureDetector == null) {
            gestureDetector = new GestureDetector(context, this);
            gestureDetector.setOnDoubleTapListener(this);
        }
    }

    public CS2DMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (gestureDetector == null) {
            gestureDetector = new GestureDetector(context, this);
            gestureDetector.setOnDoubleTapListener(this);
        }
    }

    public CS2DMapView(Context context, AMapOptions aMapOptions) {
        super(context, aMapOptions);
        if (gestureDetector == null) {
            gestureDetector = new GestureDetector(context, this);
            gestureDetector.setOnDoubleTapListener(this);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);//解决和viewpager事件冲突问题
        return gestureDetector.onTouchEvent(ev);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    //    private OnClickListener onClickListener;
//
//    @Override
//    public void setOnClickListener(OnClickListener onClickListener) {
//        this.onClickListener = onClickListener;
//    }
//
//
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        //      onClickListener.onClick(OAMapView.this);
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }
}
