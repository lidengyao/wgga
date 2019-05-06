package com.bcxd.wgga.ui.view;

import com.bcxd.wgga.base.BaseMvpView;
import com.bcxd.wgga.model.bean.UploadfileBean;

/**
 * Created by lidengyao on 2016-10-11 0011.
 */
public interface BtnUpLoadFileView  extends BaseMvpView {

    void uploadfileSuccess(UploadfileBean model);
}
