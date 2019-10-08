package com.example.messservice;

import android.content.Context;
import android.util.Log;
import android.view.WindowManager;

public class MyViewHolder {
    private final static String TAG = "MyViewHolder";
    private static Context applicationContext;
    private static TouchView touchView;
    private static WindowManager windowManager;
    public static boolean isServiceRunning = false;

    public MyViewHolder(Context context) {
        applicationContext = context.getApplicationContext();
        //Null???
        this.touchView = new TouchView(applicationContext, null);
        WindowManagerInstance.setApplicationContext(applicationContext);
        windowManager = WindowManagerInstance.newInstance();
    }

    public static void showEasyTouchView(){
        if (!TouchView.isAlive) {
            TouchView.isAlive = true;
            WindowManager.LayoutParams layoutParams = touchView.getLayoutParams();
            windowManager.addView(touchView, layoutParams);
            Log.d(TAG, "show");
        }
    }

    public static void hideEasyTouchView(){
        if (TouchView.isAlive) {
            TouchView.isAlive = false;
            windowManager.removeView(touchView);
            Log.d(TAG, "hide");
        }
    }

    public static void setIsServiceRunning(boolean isRunning) {
        isServiceRunning = isRunning;

    }
}
