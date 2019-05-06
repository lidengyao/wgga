package com.bcxd.wgga.ui.view;

import com.bcxd.wgga.base.BaseMvpView;
import com.bcxd.wgga.model.info.ZhengGaiInfoDetail;

/**
 * Created by jinxh on 16/6/15.
 */
public interface Z_ZhengGaiChuLi_View extends BaseMvpView {
    void zhenggaiUpdateSuccess(String model);

    void zhenggaiquerybyzidSuccess(ZhengGaiInfoDetail model);

    void refreshtokenSuccess(String model);

    void onFailure(int code, String msg);
}
