package com.bcxd.wgga.present;

import android.content.Context;

import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.model.api.APIService;
import com.bcxd.wgga.model.api.ApiCallBack;
import com.bcxd.wgga.model.api.ApiSubscriber;
import com.bcxd.wgga.model.api.RetrofitClient;
import com.bcxd.wgga.model.bean.ResponseBean;
import com.bcxd.wgga.model.info.BaoJingInfoDetail;
import com.bcxd.wgga.ui.view.Z_BaojingChuLi_View;
import com.bcxd.wgga.utils.LogCode;

import rx.Observable;

/**
 * Created by jinxh on 16/2/1.
 */
public class Z_BaojingChuLi_Present extends BasePresent<Z_BaojingChuLi_View> {

    public void alertsearchrealbyrid(int rid, Context context) {
        Observable<ResponseBean<BaoJingInfoDetail>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).alertsearchrealbirid(rid);

        // 此处请求接口
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<BaoJingInfoDetail>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(BaoJingInfoDetail model) {
                if (getView() != null) {
                    getView().alertsearchrealbyridSuccess(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().onFailure(code, LogCode.GetCode("Z_BaojingChuLi_alertsearchrealbyrid")+msg);
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

    public void updatealertstatus(int rid,int status, Context context) {
        Observable<ResponseBean<String>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).updatealertstatus(rid,status);

        // 此处请求接口
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<String>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(String model) {
                if (getView() != null) {
                    getView().updatealertstatusSuccess(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().onFailure(code, LogCode.GetCode("Z_BaojingChuLi_updatealertstatus")+msg);
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

    public void refreshToken(Context context) {
        Observable<ResponseBean<String>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).refreshtoken();

        // 此处请求接口
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<String>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(String model) {
                if (getView() != null) {
                    getView().refreshtokenSuccess(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().onFailure(code, LogCode.GetCode("Z_BaojingChuLi_refreshToken")+msg);
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
