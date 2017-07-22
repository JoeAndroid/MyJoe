package com.example.joe.receiver;

import com.google.gson.Gson;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;


import java.util.HashMap;

import cn.jpush.android.api.JPushInterface;

/**
 * 极光推送监听广播
 *
 * @author shenxiaolei
 */
public class JpushReceiver extends BroadcastReceiver {

    /**
     * url:yanzhi://yanzhi.cn?type=
     *
     *  报名 enroll 参数 bannerId
     视频详情 play 参数 videoId
     个人中心 view 参数 userId
     专辑 album 参数 albumId
     擂台 competition 参数 competitionId
     直播 live 参数 live 暂不考虑
     h5 h5
     */

    /**
     * 言值内部跳转URL
     */
    private static final String YANZHI_URL = "yanzhi://yanzhi.cn";
    /**
     * 跳转请求类型
     */
    private static final String YANZHI_TYPE = "type=";

    /**
     * 言值与符号 标志是否有多个请求参数
     */
    private static final String YANZHI_YU = "&";

    /**
     * type
     */
    private static final String ENROLL = "enroll";//跳转到报名页

    private static final String PLAY = "play";//视频播放

    private static final String VIEW = "view";//跳转到个人页面

    private static final String ALBUM = "album";//跳转到专辑

    private static final String COMPETITION = "competition";//跳转到擂台

    private static final String TOPIC = "topic";//跳转到话题

    private static final String NEWTOPICINFO = "newTopic";//专题详情

    private static final String LIVE = "live";//直播

    private static final String WINNERINFOCHECK = "winnerInfoCheck";//领奖审核

    private static final String EDITUSER = "editUser";//个人资料

    //类型和参数都是h5
    private static final String H5 = "h5";

    /**
     * 用户id
     */
    private static final String USER_ID = "&userId=";
    /**
     * 视频ID
     */
    private static final String VIDEO_ID = "&videoId=";

    /**
     * bannerID
     */
    private static final String BANNER_ID = "&bannerId=";
    /**
     * 专辑id
     */
    private static final String ALBUM_ID = "&albumId=";
    /**
     * 擂台ID
     */
    private static final String COMPETITION_ID = "&competitionId=";
    public static NetEventHandler netEventHandler;
    private static String NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    //直播参数maps
    private HashMap<String, String> liveMaps;
    private HashMap<String, String> enrollMaps;
    private Gson gson;
    private Bundle bundle;

    public static void setNetEventHandler(NetEventHandler netEventHandler) {
        JpushReceiver.netEventHandler = netEventHandler;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null != intent && null != intent.getAction()) {

            /*
             * 收到自定义消息
             */
            if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                // 自定义消息处理
                if (null != intent.getExtras()) {
                    bundle = intent.getExtras();
                    // title 字段。
                    String title = bundle.getString(JPushInterface.EXTRA_TITLE);
                    // 自定义消息内容”字段。
                    String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
                    // 对应 API 消息内容的 extras 字段。
                    // 对应 Portal 推送消息界面上的“可选设置”里的附加字段
                    String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
                    // 保存服务器推送下来的内容类型。
                    // 对应 API 消息内容的 content_type 字段。
                    String type = bundle.getString(JPushInterface.EXTRA_CONTENT_TYPE);
                    // SDK 1.4.0 以上版本支持。
                    // 富媒体通消息推送下载后的文件路径和文件名。
                    String file = bundle.getString(JPushInterface.EXTRA_RICHPUSH_FILE_PATH);
                    // 唯一标识消息的 ID, 可用于上报统计等
                    String messId = bundle.getString(JPushInterface.EXTRA_MSG_ID);

                    //解析message:"{'comments':0,'praises':0,'fans':2,'ats':0,'replys':0}"
                    if (null != extras && !TextUtils.isEmpty(extras.trim())) {
                       /* LogUtils.e("extras-->" + extras);
                        try {
                            java.lang.reflect.Type beantype = new TypeToken<NewsNumberBean.NewsNumBean>() {
                            }.getType();
                            gson = new GsonBuilder().serializeNulls().create();
                            NewsNumberBean.NewsNumBean newsNumBean = gson.fromJson(extras, beantype);
                            if (null != newsNumBean) {
                                EventBus.getDefault().post(newsNumBean);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }*/
                    }
                }
                // 通知最新
//                EventBus.getDefault().post(Contants.YES_NEWS);
            }
            /*
             * 通知处理
             */
            if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
                if (null != intent.getExtras()) {
                    bundle = intent.getExtras();
                    // 对应 API 通知内容的 n_title 字段。
                    // 对应 Portal 推送通知界面上的“通知标题”字段。
                    String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
                    // 保存服务器推送下来的通知内容。
                    // 对应 API 通知内容的 n_content 字段。
                    // 对应 Portal 推送通知界面上的“通知内容”字段。
                    String content = bundle.getString(JPushInterface.EXTRA_ALERT);
                    // 保存服务器推送下来的附加字段。这是个 JSON 字符串。
                    // 对应 API 通知内容的 n_extras 字段。
                    // 对应 Portal 推送消息界面上的“可选设置”里的附加字段。
                    String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
                    // SDK 1.3.5 以上版本支持。
                    // 通知栏的Notification ID，可以用于清除Notification
                    int notificationId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
                    // 保存服务器推送下来的内容类型。
                    // 对应 API 消息内容的 content_type 字段。内容类型。
                    // Portal 上暂时未提供输入字段。
                    String type = bundle.getString(JPushInterface.EXTRA_CONTENT_TYPE);
                    // SDK 1.4.0 以上版本支持。
                    // 富媒体通知推送下载的HTML的文件路径,用于展现WebView。
                    String fileHtml = bundle.getString(JPushInterface.EXTRA_RICHPUSH_HTML_PATH);
                    // 富媒体通知推送下载的图片资源的文件名,多个文件名用 “，” 分开。 与
                    // “JPushInterface.EXTRA_RICHPUSH_HTML_PATH” 位于同一个路径。
                    String fileStr = bundle.getString(JPushInterface.EXTRA_RICHPUSH_HTML_RES);
                    // String[] fileNames = fileStr.split(",");
                    // 唯一标识通知消息的 ID, 可用于上报统计等。
                    String messId = bundle.getString(JPushInterface.EXTRA_MSG_ID);
                }


                // 通知最新
//                EventBus.getDefault().post(Contants.YES_NEWS);
            }
            /**
             * 通知栏打开监听
             */
            if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                if (null != intent.getExtras()) {
                    bundle = intent.getExtras();
                    String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);//{"url": "yanzhi:\/\/yanzhi.cn?type=login"}

                }
            }
            //接收网络状态变化(直播重连)
            if (intent.getAction().equals(JPushInterface.ACTION_CONNECTION_CHANGE)) {
                if (null != netEventHandler) {
                    netEventHandler.onNetChange();
                }
            }
        }
    }


    public interface NetEventHandler {

        void onNetChange();
    }

}
