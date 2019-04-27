package com.example.yanglin.arcface.views.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yanglin.arcface.R;
import com.example.yanglin.arcface.utils.LoaddingDialog;
import com.example.yanglin.arcface.utils.data.DataUtil;
import com.example.yanglin.arcface.views.ApplyBugActivity;
import com.example.yanglin.arcface.views.MainActivity;
import com.example.yanglin.arcface.views.RoomRecordActivity;
import com.example.yanglin.arcface.views.VisitorActivity;
import com.example.yanglin.arcface.views.VisitorRegisterActivity;
import com.shizhefei.view.indicator.BannerComponent;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainFragment extends Fragment{

    @BindView(R.id.page_ad)
    protected ViewPager mVpager;
    @BindView(R.id.banner_indicator)
    Indicator indicator;
    BannerComponent bannerComponent;

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
        indicator.setScrollBar(new ColorBar(getContext(), Color.WHITE, 0, ScrollBar.Gravity.CENTENT_BACKGROUND));
        mVpager.setOffscreenPageLimit(2);
        bannerComponent = new BannerComponent(indicator, mVpager, false);
        bannerComponent.setAdapter(adapter);
        bannerComponent.setAutoPlayTime(2500);
        bannerComponent.startAutoPlay();
    }

    private int[] images = {R.mipmap.banner1,R.mipmap.banner2, R.mipmap.banner4};

    private IndicatorViewPager.IndicatorViewPagerAdapter adapter = new IndicatorViewPager.IndicatorViewPagerAdapter() {

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = new View(container.getContext());
            }
            return convertView;
        }

        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = new ImageView(getContext());
                convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
            ImageView imageView = (ImageView) convertView;
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(images[position]);
            return convertView;
        }


        @Override
        public int getCount() {
            return images.length;
        }
    };

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

