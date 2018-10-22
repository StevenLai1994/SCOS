package com.example.laiju.scos;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import es.source.code.model.MyFood;
import es.source.code.model.MyOrder;
import es.source.code.model.User;

/**
 * Created by laiju on 2018/10/17.
 */

public class OrderFragment extends Fragment {
    private User user;
    private int mPage;
    private ListView mListView;
    private Button funcButton;
    private List<MyOrder> mOrders;

    public static OrderFragment newInstance(int page, User user) {
        Bundle args = new Bundle();
        args.putInt(Const.LayoutInfo.ARGS_PAGE, page);
        args.putParcelable(Const.BackInfo.USERKEY, user);
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(Const.LayoutInfo.ARGS_PAGE);
        user = getArguments().getParcelable(Const.BackInfo.USERKEY);
        mOrders = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.food_fragment, container, false);
        mListView = (ListView) rootView.findViewById(R.id.mFoodList);
        funcButton = (Button) rootView.findViewById(R.id.order_function);
        funcButton.setVisibility(View.VISIBLE);
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
            case Const.PageId.NO_ORDER:
                initNoOrder();
                break;
            case Const.PageId.ORDERED:
                initOrdered();
                break;
            default:
                break;
        }
    }

    public  void initFragment() {
        MyOrderListAdp adapter = new MyOrderListAdp(getActivity(),
                R.layout.order_list_item, mOrders, mPage, user);
        mListView.setAdapter(adapter);
    }

    public void initNoOrder() {
        mOrders = user.mNoOrders;
        funcButton.setText(Const.ButtonText.SUBMIT);
    }

    public void initOrdered() {
        mOrders = user.mOrdereds;
        funcButton.setText(Const.ButtonText.SETTLE);
    }
}
