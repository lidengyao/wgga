package com.bcxd.wgga.ui.view;

import com.bcxd.wgga.base.BaseMvpView;

/**
 * Created by jinxh on 16/6/15.
 */
public interface Z_ChangePwd_View extends BaseMvpView {

    void changepwdSucess(String model);

    void refreshtokenSuccess(String model);

    void onFailure(int code, String msg);
}
