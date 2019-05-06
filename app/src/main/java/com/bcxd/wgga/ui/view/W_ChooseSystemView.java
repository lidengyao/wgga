package com.bcxd.wgga.ui.view;

import com.bcxd.wgga.base.BaseMvpView;
import com.bcxd.wgga.model.bean.LoginInfo;
import com.bcxd.wgga.model.info.GongDiInfo;
import com.bcxd.wgga.model.info.RuleIndexInfo;
import com.bcxd.wgga.model.info.Z_UserInfo;

import java.util.ArrayList;

/**
 * Created by jinxh on 16/6/15.
 */
public interface W_ChooseSystemView extends BaseMvpView {
    void ruleIndexSuccess(ArrayList<RuleIndexInfo> model);


    void onFailure(int code, String msg);


}
