package com.example.joe.api;

import com.google.gson.Gson;

import android.text.TextUtils;
import android.util.Log;

import com.example.joe.bean.JsonBean;
import com.example.joe.contants.Constant;

import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by qiaobing on 2017/6/9.
 */

public class BaseObserver<T> implements Observer<JsonBean<T>> {

    private static final String TAG = "BaseObserver";

    private Gson gson;

    private RequestCallBack requestBack;

    public BaseObserver(RequestCallBack<T> requestBack) {
        this.requestBack = requestBack;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(JsonBean<T> value) {
        if (value.isSuccess()) {
            JsonBean jsonBean = value;
            if (null != jsonBean) {
                if (!TextUtils.isEmpty(jsonBean.getCode()) && "0".equals(jsonBean.getCode())) {
                    requestBack.onSuccess(jsonBean.getData());
                } else {
                    if (!TextUtils.isEmpty(jsonBean.getMsg())) {
                        requestBack.onFailure(jsonBean.getMsg());
                    } else {
                        requestBack.onFailure(Constant.EX_REQUEST_FAILURE);
                    }
                }
            } else {
                requestBack.onFailure(Constant.EX_REQUEST_FAILURE);
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "error:" + e.toString());
        requestBack.onCompleted();
        String message = Constant.EX_REQUEST_FAILURE;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            switch (code) {
                case 400:
                case 403:
                case 404:
                    message = Constant.EX_NETWORK_ERR;
                    break;
                case 413:
                    message = Constant.EX_FILE_BIG_ERR;
                    break;
                case 500:
                    message = Constant.EX_SERVER_ERR;
                    break;
                case 408:
                case 504:
                    message = Constant.EX_NETWORK_TIME_OUT;
                    break;
            }
        } else if (e instanceof TimeoutException) {
            message = Constant.EX_NETWORK_TIME_OUT;
        }
        requestBack.onFailure(message);
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete");
        requestBack.onCompleted();
    }

}