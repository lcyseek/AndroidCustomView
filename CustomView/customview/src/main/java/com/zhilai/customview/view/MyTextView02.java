package com.zhilai.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lcy on 2016/6/21.
 */
public class MyTextView02 extends View {

    public MyTextView02(Context context) {
        super(context);
    }

    public MyTextView02(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        String text = "hello world java";
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(36);
        paint.setColor(Color.RED);

        canvas.drawText("hello world java",10,200,paint);
        float top = paint.getFontMetrics().top;
        float bottom = paint.getFontMetrics().bottom;
        System.out.println("top="+top+" bottom="+bottom);

        float width = paint.measureText("hello world");
        System.out.println(width);

        Rect rect = new Rect();
        paint.getTextBounds(text,0,text.length(),rect);
        System.out.println(rect.left+"|"+rect.top+"|"+rect.right+"|"+rect.bottom);


        Rect target = new Rect(10,400,800,600);
        paint.setColor(Color.parseColor("#cccccc"));
        canvas.drawRect(target,paint);

        System.out.println(target.centerX()+"|="+target.centerY());
        paint.setColor(Color.BLUE);
        Paint.FontMetricsInt metrics = paint.getFontMetricsInt();
        int baseLine = (target.bottom + target.top - metrics.bottom - metrics.top)/2;
        canvas.drawCircle(target.centerX(),baseLine,5,paint);
        System.out.println("baseline="+baseLine);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text,target.centerX(),baseLine,paint);
    }
}
