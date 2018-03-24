package com.example.yanglin.arcface.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;
import com.example.yanglin.arcface.views.dialog.CenterDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yanglin on 18-3-22.
 */

public class ChangeUserInfoActivity extends AppCompatActivity implements  CenterDialog.OnCenterItemClickListener {

    private CenterDialog centerDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_info);
        ButterKnife.bind(this);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);

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
                dialog.dismiss();
                Toast.makeText(ChangeUserInfoActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dialog_cancel:
                dialog.dismiss();
                break;
            default:
                break;
        }
    }
}
