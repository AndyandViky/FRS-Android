package com.example.yanglin.arcface.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.models.Record;
import com.example.yanglin.arcface.utils.data.DataUtil;
import com.example.yanglin.arcface.utils.systemBar.SystemBarUI;
import com.example.yanglin.arcface.views.adapter.RecordListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yanglin on 18-3-21.
 */

public class RoomRecordActivity extends AppCompatActivity {
    @BindView(R.id.record_list)
    RecyclerView recordRecyclerView;
    int types[] = {1, 2, 1, 2, 2, 1, 1};
    String times[] = {"2018-10-10 12:20:10", "2018-10-10 12:20:10", "2018-10-10 12:20:10", "2018-10-10 12:20:10", "2018-10-10 12:20:10", "2018-10-10 12:20:10", "2018-10-10 12:20:10"};
    int peopleCounts[] = {3, 2, 1, 3, 2, 1, 5};
    List<Record> recordList = new ArrayList<>();
    int currentPosition;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_record);
        ButterKnife.bind(this);
        SystemBarUI.initSystemBar(this, R.color.actionTitle);

        recordList = DataUtil.getRecord(types, peopleCounts, times);
        recordRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecordListAdapter recordListAdapter = new RecordListAdapter(this, recordList);
        recordListAdapter.setOnItemClickListener(new RecordListAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RoomRecordActivity.this,String.valueOf(position),Toast.LENGTH_SHORT).show();
            }
        });
        recordRecyclerView.setAdapter(recordListAdapter);
    }

    @OnClick(R.id.record_more_button)
    void getMoreRecord() {

    }

    @OnClick(R.id.record_back)
    void recordBack() {
        RoomRecordActivity.this.finish();
    }
}