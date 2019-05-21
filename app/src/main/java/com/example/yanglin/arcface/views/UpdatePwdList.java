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
import com.example.yanglin.arcface.models.MyList;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;
import com.example.yanglin.arcface.views.adapter.UpdatePwdListAdapter;
import com.example.yanglin.arcface.views.dialog.CenterDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdatePwdList extends AppCompatActivity{
    private CenterDialog centerDialog;
    List<MyList> myLists = new ArrayList<>();
    String title[] = {"账号密码", "开锁密码"};
    String content[] = {"立即更改", "立即更改"};

    @BindView(R.id.change_pwd_list)
    RecyclerView pwdList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_pwd_list);
        ButterKnife.bind(this);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);

        for(int i=0; i<title.length; i++) {
            MyList list = new MyList(title[i], content[i]);
            myLists.add(list);
        }

        pwdList.setLayoutManager(new LinearLayoutManager(this));
        UpdatePwdListAdapter updatePwdListAdapter = new UpdatePwdListAdapter(this, myLists);
        updatePwdListAdapter.setOnItemClickListener(new UpdatePwdListAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                if(position == 0) {
                    startActivity(new Intent(UpdatePwdList.this, UpdatePwdActivity.class));
                } else if(position == 1) {
                    startActivity(new Intent(UpdatePwdList.this, UpdateSelfPwdActivity.class));
                }
            }
        });
        pwdList.setAdapter(updatePwdListAdapter);
    }

    @OnClick(R.id.pwd_back_main)
    void back() {
        UpdatePwdList.this.finish();
    }
}
