package com.example.readyknowledge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity{

    public static final String TAG = MainActivity.class.getSimpleName();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //return false： 表明事件不会被进行分发。事件会以冒泡的方式被传递给上层的view或activity的onTouchEvent方法进行消费掉。
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("MainActivity", "dispatchTouchEvent: "+ev.getAction());
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent: "+event.getAction());
        return super.onTouchEvent(event);
    }
}
