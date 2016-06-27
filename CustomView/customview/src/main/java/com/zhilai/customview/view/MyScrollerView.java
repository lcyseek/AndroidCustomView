package com.zhilai.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.BounceInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by lcy on 2016/5/31.
 */
public class MyScrollerView extends LinearLayout {

    public static final String TAG = MyScrollerView.class.getSimpleName();
    private Scroller scroller;

    public MyScrollerView(Context context) {
        super(context);
    }

    public MyScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context,new BounceInterpolator());
    }

    public void startScroll(int fx, int fy){
        //滚动，startX, startY为开始滚动的位置，dx,dy为滚动的偏移量, duration为完成滚动的时间
        scroller.startScroll(0,0,fx,fy,1500);
        Log.i(TAG, "startScroll: "+scroller.getFinalX()+"->"+scroller.getFinalY());
        invalidate();
    }

    @Override
    public void computeScroll() {
        //返回值为boolean，true说明滚动尚未完成，false说明滚动已经完成
         if(scroller.computeScrollOffset()){
             Log.i(TAG, "computeScroll: "+scroller.getCurrX()+"|"+scroller.getCurrY());

             //这里调用View的scrollTo()完成实际的滚动
             scrollTo(scroller.getCurrX(),scroller.getCurrY());

             //必须调用该方法，否则不一定能看到滚动效果
             invalidate();
         }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
