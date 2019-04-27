package com.example.yanglin.arcface.views;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.controllers.UserCtrl;
import com.example.yanglin.arcface.models.Record;
import com.example.yanglin.arcface.utils.LoaddingDialog;
import com.example.yanglin.arcface.utils.OkhttpService;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;
import com.example.yanglin.arcface.views.adapter.RecordListAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;

/**
 * Created by yanglin on 18-3-21.
 */

public class RoomRecordActivity extends AppCompatActivity {
    @BindView(R.id.record_list)
    RecyclerView recordRecyclerView;
    int currentPosition;
    Record record;
    RecordListAdapter recordListAdapter;
    OkHttpClient okHttpClient = new OkHttpClient();
    private Dialog Loadding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_record);
        ButterKnife.bind(this);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);

        recordRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        recordListAdapter = new RecordListAdapter(RoomRecordActivity.this, new ArrayList<Record.DataBean.DatasBean>());
        recordRecyclerView.setAdapter(recordListAdapter);
        UserCtrl userCtrl = new UserCtrl();
        Loadding = LoaddingDialog.createLoadingDialog(RoomRecordActivity.this, "加载中...");
        userCtrl.getGateRecord(okHttpClient, 1, 10, new OkhttpService.OnResponseListener() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                java.lang.reflect.Type type = new TypeToken<Record>() {}.getType();
                record = gson.fromJson(result, type);
                recordRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        recordListAdapter.replace(record.getData().getDatas());
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

    @OnClick(R.id.record_more_button)
    void getMoreRecord() {

    }

    @OnClick(R.id.record_back)
    void recordBack() {
        RoomRecordActivity.this.finish();
    }
}
