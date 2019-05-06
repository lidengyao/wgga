package com.bcxd.wgga.ui.view;

import com.bcxd.wgga.base.BaseMvpView;
import com.bcxd.wgga.model.info.ToxicCompanyMemberInfo;

import java.util.ArrayList;

/**
 * Created by jinxh on 16/6/15.
 */
public interface W_RenYuanBeiAnView extends BaseMvpView {
    void toxicCompanyMemberSuccess(ArrayList<ToxicCompanyMemberInfo> model);

    void onFailure(int code, String msg);


}
