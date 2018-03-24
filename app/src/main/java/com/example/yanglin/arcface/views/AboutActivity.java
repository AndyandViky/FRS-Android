package com.example.yanglin.arcface.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.models.MyList;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;
import com.example.yanglin.arcface.views.adapter.AboutListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yanglin on 18-3-20.
 */

public class AboutActivity extends AppCompatActivity{

    List<MyList> myLists = new ArrayList<>();
    String title[] = {"版本号", "服务协议", "软件说明"};
    String content[] = {"v1.0", "...", "..."};
    @BindView(R.id.about_list)
    RecyclerView aboutList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);

        for(int i=0; i<title.length; i++) {
            MyList list = new MyList(title[i], content[i]);
            myLists.add(list);
        }

        aboutList.setLayoutManager(new LinearLayoutManager(this));
        AboutListAdapter aboutListAdapter = new AboutListAdapter(this, myLists);
        aboutListAdapter.setOnItemClickListener(new AboutListAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(AboutActivity.this,String.valueOf(position),Toast.LENGTH_SHORT).show();
            }
        });
        aboutList.setAdapter(aboutListAdapter);
    }

    @OnClick(R.id.about_back_main)
    void backMain() {
        AboutActivity.this.finish();
    }
}
