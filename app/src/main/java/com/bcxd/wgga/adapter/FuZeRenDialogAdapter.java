package com.bcxd.wgga.adapter;

import android.content.Context;

import com.bcxd.wgga.R;
import com.bcxd.wgga.model.info.FuZeRenInfo;

import java.util.List;

/**
 * Created by lidengyao on 2016-09-01 0001.
 */
public class FuZeRenDialogAdapter extends CommonAdapter<FuZeRenInfo> {

    public FuZeRenDialogAdapter(Context context, List<FuZeRenInfo> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
    }

    @Override
    public void convert(int position, ViewHolder helper, FuZeRenInfo item) {
        helper.setText(R.id.ContentTV, item.getUsername());
    }
}
