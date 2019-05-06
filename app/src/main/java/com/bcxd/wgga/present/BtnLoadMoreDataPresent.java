package com.bcxd.wgga.present;

import android.content.Context;

import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.model.api.APIService;
import com.bcxd.wgga.model.api.ApiCallBack;
import com.bcxd.wgga.model.api.ApiSubscriberCommonMessage;
import com.bcxd.wgga.model.api.RetrofitClient;
import com.bcxd.wgga.model.bean.CommonMessageBean;
import com.bcxd.wgga.model.info.CommonMessageInfo;
import com.bcxd.wgga.ui.view.BtnLoadMoreDataView;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by lidengyao on 2016-10-10 0010.
 */
public class BtnLoadMoreDataPresent  extends BasePresent<BtnLoadMoreDataView> {
    public void commonMessage(final String userId, String messageMode, String index, String pageSize, Context context) {
        Observable<CommonMessageBean<ArrayList<CommonMessageInfo>>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).commonMessage(userId, messageMode, index, pageSize);
        addIOSubscription(observable, new ApiSubscriberCommonMessage(new ApiCallBack<ArrayList<CommonMessageInfo>>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
//                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(ArrayList<CommonMessageInfo> model) {
                if (getView() != null) {
                    getView().Success(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().showMessage(msg);
                }
            }

            @Override
            public void onCompleted() {
                if (getView() != null) {
                    getView().dismissLoading();
                }
            }
        }));
    }
}
