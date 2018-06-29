package com.common.utils.utils.share;

import cn.sharesdk.framework.authorize.AuthorizeAdapter;

/**
 * 自建ShareSDKAdapter  配置sdk分享
 * Created by shenxiaolei on 16/1/15.
 */
public class ShareSDKAdapter extends AuthorizeAdapter {
    public void onCreate() {

        // 隐藏标题栏右部的ShareSDK Logo
        hideShareSDKLogo();
    }
}
