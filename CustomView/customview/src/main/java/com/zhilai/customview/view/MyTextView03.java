package com.zhilai.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zhilai.customview.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by lcy on 2016/6/21.
 */
public class MyTextView03 extends View implements View.OnClickListener{

    class A extends ViewGroup{


        public A(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {

        }
    }

    class B extends LinearLayout{

        public B(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    int backgroundColor;
    int textColor;
    float textSize;
    String textString;
    Paint paint ;
    Rect bound = new Rect();

    public static final String TAG = MyTextView03.class.getSimpleName();

    public MyTextView03(Context context) {
        this(context,null);
    }

    public MyTextView03(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTextView03(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        setOnClickListener(this);

        //当View从xml加载后，attrs包含了xml中所有的属性。包含了自定义的和系统的
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            System.out.println(attrs.getAttributeName(i)+"|"+attrs.getAttributeValue(i));
        }

        //从layout设置的属性集中获取attrs中的属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyTextView03);

        backgroundColor = array.getColor(R.styleable.MyTextView03_myBackground,0xcccccc);
        textColor = array.getColor(R.styleable.MyTextView03_myTextColor,0xff0000);
        textSize = array.getDimension(R.styleable.MyTextView03_myTextSize,10);
        textString = array.getString(R.styleable.MyTextView03_myTextString);

        Log.i(TAG, "MyTextView03: backgroundColor="+backgroundColor+" textColor="+textColor+" textSize="+textSize+" textString="+textString);
        array.recycle();

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.getTextBounds(textString,0,textString.length(),bound);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(backgroundColor);
        paint.setColor(textColor);

        Paint.FontMetrics metrics = paint.getFontMetrics();
        float baseline = getHeight() / 2 - (metrics.descent + metrics.top)/2;
        //canvas.drawText(textString,getWidth()/2 - bound.width()/2,baseline,paint);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(textString,getWidth()/2,baseline,paint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "onMeasure: ");
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if(widthMode == MeasureSpec.AT_MOST)
            Log.i(TAG, "widthMode = AT_MOST");
        else if(widthMode == MeasureSpec.EXACTLY)
            Log.i(TAG, "widthMode = EXACTLY");
        else if(widthMode == MeasureSpec.UNSPECIFIED)
            Log.i(TAG, "widthMode = UNSPECIFIED");

        if(heightMode == MeasureSpec.AT_MOST)
            Log.i(TAG, "heightMode = AT_MOST");
        else if(heightMode == MeasureSpec.EXACTLY)
            Log.i(TAG, "heightMode = EXACTLY");
        else if(heightMode == MeasureSpec.UNSPECIFIED)
            Log.i(TAG, "heightMode = UNSPECIFIED");

        Log.i(TAG, " widthSize = "+widthSize+" heightSize = "+heightSize);

        //当设置了WRAP_CONTENT时，我们需要自己进行测量
        int height = 0,width = 0;
        if(widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        }else if(widthMode == MeasureSpec.AT_MOST){
            //paint.measureText(text) + getPaddingLeft() + getPaddingRight();
//            width = getPaddingLeft() + mBound.width() + getPaddingRight();
            //System.out.println(getPaddingLeft()+"|"+bound.width() + "|"+getPaddingRight());
            width = Math.min(getPaddingLeft() + bound.width() + getPaddingRight(),widthSize);
        }

        if(heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }else if(heightMode == MeasureSpec.AT_MOST){
            height = getPaddingTop() + bound.height() + getPaddingBottom();
//          height = Math.min(getPaddingTop() + mBound.height() + getHeight(),heightSize);
        }


        setMeasuredDimension(width,height);
    }

    @Override
    public void onClick(View v) {
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < 4)
        {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set)
        {
            sb.append("" + i);
        }

        textString = sb.toString();

        //重新绘制 onDraw.不会调用onMeasure
        //requestLayout();

        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.i(TAG, "onSizeChanged: ");
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        Log.i(TAG, "layout: ");
        super.layout(l, t, r, b);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
