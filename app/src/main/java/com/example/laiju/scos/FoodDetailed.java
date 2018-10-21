package com.example.laiju.scos;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import es.source.code.model.MyFood;

/**
 * Created by laiju on 2018/10/18.
 */

public class FoodDetailed extends AppCompatActivity implements View.OnTouchListener {
    private GestureDetector mGestureDetector;
    private LinearLayout mLayout;
    private MyFood mFood;
    private ArrayList<MyFood> mFoods;
    private int position;
    private ImageView foodPic;
    private TextView foodName;
    private TextView foodPrice;
    private EditText tips;
    private Button orderThis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_detailed);
        initTouch();
        getData();
        setLayout(position);
    }

    public void initTouch() {
        mLayout = (LinearLayout)findViewById(R.id.food_detailed_layout) ;
        mLayout.setOnTouchListener(this);
        mLayout.setClickable(true);
        mLayout.setLongClickable(true);
        mGestureDetector = new GestureDetector(this, new simpleGestureListener());
    }

    public void setLayout(int pos) {
        findViews();
        mFood = mFoods.get(pos);
        foodName.setText(mFood.getName());
        foodPrice.setText("价格："+mFood.getPrice() + "元");
        orderThis.setText(Const.ButtonText.ORDER_THIS);
        foodPic.setImageDrawable(ContextCompat.getDrawable(this,
                mFood.getImageId()));
        orderThis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(orderThis.getText().equals(Const.ButtonText.ORDER_THIS)) {
                    orderThis.setText(Const.ButtonText.UNSUBSCRIBE);
                }
                else {
                    orderThis.setText(Const.ButtonText.ORDER_THIS);
                }
            }
        });
    }

    public void getData() {
        Bundle bundle = getIntent().getExtras();
        position = bundle.getInt(Const.BackInfo.POSITION);
        mFoods = bundle.getParcelableArrayList(Const.BackInfo.PARLIST);
        mFood = mFoods.get(position);
    }

    public void findViews() {
        foodPic = (ImageView)findViewById(R.id.food_pic);
        foodName = (TextView) findViewById(R.id.food_name);
        foodPrice = (TextView)findViewById(R.id.food_price);
        tips = (EditText)findViewById(R.id.tips);
        orderThis = (Button)findViewById(R.id.order_this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    //class Listener
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
                if(position < mFoods.size()-1)
                    position++;
                else position = 0;
                setLayout(position);
            }
            // 向右滑动
            if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
                    && Math.abs(velocityX) > FLING_MIN_VELOCITX) {
                if(position > 0)
                    position--;
                else position = mFoods.size()-1;
                setLayout(position);
            }
            return false;
        }
    }
}
