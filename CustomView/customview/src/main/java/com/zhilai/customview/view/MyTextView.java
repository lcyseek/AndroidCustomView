package com.zhilai.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lcy on 2016/6/18.
 * 步骤
 * 1、自定义View的属性
 *      res/values/  下建立一个attrs.xml
 * 2、在使用的xml里加上 xmlns:seek="http://schemas.android.com/apk/res-auto"
 */
public class MyTextView extends View{

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#cccccc"));

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(35);
        paint.setColor(Color.BLUE);
        paint.setTextAlign(Paint.Align.LEFT);

        Paint.FontMetrics metrics = paint.getFontMetrics();

        float baseX = 20;
        float baseY = 100;
        canvas.drawText("hello World jave",baseX,baseY,paint);//绘制文本的基准线

        //绘制文字基准点
        canvas.drawCircle(baseX,baseY,5,paint);

        //绘制基准线
        Paint baseLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        baseLinePaint.setColor(Color.RED);
        canvas.drawLine(0,baseY,getWidth(),baseY,baseLinePaint);

        //ascent: 系统建议的，绘制单个字符时，字符应当的最高高度所在线
        Paint ascentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ascentPaint.setColor(Color.GREEN);
        System.out.println("ascent="+metrics.ascent);//ascent是负值 以baseline为原点
        float ascentY = baseY + metrics.ascent;
        canvas.drawLine(0,ascentY,getWidth(),ascentY,ascentPaint);

        //descent:系统建议的，绘制单个字符时，字符应当的最低高度所在线
        //如果文字里没有'j',这个descent线还是一样的。
        Paint descentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        descentPaint.setColor(Color.YELLOW);
        float descentY = baseY + metrics.descent;
        System.out.println("descent="+metrics.descent);
        canvas.drawLine(0,descentY,getWidth(),descentY,descentPaint);

        //top: 可绘制的最高高度所在线
        Paint topPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        topPaint.setColor(Color.DKGRAY);
        System.out.println("top="+metrics.top);//top也是是负值
        float topY = baseY + metrics.top;
        canvas.drawLine(0,topY,getWidth(),topY,topPaint);

        //bottom: 可绘制的最低高度所在线
        Paint bottomPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bottomPaint.setColor(Color.DKGRAY);
        float bottomY = baseY + metrics.bottom;
        System.out.println("bottom="+metrics.bottom);
        canvas.drawLine(0,bottomY,getWidth(),bottomY,topPaint);

    }
}
