package com.bcxd.wgga.present;

import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.ui.view.BtnUpLoadFileView;

/**
 * Created by lidengyao on 2016-10-11 0011.
 */
public class BtnUpLoadFilePresent extends BasePresent<BtnUpLoadFileView> {

//    public void uploadimg(int type, String id, File file) {
//
//        RequestBody requestBody = MultipartBody.create(MediaType.parse("image/png"), file);
//
//        Map<String, RequestBody> map = new HashMap<>();
//        map.put("path\"; filename=\"" + file.getName() + "", requestBody);
//        Observable<UploadfileBean> observable = RetrofitClient.builderRetrofit().create(APIService.class).uploadImage(map, type, id);
//        addIOSubscription(observable, new ApiSubscriberUpLoadImageInfo(new ApiCallBack<UploadfileBean>() {
//            @Override
//            public void onStart() {
//                super.onStart();
//                if (getView() != null) {
//                    getView().showLoading();
//                }
//            }
//
//            @Override
//            public void onSuccess(UploadfileBean model) {
//                if (getView() != null) {
//                    getView().uploadfileSuccess(model);
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
//    }
}
