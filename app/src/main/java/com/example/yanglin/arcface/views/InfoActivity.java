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
    NoticeCtrl noticeCtrl;

    int pageNo = 1;
    int currentStatus = -1;
    int pageSize = 10;
    int totalCount = 0;

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
                Intent intent = new Intent(InfoActivity.this, InfoDetailActivity.class);
                Info.DataBean.DatasBean info = (Info.DataBean.DatasBean) view.getTag();
                intent.putExtra("info", info);
                startActivity(intent);
            }
        });

        infoRecyclerView.setAdapter(infoListAdapter);
        noticeCtrl = new NoticeCtrl();
        getNotice(pageNo, pageSize, currentStatus);
    }

    @OnClick(R.id.info_back)
    void backMain() {
        InfoActivity.this.finish();
    }

    @OnClick(R.id.info_all)
    void clickAll() {
        currentStatus = -1;
        pageNo = 1;
        getNotice(pageNo, pageSize, currentStatus);
        clearStyle();
        textAll.setTextColor(borderColor);
        borderAll.setBackgroundColor(borderColor);
    }
    @OnClick(R.id.info_wait_read)
    void clickWaitPass() {
        currentStatus = 0;
        pageNo = 1;
        getNotice(pageNo, pageSize, currentStatus);
        clearStyle();
        textWait.setTextColor(borderColor);
        borderWait.setBackgroundColor(borderColor);
    }
    @OnClick(R.id.info_read)
    void clickPassed() {
        currentStatus = 1;
        pageNo = 1;
        getNotice(pageNo, pageSize, currentStatus);
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
    void getNotice(final int pageNo, int pageSize, int status) {
        Loadding = LoaddingDialog.createLoadingDialog(InfoActivity.this, "加载中...");
        noticeCtrl.getNotice(okHttpClient, pageNo, pageSize, status, new OkhttpService.OnResponseListener() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                java.lang.reflect.Type type = new TypeToken<Info>() {}.getType();
                info = gson.fromJson(result, type);
                infoRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        totalCount = info.getData().getTotal();
                        if(pageNo == 1) infoListAdapter.replace(info.getData().getDatas());
                        else infoListAdapter.addNew(info.getData().getDatas());
                        LoaddingDialog.closeDialog(Loadding);
                    }
                });
            }

            @Override
            public void onFailure(IOException error) {
                LoaddingDialog.closeDialog(Loadding);
            }
        });
    }

    @OnClick(R.id.info_more_button)
    void getMore() {
        if((pageNo)*pageSize >= totalCount) {
            Toast.makeText(InfoActivity.this, "没有更多数据了！", Toast.LENGTH_SHORT).show();
        }
        else {
            pageNo++;
            getNotice(pageNo, pageSize, currentStatus);
        }
    }
}
