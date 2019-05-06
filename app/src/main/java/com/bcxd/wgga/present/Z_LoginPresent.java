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
import com.bcxd.wgga.model.info.GongDiInfo;
import com.bcxd.wgga.model.info.Z_UserInfo;
import com.bcxd.wgga.ui.view.Z_LoginView;
import com.bcxd.wgga.utils.LogCode;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by jinxh on 16/2/1.
 */
public class Z_LoginPresent extends BasePresent<Z_LoginView> {

    public void login(final String user_account, String password,String  deviceid, Context context) {

        LoginBean loginBean = new LoginBean();
        loginBean.setUser_account(user_account);
        loginBean.setPassword(password);
        loginBean.setDeviceid(deviceid);

        Observable<ResponseBean<LoginInfo>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).login(loginBean);

        // 此处请求接口
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<LoginInfo>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(LoginInfo model) {
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

    public void getuserinfo(Context context) {

        Observable<ResponseBean<Z_UserInfo>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).getuserinfo();

        // 此处请求接口
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<Z_UserInfo>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(Z_UserInfo model) {
                if (getView() != null) {
                    getView().getuserinfoSuccess(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().onFailure(code, LogCode.GetCode("login_getuserinfo") + msg);
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

    public void list(Context context) {
        Observable<ResponseBean<ArrayList<GongDiInfo>>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).list();

        // 此处请求接口
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<ArrayList<GongDiInfo>>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(ArrayList<GongDiInfo> model) {
                if (getView() != null) {
                    getView().listSuccess(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().onFailure(code, LogCode.GetCode("login_list") + msg);
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
