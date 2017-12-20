package com.example.joe.ui.module.custom.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.joe.R;
import com.example.joe.ui.module.custom.adapter.UltraPagerAdapter;
import com.example.joe.ui.base.BaseActivity;
import com.example.joe.ui.base.BasePresenter;
import com.example.joe.widget.refreshlayout.OnPullListener;
import com.example.joe.widget.refreshlayout.RefreshLayout;
import com.example.joe.widget.ultraviewpager.UltraViewPager;
import com.example.joe.widget.ultraviewpager.transformer.CustPagerTransformer;
import com.example.joe.widget.ultraviewpager.transformer.UltraDepthScaleTransformer;
import com.example.joe.widget.ultraviewpager.transformer.UltraScaleTransformer;

import butterknife.BindView;

/**
 * 垂直滑动的ViewPager下拉刷新
 */
public class RefreshVerticalViewPagerActivity extends BaseActivity {
    @BindView(R.id.myRefreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.ultra_viewpager)
    UltraViewPager ultraViewPager;

    private PagerAdapter adapter;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            refreshLayout.stopRefresh(true);
        }
    };

    @Override
    public void initBundle(Bundle bundle) {

    }

    @Override
    public int bindRootView() {
        return R.layout.activity_refresh_vertical_view_pager;
    }

    @Override
    public void initData() {
        ultraViewPager.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        ultraViewPager.setMultiScreen(0.5f);
        ultraViewPager.setItemRatio(1.0f);
        ultraViewPager.setOffscreenPageLimit(5);
        ultraViewPager.setPageTransformer(false, new CustPagerTransformer(ultraViewPager.getViewPager()));
        ultraViewPager.setAutoMeasureHeight(true);
        adapter = new UltraPagerAdapter(false);
        ultraViewPager.setAdapter(adapter);
        ultraViewPager.setCurrentItem(2);
    }

    @Override
    public void addLisenter() {
        refreshLayout.setOnPullListener(new OnPullListener() {
            @Override
            public void onRefresh() {
                handler.sendEmptyMessageDelayed(0, 2000);
            }

            @Override
            public void onLoadMore() {
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
