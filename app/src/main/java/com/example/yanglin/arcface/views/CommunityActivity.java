package com.example.yanglin.arcface.views;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.controllers.UserCtrl;
import com.example.yanglin.arcface.models.Community;
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
    List<Community> communityList = new ArrayList<>();
    CommunityAdapter communityAdapter;
    OkHttpClient okHttpClient = new OkHttpClient();
    private Dialog Loadding;
    UserCtrl userCtrl;
    Community community;

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
                startActivity(new Intent(CommunityActivity.this, CommunityTrendsDetailActivity.class));
                Toast.makeText(CommunityActivity.this,String.valueOf(position),Toast.LENGTH_SHORT).show();
            }
        });
        communityRecycleView.setAdapter(communityAdapter);
        userCtrl = new UserCtrl();
        Loadding = LoaddingDialog.createLoadingDialog(CommunityActivity.this, "加载中...");
        userCtrl.getArticles(okHttpClient, 1, 10, new OkhttpService.OnResponseListener() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                java.lang.reflect.Type type = new TypeToken<Community>() {}.getType();
                community = gson.fromJson(result, type);
                communityRecycleView.post(new Runnable() {
                    @Override
                    public void run() {
                        communityAdapter.replace(community.getData().getDatas());
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

    @OnClick(R.id.commmunity_back)
    void backMain() {
        CommunityActivity.this.finish();
    }
}
