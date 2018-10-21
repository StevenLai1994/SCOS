package com.example.laiju.scos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import es.source.code.model.User;

/**
 * Created by laiju on 2018/10/10.
 */

public class LoginOrRegister extends AppCompatActivity implements View.OnClickListener {
    private Button login;
    private Button register;
    private Button back;
    private EditText editId;
    private EditText editKey;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_or_register);
        login = (Button)findViewById(R.id.login);
        register = (Button)findViewById(R.id.register);
        back = (Button)findViewById(R.id.back);
        editId = (EditText)findViewById(R.id.editId);
        editKey = (EditText)findViewById(R.id.editKey);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                clickLogin();
                break;
            case R.id.register:
                clickRegister();
                break;
            case R.id.back:
                clickBack();
                break;
            default:
                break;
        }
    }

    private boolean validate(String str) {
        return str.matches("^[A-Z0-9a-z]+$") && !str.isEmpty();
    }

    public boolean isLeagalIdAndDo() {
        boolean flag = validate(editId.getText().toString());
        if(!flag)
            editId.setError(getString(R.string.error));
        else
            editId.setError(null);
        return flag;
    }

    public boolean isLeagalKeyAndDo() {
        boolean flag = validate(editKey.getText().toString());
        if(!flag)
            editKey.setError(getString(R.string.error));
        else
            editKey.setError(null);
        return flag;
    }

    public void clickLogin() {
        showProgressBar();
        if(isLeagalIdAndDo() && isLeagalKeyAndDo()) {
            sendBundleToMainScreen(Const.BackInfo.LOGINLOGIN);
        }
    }

    public void clickRegister() {
        showProgressBar();
        if(isLeagalIdAndDo() && isLeagalKeyAndDo()) {
            sendBundleToMainScreen(Const.BackInfo.LOGINREGISTER);
        }
    }

    public void clickBack() {
        sendBundleToMainScreen(Const.BackInfo.LOGINBAKE);
    }

    public void showProgressBar() {
        //显示进度，延迟2s关闭
        progressBar.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
            }
        }, 2000);
    }

    //跳转intent并且发送msg
    private void sendBundleToMainScreen(String msg) {
        Intent intent = new Intent("scos.intent.action.SCOSMAIN");
        intent.addCategory("scos.intent.category.SCOSLAUNCHER");
        Bundle bundle = new Bundle();
        bundle.putString(Const.BackInfo.STRINGKEY, msg);
        switch (msg) {
            case Const.BackInfo.LOGINLOGIN:
                bundle.putParcelable(Const.BackInfo.USERKEY,
                        new User(editId.getText().toString(),
                                editKey.getText().toString(), true));
                break;
            case Const.BackInfo.LOGINREGISTER:
                bundle.putParcelable(Const.BackInfo.USERKEY,
                        new User(editId.getText().toString(),
                                editKey.getText().toString(), false));
                break;
            case Const.BackInfo.LOGINBAKE:
                break;
            default:
                break;
        }
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
