package com.example.yanglin.arcface.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.models.Community;
import com.example.yanglin.arcface.models.Info;
import com.example.yanglin.arcface.utils.data.DataUtil;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;
import com.example.yanglin.arcface.views.adapter.CommunityAdapter;
import com.example.yanglin.arcface.views.adapter.InfoListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yanglin on 18-4-2.
 */

public class CommunityActivity extends AppCompatActivity {
    @BindView(R.id.community_list)
    RecyclerView communityRecycleView;
    List<Community> communityList = new ArrayList<>();

    String titles[] = {"中国人脸门禁排名再次更新, 我们光荣上榜.", "中国人脸门禁排名再次更新, 我们光荣上榜.", "中国人脸门禁排名再次更新, 我们光荣上榜."};
    String contents[] = {"登录通知: 登录通知登录通知登录通知登录通知", "天空一号坠毁: 天空一号坠毁天空一号坠毁天空一号坠毁", ""};
    String times[] = {"12月20日", "12月20日", ""};
    String tags[] = {"正常通知", "新闻通知", ""};
    String categorys[] = {"通知", "通知", ""};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);
        ButterKnife.bind(this);

        communityList = DataUtil.getCommunity(titles, contents, times, tags, categorys);
        communityRecycleView.setLayoutManager(new LinearLayoutManager(this));
        CommunityAdapter communityAdapter = new CommunityAdapter(this, communityList);
        communityAdapter.setOnItemClickListener(new CommunityAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(CommunityActivity.this, CommunityTrendsDetailActivity.class));
                Toast.makeText(CommunityActivity.this,String.valueOf(position),Toast.LENGTH_SHORT).show();
            }
        });
        communityRecycleView.setAdapter(communityAdapter);
    }

    @OnClick(R.id.commmunity_back)
    void backMain() {
        CommunityActivity.this.finish();
    }
}
