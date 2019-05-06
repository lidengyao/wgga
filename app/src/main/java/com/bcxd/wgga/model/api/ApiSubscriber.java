package com.bcxd.wgga.model.api;


import android.content.Context;

import com.bcxd.wgga.AppContext;
import com.bcxd.wgga.model.bean.ResponseBean;
import com.bcxd.wgga.utils.DbKeyS;
import com.bcxd.wgga.utils.SpUtils;

import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by jinxh on 16/5/30.
 */
public class ApiSubscriber<T> extends Subscriber<ResponseBean<T>> {

    public static int UNKNOWN_CODE = -1;
    private ApiCallBack apiCallback;

    private Context _context;
    public ApiSubscriber(ApiCallBack apiCallback, Context context) {
        this.apiCallback = apiCallback;
        _context=context;
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
    public void onNext(ResponseBean<T> httpBean) {
        if (httpBean.getCode() == 0) {
//            AppContext.dbHelper.SetData(DbKeyS.token, "1");
//            SpUtils.saveSettingNote(_context, DbKeyS.token, "1");
            apiCallback.onSuccess(httpBean.getBody());
        } else {
            if (httpBean.getCode() == 499) {
//                AppContext.dbHelper.SetData(DbKeyS.token, "-1");
//                SpUtils.saveSettingNote(_context, DbKeyS.token, "-1");
            }
            apiCallback.onFailure(httpBean.getCode(), httpBean.getMsg());
        }
    }
}
