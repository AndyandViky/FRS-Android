package com.example.yanglin.arcface.views;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.controllers.UserCtrl;
import com.example.yanglin.arcface.models.Visitor;
import com.example.yanglin.arcface.utils.LoaddingDialog;
import com.example.yanglin.arcface.utils.OkhttpService;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;
import com.example.yanglin.arcface.views.adapter.VisitorAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;

/**
 * Created by yanglin on 18-3-22.
 */

public class VisitorActivity extends AppCompatActivity {
    @BindView(R.id.visitor_all_text)
    TextView textAll;
    @BindView(R.id.visitor_wait_pass_text)
    TextView textWait;
    @BindView(R.id.visitor_passed_text)
    TextView textPassed;

    @BindView(R.id.visitor_all_border)
    View borderAll;
    @BindView(R.id.visitor_wait_pass_border)
    View borderWait;
    @BindView(R.id.visitor_passed_border)
    View borderPassed;
    @BindView(R.id.visitor_list)
    RecyclerView visitorListView;

    int borderColor = 0xff48D1CC;
    VisitorAdapter visitorAdapter;
    Visitor visitor;
    OkHttpClient okHttpClient = new OkHttpClient();
    private Dialog Loadding;
    UserCtrl userCtrl;

    int pageNo = 1;
    int currentStatus = -1;
    int pageSize = 10;
    int totalCount = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor);
        ButterKnife.bind(this);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);

        visitorListView.setLayoutManager(new LinearLayoutManager(this));
        visitorAdapter = new VisitorAdapter(this, new ArrayList<Visitor.DataBean.DatasBean>());
        visitorListView.setAdapter(visitorAdapter);
        userCtrl = new UserCtrl();
        getVisitorHttp(pageNo, pageSize, currentStatus);
    }

    @OnClick(R.id.visitor_list_back)
    void backMain() {
        VisitorActivity.this.finish();
    }

    @OnClick(R.id.visitor_all)
    void clickAll() {
        currentStatus = -1;
        pageNo = 1;
        getVisitorHttp(pageNo, pageSize, currentStatus);
        clearStyle();
        textAll.setTextColor(borderColor);
        borderAll.setBackgroundColor(borderColor);
    }
    @OnClick(R.id.visitor_wait_pass)
    void clickWaitPass() {
        currentStatus = 0;
        pageNo = 1;
        getVisitorHttp(pageNo, pageSize, currentStatus);
        clearStyle();
        textWait.setTextColor(borderColor);
        borderWait.setBackgroundColor(borderColor);
    }
    @OnClick(R.id.visitor_passed)
    void clickPassed() {
        currentStatus = 3;
        pageNo = 1;
        getVisitorHttp(pageNo, pageSize, currentStatus);
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
    void getVisitorHttp(final int pageNo, int pageSize, int status) {
        Loadding = LoaddingDialog.createLoadingDialog(VisitorActivity.this, "加载中...");
        userCtrl.getVisitors(okHttpClient, pageNo, pageSize, status, new OkhttpService.OnResponseListener() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                java.lang.reflect.Type type = new TypeToken<Visitor>() {}.getType();
                visitor = gson.fromJson(result, type);
                visitorListView.post(new Runnable() {
                    @Override
                    public void run() {
                        totalCount = visitor.getData().getTotal();
                        if(pageNo == 1) visitorAdapter.replace(visitor.getData().getDatas());
                        else visitorAdapter.addNew(visitor.getData().getDatas());
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

    @OnClick(R.id.visitor_more_button)
    void getMore() {
        if((pageNo)*pageSize >= totalCount) {
            Toast.makeText(VisitorActivity.this, "没有更多数据了！", Toast.LENGTH_SHORT).show();
        }
        else {
            pageNo++;
            getVisitorHttp(pageNo, pageSize, currentStatus);
        }
    }
}
