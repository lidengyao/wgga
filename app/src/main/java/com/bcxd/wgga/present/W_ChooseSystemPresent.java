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
import com.bcxd.wgga.model.info.RuleIndexInfo;
import com.bcxd.wgga.model.info.Z_UserInfo;
import com.bcxd.wgga.ui.activity.Z_Login_Activity;
import com.bcxd.wgga.ui.view.W_ChooseSystemView;
import com.bcxd.wgga.ui.view.Z_LoginView;
import com.bcxd.wgga.utils.DbKeyS;
import com.bcxd.wgga.utils.LogCode;
import com.bcxd.wgga.utils.SpUtils;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by jinxh on 16/2/1.
 */
public class W_ChooseSystemPresent extends BasePresent<W_ChooseSystemView> {

    public void ruleIndex(Context context) {

        Observable<ResponseBean<ArrayList<RuleIndexInfo>>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).ruleIndex();

        // 此处请求接口
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<ArrayList<RuleIndexInfo>>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(ArrayList<RuleIndexInfo> model) {
                if (getView() != null) {
                    getView().ruleIndexSuccess(model);
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


}
