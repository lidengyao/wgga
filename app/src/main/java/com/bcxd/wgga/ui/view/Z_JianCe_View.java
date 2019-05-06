package com.bcxd.wgga.ui.view;

import com.bcxd.wgga.base.BaseMvpView;
import com.bcxd.wgga.model.info.RealtimeInfo;

import java.util.ArrayList;

/**
 * Created by jinxh on 16/6/15.
 */
public interface Z_JianCe_View extends BaseMvpView {
    void realtimebysiteidSuccess(ArrayList<RealtimeInfo> model);

    void refreshtokenSuccess(String model);

    void onFailure(int code, String msg);
}
