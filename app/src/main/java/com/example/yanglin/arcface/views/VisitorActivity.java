package com.example.yanglin.arcface.views;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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
    int borderColor = 0xff48D1CC;

    List<Visitor> visitorList = new ArrayList<>();
    VisitorAdapter visitorAdapter;
    @BindView(R.id.visitor_list)
    RecyclerView visitorListView;

    Visitor visitor;
    OkHttpClient okHttpClient = new OkHttpClient();
    private Dialog Loadding;
    UserCtrl userCtrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor);
        ButterKnife.bind(this);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);

        visitorListView.setLayoutManager(new LinearLayoutManager(this));
        visitorAdapter = new VisitorAdapter(this, new ArrayList<Visitor.DataBean.DatasBean>());
        visitorAdapter.setOnItemClickListener(new VisitorAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
               // Toast.makeText(VisitorActivity.this,String.valueOf(position),Toast.LENGTH_SHORT).show();
            }
        });
        visitorListView.setAdapter(visitorAdapter);
        userCtrl = new UserCtrl();
        getVisitorHttp(1, 10, -1);
    }

    @OnClick(R.id.visitor_list_back)
    void backMain() {
        VisitorActivity.this.finish();
    }

    @OnClick(R.id.visitor_all)
    void clickAll() {
        getVisitorHttp(1, 10, -1);
        clearStyle();
        textAll.setTextColor(borderColor);
        borderAll.setBackgroundColor(borderColor);
    }
    @OnClick(R.id.visitor_wait_pass)
    void clickWaitPass() {
        getVisitorHttp(1, 10, 0);
        clearStyle();
        textWait.setTextColor(borderColor);
        borderWait.setBackgroundColor(borderColor);
    }
    @OnClick(R.id.visitor_passed)
    void clickPassed() {
        getVisitorHttp(1, 10, 1);
//        ArrayList<Visitor> visitors = new ArrayList<>();
//        for(int i=0; i<visitorList.size(); i++) {
//            if (visitorList.get(i).getType() == 1) {
//                visitors.add((visitorList.get(i)));
//            }
//        }
//        visitorAdapter = new VisitorAdapter(this, visitors);
//        visitorListView.setAdapter(visitorAdapter);
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
    void getVisitorHttp(int pageNo, int pageSize, int status) {
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
                        visitorAdapter.replace(visitor.getData().getDatas());
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
}
