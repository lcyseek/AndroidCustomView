package com.zhilai.customview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhilai.customview.view.MyScrollerView;

public class MainActivity extends Activity {

    private MyScrollerView scrollerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollerView = (MyScrollerView) findViewById(R.id.scrollView);
    }


    public void start(View view) {
        scrollerView.startScroll(-100,-400);
    }

    public void customTextView(View view) {
        startActivity(new Intent(this,MyTextViewActivity.class));
    }
}
