package com.example.joe.api;

/**
 * 自定义返回
 * Created by shenxiaolei on 16/11/14.
 */
public abstract class RequestCallBack<T> {


    public abstract void onCompleted();

    public abstract void onFailure(String message);

    public abstract void onSuccess(T value);

}
