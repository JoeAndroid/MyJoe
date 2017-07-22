package com.example.joe.widget.refreshlayout;

/**
 * 上拉加载状态接口 Created by qiaobing on 2017/4/5.
 */
public interface PullFooter {

    //上拉加载
    void onUpBefore(int scrollY);

    //松开刷新
    void onUpAfter(int scrollY);

    //准备刷新
    void onLoadScrolling(int scrollY);

    //正在刷新
    void onLoadDoing(int scrollY);

    //加载完成后，能回到默认状态中
    void onLoadCompleteScrolling(int scrollY, boolean isLoadSuccess);

    //加载取消，回到默认状态中
    void onLoadCancelScrolling(int scrollY);
}
