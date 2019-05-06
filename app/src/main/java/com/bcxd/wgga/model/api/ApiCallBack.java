package com.bcxd.wgga.model.api;

/**
 * Created by jinxh on 16/5/30.
 */
public abstract class ApiCallBack<T> {
    public abstract void onSuccess(T model);
    public void onSuccess(T model, int  messagecount) {

    }

    public void onStart() {

    }
    public abstract void onFailure(int code, String msg);

    public void onCompleted() {

    }
}
