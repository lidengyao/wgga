package com.bcxd.wgga.ui.view;

import com.bcxd.wgga.base.BaseMvpView;
import com.bcxd.wgga.model.info.FuZeRenInfo;
import com.bcxd.wgga.model.info.GongDiInfo;
import com.bcxd.wgga.model.info.ProjectInfo;
import com.bcxd.wgga.model.info.TypeInfo;
import com.bcxd.wgga.model.info.UpLoadInfo;

import java.util.ArrayList;

/**
 * Created by jinxh on 16/6/15.
 */
public interface Z_AddJianCha_View extends BaseMvpView {
    void listbycondition_questionSuccess(ArrayList<TypeInfo> model);

    void humandutySuccess(ArrayList<FuZeRenInfo> model);

    void checkAddSuccess(String model);

    void uploadSuccess(UpLoadInfo model);

    void projectlistSuccess(ArrayList<ProjectInfo> model);

    void gongdibypidlistSuccess(ArrayList<GongDiInfo> model);

    void refreshtokenSuccess(String model);

    void onFailure(int code, String msg);
}
