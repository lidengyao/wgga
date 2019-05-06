package com.bcxd.wgga.present;

import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.model.bean.LoginBean;
import com.bcxd.wgga.ui.view.GetWebDataBodyView;

/**
 * Created by lidengyao on 2016-09-30 0030.
 */
public class GetWebDataBodyPresent extends BasePresent<GetWebDataBodyView> {

    public void login(final String mobileNo, String password) {

        LoginBean loginBean = new LoginBean();
//        loginBean.setPhone(mobileNo);
//        loginBean.setPwd(password);
//        loginBean.setSystype("APP");
//        loginBean.setIsAutoLogin("1");

//        Observable<ResponseBean<UserInfo>> observable = RetrofitClient.builderRetrofit().create(APIService.class).login(loginBean);

        // 此处请求接口
//        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<UserInfo>() {
//            @Override
//            public void onStart() {
//                super.onStart();
//                if (getView() != null) {
//                    getView().showLoading();
//                }
//            }
//
//            @Override
//            public void onSuccess(UserInfo model) {
//                if (getView() != null) {
//                    getView().loginSuccess(model);
//                }
//            }
//
//            @Override
//            public void onFailure(int code, String msg) {
//                if (getView() != null) {
//                    getView().showMessage(msg);
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
    }

}

