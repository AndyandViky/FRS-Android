package com.example.yanglin.arcface.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.controllers.UserCtrl;
import com.example.yanglin.arcface.models.User;
import com.example.yanglin.arcface.utils.Cache;
import com.example.yanglin.arcface.utils.OkhttpService;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;
import com.example.yanglin.arcface.views.dialog.CenterDialog;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;

/**
 * Created by yanglin on 18-3-22.
 */

public class ChangeUserInfoActivity extends AppCompatActivity implements  CenterDialog.OnCenterItemClickListener {

    private CenterDialog centerDialog;
    Cache cache = new Cache();

    @BindView(R.id.user_name_edit)
    EditText userName;
    @BindView(R.id.user_gender_edit)
    EditText userGender;
    @BindView(R.id.user_email_edit)
    EditText userEmail;
    @BindView(R.id.user_age_edit)
    EditText userAge;

    UserCtrl userCtrl = new UserCtrl();
    OkHttpClient okHttpClient = new OkHttpClient();

    String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_info);
        ButterKnife.bind(this);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);

        User user = cache.getUser();
        userName.setText(user.getData().getUser().getName());
        String ageStr = user.getData().getUser().getGender() == 0 ? "男" : "女";
        userGender.setText(ageStr);
        userEmail.setText(user.getData().getUser().getEmail());
        userAge.setText(String.valueOf(user.getData().getUser().getAge()));

        centerDialog = new CenterDialog(this, R.layout.confirm_dialog,
                new int[]{R.id.dialog_cancel, R.id.dialog_sure});
        centerDialog.setOnCenterItemClickListener(ChangeUserInfoActivity.this);

    }

    @OnClick(R.id.user_info_back_main)
    void backMain() {
        ChangeUserInfoActivity.this.finish();
    }

    @OnClick(R.id.put_user_info_button)
    void putUserInfo() {
        centerDialog.show();
    }

    @Override
    public void OnCenterItemClick(CenterDialog dialog, View view) {
        switch (view.getId()) {
            case R.id.dialog_sure:
                String email = userEmail.getText().toString().trim();
                int age = Integer.parseInt(userAge.getText().toString().trim());
                int gender = userGender.getText().toString().trim().equals("男") ? 0 : 1;
                String name = userName.getText().toString().trim();
                if(name.isEmpty() || email.isEmpty()) {
                    Toast.makeText(ChangeUserInfoActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(age <= 0 || age >= 100) {
                    Toast.makeText(ChangeUserInfoActivity.this, "年龄不能小于0且不能大于100", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!Pattern.matches(REGEX_EMAIL, email)) {
                    Toast.makeText(ChangeUserInfoActivity.this, "邮箱格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = cache.getUser();
                user.getData().getUser().setAge(age);
                user.getData().getUser().setEmail(email);
                user.getData().getUser().setGender(gender);
                user.getData().getUser().setName(name);
                cache.setUser(new Gson().toJson(user));
//                userCtrl.changeUserInfo(okHttpClient, new Gson().toJson(), new OkhttpService.OnResponseListener() {
//                    @Override
//                    public void onSuccess(String result) {
//                        (ChangeUserInfoActivity.this).runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(ChangeUserInfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onFailure(IOException error) {
//
//                    }
//                });
                Toast.makeText(ChangeUserInfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                break;
            case R.id.dialog_cancel:
                dialog.dismiss();
                break;
            default:
                break;
        }
    }
}
