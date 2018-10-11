package com.example.laiju.scos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

/**
 * Created by laiju on 2018/10/10.
 */

public class LoginOrRegister extends AppCompatActivity implements View.OnClickListener {
    private Button login;
    private Button back;
    private EditText editId;
    private EditText editKey;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_or_register);
        login = (Button)findViewById(R.id.login);
        back = (Button)findViewById(R.id.back);
        editId = (EditText)findViewById(R.id.editId);
        editKey = (EditText)findViewById(R.id.editKey);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        login.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                //判断输入ID,KEY是否合法
                boolean flagId = validate(editId.getText().toString());
                boolean flagKey = validate(editKey.getText().toString());
                //显示进度，延迟2s关闭
                progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }, 2000);
                //输入合法则跳转
                if(flagId && flagKey) {
                    String msg = "LoginSuccess";
                    send_msg_MainScreen(msg);
                }
                //输入不合法，提示，合法清空提示
                if(!flagId)
                    editId.setError(getString(R.string.error));
                else
                    editId.setError(null);
                if(!flagKey)
                    editKey.setError(getString(R.string.error));
                else
                    editKey.setError(null);
                break;

            case R.id.back:
                send_msg_MainScreen("Return");
                break;
            default:
                break;
        }
    }

    private boolean validate(String str) {
        return str.matches("^[A-Z0-9a-z]+$") && !str.isEmpty();
    }

    //跳转intent并且发送msg
    private void send_msg_MainScreen(String msg) {
        Intent intent = new Intent("scos.intent.action.SCOSMAIN");
        intent.addCategory("scos.intent.category.SCOSLAUNCHER");
        intent.putExtra("data", msg);
        startActivity(intent);
    }

}
