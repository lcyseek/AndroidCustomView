package com.zhilai.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/2/23.
 */
public class MyViewThree extends View {

    private String text;
    private int textColor;
    private float textSize;
    Paint paint;
    Rect rect;

    public MyViewThree(Context context) {
        super(context);
    }

    public MyViewThree(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleView);
        text = array.getString(R.styleable.CustomTitleView_titleText);
        textColor = array.getColor(R.styleable.CustomTitleView_titleColor, 0x00ff00);
        textSize = array.getDimension(R.styleable.CustomTitleView_titleTextSize, 20);

        paint = new Paint();
        paint.setTextSize(textSize);

        rect = new Rect();
        paint.getTextBounds(text,0,text.length(),rect);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        System.out.println(getWidth() + " | " + getHeight());
        System.out.println(getMeasuredWidth() + " | " + getMeasuredHeight());

//        measure(0,0);
        paint.setColor(Color.GREEN);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),paint);

        paint.setColor(textColor);
        canvas.drawText(text, getWidth() / 2 - rect.width() / 2, getHeight() / 2 + rect.height() / 2, paint);

        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        System.out.println("------------------------- onMeasure ------------------------- ");
        System.out.println("onMeasure getWidth" + getWidth());
        System.out.println("onMeasure getHeight " + getHeight());

        int result_width = 0;
        int specMode_width = MeasureSpec.getMode(widthMeasureSpec);
        int specSize_width = MeasureSpec.getSize(widthMeasureSpec);

        if(specMode_width == MeasureSpec.AT_MOST){
            System.out.println("specMode_width=" + "AT_MOST" + " specSize_width="+specSize_width);
            result_width = (int)paint.measureText(text) + getPaddingLeft() + getPaddingRight();
            result_width = Math.min(result_width, specSize_width);
        }else if(specMode_width == MeasureSpec.EXACTLY){
            result_width = specSize_width;
            System.out.println("specMode_width=" + "EXACTLY" + " specSize_width="+specSize_width);
        }

        System.out.println("result_width--->" + result_width);

        int specMode_heigh = MeasureSpec.getMode(heightMeasureSpec);
        int specSize_heigh = MeasureSpec.getSize(heightMeasureSpec);
        int result_heigh = 0;

        if(specMode_heigh == MeasureSpec.AT_MOST){
            System.out.println("specMode_width=" + "AT_MOST" + " specSize_width=" + specSize_heigh);
            result_heigh = rect.height() + getPaddingTop() + getPaddingBottom();
            result_heigh = Math.min(result_heigh, specSize_heigh);
        }else if(specMode_heigh == MeasureSpec.EXACTLY){
            System.out.println("specMode_width=" + "EXACTLY" + " specSize_width="+specSize_heigh);
            result_heigh = specSize_heigh;
        }
        System.out.println("result_heigh--->" + result_heigh);

        setMeasuredDimension(result_width, result_heigh);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        System.out.println("onLayout--->");
    }
}
