package com.example.joe.ui.module.mine;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;

import com.common.utils.base.BaseFragment;
import com.common.utils.view.refreshlistview.RefreshListView;
import com.example.joe.R;
import com.example.joe.ui.presenter.HomePresenter;
import com.example.joe.ui.view.HomeView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by qiaobing on 2017/5/19.
 */

public class OtherFragment extends BaseFragment<HomeView, HomePresenter> {
    private ArrayList<String> list;
    @BindView(R.id.listview)
    RefreshListView listview;

    private Handler mHandler;

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
        mHandler = new Handler();
        //构造数据源
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("数据源" + i);
        }
//        listview.setEnablePullLoad(true);
        listview.setAdapter(new ArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_1, list));
        listview.setListViewListenre(new RefreshListView.RefreshListViewListenre() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listview.refreshComplete();
                    }
                }, 5000);
            }

            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listview.loadMoreComplete();
                    }
                }, 5000);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        listview.doPullRefreshing(100);
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this.getContext());
    }

}
