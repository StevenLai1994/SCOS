package com.example.laiju.scos;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.source.code.model.MyFood;
import es.source.code.model.MyOrder;
import es.source.code.model.User;

/**
 * Created by laiju on 2018/10/17.
 */


public class MyOrderListAdp extends ArrayAdapter {
    private User user;
    private final int resourceId;
    private final int mPage;
    private ArrayList<MyOrder> mOrders;

    public MyOrderListAdp(Context context, int textViewResourceId,
                          List<MyOrder> objects, int mPage, User user) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        mOrders = new ArrayList<>(objects);
        this.mPage = mPage;
        this.user = user;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyOrder mOrder = (MyOrder) getItem(position); // 获取当前项的Fruit实例
        View view;//实例化一个对象
        final ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent,
                    false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();

        }
        //设置页面的控件
        viewHolder.setViews(mOrder, mPage);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRow(mOrder);
            }
        });
        return view;
    }

    //点击某一行的事件
    public void clickRow(MyOrder mOrder) {
        Toast.makeText(getContext(), mOrder.getName(),
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent("scos.intent.action.SCOSFOOD_DETAILED");
        intent.putExtra(Const.BackInfo.FoodKey, mOrder);
        getContext().startActivity(intent);
    }

    class ViewHolder {
        private ImageView foodPic;
        private TextView foodName;
        private TextView foodPrice;
        private TextView orderCount;
        private TextView orderTips;
        private Button orderThis;

        public  ViewHolder(View view) {
            //获取每一行控件位置
            this.foodPic = (ImageView) view.findViewById(R.id.order_food_pic);
            this.foodName = (TextView) view.findViewById(R.id.food_name);
            this.foodPrice = (TextView) view.findViewById(R.id.food_price);
            this.orderCount = (TextView) view.findViewById(R.id.order_count);
            this.orderTips = (TextView) view.findViewById(R.id.order_tips);
            this.orderThis = (Button) view.findViewById(R.id.order_this);
        }

        public void setViews(final MyOrder mOrder, int mPage) {
            foodPic.setImageDrawable(ContextCompat.getDrawable(getContext(),
                    mOrder.getImageId()));
            foodName.setText(mOrder.getName());//为文本视图设置文本内容
            foodPrice.setText("价格："+mOrder.getPrice() + "元");
            orderCount.setText("数量:"+mOrder.getCount());
            orderTips.setText(mOrder.getTips());

            //如果是已下菜单，不显示点菜按钮
            if(mPage == Const.PageId.ORDERED) {
                orderThis.setVisibility(View.GONE);
                return;
            }
            setButtonText(orderThis, mOrder);
            orderThis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setButtonText(orderThis, mOrder);
                    if(false == mOrder.getIsOrdered()) {
                        user.ortherThis(mOrder.getmFood(), 1, "");
                        orderThis.setText(Const.ButtonText.UNSUBSCRIBE);
                    }
                    else {
                        user.unsubscrib(mOrder.getmFood());
                        orderThis.setText(Const.ButtonText.ORDER_THIS);
                    }
                }
            });
        }

        public void setButtonText(Button button, MyOrder mOrder) {
            if(mOrder.getIsOrdered())
                button.setText(Const.ButtonText.UNSUBSCRIBE);
            else
                button.setText(Const.ButtonText.ORDER_THIS);
        }

    }
}
