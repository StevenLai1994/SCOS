package com.example.laiju.scos;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import es.source.code.model.MyFood;
import es.source.code.model.MyOrder;

/**
 * Created by laiju on 2018/10/17.
 */

public class OrderFragment extends Fragment {
    private int mPage;
    private ListView mListView;
    private List<MyOrder> mOrders;

    public static OrderFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(Const.LayoutInfo.ARGS_PAGE, page);
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(Const.LayoutInfo.ARGS_PAGE);
        mOrders = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.food_fragment, container, false);
        mListView = (ListView) rootView.findViewById(R.id.mFoodList);
        initOrder();
        initFragment();
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void initOrder() {
        switch(mPage) {
            case Const.PageId.ORDERED:
                initOrdered();
                break;
            case Const.PageId.NO_ORDER:
                initNoOrder();
                break;
            default:
                break;
        }
    }

    public  void initFragment() {
        MyOrderListAdp adapter = new MyOrderListAdp(getActivity(),
                R.layout.order_list_item, mOrders, mPage);
        mListView.setAdapter(adapter);
    }

    public void initNoOrder() {
        MyOrder order;
        MyFood food;
        mOrders.clear();
        food = new MyFood("未点1", 1.1, R.drawable.main_screen, 1);
        order = new MyOrder(food);
        mOrders.add(order);
        food = new MyFood("未点2", 1.1, R.drawable.main_screen, 1);
        order = new MyOrder(food);
        mOrders.add(order);
        food = new MyFood("未点3", 1.1, R.drawable.main_screen, 1);
        order = new MyOrder(food);
        mOrders.add(order);
    }

    public void initOrdered() {
        MyOrder order;
        MyFood food;
        mOrders.clear();
        food = new MyFood("已点1", 1.1, R.drawable.main_screen, 1);
        order = new MyOrder(food);
        mOrders.add(order);
        food = new MyFood("已点2", 1.1, R.drawable.main_screen, 1);
        order = new MyOrder(food);
        mOrders.add(order);
        food = new MyFood("已点3", 1.1, R.drawable.main_screen, 1);
        order = new MyOrder(food);
        mOrders.add(order);
    }
}
