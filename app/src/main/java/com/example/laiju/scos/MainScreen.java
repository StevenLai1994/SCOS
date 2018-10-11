package com.example.laiju.scos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by laiju on 2018/9/27.
 */

public class MainScreen extends AppCompatActivity implements View.OnClickListener{
    private Button log_or_reg;
    private Button order;
    private Button view_order;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        log_or_reg = (Button)findViewById(R.id.log_or_reg);
        order = (Button)findViewById(R.id.order);
        view_order = (Button)findViewById(R.id.view_order);
        log_or_reg.setOnClickListener(this);

        String fromEntry = "FromEntry";
        String fromLogin = "LoginSuccess";
        String recvMsg = getIntent().getStringExtra("data");
        //收到的消息不是FromEntry
        if(!recvMsg.equals(fromEntry)) {
            order.setVisibility(View.GONE);
            view_order.setVisibility(View.GONE);
            Toast.makeText(MainScreen.this, "不为FromEntry", Toast.LENGTH_SHORT).show();
        }
        //收到的消息是LoginSuccess
        if(recvMsg.equals(fromLogin)) {
            if(order.getVisibility() == View.GONE)
                order.setVisibility(View.VISIBLE);
            if(view_order.getVisibility() == View.GONE)
                view_order.setVisibility(View.VISIBLE);
            Toast.makeText(MainScreen.this, "相等", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.log_or_reg:
                Intent intent = new Intent("scos.intent.action.SCOSLOGORREG");
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
