package com.bcxd.wgga.ui.view;

import com.bcxd.wgga.base.BaseMvpView;
import com.bcxd.wgga.model.info.CommonMessageInfo;

import java.util.ArrayList;

/**
 * Created by lidengyao on 2016-10-10 0010.
 */
public interface BtnLoadMoreDataView  extends BaseMvpView {
    void Success(ArrayList<CommonMessageInfo> model);
}
