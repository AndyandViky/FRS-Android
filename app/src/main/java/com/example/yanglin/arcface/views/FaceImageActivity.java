package com.example.yanglin.arcface.views;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.models.FaceImage;
import com.example.yanglin.arcface.utils.Enums;
import com.example.yanglin.arcface.utils.camera.CameraUtil;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;
import com.example.yanglin.arcface.views.adapter.FaceImageAdapter;
import com.example.yanglin.arcface.views.dialog.BottomDialog;
import com.example.yanglin.arcface.views.dialog.CenterDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yanglin on 18-3-24.
 */

public class FaceImageActivity extends CameraUtil implements  CenterDialog.OnCenterItemClickListener, BottomDialog.OnBottomItemClickListener{
    private CenterDialog centerDialog;
    private BottomDialog bottomDialog;
    private static final int CHOOSE_PHOTO = 2;

    @BindView(R.id.face_all_text)
    TextView textAll;
    @BindView(R.id.face_wait_use_text)
    TextView textWait;
    @BindView(R.id.face_use_text)
    TextView textPassed;

    @BindView(R.id.face_all_border)
    View borderAll;
    @BindView(R.id.face_wait_use_border)
    View borderWait;
    @BindView(R.id.face_use_border)
    View borderPassed;
    int borderColor = 0xff48D1CC;

    @BindView(R.id.face_image_list)
    RecyclerView faceImageRecycleView;
    List<FaceImage> faceImageList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_image);
        centerDialog = new CenterDialog(this, R.layout.face_image_dialog,
                new int[]{R.id.face_cancel, R.id.face_sure});
        centerDialog.setOnCenterItemClickListener(this);
        bottomDialog = new BottomDialog(this, R.layout.photo_dialog,
                new int[]{R.id.photo_dialog_camera, R.id.photo_dialog_photo, R.id.photo_dialog_cancel});
        bottomDialog.setOnBottomItemClickListener(this);
        ButterKnife.bind(this);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);

        for(int i=0; i<5; i++) {
            FaceImage faceImage = new FaceImage("");
            faceImageList.add(faceImage);
        }
        faceImageRecycleView.setLayoutManager(new LinearLayoutManager(this));
        FaceImageAdapter faceImageAdapter = new FaceImageAdapter(this, faceImageList);
        faceImageAdapter.setOnItemLongClickListener(new FaceImageAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                centerDialog.show();
            }
        });
        faceImageRecycleView.setAdapter(faceImageAdapter);
    }

    @OnClick(R.id.face_image_back)
    void backMain() {
        FaceImageActivity.this.finish();
    }

    @OnClick(R.id.face_all)
    void clickAll() {
        clearStyle();
        textAll.setTextColor(borderColor);
        borderAll.setBackgroundColor(borderColor);
    }
    @OnClick(R.id.face_wait_use)
    void clickWaitPass() {
        clearStyle();
        textWait.setTextColor(borderColor);
        borderWait.setBackgroundColor(borderColor);
    }
    @OnClick(R.id.face_use)
    void clickPassed() {
        clearStyle();
        textPassed.setTextColor(borderColor);
        borderPassed.setBackgroundColor(borderColor);
    }
    void clearStyle() {
        int color = 0xff666666;
        int wite = 0xffffffff;

        textAll.setTextColor(color);
        textWait.setTextColor(color);
        textPassed.setTextColor(color);

        borderAll.setBackgroundColor(wite);
        borderWait.setBackgroundColor(wite);
        borderPassed.setBackgroundColor(wite);
    }

    @Override
    public void OnCenterItemClick(CenterDialog dialog, View view) {
        switch (view.getId()) {
            case R.id.face_cancel:
                dialog.dismiss();
                Toast.makeText(FaceImageActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.face_sure:
                dialog.dismiss();
                Toast.makeText(FaceImageActivity.this, "启用成功", Toast.LENGTH_SHORT).show();
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
                this.openCamera(Enums.Camera.UPLOADFACE);
                break;
            case R.id.photo_dialog_photo:
                dialog.dismiss();
                this.openAlbum(Enums.Camera.UPLOADFACE);
                break;
            case R.id.photo_dialog_cancel:
                dialog.dismiss();
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.add_face_image)
    void addFaceImage() {
        bottomDialog.show();
    }
}
