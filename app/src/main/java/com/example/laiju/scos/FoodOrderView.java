package com.example.laiju.scos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;

import java.util.ArrayList;
import java.util.List;

import es.source.code.model.User;

/**
 * Created by laiju on 2018/10/17.
 */

public class FoodOrderView extends AppCompatActivity implements TabLayout.OnTabSelectedListener{
    private User user;
    private TabLayout mTabL;
    private ViewPager mFoodVP;
    private int initPagePos;
    private List<String> mTabs = new ArrayList<>();
    private List<Fragment> mFoodsFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_order_view);

        mTabL = (TabLayout) findViewById(R.id.morder_tablayout);
        mFoodVP = (ViewPager) findViewById(R.id.morder_foodvp);
        Bundle bundle = getIntent().getExtras();
        initPagePos = bundle.getInt(Const.BackInfo.POSITION);
        user = bundle.getParcelable(Const.BackInfo.USERKEY);
        initTabs();
        initViews();
    }

    private void initTabs() {
        mTabs.add(Const.FoodsTag.NO_ORDER);
        mTabs.add(Const.FoodsTag.ORDERED);
    }

    private void initViews() {
        //循环注入标签
        for (String tab : mTabs) {
            mTabL.addTab(mTabL.newTab().setText(tab));
        }
        for (int i = 0; i < 2; i++) {
            mFoodsFragments.add(OrderFragment.newInstance(i, user));
        }
        //设置TabLayout点击事件
        mTabL.addOnTabSelectedListener(this);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager(), mTabs, mFoodsFragments);
        mFoodVP.setAdapter(mAdapter);
        mTabL.setupWithViewPager(mFoodVP);
        mFoodVP.setCurrentItem(initPagePos);
    }

    //pageView点击事件
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mFoodVP.setCurrentItem(tab.getPosition());
    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }
    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    //pageView适配器
    public class MyPagerAdapter extends FragmentPagerAdapter {
        List<String> list;
        List<Fragment> fragments = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm, List<String> list, List<Fragment> fragments) {
            super(fm);
            this.list = list;
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return fragments != null ? fragments.size() : 0;
        }
    }
}
