package com.bcxd.wgga.present;

import android.content.Context;

import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.model.api.APIService;
import com.bcxd.wgga.model.api.ApiCallBack;
import com.bcxd.wgga.model.api.ApiSubscriber;
import com.bcxd.wgga.model.api.RetrofitClient;
import com.bcxd.wgga.model.bean.LoginBean;
import com.bcxd.wgga.model.bean.LoginInfo;
import com.bcxd.wgga.model.bean.ResponseBean;
import com.bcxd.wgga.model.bean.W_LoginBean;
import com.bcxd.wgga.model.bean.W_LoginInfo;
import com.bcxd.wgga.model.info.GongDiInfo;
import com.bcxd.wgga.model.info.Z_UserInfo;
import com.bcxd.wgga.ui.view.W_LoginView;
import com.bcxd.wgga.ui.view.Z_LoginView;
import com.bcxd.wgga.utils.LogCode;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by jinxh on 16/2/1.
 */
public class W_LoginPresent extends BasePresent<W_LoginView> {

    public void w_login(final String user_account, String password, Context context) {

        W_LoginBean w_loginBean = new W_LoginBean();
        w_loginBean.setUserAccount(user_account);
        w_loginBean.setUserPassword(password);

        Observable<ResponseBean<W_LoginInfo>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).w_login(w_loginBean);

        // 此处请求接口
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<W_LoginInfo>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(W_LoginInfo model) {
                if (getView() != null) {
                    getView().loginSuccess(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().showMessage(LogCode.GetCode("login") + msg);
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
