package com.bcxd.wgga.present;

import android.content.Context;

import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.model.api.APIService;
import com.bcxd.wgga.model.api.ApiCallBack;
import com.bcxd.wgga.model.api.ApiSubscriber;
import com.bcxd.wgga.model.api.RetrofitClient;
import com.bcxd.wgga.model.bean.ResponseBean;
import com.bcxd.wgga.model.info.ToxicCompanyMemberInfo;
import com.bcxd.wgga.ui.view.W_RenYuanBeiAnView;
import com.bcxd.wgga.utils.LogCode;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by jinxh on 16/2/1.
 */
public class W_RenYuanBeiAnPresent extends BasePresent<W_RenYuanBeiAnView> {

    public void toxicCompanyMember(Context context) {


        Observable<ResponseBean<ArrayList<ToxicCompanyMemberInfo>>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).toxicCompanyMember();

        // 此处请求接口
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<ArrayList<ToxicCompanyMemberInfo>>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(ArrayList<ToxicCompanyMemberInfo> model) {
                if (getView() != null) {
                    getView().toxicCompanyMemberSuccess(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().showMessage(LogCode.GetCode("W_RenYuanBeiAnPresent_toxicCompanyMember") + msg);
                }
            }

            @Override
            public void onCompleted() {
                if (getView() != null) {
                    getView().dismissLoading();
                }
            }
        }, context));
    }


}
