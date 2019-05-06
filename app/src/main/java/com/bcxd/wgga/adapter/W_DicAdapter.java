package com.bcxd.wgga.adapter;

import android.content.Context;

import com.bcxd.wgga.R;
import com.bcxd.wgga.model.info.XiaoXiInfo;
import com.bcxd.wgga.widget.DicData;

import java.util.List;

/**
 * Created by lidengyao on 2016-09-01 0001.
 */
public class W_DicAdapter extends CommonAdapter<DicData> {

    private Context _context;

    public W_DicAdapter(Context context, List<DicData> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
        _context = context;
    }

    @Override
    public void convert(int position, ViewHolder helper, DicData item) {
        helper.setText(R.id.dicTV, item.getValue());
    }
}
