package com.bcxd.wgga.ui.view;

import com.bcxd.wgga.base.BaseMvpView;
import com.bcxd.wgga.model.bean.LoginInfo;
import com.bcxd.wgga.model.bean.W_LoginInfo;
import com.bcxd.wgga.model.info.GongDiInfo;
import com.bcxd.wgga.model.info.Z_UserInfo;

import java.util.ArrayList;

/**
 * Created by jinxh on 16/6/15.
 */
public interface W_LoginView extends BaseMvpView {
    void loginSuccess(W_LoginInfo model);

    void onFailure(int code, String msg);


}
