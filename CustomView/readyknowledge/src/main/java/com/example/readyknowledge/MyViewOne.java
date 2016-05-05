package com.example.readyknowledge;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by luchunyang on 16/5/4.
 */
public class MyViewOne extends LinearLayout implements View.OnTouchListener{

    public static final String TAG = MyViewOne.class.getSimpleName();

    public MyViewOne(Context context) {
        super(context);
    }

    public MyViewOne(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //onInterceptTouchEvent()是ViewGroup的一个方法，目的是在系统向该ViewGroup及其各个childView触发onTouchEvent()之前对相关事件进行一次拦截
    //由于ViewGroup会包含若干childView,因此需要能够统一监控各种touch事件的机会，因此纯粹的不能包含子view的控件是没有这个方法的，如LinearLayout就有，TextView就没有。
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i(TAG, "dispatchTouchEvent: "+event.getAction());
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onInterceptTouchEvent: "+ev.getAction());
//        return super.onInterceptTouchEvent(ev);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent: "+event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i(TAG, "onTouch: "+event.getAction());
        return false;
    }
}
