package com.example.messservice;

import android.content.Context;
import android.view.WindowManager;

public class WindowManagerInstance {

    private static WindowManager windowManager ;
    public static Context applicationContext;

    public static WindowManager newInstance(){
        if (windowManager == null){
            windowManager = (WindowManager) applicationContext.getSystemService(Context.WINDOW_SERVICE);//mo cua no xem
        }
        return windowManager;
    }

    public static void setApplicationContext(Context context){
        applicationContext = context;
    }
}
