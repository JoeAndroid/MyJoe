package com.example.joe.popupwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.example.joe.R;
import com.example.joe.bean.ShareContentBean;
import com.example.joe.utils.ToastUtils;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by qiaobing on 2017/1/17.
 */

public class SharePopupwindow extends PopupWindow implements View.OnClickListener {
    //分享成功
    private static final int SHARE_SUCCESS = 0x11;
    //分享失败
    private static final int SHARE_ERR = 0x12;
    //分享取消
    private static final int SHARE_CANLE = 0x13;

    private Context myContext;

    private View view;

    // 微信好友
    private TextView tvFriend;

    // 微信朋友圈
    private TextView tvFriendCircle;

    // 新浪微博
    private TextView tvSina;

    private TextView tvCancle;

    // 分享类型 1为微信好友 2微信朋友圈 3为新浪微博
    private int type = 1;
    //分享参数
    private ArrayMap<String, String> params;

    private ShareContentBean shareBean;

    //t弹出分享
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case SHARE_SUCCESS:
                    //成功
                    ToastUtils.showShort("分享成功");
                    shareSuccessToServer(params);
                    break;
                case SHARE_ERR:
                    //失败
                    ToastUtils.showShort("分享失败");
                    break;
                case SHARE_CANLE:
                    //取消
                    ToastUtils.showShort("分享取消");
                    break;
            }
            return false;
        }
    });
    /**
     * sharSDK分享集成
     */
    private PlatformActionListener shareSDKPlatformActionListener = new PlatformActionListener() {
        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            handler.sendEmptyMessage(SHARE_SUCCESS);
        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
            handler.sendEmptyMessage(SHARE_ERR);
        }

        @Override
        public void onCancel(Platform platform, int i) {
            handler.sendEmptyMessage(SHARE_CANLE);
        }
    };

    public SharePopupwindow(Context context) {
        this.myContext = context;
        initViews();
    }

    /**
     * 分享参数
     */
    public void setParams(ArrayMap<String, String> params) {
        this.params = params;
    }

    /**
     * 分享文案
     */
    public void setShareBean(ShareContentBean shareBean) {
        this.shareBean = shareBean;
    }

    private void initViews() {
        view = LayoutInflater.from(myContext).inflate(R.layout.layout_share_pop, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(lp);
      /*  int mUIFlag =
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        view.setSystemUiVisibility(mUIFlag);
        view.setFitsSystemWindows(true);*/
        setContentView(view);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);

        //设置点击外部消失
        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(new ColorDrawable(0xff000000));
        //设置焦点
        this.setFocusable(true);
        this.setTouchable(true);
        // 设置显示动画效果
        setAnimationStyle(R.style.popwindow_anim_style);
        // 设置无背景
        setBackgroundDrawable(new ColorDrawable(0x88000000));
        this.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        tvFriend = (TextView) view.findViewById(R.id.textView_weixin);
        tvFriendCircle = (TextView) view.findViewById(R.id.textView_friend);
        tvSina = (TextView) view.findViewById(R.id.textView_weibo);
        tvCancle = (TextView) view.findViewById(R.id.tvCancle);

        tvFriend.setOnClickListener(this);
        tvFriendCircle.setOnClickListener(this);
        tvSina.setOnClickListener(this);
        tvCancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tvFriend) {
            //获取平台
            Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
            if (wechat.isClientValid()) {
                type = 1;
                //设置分享信息
                Wechat.ShareParams shareParams = new Wechat.ShareParams();
                shareParams.setShareType(Platform.SHARE_WEBPAGE);
                shareParams.setTitle(shareBean.getTitle());
                shareParams.setText(shareBean.getContent());
                shareParams.setUrl(shareBean.getShareUrl());
                // 设置分享图片
//                if (!TextUtils.isEmpty(shareEntity.getImg())) {
                shareParams.setImageUrl(shareBean.getImg());
//                } else {
//                    try {
//                        shareParams.setImageData(BitmapFactory.decodeResource(myContext.getResources(), R.drawable.icon_square_moren));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }


                //设置回调
                wechat.setPlatformActionListener(shareSDKPlatformActionListener);
                //调用分享
                wechat.share(shareParams);
            } else {
                ToastUtils.showLong("您没有安装微信客户端");
            }
        }
        if (v == tvFriendCircle) {
            // 微信朋友圈
//            pyqTextView.setEnabled(false);
            Platform wechatMoments = ShareSDK.getPlatform(WechatMoments.NAME);
            if (wechatMoments.isClientValid()) {
                type = 2;
                WechatMoments.ShareParams shareParams = new WechatMoments.ShareParams();
                shareParams.setShareType(Platform.SHARE_WEBPAGE);
                shareParams.setTitle(shareBean.getContent());
                shareParams.setUrl(shareBean.getShareUrl());
                // 设置分享图片
//                if (!TextUtils.isEmpty(shareBean.getImg())) {
                shareParams.setImageUrl(shareBean.getImg());
//                } else {
//                    try {
//                        shareParams.setImageData(BitmapFactory.decodeResource(myContext.getResources(), R.drawable.icon_square_moren));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
                wechatMoments.setPlatformActionListener(shareSDKPlatformActionListener);
                wechatMoments.share(shareParams);
            } else {
                ToastUtils.showLong("您没有安装微信客户端");
            }
        }
        if (v == tvSina) {
            // 新浪微博
            Platform sinaWeiBo = ShareSDK.getPlatform(SinaWeibo.NAME);
            if (sinaWeiBo.isClientValid()) {
                type = 3;
                // 设置分享内容
                SinaWeibo.ShareParams shareParams = new SinaWeibo.ShareParams();
                shareParams.setText(shareBean.getContent() + " " + shareBean.getSinaShareUrl() + String.valueOf("  "));
                // 设置分享图片
//                if (!TextUtils.isEmpty(shareEntity.getImg())) {
                shareParams.setImageUrl(shareBean.getImg());
//                } else {
//                    try {
//                        shareParams.setImageData(BitmapFactory.decodeResource(myContext.getResources(), R.drawable.icon_square_moren));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
                sinaWeiBo.setPlatformActionListener(shareSDKPlatformActionListener);
                sinaWeiBo.share(shareParams);
            } else {
                ToastUtils.showLong("您没有安装新浪微博客户端");
            }
        }

        //隐藏
        this.dismiss();
    }

    private void shareSuccessToServer(ArrayMap<String, String> params) {

    }

}
