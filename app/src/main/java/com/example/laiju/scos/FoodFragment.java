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

/**
 * Created by laiju on 2018/10/17.
 */

public class FoodFragment extends Fragment {
    private int mPage;
    private ListView mListView;
    private List<MyFood> mFoods;

    public static FoodFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(Const.LayoutInfo.ARGS_PAGE, page);
        FoodFragment fragment = new FoodFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(Const.LayoutInfo.ARGS_PAGE);
        mFoods = new ArrayList<>();
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

    public void initFood() {
        switch(mPage) {
            case Const.PageId.COLDFOOD:
                initColdFood();
                break;
            case Const.PageId.HOTFOOD:
                initHotFood();
                break;
            case Const.PageId.SEAFOOD:
                initSeaFood();
                break;
            case Const.PageId.DRINKING:
                initDrinking();
                break;
            default:
                break;
        }
    }

    public  void initFragment() {
        MyFoodListAdp adapter = new MyFoodListAdp(getActivity(), R.layout.list_item, mFoods);
        mListView.setAdapter(adapter);
    }

    public void initColdFood() {
        mFoods.clear();
        MyFood food;
        food = new MyFood("冷菜1", 1.1, R.drawable.main_screen, 1);
        mFoods.add(food);
        food = new MyFood("冷菜2", 1.1, R.drawable.main_screen, 1);
        mFoods.add(food);
        food = new MyFood("冷菜3", 1.1, R.drawable.main_screen, 1);
        mFoods.add(food);
    }

    public void initHotFood() {
        mFoods.clear();
        MyFood food;
        food = new MyFood("热菜1", 1.1, R.drawable.main_screen, 1);
        mFoods.add(food);
        food = new MyFood("热菜2", 1.1, R.drawable.main_screen, 1);
        mFoods.add(food);
        food = new MyFood("热菜3", 1.1, R.drawable.main_screen, 1);
        mFoods.add(food);
    }

    public void initSeaFood() {
        mFoods.clear();
        MyFood food;
        food = new MyFood("海鲜1", 1.1, R.drawable.main_screen, 1);
        mFoods.add(food);
        food = new MyFood("海鲜2", 1.1, R.drawable.main_screen, 1);
        mFoods.add(food);
        food = new MyFood("海鲜3", 1.1, R.drawable.main_screen, 1);
        mFoods.add(food);
    }

    public void initDrinking() {
        mFoods.clear();
        MyFood food;
        food = new MyFood("酒水", 1.1, R.drawable.main_screen, 1);
        mFoods.add(food);
        food = new MyFood("酒水", 1.1, R.drawable.main_screen, 1);
        mFoods.add(food);
        food = new MyFood("酒水", 1.1, R.drawable.main_screen, 1);
        mFoods.add(food);
    }
}
