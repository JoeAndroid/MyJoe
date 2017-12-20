package com.example.joe.ui.module.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.joe.R;
import com.example.joe.ui.base.BaseFragment;
import com.example.joe.ui.base.BasePresenter;
import com.example.joe.ui.presenter.HomePresenter;
import com.example.joe.ui.view.HomeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiaobing on 2017/4/7.
 */

public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView, View.OnClickListener {
    @BindView(R.id.btnVideo)
    Button btnVideo;


    @Override
    public int bindRootView() {
        return R.layout.fragment_network;
    }

    @Override
    public void initBundle(Bundle bundle) {

    }

    @Override
    public void addListener() {
        btnVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("yanzhi://yanzhi.cn/open"));
        intent.setData(Uri.parse("yanzhi://yanzhi.cn/video?videoId=43376&albumId=0&topicId=0"));
        startActivity(intent);
    }

    @Override
    public void initData() {
        mPresenter.getHomeDataList(new ArrayMap<>());
    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this.getActivity());
    }

    @Override
    public void getDataListSuccess() {

    }

}
