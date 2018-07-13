package com.common.utils.api;

import android.text.TextUtils;
import android.util.Log;

import com.common.utils.constant.Constant;
import com.common.utils.bean.BaseJsonBean;
import com.common.utils.utils.LogUtils;
import com.google.gson.Gson;

import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by qiaobing on 2017/6/9.
 */

public class BaseObserver<T> implements Observer<BaseJsonBean<T>> {

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
    public void onNext(BaseJsonBean<T> value) {
        if (value.isSuccess()) {
            BaseJsonBean baseJsonBean = value;
            if (null != baseJsonBean) {
                if (!TextUtils.isEmpty(baseJsonBean.getCode()) && "0".equals(baseJsonBean.getCode())) {
                    LogUtils.i(baseJsonBean.getData());
                    requestBack.onSuccess(baseJsonBean.getData());
                } else {
                    if (!TextUtils.isEmpty(baseJsonBean.getMsg())) {
                        requestBack.onFailure(baseJsonBean.getMsg());
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