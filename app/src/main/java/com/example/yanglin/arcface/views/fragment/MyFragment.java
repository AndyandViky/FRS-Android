package com.example.yanglin.arcface.views.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.views.AboutActivity;
import com.example.yanglin.arcface.views.ChangeUserInfoActivity;
import com.example.yanglin.arcface.views.CommunityActivity;
import com.example.yanglin.arcface.views.FaceImageActivity;
import com.example.yanglin.arcface.views.InfoActivity;
import com.example.yanglin.arcface.views.UpdatePwdActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yanglin on 18-3-18.
 */

public class MyFragment extends Fragment{

    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_my,container,false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick(R.id.get_about_button)
    void getAboutUs() {
        Intent intent = new Intent(getActivity(), AboutActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.change_info_button)
    void getChangeInfo() {
        Intent intent = new Intent(getActivity(), ChangeUserInfoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.change_pwd_button)
    void getChangePwd() {
        Intent intent = new Intent(getActivity(), UpdatePwdActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.update_user_face)
    void getFaceImage() {
        Intent intent = new Intent(getActivity(), FaceImageActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.info_center)
    void getInfo() {
        Intent intent = new Intent(getActivity(), InfoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.comunity_info)
    void getCommunity() {
        Intent intent = new Intent(getActivity(), CommunityActivity.class);
        startActivity(intent);
    }
}
