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
import es.source.code.model.User;

/**
 * Created by laiju on 2018/10/18.
 */

public class FoodDetailed extends AppCompatActivity implements View.OnTouchListener {
    private User user;
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
        setButtonText(orderThis, mFood);
        foodPic.setImageDrawable(ContextCompat.getDrawable(this,
                mFood.getImageId()));
        orderThis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonText(orderThis, mFood);
                if(mFood.getIsOrdered() == false) {
                    user.ortherThis(mFood, 1, tips.getText().toString());
                    mFood.setOrdered(true);
                    orderThis.setText(Const.ButtonText.UNSUBSCRIBE);
                }
                else {
                    user.unsubscrib(mFood);
                    mFood.setOrdered(false);
                    orderThis.setText(Const.ButtonText.ORDER_THIS);
                }
            }
        });
    }

    public void setButtonText(Button button, MyFood mFood) {
        if(mFood.getIsOrdered())
            button.setText(Const.ButtonText.UNSUBSCRIBE);
        else
            button.setText(Const.ButtonText.ORDER_THIS);
    }

    public void getData() {
        Bundle bundle = getIntent().getExtras();
        position = bundle.getInt(Const.BackInfo.POSITION);
        user = bundle.getParcelable(Const.BackInfo.USERKEY);
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
