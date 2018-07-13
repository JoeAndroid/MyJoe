package com.common.utils.api;

import android.support.v4.util.ArrayMap;

import com.common.utils.bean.BaseJsonBean;
import com.common.utils.utils.AesUtil;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 请求返回数据处理
 * Created by shenxiaolei on 16/11/12.
 */

public class JsonBeanConverterFactory extends Converter.Factory {


    public JsonBeanConverterFactory() {
    }

    public static JsonBeanConverterFactory create() {
        return new JsonBeanConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new JsonBeanResponseBodyConverter<>();
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    /**
     * 返回结果处理类
     *
     * @param <T>
     */
    public static class JsonBeanResponseBodyConverter<T> implements Converter<ResponseBody, T> {


        public JsonBeanResponseBodyConverter() {

        }

        @Override
        public T convert(ResponseBody value) throws IOException {
            try {
                String json = AesUtil.decrypt(value.string());
                JSONObject jsonObject = new JSONObject(json);
                BaseJsonBean baseJsonBean = new BaseJsonBean();
                String code = jsonObject.optString("code");
                baseJsonBean.setCode(code);
                if ("0".equals(code)) {
                    baseJsonBean.setData(jsonObject.opt("result"));
                } else {
                    baseJsonBean.setMsg(jsonObject.optString("msg"));
                }
                return (T) baseJsonBean;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static class JsonBeanRequestBodyConverter<T> implements Converter<T, RequestBody> {

        private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
        private static final Charset UTF_8 = Charset.forName("UTF-8");

        private TypeAdapter<?> typeAdapter;

        private Gson gson;

        public JsonBeanRequestBodyConverter(Gson gson, TypeAdapter<?> typeAdapter) {
            this.typeAdapter = typeAdapter;
            this.gson = gson;

        }

        @Override
        public RequestBody convert(T value) throws IOException {
            ArrayMap<String, String> params = gson.fromJson(value.toString(), ArrayMap.class);
            return RequestBody.create(MEDIA_TYPE, gson.toJson(params).toString());
        }
    }
}




