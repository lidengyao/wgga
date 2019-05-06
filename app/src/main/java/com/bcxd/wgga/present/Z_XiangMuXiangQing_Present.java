package com.bcxd.wgga.present;

import android.content.Context;

import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.model.api.APIService;
import com.bcxd.wgga.model.api.ApiCallBack;
import com.bcxd.wgga.model.api.ApiSubscriber;
import com.bcxd.wgga.model.api.RetrofitClient;
import com.bcxd.wgga.model.bean.ResponseBean;
import com.bcxd.wgga.model.info.ProjectDetailInfo;
import com.bcxd.wgga.model.info.apppicInfo;
import com.bcxd.wgga.ui.view.Z_XiangMuXiangQing_View;
import com.bcxd.wgga.utils.LogCode;

import rx.Observable;

/**
 * Created by jinxh on 16/2/1.
 */
public class Z_XiangMuXiangQing_Present extends BasePresent<Z_XiangMuXiangQing_View> {

    public void projectDetail(Integer pid, Context context) {
        Observable<ResponseBean<ProjectDetailInfo>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).projectDetail(pid);

        // 此处请求接口
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<ProjectDetailInfo>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(ProjectDetailInfo model) {
                if (getView() != null) {
                    getView().projectDetail(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().onFailure(code,LogCode.GetCode("Z_XiangMuXiangQing_Present_projectDetail")+ msg);
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
    public void apppicQuery(Integer type, Integer pid, Context context) {
        Observable<ResponseBean<apppicInfo>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).apppicQuery(type, pid);

        // 此处请求接口
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<apppicInfo>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(apppicInfo model) {
                if (getView() != null) {
                    getView().apppicQuery(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().onFailure(code,LogCode.GetCode("Z_XiangMuXiangQing_Present_apppicQuery")+ msg);
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
                    getView().onFailure(code,LogCode.GetCode("Z_XiangMuXiangQing_Present_refreshToken")+ msg);
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
