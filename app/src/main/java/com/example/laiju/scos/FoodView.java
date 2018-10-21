package com.example.laiju.scos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laiju on 2018/10/17.
 */

public class FoodView extends AppCompatActivity implements TabLayout.OnTabSelectedListener{
    private TabLayout mTabL;
    private ViewPager mFoodVP;
    private List<String> mTabs = new ArrayList<>();
    private List<Fragment> mFoodsFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_view);

        mTabL = (TabLayout) findViewById(R.id.mtablayout);
        mFoodVP = (ViewPager) findViewById(R.id.mfoodvp);

        initTabs();
        initViews();

    }

    private void initTabs() {
        mTabs.add(Const.FoodsTag.COLDFOOD);
        mTabs.add(Const.FoodsTag.HOTFOOD);
        mTabs.add(Const.FoodsTag.SEEFOOD);
        mTabs.add(Const.FoodsTag.DRINKING);
    }

    private void initViews() {
        initMenu();
        //循环注入标签
        for (String tab : mTabs) {
            mTabL.addTab(mTabL.newTab().setText(tab));
        }
        //设置TabLayout点击事件
        for (int i = 0; i < 4; i++) {
            mFoodsFragments.add(FoodFragment.newInstance(i));
        }
        mTabL.addOnTabSelectedListener(this);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager(), mTabs, mFoodsFragments);
        mFoodVP.setAdapter(mAdapter);
        mTabL.setupWithViewPager(mFoodVP);
    }

    //设置actionBar menu
    protected void initMenu() {
        super.onStart();
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);          // 是否显示应用程序图标，默认为true
        actionBar.setDisplayShowTitleEnabled(false);    // 是否显示应用程序标题，默认为true
        actionBar.setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ordered:
                Toast.makeText(this, "已点菜品", Toast.LENGTH_LONG).show();
                break;
            case R.id.view_order:
                Toast.makeText(this, "查看订单", Toast.LENGTH_LONG).show();
                break;
            case R.id.help:
                Toast.makeText(this, "帮助", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
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
