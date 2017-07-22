package com.example.joe.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.example.joe.MyApp;
import com.example.joe.contants.Constant;

public class CommonUtils {

    /**
     * 检测网络
     *
     * @return true 网络可用 false otherwise
     */
    public static boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) Utils.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /**
     * 获取网络状态
     */
    public static synchronized int getNetWorkStatus(Context context) {
        ConnectivityManager connectManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // 获取网络信息
        NetworkInfo networkInfo = connectManager.getActiveNetworkInfo();
        // 判断网络状态
        if (networkInfo != null && networkInfo.isConnected()) {
            String type = networkInfo.getTypeName();

            if (type.equalsIgnoreCase("WIFI")) {
                return Constant.NETWORK_WIFI;
            } else if (type.equalsIgnoreCase("MOBILE")) {
                return Constant.NETWORK_GPRS;
            }
        } else {
            return Constant.NETWORK_NONE;// 断网状态
        }
        return Constant.NETWORK_NONE;// 断网状态

    }

    /**
     * 组织加密参数方法
     */
    public static String orgParams(ArrayMap<String, String> params) {
        String strData = null;
        try {
            // 平台
            params.put("platform", "android");
            //品牌
            params.put("deviceModel", DeviceUtils.getManufacturer());
            //型号
            params.put("deviceVersion", DeviceUtils.getModel() + "");
            // 判断网络类型
            if (Constant.NETWORK_GPRS == NetworkUtils.getNetWorkStatus()) {
                params.put("network", "2G/3G/4G");
            } else if (Constant.NETWORK_WIFI == NetworkUtils.getNetWorkStatus()) {
                params.put("network", "WIFI");
            }
            //运营商（中国联通、中国移动、中国电信）
            if (null != NetworkUtils.getSimOperatorInfo()) {
                params.put("isp", NetworkUtils.getSimOperatorInfo());
            }
            //定位信息
            if (null != MyApp.lontitude) {
                params.put("longitude", MyApp.lontitude);
            }
            if (null != MyApp.latitude) {
                params.put("latitude", MyApp.latitude);
            }
            if (null != MyApp.address) {
                params.put("location", MyApp.address);
            }
            // 版本号
            params.put("appversion", DeviceUtils.getSofftVersion());
            //手机唯一标示
            StringBuilder stringBuilder = new StringBuilder(DeviceUtils.getDeviceId());
            stringBuilder.insert(10, "w");
            params.put("uuid", stringBuilder.toString());
            //userId
            String userId = SPUtils.getInstance().getString(Constant.LOGIN_USER, null);
            if (null != userId && !TextUtils.isEmpty(userId) && !params.containsKey("userId")) {
                params.put("userId", userId);
            } else if (params.containsKey("userId") && TextUtils.isEmpty(params.get("userId"))) {
                params.remove("userId");
            }
            Gson gson = new GsonBuilder().serializeNulls().create();
            strData = AesUtil.encrypt(gson.toJson(params));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strData;
    }

}
