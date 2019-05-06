package com.bcxd.wgga.model.api;

import com.bcxd.wgga.AppContext;
import com.bcxd.wgga.model.bean.CommonMessageBean;

import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by lidengyao on 2016-09-01 0001.
 */
public class ApiSubscriberCommonMessage<T> extends Subscriber<CommonMessageBean<T>> {

    public static int UNKNOWN_CODE = -1;
    private ApiCallBack apiCallback;

    public ApiSubscriberCommonMessage(ApiCallBack apiCallback) {
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
    public void onNext(CommonMessageBean<T> httpBean) {
        if (httpBean.getStatus() == 0) {
            apiCallback.onSuccess(httpBean.getMessagelist());
            apiCallback.onSuccess(httpBean.getMessagelist(), httpBean.getMessagecount());


        } else {
            apiCallback.onFailure(-1, httpBean.getMsg());
        }
    }
}
