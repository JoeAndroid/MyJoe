package com.example.joe.ui.model

import com.common.utils.api.RxSchedulers
import com.common.utils.base.BaseRequestCallBackListener
import com.common.utils.utils.LogUtils
import com.example.joe.api.ApiHomeFactory
import com.example.joe.bean.HomeBean
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class HomeVideoModel(var requestHomeCallBackListener: RequestHomeCallBackListener) {

    fun getHomeVideoData(num: Int) {
        ApiHomeFactory.homeService!!.getFirstHomeData(num)
                .compose(RxSchedulers.compose())
                .subscribe(object : Observer<HomeBean> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: HomeBean) {
                        requestHomeCallBackListener.getHomeDataSuccess(t)
                        LogUtils.i("HomeBean--", t.toString())
                    }

                    override fun onError(e: Throwable) {
                    }

                })
    }

    interface RequestHomeCallBackListener : BaseRequestCallBackListener {
        fun getHomeDataSuccess(value: HomeBean?)
    }
}
