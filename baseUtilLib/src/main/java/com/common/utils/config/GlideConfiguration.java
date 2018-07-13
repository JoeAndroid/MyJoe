package com.common.utils.config;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.common.utils.constant.Constant;
import com.common.utils.utils.glide.OkHttpUrlLoader;

import java.io.File;
import java.io.InputStream;

/**
 * glide图片加载配置
 * Created by shenxiaolei on 17/2/15.
 */

public class GlideConfiguration implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
//设置缓存目录
        builder.setDiskCache(new DiskCache.Factory() {
            @Override
            public DiskCache build() {
                File fileDisk = new File(Constant.PicCachePath);
                //缓存目录和大小
                return DiskLruCacheWrapper.get(fileDisk, 1 * 24 * 60 * 60 * 1000);
            }
        });
        //缓存大小
        builder.setMemoryCache(new LruResourceCache(8 * 1024 * 1024));
        //
        builder.setBitmapPool(new LruBitmapPool(8 * 1024 * 1024));
        // 默认为RGB_565
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }
}
