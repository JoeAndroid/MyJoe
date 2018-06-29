package com.example.joe.ui.presenter;


import android.content.Context;
import android.support.v4.util.ArrayMap;

import com.common.utils.api.BaseObserver;
import com.common.utils.api.RequestCallBack;
import com.common.utils.api.RxSchedulers;
import com.common.utils.base.BasePresenter;
import com.common.utils.bean.HomeBean;
import com.common.utils.bean.JsonBean;
import com.common.utils.utils.CommonUtils;
import com.example.joe.ui.view.HomeView;

import java.util.List;

/**
 * 首页
 * Created by qiaobing on 2017/6/9.
 */
public class HomePresenter extends BasePresenter<HomeView> {

    private Context context;
    private HomeView homeView;

    private JsonBean jsonBean;

    public HomePresenter(Context context) {
        this.context = context;
    }

    public void getHomeDataList(ArrayMap<String, String> params) {
        homeView = getView();
        if (homeView != null) {
            homeService.getHomeDataListServer(CommonUtils.orgParams(params))
                    .compose(RxSchedulers.compose())
                    .subscribe(new BaseObserver<List<HomeBean>>(new RequestCallBack<List<HomeBean>>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onFailure(String message) {

                                }

                                @Override
                                public void onSuccess(List<HomeBean> value) {
                                    homeView.getDataListSuccess();
                                }
                            })

                    );
        }
    }
}
