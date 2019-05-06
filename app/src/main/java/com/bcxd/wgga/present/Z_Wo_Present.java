package com.bcxd.wgga.present;

import android.content.Context;

import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.model.api.APIService;
import com.bcxd.wgga.model.api.ApiCallBack;
import com.bcxd.wgga.model.api.ApiSubscriber;
import com.bcxd.wgga.model.api.RetrofitClient;
import com.bcxd.wgga.model.bean.HeadImgObject;
import com.bcxd.wgga.model.bean.ResponseBean;
import com.bcxd.wgga.model.info.UpLoadInfo;
import com.bcxd.wgga.model.info.VersionInfo;
import com.bcxd.wgga.ui.view.Z_Wo_View;
import com.bcxd.wgga.utils.LogCode;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by jinxh on 16/2/1.
 */
public class Z_Wo_Present extends BasePresent<Z_Wo_View> {

    public void logout(Context context) {
        Observable<ResponseBean<String>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).logout();

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
                    getView().logoutSuccess(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().onFailure(code,LogCode.GetCode("Z_Wo_Present_logout")+ msg);
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

    public void upload(File file, Context context) {

        RequestBody requestBody = MultipartBody.create(MediaType.parse("image/png"), file);

        Map<String, RequestBody> map = new HashMap<>();
        map.put("path\"; filename=\"" + file.getName() + "", requestBody);

        Observable<ResponseBean<UpLoadInfo>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).upload(requestBody);
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<UpLoadInfo>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(UpLoadInfo model) {
                if (getView() != null) {
                    getView().uploadSuccess(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().onFailure(code,LogCode.GetCode("Z_Wo_Present_upload")+ msg);
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

    public void update(String pictureurl, String position, String selfieid, String uid, String usercode, String username, String userpass, Context context) {

        Observable<ResponseBean<String>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).update(pictureurl, position, selfieid, uid, usercode, username, userpass);

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
                    getView().updateSucess(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().onFailure(code,LogCode.GetCode("Z_Wo_Present_upload")+ msg);
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

    public void updateHeadImg(HeadImgObject headImgObject, Context context) {

        Observable<ResponseBean<String>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).updateHeadImg(headImgObject);

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
                    getView().updateHeadImg(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().onFailure(code,LogCode.GetCode("Z_Wo_Present_upload")+ msg);
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

    public void checkVersion( Context context) {
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
                    getView().onFailure(code,LogCode.GetCode("Z_Wo_Present_refreshToken")+ msg);
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
