package com.example.joe.ui.module.mine;

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
 * Created by qiaobing on 2017/5/19.
 */

public class OtherFragment extends BaseFragment<HomeView,HomePresenter> {

    @Override
    public int bindRootView() {
        return R.layout.fragment_other;
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
        return new HomePresenter(this.getContext());
    }

}
