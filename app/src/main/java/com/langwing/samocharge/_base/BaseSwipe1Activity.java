package com.langwing.samocharge._base;


import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public abstract class BaseSwipe1Activity extends BaseActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {
    GestureDetector mGestureDetector;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenWidth = getScreenWidth();
        contentView = this.getWindow().getDecorView();
        mGestureDetector = new GestureDetector(this, this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mGestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.i("==>onShowPress<==", "marginX:" + marginX + "  e1.getAction():" + e.getAction());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        // Log.i("==>onScroll<==", "marginX:" + marginX + "  e1.getAction():" + e1.getAction() + "   e2.getAction():" + e2.getAction());
        if (Math.abs(distanceY) >= Math.abs(distanceX)) {//这里是避免有scroolview的页面上划也关闭
            return false;
        } else {
            marginX = (int) (marginX - distanceX);
            if (marginX > 0) {
                contentView.setX(marginX);
                if (marginX > screenWidth / 2) {
                    if (e2.getAction() == MotionEvent.ACTION_UP) {
                        this.finish();
                        //  marginX=0;
                    }
                } else {
                    //  marginX=0;
                    contentView.setX(marginX);
                }
            }
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.i("==>onLongPress<==", "marginX:" + marginX + "  e.getAction():" + e.getAction());
    }

    private int screenWidth;
    private View contentView;
    private int marginX;

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.i("==>onFling<==", "marginX:" + marginX + "  e1.getAction():" + e1.getAction() + "   e2.getAction():" + e2.getAction());
        return false;
    }

    public int getScreenWidth() {
        WindowManager wManager = getWindowManager();
        return wManager.getDefaultDisplay().getWidth();
    }

    public int getScreenHeight() {
        WindowManager wManager = getWindowManager();
        return wManager.getDefaultDisplay().getHeight();
    }
}