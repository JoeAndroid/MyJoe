package com.example.joe.api

import com.example.joe.bean.HomeBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiVideoService{
    @GET("v2/feed?")
    fun getFirstHomeData(@Query("sum") sum:Int): Observable<HomeBean>
}