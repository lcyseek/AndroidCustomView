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
public class MyViewOne extends View {

    private String text;
    private int textColor;
    private float textSize;
    Paint paint;

    public MyViewOne(Context context) {
        super(context);
    }

    public MyViewOne(Context context, AttributeSet attrs) {
        super(context, attrs);


        //AttributeSet attrs 当View从xml加载后，attrs包含了xml中所有的属性。
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            System.out.println(" 获取 " + attrs.getAttributeName(i) + "|" + attrs.getAttributeValue(i));
        }

        //obtainAttributes(AttributeSet set, int[] attrs) //从layout设置的属性集中获取attrs中的属性
        //attrs 包含了xml布局中所有的属性，obtainStyledAttributes 方法从attrs里面获取指定的属性的值
//        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleView);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleView);
        text = array.getString(R.styleable.CustomTitleView_titleText);
        textColor = array.getColor(R.styleable.CustomTitleView_titleColor, 0x00ff00);
        textSize = array.getDimension(R.styleable.CustomTitleView_titleTextSize,20);

        System.out.println("index count " + array.getIndexCount());
        System.out.println("text " + text);
        System.out.println("color " + textColor);


//        int n = array.getIndexCount();
//        for (int i = 0; i < n; i++) {
//            int attr = array.getIndex(i);
//            switch (attr){
//                case R.styleable.CustomTitleView_titleText:
//                    array.getString(attr);
//                    break;
//
//                case R.styleable.CustomTitleView_titleColor:
//                    array.getColor(attr, Color.BLACK);
//                    break;
//            }
//        }

        array.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        System.out.println("onDraw getWidth" + getWidth());
        System.out.println("onDraw getHeight " + getHeight());

        super.onDraw(canvas);

        paint = new Paint();
        paint.setTextSize(textSize);

        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        paint.setColor(Color.argb(255, 0x00, 0xaa, 0xaa));

        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);

        paint.setColor(textColor);
        canvas.drawText(text,getWidth()/2 - rect.width()/2,getHeight()/2 + rect.height()/2,paint);
    }
}


