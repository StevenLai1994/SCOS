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

import es.source.code.model.AllFoods;
import es.source.code.model.MyFood;
import es.source.code.model.User;

/**
 * Created by laiju on 2018/10/17.
 */

public class FoodFragment extends Fragment {
    private User user;
    private int mPage;
    private ListView mListView;
    private List<MyFood> mFoods;

    public static FoodFragment newInstance(int page, User user) {
        Bundle args = new Bundle();
        args.putInt(Const.LayoutInfo.ARGS_PAGE, page);
        args.putParcelable(Const.BackInfo.USERKEY, user);
        FoodFragment fragment = new FoodFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(Const.LayoutInfo.ARGS_PAGE);
        user = getArguments().getParcelable(Const.BackInfo.USERKEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.food_fragment, container, false);
        mListView = (ListView) rootView.findViewById(R.id.mFoodList);
        initFood();
        initFragment();
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public  void initFragment() {
        MyFoodListAdp adapter = new MyFoodListAdp(getActivity(), R.layout.list_item, mFoods, user);
        mListView.setAdapter(adapter);
    }

    public void initFood() {
        switch(mPage) {
            case Const.PageId.COLDFOOD:
                mFoods = user.getColdFoods();
                break;
            case Const.PageId.HOTFOOD:
                mFoods = user.getHotFoods();
                break;
            case Const.PageId.SEAFOOD:
                mFoods = user.getSeaFoods();
                break;
            case Const.PageId.DRINKING:
                mFoods = user.getDrinkings();
                break;
            default:
                break;
        }
    }
}
