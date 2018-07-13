package com.example.joe.ui.module.home;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;

import com.common.utils.base.BaseFragment;
import com.common.utils.utils.LogUtils;
import com.example.joe.R;
import com.example.joe.bean.HomeBean;
import com.example.joe.ui.presenter.HomePresenter;
import com.example.joe.ui.view.HomeView;

/**
 * Created by qiaobing on 2017/4/7.
 */

public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView {

    @Override
    public int bindRootView() {
        return R.layout.fragment_network;
    }

    @Override
    public void initBundle(Bundle bundle) {

    }

    @Override
    public void addListener() {
    }

    @Override
    public void initData() {
        mPresenter.getHomeDataList(1);
    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this.getActivity());
    }

    @Override
    public void getDataListSuccess(HomeBean value) {
        LogUtils.i("DataSucces-----", value.toString());
    }
}
