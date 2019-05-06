package com.bcxd.wgga.ui.view;

import com.bcxd.wgga.base.BaseMvpView;
import com.bcxd.wgga.model.info.GongDiInfo;
import com.bcxd.wgga.model.info.ProjectInfo;
import com.bcxd.wgga.model.info.VersionInfo;
import com.bcxd.wgga.model.info.Z_UserInfo;

import java.util.ArrayList;

/**
 * Created by jinxh on 16/6/15.
 */
public interface Z_MainSecView extends BaseMvpView {

    void projectlistSuccess(ArrayList<ProjectInfo> model);

    void getuserinfoSuccess(Z_UserInfo model);

    void listSuccess(ArrayList<GongDiInfo> model);

    void checkVersionSuccess(VersionInfo model);

    void refreshtokenSuccess(String model);

    void onFailure(int code, String msg);
}
