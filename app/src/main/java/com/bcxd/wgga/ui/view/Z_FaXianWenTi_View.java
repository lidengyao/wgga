package com.bcxd.wgga.ui.view;

import com.bcxd.wgga.base.BaseMvpView;
import com.bcxd.wgga.model.info.JianChaInfo;

/**
 * Created by jinxh on 16/6/15.
 */
public interface Z_FaXianWenTi_View extends BaseMvpView {
    void searchbystatusSuccess(JianChaInfo model);

    void refreshtokenSuccess(String model);

    void onFailure(int code, String msg);
}
