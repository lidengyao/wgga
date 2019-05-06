package com.bcxd.wgga.present;

import android.content.Context;

import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.model.api.APIService;
import com.bcxd.wgga.model.api.ApiCallBack;
import com.bcxd.wgga.model.api.ApiSubscriber;
import com.bcxd.wgga.model.api.RetrofitClient;
import com.bcxd.wgga.model.bean.MessageBean;
import com.bcxd.wgga.model.bean.ResponseBean;
import com.bcxd.wgga.model.info.CityenvironmentInfo;
import com.bcxd.wgga.model.info.ProjectInfo;
import com.bcxd.wgga.model.info.XiaoXiInfo;
import com.bcxd.wgga.model.info.apppicInfo;
import com.bcxd.wgga.ui.view.Z_ShouYe_View;
import com.bcxd.wgga.utils.LogCode;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by jinxh on 16/2/1.
 */
public class Z_ShouYe_Present extends BasePresent<Z_ShouYe_View> {

    public void projectlist(Integer type, Integer level, Context context) {
        Observable<ResponseBean<ArrayList<ProjectInfo>>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).projectlist(type, level);

        // 此处请求接口
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<ArrayList<ProjectInfo>>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(ArrayList<ProjectInfo> model) {
                if (getView() != null) {
                    getView().projectlistSuccess(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().onFailure(code,LogCode.GetCode("Z_ShouYe_Present_projectlist")+ msg);
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

    public void listMsg(MessageBean messageBean, Context context) {
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
                    getView().listMsgSuccess(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().onFailure(code,LogCode.GetCode("Z_ShouYe_Present_listMsg")+ msg);
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
                    getView().onFailure(code,LogCode.GetCode("Z_ShouYe_Present_apppicQuery")+ msg);
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

    public void Cityenvironment( Context context) {
        Observable<ResponseBean<CityenvironmentInfo>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).Cityenvironment();

        // 此处请求接口
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<CityenvironmentInfo>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(CityenvironmentInfo model) {
                if (getView() != null) {
                    getView().Cityenvironment(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().onFailure(code,LogCode.GetCode("Z_ShouYe_Present_Cityenvironment")+ msg);
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
                    getView().onFailure(code,LogCode.GetCode("Z_ShouYe_Present_refreshToken")+ msg);
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
