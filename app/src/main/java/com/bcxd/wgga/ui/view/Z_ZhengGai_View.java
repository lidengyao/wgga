package com.bcxd.wgga.ui.view;

import com.bcxd.wgga.base.BaseMvpView;
import com.bcxd.wgga.model.info.ZhengGaiInfo;

/**
 * Created by jinxh on 16/6/15.
 */
public interface Z_ZhengGai_View extends BaseMvpView {
    void querypagebyconditionSuccess(ZhengGaiInfo model);

    void refreshtokenSuccess(String model);

    void onFailure(int code, String msg);

}
