package com.bcxd.wgga.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bcxd.wgga.R;
import com.bcxd.wgga.model.info.XiaoXiInfo;
import com.bcxd.wgga.ui.activity.Z_BaojingChuLi_Activity;
import com.bcxd.wgga.ui.activity.Z_ChuLiJianCha_Activity;
import com.bcxd.wgga.ui.activity.Z_ZhengGaiChuLi_Activity;

import java.util.List;

/**
 * Created by lidengyao on 2016-09-01 0001.
 */
public class W_XiaoXiAdapter extends CommonAdapter<XiaoXiInfo.RecordsBean> {

    private Context _context;

    public W_XiaoXiAdapter(Context context, List<XiaoXiInfo.RecordsBean> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
        _context = context;
    }

    @Override
    public void convert(int position, ViewHolder helper, XiaoXiInfo.RecordsBean item) {
    }
}
