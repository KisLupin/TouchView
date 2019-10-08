package com.example.messservice;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class TouchView extends LinearLayout {

    private WindowManager.LayoutParams layoutParams;
    private static int screenWidth;
    private static int screenHeight;
    public static boolean isAlive = false;

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        screenWidth = this.getResources().getDisplayMetrics().widthPixels;
        screenHeight = this.getResources().getDisplayMetrics().heightPixels;
        initLayoutParams();
        LayoutInflater.from(context).inflate(R.layout.view_icon, this);
    }

    private void initLayoutParams() {
        layoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                0,
                0,
                PixelFormat.TRANSPARENT
        );
        layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        layoutParams.x = screenWidth;
        layoutParams.y = screenHeight / 2;
    }

    public WindowManager.LayoutParams getLayoutParams() {
        return layoutParams;
    }
}
