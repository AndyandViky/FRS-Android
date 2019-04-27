package com.example.yanglin.arcface.views;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.controllers.NoticeCtrl;
import com.example.yanglin.arcface.models.Info;
import com.example.yanglin.arcface.utils.LoaddingDialog;
import com.example.yanglin.arcface.utils.OkhttpService;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;
import com.example.yanglin.arcface.views.adapter.InfoListAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;

/**
 * Created by yanglin on 18-3-31.
 */

public class InfoActivity extends AppCompatActivity {
    @BindView(R.id.info_all_text)
    TextView textAll;
    @BindView(R.id.info_wait_read_text)
    TextView textWait;
    @BindView(R.id.info_read_text)
    TextView textPassed;

    @BindView(R.id.info_all_border)
    View borderAll;
    @BindView(R.id.info_wait_read_border)
    View borderWait;
    @BindView(R.id.info_read_border)
    View borderPassed;
    int borderColor = 0xff48D1CC;

    @BindView(R.id.info_list)
    RecyclerView infoRecyclerView;
    Info info;
    InfoListAdapter infoListAdapter;
    OkHttpClient okHttpClient = new OkHttpClient();
    private Dialog Loadding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);
        ButterKnife.bind(this);
        infoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        infoListAdapter = new InfoListAdapter(InfoActivity.this, new ArrayList<Info.DataBean.DatasBean>());
        infoListAdapter.setOnItemClickListener(new InfoListAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(InfoActivity.this, InfoDetailActivity.class));
                Toast.makeText(InfoActivity.this,String.valueOf(position),Toast.LENGTH_SHORT).show();
            }
        });

        infoRecyclerView.setAdapter(infoListAdapter);
        NoticeCtrl noticeCtrl = new NoticeCtrl();
        Loadding = LoaddingDialog.createLoadingDialog(InfoActivity.this, "加载中...");
        noticeCtrl.getNotice(okHttpClient, 1, 10, -1, new OkhttpService.OnResponseListener() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                java.lang.reflect.Type type = new TypeToken<Info>() {}.getType();
                info = gson.fromJson(result, type);
                infoRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        infoListAdapter.replace(info.getData().getDatas());
                    }
                });
                LoaddingDialog.closeDialog(Loadding);
            }

            @Override
            public void onFailure(IOException error) {
                LoaddingDialog.closeDialog(Loadding);
            }
        });
    }

    @OnClick(R.id.info_back)
    void backMain() {
        InfoActivity.this.finish();
    }

    @OnClick(R.id.info_all)
    void clickAll() {
        infoListAdapter.replace(info.getData().getDatas());
        clearStyle();
        textAll.setTextColor(borderColor);
        borderAll.setBackgroundColor(borderColor);
    }
    @OnClick(R.id.info_wait_read)
    void clickWaitPass() {
        ArrayList<Info.DataBean.DatasBean> infos = new ArrayList<>();
        for(int i=0; i<info.getData().getDatas().size(); i++) {
            if (info.getData().getDatas().get(i).getStatus() == 1) {
                infos.add(info.getData().getDatas().get(i));
            }
        }
        infoListAdapter.replace(infos);
        clearStyle();
        textWait.setTextColor(borderColor);
        borderWait.setBackgroundColor(borderColor);
    }
    @OnClick(R.id.info_read)
    void clickPassed() {
        ArrayList<Info.DataBean.DatasBean> infos = new ArrayList<>();
        for(int i=0; i<info.getData().getDatas().size(); i++) {
            if (info.getData().getDatas().get(i).getStatus() == 0) {
                infos.add(info.getData().getDatas().get(i));
            }
        }
        infoListAdapter.replace(infos);
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
}
