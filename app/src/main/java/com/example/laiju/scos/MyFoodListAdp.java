package com.example.laiju.scos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import es.source.code.model.MyFood;
import es.source.code.model.User;

/**
 * Created by laiju on 2018/10/17.
 */


public class MyFoodListAdp extends ArrayAdapter {
    private User user;

    private final int resourceId;
    private ArrayList<MyFood> mFoods;

    public MyFoodListAdp(Context context, int textViewResourceId, List<MyFood> objects, User user) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        mFoods = new ArrayList<>(objects);
        this.user = user;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MyFood mFood = (MyFood) getItem(position); // 获取当前项的Fruit实例
        View view;//实例化一个对象
        final ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent,
                    false);
            viewHolder = new ViewHolder();
            viewHolder.foodName = (TextView) view.findViewById(R.id.food_name);//获取该布局内的文本视图
            viewHolder.foodPrice = (TextView) view.findViewById(R.id.food_price);
            viewHolder.orderThis = (Button) view.findViewById(R.id.order_this);
            viewHolder.foodPic = (ImageView) view.findViewById(R.id.food_pic);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();

        }
        viewHolder.foodName.setText(mFood.getName());//为文本视图设置文本内容
        viewHolder.foodPrice.setText("价格："+mFood.getPrice() + "元");
        viewHolder.foodPic.setImageDrawable(ContextCompat.getDrawable(getContext(),
                mFood.getImageId()));
        setButtonText(viewHolder.orderThis, mFood);
        viewHolder.orderThis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOrderThis(viewHolder.orderThis, mFood);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               clickRow(position);
            }
        });
        return view;
    }

    public void clickOrderThis(Button orderThis, MyFood mFood) {
        setButtonText(orderThis, mFood);
        if(false == mFood.getIsOrdered()) {
            user.ortherThis(mFood, 1, "");
            orderThis.setText(Const.ButtonText.UNSUBSCRIBE);
        }
        else {
            user.unsubscrib(mFood);
            orderThis.setText(Const.ButtonText.ORDER_THIS);
        }
    }

    public void setButtonText(Button button, MyFood mFood) {
        if(mFood.getIsOrdered())
            button.setText(Const.ButtonText.UNSUBSCRIBE);
        else
            button.setText(Const.ButtonText.ORDER_THIS);
    }

    //点击某行
    public void clickRow(int pos) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent("scos.intent.action.SCOSFOOD_DETAILED");
        bundle.putInt(Const.BackInfo.POSITION, pos);
        bundle.putParcelable(Const.BackInfo.USERKEY, user);
        bundle.putParcelableArrayList(Const.BackInfo.PARLIST, mFoods);
        intent.putExtras(bundle);
        getContext().startActivity(intent);
    }

    class ViewHolder {
        TextView foodName;
        TextView foodPrice;
        Button orderThis;
        ImageView foodPic;
    }
}
