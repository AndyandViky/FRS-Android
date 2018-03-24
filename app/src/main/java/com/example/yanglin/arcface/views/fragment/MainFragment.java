package com.example.yanglin.arcface.views.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.utils.data.DataUtil;
import com.example.yanglin.arcface.views.ApplyBugActivity;
import com.example.yanglin.arcface.views.ChangeUserInfoActivity;
import com.example.yanglin.arcface.views.MainActivity;
import com.example.yanglin.arcface.views.RoomRecordActivity;
import com.example.yanglin.arcface.views.VisitorActivity;
import com.example.yanglin.arcface.views.VisitorRegisterActivity;
import com.example.yanglin.arcface.views.adapter.MainHeaderAdAdpater;
import com.example.yanglin.arcface.views.dialog.BottomDialog;
import com.example.yanglin.arcface.views.dialog.CenterDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainFragment extends Fragment{

    //头部广告
    protected int [] icons = {R.mipmap.banner1,R.mipmap.banner2,R.mipmap.banner3, R.mipmap.banner4};

    @BindView(R.id.page_ad)
    protected ViewPager mVpager;

    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main,container,false);
        ButterKnife.bind(this,rootView);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //头部广告适配器
        MainHeaderAdAdpater adpater = new MainHeaderAdAdpater(getActivity(), DataUtil.getHeaderAdInfo(getActivity(),icons));
        mVpager.setAdapter(adpater);
    }
    @OnClick(R.id.features_four)
    void bugApply() {
        startActivity(new Intent(getActivity(), ApplyBugActivity.class));
    }

    @OnClick(R.id.features_one)
    void roomRecord() {
        startActivity(new Intent(getActivity(), RoomRecordActivity.class));
    }

    @OnClick(R.id.features_two)
    void visitorList() {
        startActivity(new Intent(getActivity(), VisitorActivity.class));
    }

    @OnClick(R.id.features_three)
    void visitorManage() {
        startActivity(new Intent(getActivity(), VisitorRegisterActivity.class));
    }
}

