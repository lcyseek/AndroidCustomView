package com.zhilai.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/2/23.
 */
public class MyViewTwo extends TextView {

    public MyViewTwo(Context context) {
        super(context);
        setText("this is text");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        measure(0,0);
        Log.i(MyViewTwo.class.getSimpleName(), "onDraw: width" + getWidth() + " heigh" + getHeight());
        Log.i(MyViewTwo.class.getSimpleName(), "onDraw: MeasuredWidth" + getMeasuredWidth() + " MeasuredHeight" + getMeasuredHeight());
    }
}
