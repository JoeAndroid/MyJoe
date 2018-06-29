package com.example.joe.ui.module.custom.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.common.utils.base.BaseActivity;
import com.common.utils.base.BasePresenter;
import com.common.utils.view.refreshlayout.RefreshLayout;
import com.example.joe.R;
import com.example.joe.ui.module.custom.adapter.RefreshLayoutAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by qiaobing on 2016/6/14.
 */
public class RefreshLayoutActivity extends BaseActivity {

    private View refreshView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.listview)
    ListView listView;


    private RefreshLayoutAdapter refreshLayoutAdapter;

    private ArrayList<String> arrayList;

    @Override
    public void initBundle(Bundle bundle) {

    }

    @Override
    public int bindRootView() {
        return R.layout.activity_refreshlayout;
    }

    @Override
    public void initData() {
        refreshLayoutAdapter = new RefreshLayoutAdapter(this);
        refreshLayoutAdapter.addList(getData());
        listView.setAdapter(refreshLayoutAdapter);
    }

    @Override
    public void addLisenter() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private ArrayList<String> getData() {
        arrayList = new ArrayList<>();
        arrayList.add("RefreshLayout上拉刷新");
        arrayList.add("PullToRefreshLayout");
        arrayList.add("自定义上拉刷新listview");
        arrayList.add("自定义上拉刷新ViewGroup");
        arrayList.add("RefreshLayout上拉刷新");
        arrayList.add("PullToRefreshLayout");
        arrayList.add("自定义上拉刷新listview");
        arrayList.add("自定义上拉刷新ViewGroup");
        arrayList.add("RefreshLayout上拉刷新");
        arrayList.add("PullToRefreshLayout");
        arrayList.add("自定义上拉刷新listview");
        arrayList.add("自定义上拉刷新ViewGroup");
        arrayList.add("RefreshLayout上拉刷新");
        arrayList.add("PullToRefreshLayout");
        arrayList.add("自定义上拉刷新listview");
        arrayList.add("自定义上拉刷新ViewGroup");
        arrayList.add("RefreshLayout上拉刷新");
        arrayList.add("PullToRefreshLayout");
        arrayList.add("自定义上拉刷新listview");
        arrayList.add("自定义上拉刷新ViewGroup");
        arrayList.add("RefreshLayout上拉刷新");
        arrayList.add("PullToRefreshLayout");
        arrayList.add("自定义上拉刷新listview");
        arrayList.add("自定义上拉刷新ViewGroup");
        return arrayList;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
