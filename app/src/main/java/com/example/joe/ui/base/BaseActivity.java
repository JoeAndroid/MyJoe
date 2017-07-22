package com.example.joe.ui.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.joe.utils.LogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by qiaobing on 2016/5/6.
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity implements View.OnClickListener {

    public Unbinder bind;

    protected T mPresenter;

    /**
     * 当前Activity根布局
     */
    protected View contentView;
    /**
     * 是否全屏
     */
    private boolean isFullScreen = false;
    /**
     * 是否沉浸状态栏
     */
    private boolean isSteepStatusBar = false;
    /**
     * 上次点击时间
     */
    private long lastClick = 0;

    protected BaseActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mActivity = this;
            Bundle bundle = getIntent().getExtras();
            initBundle(bundle);
            //允许为空，不是所有都要实现MVP模式
            if (createPresenter() != null) {
                mPresenter = createPresenter();
                mPresenter.attachView((V) this);
            }
            contentView = LayoutInflater.from(this).inflate(bindRootView(), null);
            if (isFullScreen) {
                this.getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
                requestWindowFeature(Window.FEATURE_NO_TITLE);
            }
            if (isSteepStatusBar) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                }
            }
            setContentView(contentView);
            bind = ButterKnife.bind(this);
            initData();
            addLisenter();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.d(e.getMessage());
        }
    }

    /**
     * 初始化数据
     *
     * @param bundle 从上个Activity传递过来的bundle
     */
    public abstract void initBundle(Bundle bundle);

    /**
     * 初始化控件
     */
    public abstract int bindRootView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 添加监听
     */
    public abstract void addLisenter();

    /**
     * 按钮点击事件监听
     */
    @Override
    public abstract void onClick(View v);

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

    protected abstract T createPresenter();

    /**
     * 设置是否全屏
     *
     * @param isFullScreen 是否全屏
     */
    public void setFullScreen(boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
    }

    /**
     * 设置是否沉浸状态栏
     *
     * @param isSteepStatusBar 是否沉浸状态栏
     */
    public void setSteepStatusBar(boolean isSteepStatusBar) {
        this.isSteepStatusBar = isSteepStatusBar;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != bind) {
            bind.unbind();
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
