package com.example.joe.api;

/**
 * Created by qiaobing on 2017/6/9.
 */
public class ApiFactory {

    protected static final Object monitor=new Object();

    private static ApiHomeService homeService=null;

    public static ApiHomeService getHomeService(){
        synchronized (monitor){
            if (homeService==null){
                homeService=new ApiRetrofit().getHomeService();
            }
            return homeService;
        }
    }

}
