package com.bcxd.wgga.ui.view;

import com.bcxd.wgga.base.BaseMvpView;
import com.bcxd.wgga.model.info.XiaoXiInfo;

/**
 * Created by jinxh on 16/6/15.
 */
public interface Z_XiaoXi_View extends BaseMvpView {
    void listSuccess(XiaoXiInfo model);

    void listBySidSuccess(XiaoXiInfo model);

    void refreshtokenSuccess(String model);

    void onFailure(int code, String msg);

}
