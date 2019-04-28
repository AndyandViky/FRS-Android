package com.example.yanglin.arcface.views;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.controllers.NoticeCtrl;
import com.example.yanglin.arcface.controllers.UserCtrl;
import com.example.yanglin.arcface.models.Info;
import com.example.yanglin.arcface.utils.OkhttpService;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;

import org.w3c.dom.Text;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;

/**
 * Created by yanglin on 18-4-3.
 */

public class InfoDetailActivity extends AppCompatActivity {
    OkHttpClient okHttpClient = new OkHttpClient();
    private Dialog Loadding;
    NoticeCtrl noticeCtrl = new NoticeCtrl();

    @BindView(R.id.info_detail_title)
    TextView infoTitle;
    @BindView(R.id.info_detail_time)
    TextView infoSendTime;
    @BindView(R.id.info_detail_content)
    TextView infoContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_detail);
        ButterKnife.bind(this);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);

        Intent intent = getIntent();
        Info.DataBean.DatasBean info = (Info.DataBean.DatasBean)intent.getSerializableExtra("info");
        infoTitle.setText(info.getTitle());
        infoSendTime.setText(info.getCreated_at().substring(0, 10));
        infoContent.setText(info.getContent());


        if(info != null && info.getStatus() == 0) {
            noticeCtrl.setReaded(okHttpClient, "{\"noticeId\": \""+info.getId()+"\"}", new OkhttpService.OnResponseListener() {
                @Override
                public void onSuccess(String result) {
                }
                @Override
                public void onFailure(IOException error) {
                }
            });
        }
    }

    @OnClick(R.id.info_detail_back)
    void back(){
        InfoDetailActivity.this.finish();
    }
}
