package com.example.joe.api

import com.common.utils.api.HttpHelper
import com.common.utils.constant.HostType

object ApiHomeFactory {

    var homeService: ApiVideoService? = HttpHelper.getRetrofit(HostType.NETEASE_NEWS_VIDEO).create(ApiVideoService::class.java)
}