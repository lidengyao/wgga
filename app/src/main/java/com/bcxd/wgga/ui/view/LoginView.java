package com.bcxd.wgga.ui.view;

import com.bcxd.wgga.base.BaseMvpView;
import com.bcxd.wgga.model.info.PhoneLoginInfo;

/**
 * Created by jinxh on 16/6/15.
 */
public interface LoginView extends BaseMvpView{
    void loginSuccess(PhoneLoginInfo model);
}
