package com.example.yanglin.arcface.views;

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
import com.example.yanglin.arcface.models.Info;
import com.example.yanglin.arcface.models.Record;
import com.example.yanglin.arcface.utils.data.DataUtil;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;
import com.example.yanglin.arcface.views.adapter.InfoListAdapter;
import com.example.yanglin.arcface.views.adapter.RecordListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    List<Info> infoList = new ArrayList<>();

    String titles[] = {"登录通知", "天空一号坠毁"};
    String contents[] = {"登录通知: 登录通知登录通知登录通知登录通知", "天空一号坠毁: 天空一号坠毁天空一号坠毁天空一号坠毁"};
    String times[] = {"12月20日", "12月20日"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);
        ButterKnife.bind(this);

        infoList = DataUtil.getInfo(titles, contents, times);
        infoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        InfoListAdapter infoListAdapter = new InfoListAdapter(this, infoList);
        infoListAdapter.setOnItemClickListener(new InfoListAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(InfoActivity.this, InfoDetailActivity.class));
                Toast.makeText(InfoActivity.this,String.valueOf(position),Toast.LENGTH_SHORT).show();
            }
        });
        infoRecyclerView.setAdapter(infoListAdapter);
    }

    @OnClick(R.id.info_back)
    void backMain() {
        InfoActivity.this.finish();
    }

    @OnClick(R.id.info_all)
    void clickAll() {
        clearStyle();
        textAll.setTextColor(borderColor);
        borderAll.setBackgroundColor(borderColor);
    }
    @OnClick(R.id.info_wait_read)
    void clickWaitPass() {
        clearStyle();
        textWait.setTextColor(borderColor);
        borderWait.setBackgroundColor(borderColor);
    }
    @OnClick(R.id.info_read)
    void clickPassed() {
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
