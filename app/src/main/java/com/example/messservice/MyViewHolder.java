package com.example.messservice;

import android.content.Context;
import android.util.Log;
import android.view.WindowManager;

public class MyViewHolder {
    private final static String TAG = "MyViewHolder";
    private Context applicationContext;
    private static TouchView touchView;
    private static WindowManager windowManager;
    public static boolean isServiceRunning = false;

    public MyViewHolder(Context context) {
        applicationContext = context.getApplicationContext();
        this.touchView = new TouchView(applicationContext, null);
        WindowManagerInstance.setApplicationContext(applicationContext);
        windowManager = WindowManagerInstance.newInstance();  System.out.println("Showwwwww1" + windowManager);
    }

    public static void showEasyTouchView(){
        if (!TouchView.isAlive) {
            TouchView.isAlive = true;
            WindowManager.LayoutParams layoutParams = touchView.getmLayoutParams();
            System.out.println("Showwwwww" + layoutParams);
            windowManager.addView(touchView, layoutParams);//chu mo 2 cai cung luc di
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
