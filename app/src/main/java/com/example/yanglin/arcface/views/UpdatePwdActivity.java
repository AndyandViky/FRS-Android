package com.example.yanglin.arcface.views;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.controllers.UserCtrl;
import com.example.yanglin.arcface.models.BaseResponse;
import com.example.yanglin.arcface.models.Password;
import com.example.yanglin.arcface.utils.Cache;
import com.example.yanglin.arcface.utils.OkhttpService;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;
import com.example.yanglin.arcface.views.dialog.CenterDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;

/**
 * Created by yanglin on 18-3-22.
 */

public class UpdatePwdActivity extends AppCompatActivity implements CenterDialog.OnCenterItemClickListener{
    private CenterDialog centerDialog;
    @BindView(R.id.old_pwd)
    EditText oldPwd;
    @BindView(R.id.new_pwd)
    EditText newPwd;
    @BindView(R.id.new_confirm_pwd)
    EditText newConfirmPwd;
    UserCtrl userCtrl = new UserCtrl();
    OkHttpClient okHttpClient = new OkHttpClient();
    String oldP;
    String newP;
    String conP;

    Cache cache = new Cache();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pwd);
        ButterKnife.bind(this);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);
        centerDialog = new CenterDialog(this, R.layout.confirm_dialog,
                new int[]{R.id.dialog_cancel, R.id.dialog_sure});
        centerDialog.setOnCenterItemClickListener(this);
    }

    @OnClick(R.id.update_pwd_back_main)
    void back() {
        UpdatePwdActivity.this.finish();
    }

    @OnClick(R.id.update_pwd_button)
    void updatePwd() {
        oldP = oldPwd.getText().toString().trim();
        newP = newPwd.getText().toString().trim();
        conP = newConfirmPwd.getText().toString().trim();
        String result = "-1";
        if(oldP.isEmpty()) {
            result = "请输入旧密码";
        }
        if(newP.isEmpty()) {
            result = "请输入新密码";
        }
        if(conP.isEmpty()) {
            result = "请再次输入新密码";
        }
        if(!newP.equals(conP)) {
            result = "两次密码输入不一致";
        }
        if(result.equals("-1")) {
            centerDialog.show();
        }
        else Toast.makeText(UpdatePwdActivity.this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnCenterItemClick(CenterDialog dialog, View view) {
        switch (view.getId()) {
            case R.id.dialog_sure:
                Password password = new Password();
                password.setOldP(oldP);
                password.setNewP(newP);
                password.setConP(conP);
                userCtrl.updatePassword(okHttpClient, new Gson().toJson(password), new OkhttpService.OnResponseListener() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        java.lang.reflect.Type type = new TypeToken<BaseResponse>() {}.getType();
                        final BaseResponse baseResponse = gson.fromJson(result, type);
                        if(baseResponse.getCode() == -1) {
                            (UpdatePwdActivity.this).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(UpdatePwdActivity.this, baseResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else {
                            (UpdatePwdActivity.this).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(UpdatePwdActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                    cache.deleteUser();
                                    startActivity(new Intent(UpdatePwdActivity.this, LoginActivity.class));
                                    UpdatePwdActivity.this.finish();
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(IOException error) {

                    }
                });
                break;
            case R.id.dialog_cancel:
                dialog.dismiss();
                break;
            default:
                break;
        }
    }
}
