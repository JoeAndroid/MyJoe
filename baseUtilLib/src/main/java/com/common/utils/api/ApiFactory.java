package com.common.utils.api;

import com.common.utils.constant.HostType;

/**
 * Created by qiaobing on 2017/6/9.
 */
public class ApiFactory {

    protected static final Object monitor = new Object();

    private static ApiHomeService homeService = null;

    public static ApiHomeService getHomeService() {
        synchronized (monitor) {
            if (homeService == null) {
                homeService = HttpHelper.getRetrofit(HostType.NETEASE_NEWS_VIDEO).create(ApiHomeService.class);
            }
            return homeService;
        }
    }
}
