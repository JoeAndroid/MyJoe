package com.joe.wanandroid;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.common.utils.base.BaseApplication;

public class MyApp extends BaseApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
