package com.bcxd.wgga.ui.view;

import com.bcxd.wgga.base.BaseMvpView;
import com.bcxd.wgga.model.info.UpLoadInfo;
import com.bcxd.wgga.model.info.VersionInfo;

/**
 * Created by jinxh on 16/6/15.
 */
public interface Z_Wo_View extends BaseMvpView {
    void logoutSuccess(String model);

    void uploadSuccess(UpLoadInfo model);

    void updateSucess(String model);

    void updateHeadImg(String model);

    void checkVersionSuccess(VersionInfo model);

    void refreshtokenSuccess(String model);

    void onFailure(int code, String msg);
}
