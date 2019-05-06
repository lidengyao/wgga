package com.bcxd.wgga.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.bcxd.wgga.R;
import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.base.MvpActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lidengyao on 2016-10-18 0018.
 */
public class TabLayoutActivtiy extends MvpActivity {
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.vp)
    ViewPager vp;

    @Override
    protected BasePresent createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tablayout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
