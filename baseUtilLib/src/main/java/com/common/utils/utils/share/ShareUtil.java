package com.common.utils.utils.share;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.view.Gravity;
import android.view.View;

import com.common.utils.api.ApiFactory;
import com.common.utils.api.ApiHomeService;
import com.common.utils.api.HttpHelper;
import com.common.utils.api.BaseObserver;
import com.common.utils.api.RequestCallBack;
import com.common.utils.api.RxSchedulers;
import com.common.utils.bean.ShareContentBean;
import com.common.utils.constant.HostType;
import com.common.utils.popupwindow.SharePopupwindow;
import com.common.utils.utils.CommonUtils;

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
        ApiFactory.getHomeService().getShareContentByServer(CommonUtils.orgParams(params))
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
