package com.zhilai.customview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        LinearLayout linearLayout = new LinearLayout(this);
//        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
//        linearLayout.setBackgroundColor(Color.RED);
//
//        MyViewTwo view = new MyViewTwo(this);
//         linearLayout.addView(view);
//        setContentView(linearLayout);

        System.out.println("???????" + View.MeasureSpec.AT_MOST);
        System.out.println("???????" +View.MeasureSpec.EXACTLY);

    }


}
