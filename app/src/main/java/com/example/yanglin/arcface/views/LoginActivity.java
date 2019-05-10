package com.example.yanglin.arcface.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.controllers.UserCtrl;
import com.example.yanglin.arcface.models.User;
import com.example.yanglin.arcface.utils.Cache;
import com.example.yanglin.arcface.utils.OkhttpService;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;

/**
 * Created by yanglin on 18-3-20.
 */

public class LoginActivity extends AppCompatActivity{

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.password)
    EditText password;
    UserCtrl userCtrl = new UserCtrl();
    OkHttpClient okHttpClient = new OkHttpClient();
    Cache cache = new Cache();
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
            userCtrl.login(okHttpClient, "{\"username\": \""+name+"\", \"password\": \""+pwd+"\"}", new OkhttpService.OnResponseListener() {
                @Override
                public void onSuccess(String result) {
                    java.lang.reflect.Type type = new TypeToken<User>() {}.getType();
                    final User user = new Gson().fromJson(result, type);
                    if (user.getCode() != 1) {
                        (LoginActivity.this).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, user.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        return;
                    }
                    cache.setUser(result, getFilesDir());
                    (LoginActivity.this).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "登录成功",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            LoginActivity.this.finish();
                        }
                    });
                }

                @Override
                public void onFailure(IOException error) {
                    (LoginActivity.this).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "添加失败，网络异常", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }
}
