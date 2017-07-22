package com.example.joe.ui.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by shenxiaolei on 16/11/21.
 */

public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment implements DialogInterface.OnDismissListener {

    private static final String TAG = "BaseFragment";

    protected T mPresenter;
    /**
     * 当前Activity渲染的视图View
     */
    protected View contentView;
    /**
     * 上次点击时间
     */
    private long lastClick = 0;

    protected BaseActivity mActivity;

    public int page = 1;
    //是否隐藏
    private boolean isHide;
    //是否暂停
    private boolean isPause;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(bindRootView(), null);
        ButterKnife.bind(this, contentView);
        return contentView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        initBundle(bundle);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
        initData();
        addListener();
    }

    /**
     * 初始化控件
     */
    public abstract int bindRootView();

    /**
     * 初始化数据
     *
     * @param bundle 从上个Activity传递过来的bundle
     */
    public abstract void initBundle(Bundle bundle);

    /**
     * 添加监听
     */
    public abstract void addListener();

    /**
     * 业务操作
     */
    public abstract void initData();

    protected abstract T createPresenter();

    /**
     * 判断是否快速点击
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    private boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 200) {
            lastClick = now;
            return false;
        }
        return true;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isHide = hidden;
    }

    @Override
    public void onPause() {
        super.onPause();
        isPause = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        isPause = false;
    }


    @Override
    public void onDismiss(DialogInterface dialog) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
        Log.d(TAG, "onDestroy: ");
    }

}
