package com.example.yanglin.arcface.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yanglin on 18-3-20.
 */

public class LoginActivity extends AppCompatActivity{

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.password)
    EditText password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);
    }

    @OnClick(R.id.login)
    void login() {
        String name = phone.getText().toString().trim();
        String pwd = password.getText().toString().trim();
        if(name==null || name.isEmpty() || pwd == null || pwd.isEmpty()) {
            Toast.makeText(LoginActivity.this, "帐号或密码不能未空",Toast.LENGTH_SHORT).show();
        }
        else{
            if (!name.equals("123456") || !pwd.equals("123456")) {
                Toast.makeText(LoginActivity.this, "密码错误",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, "登录成功",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        }
    }

    @OnClick(R.id.login_register)
    void toRegister() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        intent.putExtra("type", "1");
        startActivity(intent);
    }

    @OnClick(R.id.login_forget_pwd)
    void toForgetPwd() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        intent.putExtra("type", "2");
        startActivity(intent);
    }
}
