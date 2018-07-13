package com.common.utils.base;

public interface BaseRequestCallBackListener {
    void onFailure(String message);

    void onComplete();
}
