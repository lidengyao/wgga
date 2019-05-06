package com.bcxd.wgga.ui.view;

import com.bcxd.wgga.base.BaseMvpView;
import com.bcxd.wgga.model.info.CityenvironmentInfo;
import com.bcxd.wgga.model.info.ProjectInfo;
import com.bcxd.wgga.model.info.XiaoXiInfo;
import com.bcxd.wgga.model.info.apppicInfo;

import java.util.ArrayList;

/**
 * Created by jinxh on 16/6/15.
 */
public interface Z_ShouYe_View extends BaseMvpView {
    void projectlistSuccess(ArrayList<ProjectInfo> model);

    void listMsgSuccess(XiaoXiInfo model);

    void apppicQuery(apppicInfo model);

    void Cityenvironment(CityenvironmentInfo model);

    void refreshtokenSuccess(String model);

    void onFailure(int code, String msg);
}
