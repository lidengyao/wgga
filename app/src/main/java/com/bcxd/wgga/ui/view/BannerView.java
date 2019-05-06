package com.bcxd.wgga.ui.view;

import com.bcxd.wgga.base.BaseMvpView;

import java.util.ArrayList;

/**
 * Created by lidengyao on 2016-10-11 0011.
 */
public interface BannerView extends BaseMvpView {
    void getBannerSuccess(ArrayList<String> model);
}
