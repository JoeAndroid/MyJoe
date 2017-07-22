package com.example.joe.utils;

import android.os.Environment;

/**
 * Created by qiaobing on 2017/6/8.
 */

public class FileUtil {

    /**
     * 获取手机存储路径
     */
    public static String getFilePath() {
        String path = null;
        // 判断外部设备是否存在
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            // 存在获取外部文件路径
            path = Environment.getExternalStorageDirectory().getPath();
        } else {
            // 不存在获取内部存储
            path = Environment.getDataDirectory().getPath();
        }
        return path;
    }
}
