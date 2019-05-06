package com.bcxd.wgga.present;

import android.content.Context;

import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.model.api.APIService;
import com.bcxd.wgga.model.api.ApiCallBack;
import com.bcxd.wgga.model.api.ApiSubscriber;
import com.bcxd.wgga.model.api.RetrofitClient;
import com.bcxd.wgga.model.bean.MessageBean;
import com.bcxd.wgga.model.bean.ResponseBean;
import com.bcxd.wgga.model.info.XiaoXiInfo;
import com.bcxd.wgga.ui.view.Z_XiaoXi_View;
import com.bcxd.wgga.utils.LogCode;

import rx.Observable;

/**
 * Created by jinxh on 16/2/1.
 */
public class Z_JianKong_Present extends BasePresent<Z_XiaoXi_View> {

    public void siteList(MessageBean messageBean, Context context) {
        Observable<ResponseBean<XiaoXiInfo>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).messagelist(messageBean);

        // 此处请求接口
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<XiaoXiInfo>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(XiaoXiInfo model) {
                if (getView() != null) {
                    getView().listSuccess(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().onFailure(code,LogCode.GetCode("jiankong_sitelist")+ msg);
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
