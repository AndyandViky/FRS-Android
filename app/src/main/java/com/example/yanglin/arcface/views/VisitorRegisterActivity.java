package com.example.yanglin.arcface.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.utils.Enums;
import com.example.yanglin.arcface.utils.camera.CameraUtil;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;
import com.example.yanglin.arcface.views.dialog.BottomDialog;
import com.example.yanglin.arcface.views.dialog.CenterDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yanglin on 18-3-23.
 */

public class VisitorRegisterActivity extends CameraUtil implements  CenterDialog.OnCenterItemClickListener, BottomDialog.OnBottomItemClickListener{
    private CenterDialog centerDialog;
    private BottomDialog bottomDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_register);
        ButterKnife.bind(this);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);

        centerDialog = new CenterDialog(this, R.layout.confirm_dialog,
                new int[]{R.id.dialog_cancel, R.id.dialog_sure});
        centerDialog.setOnCenterItemClickListener(this);

        bottomDialog = new BottomDialog(this, R.layout.photo_dialog,
                new int[]{R.id.photo_dialog_camera, R.id.photo_dialog_photo, R.id.photo_dialog_cancel});
        bottomDialog.setOnBottomItemClickListener(this);
    }

    @OnClick(R.id.visitor_register_back)
    void visitorBack() {
        VisitorRegisterActivity.this.finish();
    }

    @OnClick(R.id.visitor_register_button)
    void postVisitor() {
        centerDialog.show();
    }

    @OnClick(R.id.visitor_image)
    void addImage() {
        bottomDialog.show();
    }


    @Override
    public void OnCenterItemClick(CenterDialog dialog, View view) {
        switch (view.getId()) {
            case R.id.dialog_sure:
                dialog.dismiss();
                Toast.makeText(VisitorRegisterActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dialog_cancel:
                dialog.dismiss();
                break;
            default:
                break;
        }
    }

    @Override
    public void OnBottomItemClick(BottomDialog dialog, View view) {
        switch (view.getId()) {
            case R.id.photo_dialog_camera:
                dialog.dismiss();
                this.openCamera(Enums.Camera.UPLOADVISITOR);
                break;
            case R.id.photo_dialog_photo:
                dialog.dismiss();
                this.openAlbum(Enums.Camera.UPLOADVISITOR);
                break;
            case R.id.photo_dialog_cancel:
                dialog.dismiss();
                break;
            default:
                break;
        }
    }
}
