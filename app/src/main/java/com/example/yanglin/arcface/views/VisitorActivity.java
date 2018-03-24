package com.example.yanglin.arcface.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.models.Visitor;
import com.example.yanglin.arcface.utils.data.DataUtil;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;
import com.example.yanglin.arcface.views.adapter.RecordListAdapter;
import com.example.yanglin.arcface.views.adapter.VisitorAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.visitor_list)
    RecyclerView visitorListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor);
        ButterKnife.bind(this);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);

        visitorList = DataUtil.getVisitor();
        visitorListView.setLayoutManager(new LinearLayoutManager(this));
        VisitorAdapter visitorAdapter = new VisitorAdapter(this, visitorList);
        visitorAdapter.setOnItemClickListener(new VisitorAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
               // Toast.makeText(VisitorActivity.this,String.valueOf(position),Toast.LENGTH_SHORT).show();
            }
        });
        visitorListView.setAdapter(visitorAdapter);

    }

    @OnClick(R.id.visitor_list_back)
    void backMain() {
        VisitorActivity.this.finish();
    }

    @OnClick(R.id.visitor_all)
    void clickAll() {
        clearStyle();
        textAll.setTextColor(borderColor);
        borderAll.setBackgroundColor(borderColor);
    }
    @OnClick(R.id.visitor_wait_pass)
    void clickWaitPass() {
        clearStyle();
        textWait.setTextColor(borderColor);
        borderWait.setBackgroundColor(borderColor);
    }
    @OnClick(R.id.visitor_passed)
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
