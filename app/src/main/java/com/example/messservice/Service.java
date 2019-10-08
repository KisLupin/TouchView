package com.example.messservice;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import org.greenrobot.eventbus.EventBus;

public class Service extends AccessibilityService {

    private final static String TAG = "MyService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        MyViewHolder.setIsServiceRunning(true);
        Log.d(TAG, "isServiceRunning");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {

    }

    @Override
    public void onInterrupt() {
        Log.d(TAG, "onInterrupt");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        MyViewHolder.setIsServiceRunning(false);
        Log.d(TAG, intent.toString());
        return super.onUnbind(intent);
    }
}
