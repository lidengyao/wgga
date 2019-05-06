package com.bcxd.wgga.ui.view;

import com.bcxd.wgga.base.BaseMvpView;
import com.bcxd.wgga.model.info.ToxicCompanyMemberInfo;

/**
 * Created by jinxh on 16/6/15.
 */
public interface W_RenYuanBeiAn_DetailView extends BaseMvpView {
    void toxicCompanyMemberDetailSuccess(ToxicCompanyMemberInfo model);

    void onFailure(int code, String msg);


}
