package com.example.yanglin.arcface.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yanglin on 18-3-20.
 */

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.register_phone)
    EditText rePhone;
    @BindView(R.id.register_hint)
    EditText reHint;
    @BindView(R.id.register_pwd)
    EditText rePwd;
    @BindView(R.id.register_conforim_pwd)
    EditText reCPwd;
    String type = "";
    @BindView(R.id.register)
    Button registerButton;
    @BindView(R.id.register_title)
    TextView registerTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);
        type = getIntent().getStringExtra("type");
        if(type!=null && !type.isEmpty() && type.equals("1")) {
            registerTitle.setText("用户注册");
            registerButton.setText("注册");
        } else {
            registerTitle.setText("找回密码");
            registerButton.setText("重置");
        }
    }

    @OnClick(R.id.register_back_login)
    void registerBack() {
        RegisterActivity.this.finish();
    }

    @OnClick(R.id.register)
    void register() {
        if(type.equals("1")) {

        } else if(type.equals("2")) {

        } else {

        }
    }
}
