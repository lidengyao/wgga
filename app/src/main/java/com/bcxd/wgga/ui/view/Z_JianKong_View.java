package com.bcxd.wgga.ui.view;

import com.bcxd.wgga.base.BaseMvpView;
import com.bcxd.wgga.model.info.GongDiInfo;

import java.util.ArrayList;

/**
 * Created by jinxh on 16/6/15.
 */
public interface Z_JianKong_View extends BaseMvpView {
    void siteListSuccess(ArrayList<GongDiInfo> model);
}
