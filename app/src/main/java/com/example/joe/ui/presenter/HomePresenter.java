package com.example.joe.ui.presenter;


import android.content.Context;

import com.common.utils.base.BasePresenter;
import com.example.joe.bean.HomeBean;
import com.example.joe.ui.model.HomeVideoModel;
import com.example.joe.ui.view.HomeView;

import org.jetbrains.annotations.Nullable;

/**
 * 首页
 * Created by qiaobing on 2017/6/9.
 */
public class HomePresenter extends BasePresenter<HomeView> implements HomeVideoModel.RequestHomeCallBackListener {

    private Context context;
    private HomeView homeView;

    private HomeVideoModel homeModel;

    public HomePresenter(Context context) {
        this.context = context;
        homeModel = new HomeVideoModel(this);
    }

    public void getHomeDataList(int num) {
        homeView = getView();
        homeView.showProgressView();
        homeModel.getHomeVideoData(num);
    }


    @Override
    public void getHomeDataSuccess(@Nullable HomeBean value) {
        homeView.getDataListSuccess(value);
    }
}
