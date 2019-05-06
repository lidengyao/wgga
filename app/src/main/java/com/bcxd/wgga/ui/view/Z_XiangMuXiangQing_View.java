package com.bcxd.wgga.ui.view;

import com.bcxd.wgga.base.BaseMvpView;
import com.bcxd.wgga.model.info.ProjectDetailInfo;
import com.bcxd.wgga.model.info.apppicInfo;

/**
 * Created by jinxh on 16/6/15.
 */
public interface Z_XiangMuXiangQing_View extends BaseMvpView {
    void projectDetail(ProjectDetailInfo model);

    void apppicQuery(apppicInfo model);

    void refreshtokenSuccess(String model);

    void onFailure(int code, String msg);
}
