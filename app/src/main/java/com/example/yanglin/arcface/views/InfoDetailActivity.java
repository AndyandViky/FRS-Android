package com.example.yanglin.arcface.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yanglin on 18-4-3.
 */

public class InfoDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_detail);
        ButterKnife.bind(this);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);
    }

    @OnClick(R.id.info_detail_back)
    void back(){
        InfoDetailActivity.this.finish();
    }
}
