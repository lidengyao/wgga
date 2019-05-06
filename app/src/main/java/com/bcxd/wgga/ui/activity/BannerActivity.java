package com.bcxd.wgga.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bcxd.wgga.R;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.imagebanner.bean.ADInfo;
import com.bcxd.wgga.imagebanner.lib.CycleViewPager;
import com.bcxd.wgga.imagebanner.utils.ViewFactory;
import com.bcxd.wgga.present.BannerPresent;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lidengyao on 2016-10-11 0011.
 */
public class BannerActivity extends MvpActivity{
    @Bind(R.id.homeTopLL)
    RelativeLayout homeTopLL;
    private CycleViewPager cycleViewPager;

    @Override
    protected BannerPresent createPresenter() {
        return new BannerPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_banner;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        configImageLoader();
        initialize();
    }
    private List<ADInfo> infos = new ArrayList<ADInfo>();

    private List<ImageView> views = new ArrayList<ImageView>();

    /**
     * 配置ImageLoder
     */
    private void configImageLoader() {
        // 初始化ImageLoader
        @SuppressWarnings("deprecation")
        DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.mipmap.banner) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.banner) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.banner) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                // .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    @SuppressLint("NewApi")
    private void initialize() {

        cycleViewPager = (CycleViewPager) getFragmentManager()
                .findFragmentById(R.id.fragment_cycle_viewpager_content);

        ADInfo info1 = new ADInfo();
        info1.setUrl("http://img2.bitautoimg.com/autoalbum/files/20160811/367/14221936784644_5169213_14.JPG");
        infos.add(info1);

        ADInfo info2 = new ADInfo();
        info2.setUrl("http://img3.bitautoimg.com/autoalbum/files/20160811/892/14222089284163_5169214_14.JPG");
        infos.add(info2);

        ADInfo info3 = new ADInfo();
        info3.setUrl("http://img4.bitautoimg.com/autoalbum/files/20160811/266/14222226691839_5169215_14.JPG");
        infos.add(info3);

        ADInfo info4 = new ADInfo();
        info4.setUrl("http://img1.bitautoimg.com/autoalbum/files/20160811/385/14222338503899_5169216_14.JPG");
        infos.add(info4);

        ADInfo info5 = new ADInfo();
        info5.setUrl("http://img2.bitautoimg.com/autoalbum/files/20160811/849/14222484912654_5169217_14.JPG");
        infos.add(info5);



        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(this, infos.get(infos.size() - 1).getUrl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ViewFactory.getImageView(this, infos.get(i).getUrl()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(this, infos.get(0).getUrl()));

        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, infos, mAdCycleViewListener);
        //设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(3000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }

    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager.ImageCycleViewListener() {

        @Override
        public void onImageClick(ADInfo info, int position, View imageView) {
            if (cycleViewPager.isCycle()) {
                position = position - 1;
            }

        }

    };

}
