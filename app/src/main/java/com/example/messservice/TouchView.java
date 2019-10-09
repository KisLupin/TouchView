package com.example.messservice;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class TouchView extends LinearLayout {

    private WindowManager.LayoutParams mLayoutParams;
    private  int screenWidth;
    private  int screenHeight;
    public  static boolean isAlive = false;
    private static int statusBarHeight;
    private  int viewWidth;
    private  int viewHeight;
    private  float xInScreen;
    private  float yInScreen;
    private  float xDownInScreen;
    private  float yDownInScreen;
    private  float xInView;
    private  float yInView;

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        screenWidth = this.getResources().getDisplayMetrics().widthPixels;
        screenHeight = this.getResources().getDisplayMetrics().heightPixels;
        initLayoutParams();
        LayoutInflater.from(context).inflate(R.layout.view_icon, this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = this.getWidth();
        viewHeight = this.getHeight();
    }

    public void initLayoutParams(){
        mLayoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                0,
                0,
                PixelFormat.TRANSPARENT
        );
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        mLayoutParams.x = screenWidth;
        mLayoutParams.y = screenHeight / 2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xInView = event.getX();
                yInView = event.getY();
                xDownInScreen = event.getRawX();
                yDownInScreen = event.getRawY() - getStatusBarHeight();
                xInScreen = event.getRawX();
                yInScreen = event.getRawY() - getStatusBarHeight();
                break;
            case MotionEvent.ACTION_MOVE:
                xInScreen = event.getRawX();
                yInScreen = event.getRawY() - getStatusBarHeight();
                mLayoutParams.x = (int) (xInScreen - xInView);
                mLayoutParams.y = (int) (yInScreen - yInView);
                updateViewPosition();
                break;
            case MotionEvent.ACTION_UP:
                startViewPositionAnimator();
                break;
            case MotionEvent.ACTION_BUTTON_PRESS:
                break;
        }
        return super.onTouchEvent(event);
    }

    public void updateViewPosition() {
        WindowManager windowManager = WindowManagerInstance.newInstance();
        windowManager.updateViewLayout(this, mLayoutParams);
    }

    public void startViewPositionAnimator(){
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float)animation.getAnimatedValue();
                if (mLayoutParams.x + viewWidth / 2 <= screenWidth / 2){
                    mLayoutParams.x = (int) ((float)mLayoutParams.x * (1 - value));
                }
                else{
                    mLayoutParams.x += (int) ((float)(screenWidth - mLayoutParams.x) * value);
                }
                updateViewPosition();
            }
        });
        valueAnimator.start();
    }

    public float getStatusBarHeight() {
        Rect rectangle = new Rect();
        this.getWindowVisibleDisplayFrame(rectangle);
        statusBarHeight = rectangle.top;
        return statusBarHeight;
    }

    public WindowManager.LayoutParams getmLayoutParams() {//mo cai cua minh
        return mLayoutParams;
    }
}
