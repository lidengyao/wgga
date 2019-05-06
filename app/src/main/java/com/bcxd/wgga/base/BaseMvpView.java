package com.bcxd.wgga.base;


import com.bcxd.wgga.mvp.MvpView;

/**
 * Created by jinxh on 16/6/15.
 */
public interface BaseMvpView extends MvpView {

    void showMessage(int res);

    void showMessage(CharSequence text);

    void showLoading();

    void dismissLoading();
}
