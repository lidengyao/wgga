package com.bcxd.wgga.present;

import android.content.Context;

import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.model.api.APIService;
import com.bcxd.wgga.model.api.ApiCallBack;
import com.bcxd.wgga.model.api.ApiSubscriber;
import com.bcxd.wgga.model.api.RetrofitClient;
import com.bcxd.wgga.model.bean.ResponseBean;
import com.bcxd.wgga.ui.view.BannerView;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by lidengyao on 2016-10-11 0011.
 */
public class BannerPresent extends BasePresent<BannerView> {
    public void bannerImg(Context context) {
        Observable<ResponseBean<ArrayList<String>>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).bannerImg();
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<ArrayList<String>>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(ArrayList<String> model) {
                if (getView() != null) {
                    getView().getBannerSuccess(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().showMessage(msg);
                }
            }

            @Override
            public void onCompleted() {
                if (getView() != null) {
                    getView().dismissLoading();
                }
            }
        },context));
    }
}
