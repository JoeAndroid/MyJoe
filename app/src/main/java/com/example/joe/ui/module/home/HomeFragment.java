package com.example.joe.ui.module.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.joe.R;
import com.example.joe.ui.base.BaseFragment;
import com.example.joe.ui.base.BasePresenter;
import com.example.joe.ui.presenter.HomePresenter;
import com.example.joe.ui.view.HomeView;

import butterknife.ButterKnife;

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

    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this.getActivity());
    }

    @Override
    public void getDataListSuccess() {

    }
}
