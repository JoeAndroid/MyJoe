package com.common.utils.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.common.utils.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by qiaobing on 2016/5/6.
 */
public abstract class BaseActivity<V extends BaseView, T extends BasePresenter<V>> extends AppCompatActivity implements BaseView, View.OnClickListener {

    private static final int REQUESTCODE = 1;
    private PermissionCallBack mPermCallBack;
    private String mPermSettingMsg;


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
            ButterKnife.bind(this);
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

    @Override
    public void showProgressView() {

    }

    @Override
    public void cancleProgressView() {

    }

    @Override
    public void showError(String msg) {
        
    }

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

    /**
     * 权限请求结果回调
     */
    interface PermissionCallBack {
        /**
         * 允许权限
         */
        void permGrant(String[] permsGranted);

        /**
         * 拒绝权限
         */
        void permDeny(String[] permsDenied);
    }

    public void baseRequestPermission(String[] perms, PermissionCallBack callBack, String settingMsg) {
        mPermCallBack = callBack;
        mPermSettingMsg = settingMsg;
        if (!checkPerm(perms)) {
            ActivityCompat.requestPermissions(this, perms, REQUESTCODE);
        } else {
            if (callBack != null) {
                callBack.permGrant(perms);
            }
        }
    }

    /**
     * 权限检查
     */
    public boolean checkPerm(String[] perms) {
        for (String perm : perms) {
            if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        /**
         * 允许的权限
         */
        List<String> permsGranted = new ArrayList<>();
        /**
         * 拒绝的权限
         */
        List<String> permsDenied = new ArrayList<>();
        if (requestCode == REQUESTCODE) {
            /**
             * 添加允许权限和拒绝权限到相应的集合中
             */
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    permsGranted.add(permissions[i]);
                } else {
                    permsDenied.add(permissions[i]);
                }

            }
            /**
             *进行权限回调
             */
            if (permsGranted.size() != 0 && permsDenied.size() == 0) {
                if (mPermCallBack != null) {
                    mPermCallBack.permGrant((String[]) permsGranted.toArray(new String[permsGranted.size()]));
                }
            } else {
                if (mPermCallBack != null) {
                    mPermCallBack.permDeny((String[]) permsDenied.toArray(new String[permsDenied.size()]));
                }
                /**
                 * 点击不再提醒拒绝权限
                 * 提示是否进入设置界面设置权限
                 */
                for (String permDenied : permsDenied) {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permDenied)) {
                        gotoSetting(mPermSettingMsg);
                        return;
                    }
                }

            }
        }
    }

    /**
     * 进入应用设置界面设置权限
     *
     * @param msg
     */
    private void gotoSetting(String msg) {
        new AlertDialog.Builder(this)
                .setMessage(msg)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent localIntent = new Intent();
                        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                        localIntent.setData(Uri.fromParts("package", getPackageName(), null));
                        startActivity(localIntent);
                    }

                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
