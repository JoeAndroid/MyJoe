package com.common.utils.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.common.utils.BuildConfig;
import com.common.utils.arouter.RouterConfig;
import com.facebook.stetho.Stetho;

/**
 * {保存全局变量设计的基本类application}
 */

public class BaseApplication extends Application {

    public static boolean IS_DEBUG = BuildConfig.DEBUG;
    private static BaseApplication mBaseApplication;
    private String BUGLY_ID = "a29fb52485";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mBaseApplication = this;
        //MultiDex分包方法 必须最先初始化
        MultiDex.install(this);
    }

    public static BaseApplication getAppContext() {
        return mBaseApplication;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //arouter路由初始化
        RouterConfig.init(this, BuildConfig.DEBUG);
        //Stetho调试工具初始化
        Stetho.initializeWithDefaults(this);
    }


    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        exitApp();
    }

    /**
     * 退出应用
     */
    public void exitApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }


    /**
     * 低内存的时候执行
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

    // 程序在内存清理的时候执行

    /**
     * 程序在内存清理的时候执行
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

    }
}
