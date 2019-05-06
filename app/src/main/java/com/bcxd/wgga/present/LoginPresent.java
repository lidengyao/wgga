package com.bcxd.wgga.present;

import android.content.Context;

import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.model.api.APIService;
import com.bcxd.wgga.model.api.ApiCallBack;
import com.bcxd.wgga.model.api.ApiSubscriber;
import com.bcxd.wgga.model.api.RetrofitClient;
import com.bcxd.wgga.model.bean.ResponseBean;
import com.bcxd.wgga.model.info.PhoneLoginInfo;
import com.bcxd.wgga.ui.view.LoginView;

import rx.Observable;

/**
 * Created by jinxh on 16/2/1.
 */
public class LoginPresent extends BasePresent<LoginView> {
    public void login(final String phone, String password, Context context) {
        Observable<ResponseBean<PhoneLoginInfo>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).phoneLogin(phone, password);
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<PhoneLoginInfo>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(PhoneLoginInfo model) {
                if (getView() != null) {
                    getView().loginSuccess(model);
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
