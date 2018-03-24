package com.example.yanglin.arcface.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yanglin on 18-3-24.
 */

public class FaceImageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_image);
        ButterKnife.bind(this);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);
    }

    @OnClick(R.id.face_image_back)
    void backMain() {
        FaceImageActivity.this.finish();
    }
}
