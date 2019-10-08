package com.example.messservice;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private final static String TAG = "MainActivity";
    private final static int REQUESTCODE = 101;
    private static MyViewHolder myViewHolder;
    private Switch mswitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        mswitch = findViewById(R.id.open_easytouch);
        mswitch.setOnCheckedChangeListener(this);
        if (myViewHolder == null){
            myViewHolder = new MyViewHolder(this);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d(TAG, "onCheckedChanged");
        if(isChecked) {
            if (!checkFloatWindowPermission()) {
                showPromptingDialog();
            }
            else{
                MyViewHolder.showEasyTouchView();
            }
        }
        else{
            MyViewHolder.hideEasyTouchView();
        }
    }

    private void showPromptingDialog() {

    }
    private boolean checkFloatWindowPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (Settings.canDrawOverlays(this)) {
                return true;
            }
        }
        else{
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE) {
            if (!TouchView.isAlive) {
                mswitch.setChecked(false);
            }
        }
    }
}
