package com.example.joe.utils.share;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.view.Gravity;
import android.view.View;

import com.example.joe.api.ApiRetrofit;
import com.example.joe.api.BaseObserver;
import com.example.joe.api.RequestCallBack;
import com.example.joe.api.RxSchedulers;
import com.example.joe.bean.ShareContentBean;
import com.example.joe.bean.UserInfoBean;
import com.example.joe.popupwindow.SharePopupwindow;
import com.example.joe.utils.CommonUtils;
import com.example.joe.utils.LogUtils;

/**
 * 分享 Created by qiaobing on 2017/1/6.
 */
public class ShareUtil {

    private static ShareUtil shareUtil;

    // 分享类型 1为微信好友 2微信朋友圈 3为新浪微博
    private int type = 1;

    private Context mContext;
    private ArrayMap<String, String> params;
    //根布局
    private View rootView;

    public static ShareUtil getInstance() {
        if (null == shareUtil) {
            synchronized (ShareUtil.class) {
                if (null == shareUtil)
                    shareUtil = new ShareUtil();
            }
        }
        return shareUtil;
    }

    /**
     * 设置分享参数
     */
    public ShareUtil setShareParams(Context context, View rootView, ArrayMap<String, String> params) {
        this.mContext = context;
        this.params = params;
        this.rootView = rootView;
        return shareUtil;
    }

    public void getShareContent() {
        new ApiRetrofit().getHomeService().getShareContentByServer(CommonUtils.orgParams(params))
                .compose(RxSchedulers.compose())
                .subscribe(new BaseObserver<ShareContentBean>(new RequestCallBack<ShareContentBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onFailure(String message) {

                            }

                            @Override
                            public void onSuccess(ShareContentBean value) {
                                SharePopupwindow sharePopupwindow = new SharePopupwindow(mContext);
                                sharePopupwindow.setParams(params);
                                sharePopupwindow.setShareBean(value);
                                sharePopupwindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
                            }
                        })

                );
    }

}
