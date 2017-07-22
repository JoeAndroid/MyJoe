package com.example.joe.contants;

import com.example.joe.utils.FileUtil;
import com.example.joe.utils.Utils;

import java.util.UUID;

public class Constant {

    /**
     * 第一次启动
     */
    public static final String START_ONE = "start_one";
    /**
     * 标记网络为wifi状态
     */
    public static final int NETWORK_WIFI = 1;

    /**
     * 标记网络为Gprs状态
     */
    public static final int NETWORK_GPRS = 2;

    /**
     * 标记无网状态
     */
    public static final int NETWORK_NONE = 0;

    /**
     * 定位的城市
     */
    public static final String LOCATION_CITY = "city";

    /**
     * 设备的唯一标识信息
     */
    public final static String DEVICE_ID = "device_id";

    public static final String EX_NETWORK_NONE = "无网络连接!";

    public static final String EX_REQUEST_FAILURE = "请求失败,请稍后再试!";

    public static final String EX_NETWORK_TIME_OUT = "请求超时,请稍后再试!";

    public static final String EX_NETWORK_ERR = "网络错误,请稍后再试!";

    public static final String EX_FILE_BIG_ERR = "文件过大,请处理后重新上传!";

    public static final String EX_SERVER_ERR = "服务器异常,请稍后再试!";

    public static final String EX_REQUEST_CANCLE = "请求取消!";

    public static final String EX_NO_DATA = "暂无数据!";
    /**
     * 定义缓存路径
     */
    public static final String cachePath = FileUtil.getFilePath() + "/com.example.joe/";

    /**
     * 图片缓存路径
     */
    public static final String PicCachePath = cachePath + "pic/";

    /**
     * 登录用户
     */
    public static final String LOGIN_USER = "LOGIN_USER";
}

