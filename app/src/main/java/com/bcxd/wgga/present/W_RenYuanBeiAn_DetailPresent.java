package com.bcxd.wgga.present;

import android.content.Context;

import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.model.api.APIService;
import com.bcxd.wgga.model.api.ApiCallBack;
import com.bcxd.wgga.model.api.ApiSubscriber;
import com.bcxd.wgga.model.api.RetrofitClient;
import com.bcxd.wgga.model.bean.ResponseBean;
import com.bcxd.wgga.model.info.ToxicCompanyMemberInfo;
import com.bcxd.wgga.ui.view.W_RenYuanBeiAn_DetailView;
import com.bcxd.wgga.utils.LogCode;

import rx.Observable;

/**
 * Created by jinxh on 16/2/1.
 */
public class W_RenYuanBeiAn_DetailPresent extends BasePresent<W_RenYuanBeiAn_DetailView> {

    public void toxicCompanyMemberDetail(Context context, int id) {


        Observable<ResponseBean<ToxicCompanyMemberInfo>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).toxicCompanyMemberDetail(id);

        // 此处请求接口
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<ToxicCompanyMemberInfo>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(ToxicCompanyMemberInfo model) {
                if (getView() != null) {
                    getView().toxicCompanyMemberDetailSuccess(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().onFailure(code,LogCode.GetCode("W_RenYuanBeiAn_AddPresent_toxicCompanyMemberadd")+ msg);
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
