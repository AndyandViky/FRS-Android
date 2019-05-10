package com.example.yanglin.arcface.views.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.controllers.NoticeCtrl;
import com.example.yanglin.arcface.models.Base2Response;
import com.example.yanglin.arcface.models.User;
import com.example.yanglin.arcface.utils.Cache;
import com.example.yanglin.arcface.utils.OkhttpService;
import com.example.yanglin.arcface.views.AboutActivity;
import com.example.yanglin.arcface.views.ChangeUserInfoActivity;
import com.example.yanglin.arcface.views.CommunityActivity;
import com.example.yanglin.arcface.views.FaceImageActivity;
import com.example.yanglin.arcface.views.InfoActivity;
import com.example.yanglin.arcface.views.LoginActivity;
import com.example.yanglin.arcface.views.UpdatePwdActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;

/**
 * Created by yanglin on 18-3-18.
 */

public class MyFragment extends Fragment{

    View rootView;
    OkHttpClient okHttpClient = new OkHttpClient();
    NoticeCtrl noticeCtrl = new NoticeCtrl();
    @BindView(R.id.info_unread_count)
    TextView unreadCount;

    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_num)
    TextView userNum;
    Cache cache = new Cache();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_my,container,false);
        ButterKnife.bind(this, rootView);

        User user = cache.getUser();
        userName.setText(user.getData().getUser().getName());
        userNum.setText(user.getData().getUser().getPhone());
        noticeCtrl.getUnReadCount(okHttpClient, new OkhttpService.OnResponseListener() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                java.lang.reflect.Type type = new TypeToken<Base2Response>() {}.getType();
                Base2Response base2Response = gson.fromJson(result, type);
                String count = base2Response.getData();
                unreadCount.setText(count);
            }

            @Override
            public void onFailure(IOException error) {

            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
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

    @OnClick(R.id.logout)
    void logout() {
        cache.deleteUser();
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }
}
