package com.common.utils.base;

public interface BaseView {

    /**
     * 显示进度
     */
    void showProgressView();

    /**
     * 隐藏进度
     */
    void cancleProgressView();

    /**
     * 显示错误信息
     *
     * @param msg
     */
    void showError(String msg);

}
