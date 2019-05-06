package com.bcxd.wgga.present;

import android.content.Context;

import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.model.api.APIService;
import com.bcxd.wgga.model.api.ApiCallBack;
import com.bcxd.wgga.model.api.ApiSubscriber;
import com.bcxd.wgga.model.api.RetrofitClient;
import com.bcxd.wgga.model.bean.ResponseBean;
import com.bcxd.wgga.model.info.RealtimeInfo;
import com.bcxd.wgga.ui.view.Z_JianCe_View;
import com.bcxd.wgga.utils.LogCode;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by jinxh on 16/2/1.
 */
public class Z_Jiance_Present extends BasePresent<Z_JianCe_View> {

    public void realtimebypid(int pid, Context context) {
        Observable<ResponseBean<ArrayList<RealtimeInfo>>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).realtimebypid(pid);

        // 此处请求接口
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<ArrayList<RealtimeInfo>>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(ArrayList<RealtimeInfo> model) {
                if (getView() != null) {
                    getView().realtimebysiteidSuccess(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().onFailure(code,LogCode.GetCode("Z_Jiance_Present_realtimebypid")+ msg);
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

    public void refreshToken( Context context) {
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
                    getView().onFailure(code,LogCode.GetCode("Z_Jiance_Present_refreshToken")+ msg);
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
