package com.example.yanglin.arcface.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.controllers.BugCtrl;
import com.example.yanglin.arcface.controllers.NoticeCtrl;
import com.example.yanglin.arcface.models.ApplyBug;
import com.example.yanglin.arcface.models.BaseResponse;
import com.example.yanglin.arcface.models.Info;
import com.example.yanglin.arcface.utils.Enums;
import com.example.yanglin.arcface.utils.OkhttpService;
import com.example.yanglin.arcface.utils.camera.CameraUtil;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;
import com.example.yanglin.arcface.views.dialog.BottomDialog;
import com.example.yanglin.arcface.views.dialog.CenterDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;

/**
 * Created by yanglin on 18-3-21.
 */

public class ApplyBugActivity extends CameraUtil implements  CenterDialog.OnCenterItemClickListener, BottomDialog.OnBottomItemClickListener{
    private CenterDialog centerDialog;
    private BottomDialog bottomDialog;
    OkHttpClient okHttpClient = new OkHttpClient();
    BugCtrl bugCtrl = new BugCtrl();

    @BindView(R.id.bug_apply_image)
    ImageView bugImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug_apply);
        ButterKnife.bind(this);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);
        centerDialog = new CenterDialog(this, R.layout.confirm_dialog,
                new int[]{R.id.dialog_cancel, R.id.dialog_sure});
        centerDialog.setOnCenterItemClickListener(this);

        bottomDialog = new BottomDialog(this, R.layout.photo_dialog,
                new int[]{R.id.photo_dialog_camera, R.id.photo_dialog_photo, R.id.photo_dialog_cancel});
        bottomDialog.setOnBottomItemClickListener(this);
    }

    @OnClick(R.id.bug_back)
    void bugBack() {
        ApplyBugActivity.this.finish();
    }

    @OnClick(R.id.bug_apply_button)
    void postBug() {
        centerDialog.show();
    }

    @OnClick(R.id.bug_image)
    void addBugImage() {
        bottomDialog.show();
    }

    @BindView(R.id.bug_content)
    EditText bugContent;
    @Override
    public void OnCenterItemClick(CenterDialog dialog, View view) {
        switch (view.getId()) {
            case R.id.dialog_sure:
                dialog.dismiss();
                String content = bugContent.getText().toString();
                if (content.isEmpty()) {
                    Toast.makeText(ApplyBugActivity.this, "请输入故障内容", Toast.LENGTH_SHORT).show();
                    return;
                }
                ApplyBug applyBug = new ApplyBug();
                applyBug.setTitle("故障申报");
                applyBug.setContent(content);
                if (this.getPath() != null) {
                    applyBug.setPath(this.getPath());
                }
                Gson gs = new Gson();
                String data = gs.toJson(applyBug);
                bugCtrl.applyBug(okHttpClient, data, new OkhttpService.OnResponseListener() {
                    @Override
                    public void onSuccess(String result) {
                        java.lang.reflect.Type type = new TypeToken<BaseResponse>() {}.getType();
                        final BaseResponse baseResponse = new Gson().fromJson(result, type);
                        if (baseResponse.getCode() != 1) {
                            (ApplyBugActivity.this).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ApplyBugActivity.this, baseResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            });
                            return;
                        }
                        ApplyBugActivity.this.deltePath();
                        (ApplyBugActivity.this).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ApplyBugActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                        ApplyBugActivity.this.finish();
                    }

                    @Override
                    public void onFailure(IOException error) {
                        (ApplyBugActivity.this) .runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ApplyBugActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                            }
                        });
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

    @Override
    public void OnBottomItemClick(BottomDialog dialog, View view) {
        switch (view.getId()) {
            case R.id.photo_dialog_camera:
                dialog.dismiss();
                this.openCamera(Enums.Camera.UPLOADBUG, bugImage);
                break;
            case R.id.photo_dialog_photo:
                dialog.dismiss();
                this.openAlbum(Enums.Camera.UPLOADBUG, bugImage);
                break;
            case R.id.photo_dialog_cancel:
                dialog.dismiss();
                break;
            default:
                break;
        }
    }
}
