package com.example.joe;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.common.utils.utils.DeviceUtils;
import com.common.utils.utils.SPUtils;
import com.example.joe.contants.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends Activity implements View.OnClickListener {


    private static final int MAIN_CODE = 1;//挑战main请求类型

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    private static final int MY_PERMISSIONS_REQUEST_FILE = 2;//文件请求
    private static final int MY_PERMISSIONS_READ_PHONE_STATE = 3;

    // 引导页图片资源
    private static final int[] pics = {R.layout.guid_view1,
            R.layout.guid_view2, R.layout.guid_view3, R.layout.guid_view4, R.layout.guid_view5};

    private List<View> views;
    private Button startBtn;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case MAIN_CODE:
                    startMainActivity();
                    break;
            }
            return false;
        }
    });
    private AlertDialog alertDialog;
    private String first;

    @BindView(R.id.linaerLayout_no_first)
    RelativeLayout noFirstLayout;
    @BindView(R.id.viewPager_first)
    ViewPager firstViewPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        initData();
    }


    public void initData() {
        // 判断是否是第一次安装该程序
        first = SPUtils.getInstance().getString(Constant.START_ONE, null);
        if (null != first && !TextUtils.isEmpty(first.trim())) {
            noFirstLayout.setVisibility(View.VISIBLE);
            firstViewPage.setVisibility(View.GONE);
        } else {
            noFirstLayout.setVisibility(View.GONE);
            firstViewPage.setVisibility(View.VISIBLE);
            views = new ArrayList<View>();

            // 初始化引导页视图列表
            for (int i = 0; i < pics.length; i++) {
                View view = LayoutInflater.from(this).inflate(pics[i], null);

                if (i == pics.length - 1) {
                    startBtn = (Button) view.findViewById(R.id.btn_enter);
                    startBtn.setTag("enter");
                    startBtn.setOnClickListener(this);
                }

                views.add(view);

            }
            firstViewPage.setAdapter(new GuideViewPagerAdapter(views));
        }
        if (DeviceUtils.getSDKVersion() >= 23) {
            checkPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, MY_PERMISSIONS_REQUEST_FILE);
        } else {
            if (null != first && !TextUtils.isEmpty(first.trim())) {
                handler.sendEmptyMessageDelayed(MAIN_CODE, 3000);//3秒后跳转
            }
        }
    }

    /**
     * 检查权限
     */
    public void checkPermission(String permission, int tag) {
        if (DeviceUtils.getSDKVersion() >= 23) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, permission)) {
                ActivityCompat.requestPermissions(this, new String[]{permission}, tag);
            } else if (tag == MY_PERMISSIONS_REQUEST_FILE) {
                checkPermission(android.Manifest.permission.READ_PHONE_STATE, MY_PERMISSIONS_READ_PHONE_STATE);
            } else if (tag == MY_PERMISSIONS_READ_PHONE_STATE) {
                checkPermission(android.Manifest.permission.ACCESS_FINE_LOCATION, MY_PERMISSIONS_REQUEST_LOCATION);
            } else if (tag == MY_PERMISSIONS_REQUEST_LOCATION) {
                //如果定位也请求通过
//                ((WangCaiApplication) StartActivity.this.getApplicationContext()).initLongLegSystem();
                if (null != first && !TextUtils.isEmpty(first.trim())) {
                    handler.sendEmptyMessageDelayed(MAIN_CODE, 3000);//3秒后跳转
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FILE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkPermission(android.Manifest.permission.READ_PHONE_STATE, MY_PERMISSIONS_READ_PHONE_STATE);
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    showPermissionsDialog("没有授予存储权限，无法正常使用，请打开应用信息开启权限。");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                break;
            case MY_PERMISSIONS_READ_PHONE_STATE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkPermission(android.Manifest.permission.ACCESS_FINE_LOCATION, MY_PERMISSIONS_REQUEST_LOCATION);
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    showPermissionsDialog("没有授予读取手机状态权限，无法正常使用，请打开应用信息开启权限。");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                break;
            case MY_PERMISSIONS_REQUEST_LOCATION:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
//                    ((WangCaiApplication) StartActivity.this.getApplicationContext()).initLongLegSystem();
                    if (null != first && !TextUtils.isEmpty(first.trim())) {
                        startMainActivity();
                    }
                } else {
                    showPermissionsDialog("没有授予定位权限，无法正常使用，请打开应用信息开启权限。");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                break;

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    /**
     * 启动主页
     */
    private void startMainActivity() {
        SPUtils.getInstance().put(Constant.START_ONE, "1");
        startActivity(new Intent(StartActivity.this, MyMainActivity.class));
        StartActivity.this.finish();
    }

    /**
     * 显示提示
     */
    public void showPermissionsDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("提示").setMessage(message).setNegativeButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StartActivity.this.finish();
            }
        }).setPositiveButton("打开", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", StartActivity.this.getPackageName(), null);
                intent.setData(uri);
                StartActivity.this.startActivity(intent);
            }
        });
        alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getTag().equals("enter")) {
            startMainActivity();
            return;
        }
    }

    private class GuideViewPagerAdapter extends PagerAdapter {
        private List<View> views;

        public GuideViewPagerAdapter(List<View> views) {
            super();
            this.views = views;
        }

        @Override
        public int getCount() {
            if (views != null) {
                return views.size();
            }
            return 0;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position), 0);
            return views.get(position);
        }

    }
}
