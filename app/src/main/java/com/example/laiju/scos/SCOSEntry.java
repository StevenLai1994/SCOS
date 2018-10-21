package com.example.laiju.scos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by laiju on 2018/9/26.
 */

public class SCOSEntry extends AppCompatActivity implements View.OnTouchListener{
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);

        Button button_welcome = (Button)findViewById(R.id.welcome);
        button_welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("scos.intent.action.SCOSMAIN");
                intent.addCategory("scos.intent.category.SCOSLAUNCHER");
                Bundle bundle = new Bundle();
                bundle.putString(Const.BackInfo.STRINGKEY, Const.BackInfo.ENTRYFLING);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        ImageView  logoView = (ImageView)findViewById(R.id.logoView);
        logoView.setOnTouchListener(this);
        logoView.setClickable(true);
        mGestureDetector = new GestureDetector(this, new simpleGestureListener());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    private class simpleGestureListener extends
            GestureDetector.SimpleOnGestureListener {
        private static final float FLING_MIN_DISTANCE = 20;//mListView  滑动最小距离
        private static final float FLING_MIN_VELOCITX = 800;//mListView 滑动最大速度

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            // TODO Auto-generated method stub
            //向左滑动
            if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
                    && Math.abs(velocityX) > FLING_MIN_VELOCITX) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent("scos.intent.action.SCOSMAIN");
                intent.addCategory("scos.intent.category.SCOSLAUNCHER");
                bundle.putString(Const.BackInfo.STRINGKEY, Const.BackInfo.ENTRYFLING);
                intent.putExtras(bundle);
                startActivity(intent);
                Toast.makeText(SCOSEntry.this, "向左滑动", Toast.LENGTH_SHORT).show();
            }
            // 向右滑动
            if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
                    && Math.abs(velocityX) > FLING_MIN_VELOCITX) {
                Toast.makeText(SCOSEntry.this, "向右滑动", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    }

}


