package com.bcxd.wgga.adapter;

import android.content.Context;

import com.bcxd.wgga.R;

import java.util.List;

/**
 * Created by lidengyao on 2016-09-01 0001.
 */
public class DataAdapter extends CommonAdapter<String> {

    public DataAdapter(Context context, List<String> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
    }

    @Override
    public void convert(int position, ViewHolder helper, String item) {
        helper.setText(R.id.ContentTV, item);


    }
}
