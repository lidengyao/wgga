package com.bcxd.wgga.present;

import android.content.Context;

import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.model.api.APIService;
import com.bcxd.wgga.model.api.ApiCallBack;
import com.bcxd.wgga.model.api.ApiSubscriber;
import com.bcxd.wgga.model.api.RetrofitClient;
import com.bcxd.wgga.model.bean.MessageBean;
import com.bcxd.wgga.model.bean.ResponseBean;
import com.bcxd.wgga.model.info.ChildMenuInfo;
import com.bcxd.wgga.model.info.CityenvironmentInfo;
import com.bcxd.wgga.model.info.ProjectInfo;
import com.bcxd.wgga.model.info.XiaoXiInfo;
import com.bcxd.wgga.model.info.apppicInfo;
import com.bcxd.wgga.ui.view.W_ShouYe_View;
import com.bcxd.wgga.ui.view.Z_ShouYe_View;
import com.bcxd.wgga.utils.DbKeyS;
import com.bcxd.wgga.utils.LogCode;
import com.bcxd.wgga.utils.SpUtils;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by jinxh on 16/2/1.
 */
public class W_ShouYe_Present extends BasePresent<W_ShouYe_View> {

    public void getMenuByUser(Integer moduleId, Context context) {
        Observable<ResponseBean<ArrayList<ChildMenuInfo>>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).getMenuByUser( moduleId);

        // 此处请求接口
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<ArrayList<ChildMenuInfo>>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(ArrayList<ChildMenuInfo> model) {
                if (getView() != null) {
                    getView().getMenuByUserSuccess(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().onFailure(code, LogCode.GetCode("Z_ShouYe_Present_projectlist") + msg);
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
                    getView().onFailure(code, LogCode.GetCode("Z_ShouYe_Present_refreshToken") + msg);
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
