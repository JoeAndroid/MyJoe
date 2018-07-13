package com.common.utils.view.refreshlayout;

/**
 * 刷新监听
 * Created by qiaobing on 2017/4/5.
 */

public interface OnPullListener {

    //执行刷新
    void onRefresh();

    //执行加载更多
    void onLoadMore();
}
