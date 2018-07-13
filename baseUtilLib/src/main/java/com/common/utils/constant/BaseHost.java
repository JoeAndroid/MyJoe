package com.common.utils.constant;

/**
 * @Created by TOME .
 * @时间 2018/5/15 17:35
 * @描述 ${TODO}
 */

public class BaseHost {

    public static final String NETEAST_HOST = "http://c.m.163.com/";

    //干货集中营 API
    public static final String SINA_PHOTO_HOST = "http://gank.io/api/";

    private static final String BASE_URL = "http://123.57.0.93:8080/longleg/v1/service/";

    private static final String EYE_URL = "http://baobab.kaiyanapp.com/api/";

    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String getHost(int hostType) {
        String host;
        switch (hostType) {
            case HostType.NETEASE_NEWS_VIDEO:
                // host = NETEAST_HOST; String BASE_URL = "http://www.wanandroid.com/";
//                host = "http://www.wanandroid.com/" ;
                host = "http://baobab.kaiyanapp.com/api/";
                break;
            case HostType.GANK_GIRL_PHOTO:
                host = SINA_PHOTO_HOST;
                break;
            case HostType.NEWS_DETAIL_HTML_PHOTO:
                host = "http://kaku.com/";
                break;
            default:
                host = "";
                break;
        }
        return host;
    }
}
