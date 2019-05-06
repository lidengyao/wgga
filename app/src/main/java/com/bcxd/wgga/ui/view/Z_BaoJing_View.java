package com.bcxd.wgga.ui.view;

import com.bcxd.wgga.base.BaseMvpView;
import com.bcxd.wgga.model.info.BaoJingInfo;

/**
 * Created by jinxh on 16/6/15.
 */
public interface Z_BaoJing_View extends BaseMvpView {
    void alertListbyuidandpageSuccess(BaoJingInfo model);

    void refreshtokenSuccess(String model);

    void onFailure(int code, String msg);

}
