package com.bcxd.wgga.imagebanner.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.bcxd.wgga.R;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by lidengyao on 2016-09-07 0007.
 */
public class ViewFactory {


    /**
     * 获取ImageView视图的同时加载显示url
     *
     * @return
     */
    public static ImageView getImageView(Context context, String url) {
        ImageView imageView = (ImageView) LayoutInflater.from(context).inflate(
                R.layout.view_banner, null);
        ImageLoader.getInstance().displayImage(url, imageView);
        return imageView;
    }

}
