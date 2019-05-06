package com.bcxd.wgga.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bcxd.wgga.R;
import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.base.MvpActivity;
import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lidengyao on 2016-10-14 0014.
 */
public class MoreHeadImageActivity extends MvpActivity {
    @Bind(R.id.homeTopLL)
    RelativeLayout homeTopLL;
    @Bind(R.id.ImgWebImage)
    ImageView ImgWebImage;
    @Bind(R.id.ImgCircleWebImage)
    ImageView ImgCircleWebImage;

    @Override
    protected BasePresent createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_moreheadimage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        String imgUrl = "http://www.ytxww.com/uploadSuccess/image/201203/20120309100442708165.jpg";
//        Glide.with(this).load(imgUrl).placeholder(R.mipmap.demo).into(ImgWebImage);
//
//        Glide.with(this).load(imgUrl).transform(new GlideCircleTransform(this)).placeholder(R.mipmap.demo)
//                .into(ImgCircleWebImage);

        Glide.with(this)
                .load(imgUrl)
//                .apply(options)
                .into(ImgCircleWebImage);
    }
}
