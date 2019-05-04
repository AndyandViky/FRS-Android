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
import com.example.yanglin.arcface.controllers.UserCtrl;
import com.example.yanglin.arcface.models.Community;
import com.example.yanglin.arcface.utils.Enums;
import com.example.yanglin.arcface.utils.LoaddingDialog;
import com.example.yanglin.arcface.utils.OkhttpService;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;
import com.example.yanglin.arcface.views.adapter.CommunityAdapter;
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
 * Created by yanglin on 18-4-2.
 */

public class CommunityActivity extends AppCompatActivity {
    @BindView(R.id.community_list)
    RecyclerView communityRecycleView;
    CommunityAdapter communityAdapter;
    OkHttpClient okHttpClient = new OkHttpClient();
    private Dialog Loadding;
    UserCtrl userCtrl;
    Community community;

    int pageNo = 1;
    int pageSize = 5;
    int totalCount = 0;

    int currentIndex = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);
        ButterKnife.bind(this);

        communityRecycleView.setLayoutManager(new LinearLayoutManager(this));
        communityAdapter = new CommunityAdapter(this, new ArrayList<Community.DataBean.DatasBean>());
        communityAdapter.setOnItemClickListener(new CommunityAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(CommunityActivity.this, CommunityTrendsDetailActivity.class);
                Community.DataBean.DatasBean article = (Community.DataBean.DatasBean) view.getTag();
                intent.putExtra("article", article);
                startActivity(intent);
            }
        });
        communityRecycleView.setAdapter(communityAdapter);
        userCtrl = new UserCtrl();
        String name = Enums.Article.getName(currentIndex);
        getArticles(pageNo, pageSize, Enums.Article.getName(currentIndex));
    }

    @OnClick(R.id.commmunity_back)
    void backMain() {
        CommunityActivity.this.finish();
    }

    void getArticles(final int pageNo, int pageSize, String category) {
        Loadding = LoaddingDialog.createLoadingDialog(CommunityActivity.this, "加载中...");
        userCtrl.getArticles(okHttpClient, pageNo, pageSize, category, new OkhttpService.OnResponseListener() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                java.lang.reflect.Type type = new TypeToken<Community>() {}.getType();
                community = gson.fromJson(result, type);
                communityRecycleView.post(new Runnable() {
                    @Override
                    public void run() {
                        totalCount = community.getData().getTotal();
                        if(pageNo == 1) communityAdapter.replace(community.getData().getDatas());
                        else communityAdapter.addNew(community.getData().getDatas());
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

    @OnClick(R.id.community_more_button)
    void getMore() {
        if((pageNo)*pageSize >= totalCount) {
            Toast.makeText(CommunityActivity.this, "没有更多数据了！", Toast.LENGTH_SHORT).show();
        }
        else {
            pageNo++;
            getArticles(pageNo, pageSize, Enums.Article.getName(currentIndex));
        }
    }

    @BindView(R.id.article_recommond_text)
    TextView textRecommond;
    @BindView(R.id.article_all_text)
    TextView textAll;
    @BindView(R.id.article_other_text)
    TextView textOther;

    @BindView(R.id.article_recommond_border)
    View borderRecommond;
    @BindView(R.id.article_all_border)
    View borderAll;
    @BindView(R.id.article_other_border)
    View borderOther;
    int borderColor = 0xff48D1CC;

    @OnClick(R.id.article_recommond)
    void clickRecommond() {
        currentIndex = -1;
        pageNo = 1;
        getArticles(pageNo, pageSize, Enums.Article.getName(currentIndex));
        clearStyle();
        textRecommond.setTextColor(borderColor);
        borderRecommond.setBackgroundColor(borderColor);
    }
    @OnClick(R.id.article_all)
    void clickAll() {
        currentIndex = 0;
        pageNo = 1;
        getArticles(pageNo, pageSize, Enums.Article.getName(currentIndex));
        clearStyle();
        textAll.setTextColor(borderColor);
        borderAll.setBackgroundColor(borderColor);
    }
    @OnClick(R.id.article_other)
    void clickOther() {
        currentIndex = 1;
        pageNo = 1;
        getArticles(pageNo, pageSize, Enums.Article.getName(currentIndex));
        clearStyle();
        textOther.setTextColor(borderColor);
        borderOther.setBackgroundColor(borderColor);
    }
    void clearStyle() {
        int color = 0xff666666;
        int wite = 0xffffffff;

        textAll.setTextColor(color);
        textRecommond.setTextColor(color);
        textOther.setTextColor(color);

        borderAll.setBackgroundColor(wite);
        borderOther.setBackgroundColor(wite);
        borderRecommond.setBackgroundColor(wite);
    }
}
