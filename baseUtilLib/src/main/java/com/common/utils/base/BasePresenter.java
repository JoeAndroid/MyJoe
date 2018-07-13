package com.common.utils.base;

import com.common.utils.api.ApiFactory;
import com.common.utils.api.ApiHomeService;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by qiaobing on 2017/6/9.
 */
public abstract class BasePresenter<V extends BaseView> implements BaseRequestCallBackListener {

    protected Reference<V> mViewRef;

    public static final ApiHomeService homeService = ApiFactory.getHomeService();

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    protected V getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    @Override
    public void onComplete() {
        if (isViewAttached()) {
            mViewRef.get().cancleProgressView();
        }
    }

    @Override
    public void onFailure(String message) {
        if (isViewAttached()) {
            mViewRef.get().showError(message);
        }
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

}
