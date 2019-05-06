package com.bcxd.wgga.ui.view;

import com.bcxd.wgga.base.BaseMvpView;
import com.bcxd.wgga.model.bean.UserInfo;

/**
 * Created by lidengyao on 2016-09-30 0030.
 */
public interface GetWebDataBodyView extends BaseMvpView {
    void loginSuccess(UserInfo model);
}
