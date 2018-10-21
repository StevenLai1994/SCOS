package com.example.laiju.scos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.source.code.model.User;

/**
 * Created by laiju on 2018/9/27.
 */

public class MainScreen extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private GridView mGridView;
    List<Map<String, Object>> mData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        mGridView = (GridView) findViewById(R.id.mGrid);
        mGridView.setOnItemClickListener(this);
        initData();
        initGrid();
        dealIntentInfo();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String str = (String) mData.get(position).get("ItemText");
        Intent intent;
        switch (str) {
            case Const.ButtonText.ORDER:
                intent = new Intent("scos.intent.action.SCOSFOOD_VIEW");
                startActivity(intent);
                break;
            case Const.ButtonText.VIEW:
                intent = new Intent("scos.intent.action.SCOSFOOD_FOOD_ORDER");
                startActivity(intent);
                break;
            case Const.ButtonText.LOGIN:
                intent = new Intent("scos.intent.action.SCOSLOGORREG");
                startActivity(intent);
                break;
            case Const.ButtonText.HELP:
                break;
            default:
                break;
        }
    }

    public void initData() {
        //图片数据
        int[] images = {R.drawable.order, R.drawable.view, R.drawable.login,
                R.drawable.help};
        //图片编号
        String[] name = {Const.ButtonText.ORDER, Const.ButtonText.VIEW,
                Const.ButtonText.LOGIN, Const.ButtonText.HELP};
        //初始化数据
        mData.clear();
        for (int i = 0; i < images.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("ItemImage", images[i]);
            //如果只需要显示图片，可以不用这一行，需要同时将from和to中的相关内容删去
            map.put("ItemText", name[i]);
            mData.add(map);
        }
    }

    public void initGrid() {
        //from数组中的是map-key的值，to数组和from是相对应的
        String[] from = {"ItemImage", "ItemText"};
        int[] to = {R.id.image, R.id.text};
        //实例化一个适配器
        SimpleAdapter adapter = new SimpleAdapter(this, mData, R.layout.main_screen_item, from, to);
        //为GridView设置适配器
        mGridView.setAdapter(adapter);
    }

    public void hideItem() {
        for(int i=0; i<2; i++)
            mData.remove(0);
        initGrid();
    }

    public boolean isHideItem() {
        return mData.size() < Const.LayoutInfo.MAINSCREEN_GRIGITEM_NUM;
    }

    public void showItem() {
        initData();
        initGrid();
    }

    public void dealIntentInfo() {
        Bundle bundle = getIntent().getExtras();
        String recvMsg = bundle.getString(Const.BackInfo.STRINGKEY);
        User user;
        Toast.makeText(MainScreen.this, recvMsg, Toast.LENGTH_SHORT).show();
        switch (recvMsg) {
            case Const.BackInfo.ENTRYFLING:
                showItem();
                break;
            //登录成功
            case Const.BackInfo.LOGINLOGIN:
                user = (User) bundle.getParcelable(Const.BackInfo.USERKEY);
                if(isHideItem())
                    showItem();
                break;
            //注册成功
            case Const.BackInfo.LOGINREGISTER:
                user = (User) bundle.getParcelable(Const.BackInfo.USERKEY);
                if(isHideItem())
                    showItem();
                Toast.makeText(MainScreen.this, getString(R.string.welcome),
                        Toast.LENGTH_LONG).show();
                break;
            case Const.BackInfo.LOGINBAKE:
                hideItem();
                break;
            default:
                break;
        }
    }
}


