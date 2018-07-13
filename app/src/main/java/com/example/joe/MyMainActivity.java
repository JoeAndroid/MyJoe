package com.example.joe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.common.utils.base.BaseActivity;
import com.common.utils.base.BasePresenter;
import com.example.joe.ui.module.custom.CustomViewFragment;
import com.example.joe.ui.module.home.HomeFragment;
import com.example.joe.ui.module.mine.OtherFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jzvd.JZVideoPlayer;

public class MyMainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    static {
        System.loadLibrary("native-lib");
    }

    @BindView(R.id.fragment_frame)
    FrameLayout fragment_frame;

    @BindView(R.id.bottom_navigation)
    BottomNavigationBar bottom_navigation;

    int lastSelectedPosition = 0;

    private CustomViewFragment customViewFragmetn;

    private HomeFragment homeFragment;

    private OtherFragment otherFragment;

    private List<Fragment> fragmentList = new ArrayList<>();

    private int a = 0;

    @Override
    public void initBundle(Bundle bundle) {

    }

    @Override
    public int bindRootView() {
        return R.layout.activity_my_main;
    }

    public static native int sum(int a, int b);


    @Override
    public void initData() {
        BadgeItem numberBadge = new BadgeItem()
                .setBorderWidth(4)
                .setBackgroundColor(Color.RED)
                .setText("5")
                .setHideOnSelect(true);
        bottom_navigation.setMode(BottomNavigationBar.MODE_FIXED);
        bottom_navigation.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottom_navigation.addItem(new BottomNavigationItem(R.mipmap.icon_home_uncheck, "自定义").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.leitai_uncheck, "网络").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.my_uncheck, "其他").setActiveColorResource(R.color.colorPrimary).setBadgeItem(numberBadge))
                .initialise();
        /**
         *首次进入不会主动回调选中页面的监听
         *所以这里自己调用一遍，初始化第一个页面
         */
        onTabSelected(0);
        bottom_navigation.setTabSelectedListener(this);
    }

    @Override
    public void addLisenter() {
        bottom_navigation.setTabSelectedListener(this);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onTabSelected(int position) {
        lastSelectedPosition = position;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        /**
         *每次添加之前隐藏所有正在显示的Fragment
         *然后如果是第一次添加的话使用transaction.add();
         *但第二次显示的时候,使用transaction.show();
         *这样子我们就可以保存Fragment的状态了
         */
        hidenFragment(transaction);
        switch (position) {
            case 0:
                if (customViewFragmetn == null) {
                    customViewFragmetn = new CustomViewFragment();
                    transaction.add(R.id.fragment_frame, customViewFragmetn);
                    fragmentList.add(customViewFragmetn);
                } else {
                    transaction.show(customViewFragmetn);
                }
                Toast.makeText(this, sum(a++, 0) + "", 0).show();
                break;
            case 1:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.fragment_frame, homeFragment);
                    fragmentList.add(homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 2:
                if (otherFragment == null) {
                    otherFragment = new OtherFragment();
                    transaction.add(R.id.fragment_frame, otherFragment);
                    fragmentList.add(otherFragment);
                } else {
                    transaction.show(otherFragment);
                }
                break;
        }
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    private void hidenFragment(FragmentTransaction transaction) {
        for (Fragment fragment : fragmentList) {
            transaction.hide(fragment);
        }
    }
}
