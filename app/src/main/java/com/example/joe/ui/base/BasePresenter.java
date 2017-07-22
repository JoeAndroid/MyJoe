package com.example.joe.ui.base;

import com.example.joe.api.ApiHomeService;
import com.example.joe.api.ApiRetrofit;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by qiaobing on 2017/6/9.
 */
public abstract class BasePresenter<V> {

    protected Reference<V> mViewRef;

    public static final ApiHomeService homeService=new ApiRetrofit().getHomeService();

    public void attachView(V view){
        mViewRef = new WeakReference<V>(view);
    }

    protected V getView(){
        return mViewRef.get();
    }

    public boolean isViewAttached(){
        return mViewRef != null&&mViewRef.get()!=null;
    }

    public void detachView(){
        if(mViewRef!=null){
            mViewRef.clear();
            mViewRef = null;
        }
    }

}
