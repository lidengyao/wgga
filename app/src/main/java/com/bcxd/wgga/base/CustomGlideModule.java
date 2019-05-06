package com.bcxd.wgga.base;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bcxd.wgga.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.request.target.ViewTarget;


/**
 * Created by jinxh on 15/12/21.
 */
public class CustomGlideModule implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        ViewTarget.setTagId(R.id.glide_tag_id); // 设置别的get/set tag id，以免占用View默认的
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888); // 设置图片质量为高质量
    }

//    @Override
//    public void registerComponents(Context context, Registry registry) {
//
//    }

//    @Override
//    public void registerComponents(Context context, Glide glide, Registry registry) {
//
//    }

//    @Override
//    public void registerComponents(Context context, Registry registry) {
//
//    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {

    }


//    @Override
//    public void registerComponents(Context context, Glide glide) {
//        // 注册我们的ImageFidLoader
//    }

}
