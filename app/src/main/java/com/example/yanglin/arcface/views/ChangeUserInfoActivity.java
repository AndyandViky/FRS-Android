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

    UserCtrl userCtrl = new UserCtrl();
    OkHttpClient okHttpClient = new OkHttpClient();

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
