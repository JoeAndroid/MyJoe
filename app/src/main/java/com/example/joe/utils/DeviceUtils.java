package com.example.joe.utils;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.example.joe.contants.Constant;

import java.io.File;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/8/1
 *     desc  : 设备相关工具类
 * </pre>
 */
public final class DeviceUtils {

    private DeviceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断设备是否root
     *
     * @return the boolean{@code true}: 是<br>{@code false}: 否
     */
    public static boolean isDeviceRooted() {
        String su = "su";
        String[] locations = {"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/", "/system/bin/failsafe/",
                "/data/local/xbin/", "/data/local/bin/", "/data/local/"};
        for (String location : locations) {
            if (new File(location + su).exists()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取设备系统版本号
     *
     * @return 设备系统版本号
     */
    public static int getSDKVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 获取本地程序的版本号
     */
    public static String getSofftVersion() {
        PackageManager packageManager = Utils.getContext().getPackageManager();
        PackageInfo packageInfo;
        String version = "1.0.0";
        try {
            packageInfo = packageManager.getPackageInfo(Utils.getContext().getPackageName(), 0);
            version = packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 获取设备AndroidID
     *
     * @return AndroidID
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidID() {
        return Settings.Secure.getString(Utils.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * 获取设备MAC地址
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>}</p>
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.INTERNET"/>}</p>
     *
     * @return MAC地址
     */
    public static String getMacAddress() {
        String macAddress = getMacAddressByWifiInfo();
        if (!"02:00:00:00:00:00".equals(macAddress)) {
            return macAddress;
        }
        macAddress = getMacAddressByNetworkInterface();
        if (!"02:00:00:00:00:00".equals(macAddress)) {
            return macAddress;
        }
        macAddress = getMacAddressByFile();
        if (!"02:00:00:00:00:00".equals(macAddress)) {
            return macAddress;
        }
        return "please open wifi";
    }

    /**
     * 获取设备MAC地址
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>}</p>
     *
     * @return MAC地址
     */
    @SuppressLint("HardwareIds")
    private static String getMacAddressByWifiInfo() {
        try {
            @SuppressLint("WifiManagerLeak")
            WifiManager wifi = (WifiManager) Utils.getContext().getSystemService(Context.WIFI_SERVICE);
            if (wifi != null) {
                WifiInfo info = wifi.getConnectionInfo();
                if (info != null) return info.getMacAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    /**
     * 获取设备MAC地址
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.INTERNET"/>}</p>
     *
     * @return MAC地址
     */
    private static String getMacAddressByNetworkInterface() {
        try {
            List<NetworkInterface> nis = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface ni : nis) {
                if (!ni.getName().equalsIgnoreCase("wlan0")) continue;
                byte[] macBytes = ni.getHardwareAddress();
                if (macBytes != null && macBytes.length > 0) {
                    StringBuilder res1 = new StringBuilder();
                    for (byte b : macBytes) {
                        res1.append(String.format("%02x:", b));
                    }
                    return res1.deleteCharAt(res1.length() - 1).toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    /**
     * 获取设备MAC地址
     *
     * @return MAC地址
     */
    private static String getMacAddressByFile() {
        ShellUtils.CommandResult result = ShellUtils.execCmd("getprop wifi.interface", false);
        if (result.result == 0) {
            String name = result.successMsg;
            if (name != null) {
                result = ShellUtils.execCmd("cat /sys/class/net/" + name + "/address", false);
                if (result.result == 0) {
                    if (result.successMsg != null) {
                        return result.successMsg;
                    }
                }
            }
        }
        return "02:00:00:00:00:00";
    }

    /**
     * 获取设备厂商
     * <p>如Xiaomi</p>
     *
     * @return 设备厂商
     */

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取设备型号
     * <p>如MI2SC</p>
     *
     * @return 设备型号
     */
    public static String getModel() {
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim().replaceAll("\\s*", "");
        } else {
            model = "";
        }
        return model;
    }

    /**
     * 关机
     * <p>需要root权限或者系统权限 {@code <android:sharedUserId="android.uid.system"/>}</p>
     */
    public static void shutdown() {
        ShellUtils.execCmd("reboot -p", true);
        Intent intent = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
        intent.putExtra("android.intent.extra.KEY_CONFIRM", false);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Utils.getContext().startActivity(intent);
    }

    /**
     * 重启
     * <p>需要root权限或者系统权限 {@code <android:sharedUserId="android.uid.system"/>}</p>
     *
     */
    public static void reboot() {
        ShellUtils.execCmd("reboot", true);
        Intent intent = new Intent(Intent.ACTION_REBOOT);
        intent.putExtra("nowait", 1);
        intent.putExtra("interval", 1);
        intent.putExtra("window", 0);
        Utils.getContext().sendBroadcast(intent);
    }

    /**
     * 重启
     * <p>需系统权限 {@code <android:sharedUserId="android.uid.system"/>}</p>
     *
     * @param reason  传递给内核来请求特殊的引导模式，如"recovery"
     */
    public static void reboot(String reason) {
        PowerManager mPowerManager = (PowerManager) Utils.getContext().getSystemService(Context.POWER_SERVICE);
        try {
            mPowerManager.reboot(reason);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 重启到recovery
     * <p>需要root权限</p>
     */
    public static void reboot2Recovery() {
        ShellUtils.execCmd("reboot recovery", true);
    }

    /**
     * 重启到bootloader
     * <p>需要root权限</p>
     */
    public static void reboot2Bootloader() {
        ShellUtils.execCmd("reboot bootloader", true);
    }

    /**
     * 获取设备唯一表示码
     */
    @SuppressLint("HardwareIds")
    public static String getDeviceId() {
        String deviceId = null;
        if (deviceId == null) {
            synchronized (Utils.getContext().getClass()) {
                if (deviceId == null) {
                    // 判断是否已经存储设备ID号
                    String id = SPUtils.getInstance().getString(Constant.DEVICE_ID, null);
                    if (null != id && !TextUtils.isEmpty(id)) {
                        // 利用保存的设备号，生产UUID
                        deviceId = id;
                    } else {
                        try {
                            // 获取设备的andoid id号
                            // 在主流厂商生产的设备上，有一个很经常的bug，就是每个设备都会产生相同的ANDROID_ID：9774d56d682e549c
                            String m_szAndroidID = Settings.Secure.getString(Utils.getContext().getContentResolver(),
                                    Settings.Secure.ANDROID_ID);
                            // 获取The IMEI:
                            TelephonyManager telephonyMgr = (TelephonyManager) Utils.getContext()
                                    .getSystemService(Context.TELEPHONY_SERVICE);
                            String szImei = null;
                            if (null != telephonyMgr) {
                                szImei = telephonyMgr.getDeviceId();
                            }
                            // 获取Pseudo-Unique ID
                            // 取到13个数字，并在前面加上“35”。这样这个ID看起来就和15位IMEI一样了。
                            String m_szDevIDShort = "35" + Build.BOARD.length() % 10
                                    + Build.BRAND.length() % 10 + Build.CPU_ABI.length() % 10
                                    + Build.DEVICE.length() % 10 + Build.DISPLAY.length() % 10
                                    + Build.HOST.length() % 10 + Build.ID.length() % 10
                                    + Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10
                                    + Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10
                                    + Build.TYPE.length() % 10 + Build.USER.length() % 10; // 13
                            // //
                            // digits
                            // The WLAN MAC Address string
                            WifiManager wm = (WifiManager) Utils.getContext()
                                    .getSystemService(Context.WIFI_SERVICE);
                            String m_szWLANMAC = null;
                            String m_szBTMAC = null;
                            if (null != wm) {
                                m_szWLANMAC = wm.getConnectionInfo().getMacAddress();
                                // The BT MAC Address string 蓝牙地址
                                // 只在有蓝牙的设备上运行。并且要加入android.permission.BLUETOOTH
                                // 权限.
                                BluetoothAdapter m_BluetoothAdapter = null; // Local
                                // Bluetooth
                                // adapter
                                m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                                if (null != m_BluetoothAdapter) {
                                    m_szBTMAC = m_BluetoothAdapter.getAddress();
                                }
                            }
                            // 开始拼接唯一的识别码，拼接后的计算出的MD5值来产生一个唯一标示 可产生32位的16进制数据
                            StringBuilder stringBuilder = new StringBuilder();
                            if (null != szImei && !TextUtils.isEmpty(szImei.trim())) {
                                stringBuilder.append(szImei);
                            }
                            if (null != m_szDevIDShort
                                    && !TextUtils.isEmpty(m_szDevIDShort.trim())) {
                                stringBuilder.append(m_szDevIDShort);
                            }
                            if (null != m_szAndroidID && !TextUtils.isEmpty(m_szAndroidID.trim())) {
                                stringBuilder.append(m_szAndroidID);
                            }
                            if (null != m_szWLANMAC && !TextUtils.isEmpty(m_szWLANMAC)) {
                                stringBuilder.append(m_szWLANMAC);
                            }
                            if (null != m_szBTMAC && !TextUtils.isEmpty(m_szBTMAC)) {
                                stringBuilder.append(m_szBTMAC);
                            }
                            String m_szLongID = null;
                            if (null != stringBuilder && stringBuilder.length() > 0) {
                                m_szLongID = stringBuilder.toString();
                            }
                            // compute md5
                            if (null != m_szLongID) {
                                MessageDigest messageDigest = null;
                                messageDigest = MessageDigest.getInstance("MD5");
                                messageDigest.update(m_szLongID.getBytes(), 0, m_szLongID.length());
                                // get md5 bytes
                                byte p_md5Data[] = messageDigest.digest();
                                // create a hex string
                                String m_szUniqueID = new String();
                                for (int i = 0; i < p_md5Data.length; i++) {
                                    int b = (0xFF & p_md5Data[i]);
                                    // if it is a single digit, make sure it
                                    // have 0
                                    // in
                                    // front (proper padding)
                                    if (b <= 0xF)
                                        m_szUniqueID += "0";
                                    // add number to string 增加数字
                                    m_szUniqueID += Integer.toHexString(b);
                                }
                                // hex string to uppercase 转换成小写
                                m_szUniqueID = m_szUniqueID.toLowerCase();
                                // 判断是否为空
                                if (null != m_szUniqueID && !TextUtils.isEmpty(m_szUniqueID)) {
                                    // 转换为16进制的参数

                                    // deviceId =
                                    // Integer.toString(Integer.parseInt(m_szUniqueID.getBytes(),
                                    // 10));
                                    deviceId = String.valueOf(m_szUniqueID);
                                } else {
                                    // 判断设备的m_szUniqueID 是否有问题，如果有问题则，随机生成一个UUID
                                    deviceId = UUID.randomUUID().toString();
                                    // deviceId =
                                    // String.valueOf(hexStringToAlgorism(deviceId));
                                    // deviceId = UUID.randomUUID().toString();
                                    if (null != deviceId
                                            && deviceId.contains(String.valueOf("-"))) {
                                        deviceId = deviceId.replace(String.valueOf("-"),
                                                String.valueOf(""));
                                    }
                                    // deviceId =
                                    // Integer.toString(Integer.parseInt(deviceId,
                                    // 10));
                                }
                            } else {
                                deviceId = UUID.randomUUID().toString();
                                // deviceId =
                                // String.valueOf(hexStringToAlgorism(deviceId));
                                // deviceId = UUID.randomUUID().toString();
                                if (null != deviceId && deviceId.contains(String.valueOf("-"))) {
                                    deviceId = deviceId.replace(String.valueOf("-"),
                                            String.valueOf(""));
                                }
                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } finally {
                            LogUtils.e("uuid--" + deviceId);
                            if (null == deviceId) {
                                deviceId = UUID.randomUUID().toString();
                                // deviceId =
                                // String.valueOf(hexStringToAlgorism(deviceId));
                                // deviceId = UUID.randomUUID().toString();
                                if (null != deviceId && deviceId.contains(String.valueOf("-"))) {
                                    deviceId = deviceId.replace(String.valueOf("-"),
                                            String.valueOf(""));
                                }
                            }
                            // 保存UUID信息
                            SPUtils.getInstance().put(Constant.DEVICE_ID, deviceId);
                        }
                    }
                }
            }
        }
        return deviceId;
    }
}
