package com.common.utils.api;


import android.util.SparseArray;

import com.common.utils.BuildConfig;
import com.common.utils.constant.BaseHost;
import com.common.utils.constant.HostType;
import com.common.utils.utils.CommonUtils;
import com.common.utils.utils.Utils;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 首页 Rtrofit+OKhttp Created by qiaobing on 2017/5/31.
 */
public class HttpHelper {

    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 7676;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 7676;

    private static Gson gson;

    private static Retrofit retrofit;
    public OkHttpClient okHttpClient;

    private static SparseArray<HttpHelper> sRetrofitManager = new SparseArray<>(HostType.TYPE_COUNT);

    public HttpHelper(int hostType) {
        //缓存路径
        File httpCacheDirectory = new File(Utils.getContext().getCacheDir(), "cache");
        int cacheSize = 50 * 1024 * 1024;//10MB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = chain -> {

            CacheControl.Builder cacheBuilder = new CacheControl.Builder();
            cacheBuilder.maxAge(0, TimeUnit.SECONDS);
            cacheBuilder.maxStale(365, TimeUnit.DAYS);
            CacheControl cacheControl = cacheBuilder.build();

            Request request = chain.request();
            if (!CommonUtils.isNetworkAvailable()) {
                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build();

            }
            //有网时不缓存，没有网络时缓存四周时间
            Response originalResponse = chain.proceed(request);
            if (CommonUtils.isNetworkAvailable()) {
                int maxAge = 0; // // 有网络时 设置缓存超时时间0个小时
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // // 无网络时，设置超时为4周
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        };


        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .addNetworkInterceptor(new StethoInterceptor())
                .cache(cache);
        /**
         *可以通过 setLevel 改变日志级别
         共包含四个级别：NONE、BASIC、HEADER、BODY
         (日志拦截器)
         */
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }

        okHttpClient = builder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BaseHost.getHost(hostType))
                .addConverterFactory(GsonConverterFactory.create(buildGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

    }

    public static Retrofit getRetrofit(int hostType) {
        HttpHelper retrofitManager = sRetrofitManager.get(hostType);
        if (retrofitManager == null) {
            retrofitManager = new HttpHelper(hostType);
            sRetrofitManager.put(hostType, retrofitManager);
        }
        return retrofit;
    }

    /**
     * 增加后台返回""和"null"的处理 1.int=>0 2.double=>0.00 3.long=>0L
     */
    public static Gson buildGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                    .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                    .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                    .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                    .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                    .registerTypeAdapter(long.class, new LongDefault0Adapter())
                    .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory())
                    .create();
        }
        return gson;
    }
}
