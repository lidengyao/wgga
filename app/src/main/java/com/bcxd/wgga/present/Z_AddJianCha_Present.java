package com.bcxd.wgga.present;

import android.content.Context;

import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.model.api.APIService;
import com.bcxd.wgga.model.api.ApiCallBack;
import com.bcxd.wgga.model.api.ApiSubscriber;
import com.bcxd.wgga.model.api.RetrofitClient;
import com.bcxd.wgga.model.bean.JianChaBean;
import com.bcxd.wgga.model.bean.QuestionTypeBean;
import com.bcxd.wgga.model.bean.ResponseBean;
import com.bcxd.wgga.model.info.FuZeRenInfo;
import com.bcxd.wgga.model.info.GongDiInfo;
import com.bcxd.wgga.model.info.ProjectInfo;
import com.bcxd.wgga.model.info.TypeInfo;
import com.bcxd.wgga.model.info.UpLoadInfo;
import com.bcxd.wgga.ui.view.Z_AddJianCha_View;
import com.bcxd.wgga.utils.LogCode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by jinxh on 16/2/1.
 */
public class Z_AddJianCha_Present extends BasePresent<Z_AddJianCha_View> {

    public void listbycondition_question(QuestionTypeBean questionTypeBean, Context context) {
        Observable<ResponseBean<ArrayList<TypeInfo>>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).listbycondition_question(questionTypeBean);

        // 此处请求接口
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<ArrayList<TypeInfo>>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(ArrayList<TypeInfo> model) {
                if (getView() != null) {
                    getView().listbycondition_questionSuccess(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().onFailure(code, LogCode.GetCode("Z_AddJianCha_Present_listbycondition_question") + msg);
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

    public void humanduty(Integer pid, Context context) {
        Observable<ResponseBean<ArrayList<FuZeRenInfo>>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).humanduty(pid);

        // 此处请求接口
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<ArrayList<FuZeRenInfo>>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(ArrayList<FuZeRenInfo> model) {
                if (getView() != null) {
                    getView().humandutySuccess(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().onFailure(code, LogCode.GetCode("Z_AddJianCha_Present_humanduty") + msg);
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

    public void checkAdd(JianChaBean jianChaBean, Context context) {
        Observable<ResponseBean<String>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).checkAdd(jianChaBean);

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
                    getView().checkAddSuccess(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().onFailure(code, LogCode.GetCode("Z_AddJianCha_checkAdd") + msg);
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
                    getView().onFailure(code, LogCode.GetCode("Z_AddJianCha_upload") + msg);
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
                    getView().onFailure(code, LogCode.GetCode("Z_AddJianCha_projectlist") + msg);
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

    public void gongdibyprojectlist(Integer pid, Context context) {
        Observable<ResponseBean<ArrayList<GongDiInfo>>> observable = RetrofitClient.builderRetrofit(context).create(APIService.class).gongdibyprojectlist(pid);

        // 此处请求接口
        addIOSubscription(observable, new ApiSubscriber(new ApiCallBack<ArrayList<GongDiInfo>>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showLoading();
                }
            }

            @Override
            public void onSuccess(ArrayList<GongDiInfo> model) {
                if (getView() != null) {
                    getView().gongdibypidlistSuccess(model);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (getView() != null) {
                    getView().onFailure(code, LogCode.GetCode("Z_AddJianCha_gongdibyprojectlist") + msg);
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
                    getView().onFailure(code, LogCode.GetCode("Z_AddJianCha_refreshToken") + msg);
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
