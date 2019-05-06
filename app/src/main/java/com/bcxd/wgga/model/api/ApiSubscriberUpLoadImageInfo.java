package com.bcxd.wgga.model.api;

import com.bcxd.wgga.AppContext;
import com.bcxd.wgga.model.bean.UploadfileBean;

import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by lidengyao on 2016-10-11 0011.
 */
public class ApiSubscriberUpLoadImageInfo<T> extends Subscriber<UploadfileBean> {

    public static int UNKNOWN_CODE = -1;
    private ApiCallBack apiCallback;

    public ApiSubscriberUpLoadImageInfo(ApiCallBack apiCallback) {
        this.apiCallback = apiCallback;
    }

    @Override
    public void onCompleted() {
        apiCallback.onCompleted();
    }

    @Override
    public void onStart() {
        super.onStart();
        apiCallback.onStart();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException || e instanceof UnknownHostException) {
            apiCallback.onFailure(UNKNOWN_CODE, AppContext.NET_ERROR_MSG);
        } else {
            apiCallback.onFailure(UNKNOWN_CODE, e.getMessage());
        }
        apiCallback.onCompleted();
    }

    @Override
    public void onNext(UploadfileBean httpBean) {

        if (httpBean.getStatus() == 0) {
            apiCallback.onSuccess(httpBean);

        } else {
            apiCallback.onFailure(-1, httpBean.getMsg());
        }
    }
}
