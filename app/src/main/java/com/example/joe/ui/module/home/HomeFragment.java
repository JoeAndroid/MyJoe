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
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by qiaobing on 2017/4/7.
 */

public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView, View.OnClickListener {
    @BindView(R.id.btnVideo)
    Button btnVideo;

    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard videoplayer;

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
        videoplayer.setUp("http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "饺子闭眼睛");
        videoplayer.thumbImageView.setImageURI(Uri.parse("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640"));
    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this.getActivity());
    }

    @Override
    public void getDataListSuccess() {

    }

}
