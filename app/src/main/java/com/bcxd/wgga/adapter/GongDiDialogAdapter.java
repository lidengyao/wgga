package com.bcxd.wgga.adapter;

import android.content.Context;

import com.bcxd.wgga.R;
import com.bcxd.wgga.model.info.GongDiInfo;

import java.util.List;

/**
 * Created by lidengyao on 2016-09-01 0001.
 */
public class GongDiDialogAdapter extends CommonAdapter<GongDiInfo> {

    public GongDiDialogAdapter(Context context, List<GongDiInfo> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
    }

    @Override
    public void convert(int position, ViewHolder helper, GongDiInfo item) {
        helper.setText(R.id.ContentTV, item.getSname());
    }
}
