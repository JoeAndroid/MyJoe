package com.example.joe;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.common.utils.utils.CrashUtils;
import com.common.utils.utils.LogUtils;
import com.common.utils.utils.SPUtils;
import com.common.utils.utils.Utils;
import com.example.joe.bean.LocationBean;
import com.example.joe.contants.Constant;

import org.greenrobot.eventbus.EventBus;
import org.litepal.LitePal;

/**
 * Created by qiaobing on 2016/6/1.
 */
public class MyApp extends Application implements BDLocationListener {

    //百度获取经纬度和位置信息
    public static LocationClient mLocationClient = null;
    public static String latitude;
    public static String lontitude;
    public static String address;
    public static String city;//城市
    private LocationBean locationBean;//定位bean
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
        // 内存泄露检查工具
       /* if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);*/
        Utils.init(this);
        LitePal.initialize(this);
        initLog();
        initCrash();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static void initLog() {
        LogUtils.Builder builder = new LogUtils.Builder()
                .setLogSwitch(BuildConfig.DEBUG)// 设置log总开关，包括输出到控制台和文件，默认开
                .setConsoleSwitch(BuildConfig.DEBUG)// 设置是否输出到控制台开关，默认开
                .setGlobalTag(null)// 设置log全局标签，默认为空
                // 当全局标签不为空时，我们输出的log全部为该tag，
                // 为空时，如果传入的tag为空那就显示类名，否则显示tag
                .setLogHeadSwitch(true)// 设置log头信息开关，默认为开
                .setLog2FileSwitch(false)// 打印log时是否存到文件的开关，默认关
                .setDir("")// 当自定义路径为空时，写入应用的/cache/log/目录中
                .setBorderSwitch(true)// 输出日志是否带边框开关，默认开
                .setConsoleFilter(LogUtils.V)// log的控制台过滤器，和logcat过滤器同理，默认Verbose
                .setFileFilter(LogUtils.V);// log文件过滤器，和logcat过滤器同理，默认Verbose
        LogUtils.d(builder.toString());
    }

    private void initCrash() {
        CrashUtils.init();
    }

    /**
     * 定位
     */
    public void initLocation() {
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 5 * 60 * 1000;//定位时间间隔
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
        //设置监听
        mLocationClient.registerLocationListener(this);
        mLocationClient.start();
    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        //Receive Location
        latitude = String.valueOf(location.getLatitude());
        lontitude = String.valueOf(location.getLongitude());
        if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
            address = location.getAddrStr();
            city = location.getCity();
            //发送并保存位置信息
            SPUtils.getInstance().put(Constant.LOCATION_CITY, city);
            locationBean = new LocationBean();
            locationBean.setAddr(address);
            locationBean.setLatitude(latitude);
            locationBean.setLongitude(lontitude);
            locationBean.setLocationCity(city);
            EventBus.getDefault().post(locationBean);
        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
            address = location.getAddrStr();
            city = location.getCity();
            //发送并保存位置信息
            SPUtils.getInstance().put(Constant.LOCATION_CITY, city);
            locationBean = new LocationBean();
            locationBean.setAddr(address);
            locationBean.setLatitude(latitude);
            locationBean.setLongitude(lontitude);
            locationBean.setLocationCity(city);
            EventBus.getDefault().post(locationBean);
        }
    }

}
