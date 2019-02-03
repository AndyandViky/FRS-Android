package com.example.yanglin.arcface.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.controllers.UserCtrl;
import com.example.yanglin.arcface.models.BaseResponse;
import com.example.yanglin.arcface.models.RegisterVisitor;
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
 * Created by yanglin on 18-3-23.
 */

public class VisitorRegisterActivity extends CameraUtil implements  CenterDialog.OnCenterItemClickListener, BottomDialog.OnBottomItemClickListener{
    private CenterDialog centerDialog;
    private BottomDialog bottomDialog;
    OkHttpClient okHttpClient = new OkHttpClient();
    UserCtrl userCtrl = new UserCtrl();

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


    @BindView(R.id.visitor_name)
    EditText vName;
    @BindView(R.id.visitor_gender)
    EditText vGender;
    @BindView(R.id.visitor_age)
    EditText vAge;
    @BindView(R.id.visitor_phone)
    EditText vPhone;
    @BindView(R.id.visitor_deadline)
    EditText vTime;
    @BindView(R.id.visitor_reason)
    EditText vReason;
    @Override
    public void OnCenterItemClick(CenterDialog dialog, View view) {
        switch (view.getId()) {
            case R.id.dialog_sure:
                dialog.dismiss();
                String name = vName.getText().toString();
                String gender = vGender.getText().toString().trim() == "男"?"0":"1";
                String age = vAge.getText().toString();
                String phone = vPhone.getText().toString();
                String time = vTime.getText().toString();
                String reason = vReason.getText().toString();
                if (reason.isEmpty() || name.isEmpty() || gender.isEmpty() || age.isEmpty() || phone.isEmpty() || time.isEmpty()) {
                    Toast.makeText(VisitorRegisterActivity.this, "请输入必要信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                String path = this.getPath();
                if (path == null) {
                    Toast.makeText(VisitorRegisterActivity.this, "请上传人脸图像并检测", Toast.LENGTH_SHORT).show();
                    return;
                }
                RegisterVisitor registerVisitor = new RegisterVisitor();
                registerVisitor.setAdress_id(1);
                registerVisitor.setBelong(2);
                registerVisitor.setAge(Integer.parseInt(age));
                registerVisitor.setDeadline(Integer.parseInt(time));
                registerVisitor.setFacePath(path);
                registerVisitor.setName(name);
                registerVisitor.setGender(Integer.parseInt(gender));
                registerVisitor.setPhone(phone);
                registerVisitor.setReason(reason);
                Gson gs = new Gson();
                String data = gs.toJson(registerVisitor);
                userCtrl.applyVisitor(okHttpClient, data, new OkhttpService.OnResponseListener() {
                    @Override
                    public void onSuccess(String result) {
//                        java.lang.reflect.Type type = new TypeToken<BaseResponse>() {}.getType();
//                        final BaseResponse baseResponse = new Gson().fromJson(result, type);
//                        if (baseResponse.getCode() != 1) {
//                            (VisitorRegisterActivity.this).runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Toast.makeText(VisitorRegisterActivity.this, baseResponse.getMsg(), Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                            return;
//                        }
                        VisitorRegisterActivity.this.deltePath();
                        (VisitorRegisterActivity.this).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(VisitorRegisterActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                        VisitorRegisterActivity.this.finish();
                    }

                    @Override
                    public void onFailure(IOException error) {
                        (VisitorRegisterActivity.this).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(VisitorRegisterActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
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
