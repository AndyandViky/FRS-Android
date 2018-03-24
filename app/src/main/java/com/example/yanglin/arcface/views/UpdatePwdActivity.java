package com.example.yanglin.arcface.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;
import com.example.yanglin.arcface.views.dialog.CenterDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yanglin on 18-3-22.
 */

public class UpdatePwdActivity extends AppCompatActivity implements  CenterDialog.OnCenterItemClickListener{
    private CenterDialog centerDialog;
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
        centerDialog.show();
        Toast.makeText(UpdatePwdActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnCenterItemClick(CenterDialog dialog, View view) {
        switch (view.getId()) {
            case R.id.dialog_sure:
                dialog.dismiss();
                Toast.makeText(UpdatePwdActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dialog_cancel:
                dialog.dismiss();
                break;
            default:
                break;
        }
    }
}
