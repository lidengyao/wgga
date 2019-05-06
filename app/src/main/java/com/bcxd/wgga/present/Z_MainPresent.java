package com.bcxd.wgga.present;

import android.content.Context;

import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.model.api.APIService;
import com.bcxd.wgga.model.api.ApiCallBack;
import com.bcxd.wgga.model.api.ApiSubscriber;
import com.bcxd.wgga.model.api.RetrofitClient;
import com.bcxd.wgga.model.bean.ResponseBean;
import com.bcxd.wgga.model.info.ProjectInfo;
import com.bcxd.wgga.model.info.VersionInfo;
import com.bcxd.wgga.model.info.Z_UserInfo;
import com.bcxd.wgga.ui.view.Z_MainSecView;
import com.bcxd.wgga.utils.LogCode;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by jinxh on 16/2/1.
 */
public class Z_MainPresent extends BasePresent<Z_MainSecView> {

    public void getuserinfo( Context context) {

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
                    getView().onFailure(code,LogCode.GetCode("Z_MainPresent_getuserinfo")+ msg);
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
                    getView().onFailure(code,LogCode.GetCode("Z_MainPresent_getuserinfo")+ msg);
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
//    public void list() {
//        Observable<ResponseBean<ArrayList<GongDiInfo>>> observable = RetrofitClient.builderRetrofit().create(APIService.class).list();
//
//        // 此处请求接口
//        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<ArrayList<GongDiInfo>>() {
//            @Override
//            public void onStart() {
//                super.onStart();
//                if (getView() != null) {
//                    getView().showLoading();
//                }
//            }
//
//            @Override
//            public void onSuccess(ArrayList<GongDiInfo> model) {
//                if (getView() != null) {
//                    getView().listSuccess(model);
//                }
//            }
//
//            @Override
//            public void onFailure(int code, String msg) {
//                if (getView() != null) {
//                    getView().onFailure(code,"34"+msg);
//                }
//            }
//
//            @Override
//            public void onCompleted() {
//                if (getView() != null) {
//                    getView().dismissLoading();
//                }
//            }
//        }));
//    }

    public void checkVersion(Context context) {
        Observable<ResponseBean<VersionInfo>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).checkVersion();
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<VersionInfo>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(VersionInfo model) {
                if (getView() != null) {
                    getView().checkVersionSuccess(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().showMessage("" + msg);
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
                    getView().onFailure(code,LogCode.GetCode("Z_MainPresent_refreshToken")+ msg);
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
