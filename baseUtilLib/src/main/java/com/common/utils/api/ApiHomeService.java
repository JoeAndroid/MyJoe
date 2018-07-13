package com.common.utils.api;

import android.support.v4.util.ArrayMap;


import com.common.utils.bean.HomeBean;
import com.common.utils.bean.BaseJsonBean;
import com.common.utils.bean.ShareContentBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * 请求接口类 Created by qiaobing on 2017/5/31.
 */
public interface ApiHomeService {

    @Headers("Content-type:application/x-www-form-urlencoded;charset=UTF-8")
    @FormUrlEncoded
    @POST("pub/home")
    Observable<BaseJsonBean<List<HomeBean>>> getHomeDataListServer(@Field("data") String data);

    @Headers("Content-type:application/x-www-form-urlencoded;charset=UTF-8")
    @FormUrlEncoded
    @POST("")
    Observable<BaseJsonBean<ShareContentBean>> getShareContentByServer(@Field("data") String data);

   /* ArrayMap<String, RequestBody> fileParams = new ArrayMap<>();
    Map<String,String> params = new ArrayMap<>();
    if (!TextUtils.isEmpty(etShareContent.getText().toString()) && !TextUtils.isEmpty(etShareContent.getText().toString().trim())) {
        params.put("content", etShareContent.getText().toString().trim());
    }
    fileParams.put("data", RequestBody.create(MediaType.parse("text/plain"), NetworkUtil.orgParams(params)));
    for (int i = 0; i < listPics.size(); i++) {
        File file = new File(listPics.get(i));
        if (file.exists()) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            fileParams.put("image" + i + "\";filename=\"" + file.getName(), requestBody);
        }
    }*/
    /**
     * 多文件上传
     */
    @Multipart
    @POST("news/topic/save")
    Observable<BaseJsonBean> uploadPicturesToServer(@PartMap ArrayMap<String, RequestBody> params);

    /**
     * 图文上传
     * @param des
     * @param params
     * @return
     */
    @Multipart
    @POST("v1/public/core/?service=user.updateAvatar")
    Observable<BaseJsonBean> uploadMultipleTypeFile(@Part("data") String des, @PartMap Map<String, RequestBody> params);

}
