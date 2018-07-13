package com.common.utils.view.refreshlayout;

/**
 * 下拉刷新状态接口
 * Created by qiaobing on 2017/4/5.
 */

public interface PullHeader {

    //下拉刷新（下拉中，到达有效刷新距离前）
    void onDownBefore(int scrollY);

    //松开刷新（下拉中，到达下拉刷新后）
    void onDownAfter(int scrollY);

    //准备刷新（从松手后的位置滚动到刷新的位置）
    void onRefreshScrolling(int scrollY);

    //正在刷新.....
    void onRefreshDoing(int scrollY);

    //刷新完成后，回到默认状态中
    void onRefreshCompleteScrolling(int scrollY, boolean isRefreshSuccess);

    //刷新取消，回到默认状态中（没有超过有效的下拉距离）
    void onRefreshCancelScrolling(int scrollY);
}
