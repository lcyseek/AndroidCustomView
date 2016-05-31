package com.zhilai.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by lcy on 2016/5/6.
 *
 *
 */
public class ViewOne extends View implements View.OnClickListener{

    public static final String TAG = ViewOne.class.getSimpleName();
    private float titleSize = 10;
    private String titleText="";
    private int titleColor = 0;
    private int titleBack = 0;

    private Paint mPaint;
    private Rect mBound;

    //第一属于程序内实例化时采用，之传入Context即可
    public ViewOne(Context context) {
        super(context);
        Log.i(TAG, "ViewOne: 1");
    }

    //第二个用于layout文件实例化，会把XML内的参数通过AttributeSet带入到View内。
    public ViewOne(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "ViewOne: 2");

        //当View从xml加载后，attrs包含了xml中所有的属性。
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            System.out.println(attrs.getAttributeName(i)+"|"+attrs.getAttributeValue(i));
        }


        //obtainStyledAttributes(AttributeSet set, int[] attrs) //从layout设置的属性集中获取attrs中的属性
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.ViewOne);
        titleText = array.getString(R.styleable.ViewOne_titleText);
        titleSize = array.getDimension(R.styleable.ViewOne_titleSize,10);
        titleColor = array.getColor(R.styleable.ViewOne_titleColor,0xcccccc);
        titleBack = array.getColor(R.styleable.ViewOne_titleBackgroundColor,0x123e45);

        array.recycle();

        mPaint = new Paint();
        mPaint.setTextSize(titleSize);
        mBound = new Rect();
        mPaint.getTextBounds(titleText,0,titleText.length(),mBound);
    }

    //第三个主题的style信息，也会从XML里带入
    public ViewOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i(TAG, "ViewOne: 3");
    }

    int widthMeasureSpec,heightMeasureSpec;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "onMeasure: ");

        //这句话可以使实时监测titleText的大小
        mPaint.getTextBounds(titleText,0,titleText.length(),mBound);

        this.widthMeasureSpec = widthMeasureSpec;
        this.heightMeasureSpec = heightMeasureSpec;

        setOnClickListener(this);

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
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        //当设置了WRAP_CONTENT时，我们需要自己进行测量
        int height = 0,width = 0;
        if(widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        }else if(widthMode == MeasureSpec.AT_MOST){
            //paint.measureText(text) + getPaddingLeft() + getPaddingRight();
//            width = getPaddingLeft() + mBound.width() + getPaddingRight();
            System.out.println(getPaddingLeft()+"|"+mBound.width() + "|"+getPaddingRight());
            width = Math.min(getPaddingLeft() + mBound.width() + getPaddingRight(),widthSize);
        }

        if(heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }else if(heightMode == MeasureSpec.AT_MOST){
            height = getPaddingTop() + mBound.height() + getPaddingBottom();
//            height = Math.min(getPaddingTop() + mBound.height() + getHeight(),heightSize);
        }
        
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "onDraw: ");
        mPaint.setColor(titleBack);
        System.out.println("onDraw"+getMeasuredWidth()+" "+getMeasuredHeight());
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

        mPaint.setColor(titleColor);
        canvas.drawText(titleText,getWidth()/2 - mBound.width()/2,getHeight()/2 + mBound.height()/2,mPaint);
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

        titleText = sb.toString();

        //重新绘制 onDraw.不会调用onMeasure
        requestLayout();
        invalidate();
    }

    //在view给其孩子设置尺寸和位置时被调用
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
