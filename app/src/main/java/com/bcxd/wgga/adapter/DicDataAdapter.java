package com.bcxd.wgga.adapter;

import android.content.Context;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.model.info.BaoJingInfo;
import com.bcxd.wgga.model.info.DicDataInfo;

import java.util.List;

/**
 * Created by lidengyao on 2016-09-01 0001.
 */
public class DicDataAdapter extends CommonAdapter<DicDataInfo> {
    protected Context mContext;

    public DicDataAdapter(Context context, List<DicDataInfo> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
        this.mContext = context;
    }

    @Override
    public void convert(int position, ViewHolder helper, DicDataInfo item) {
        helper.setText(R.id.dicTV, item.getLby_type_name());
//        helper.setText(R.id.CreateTimeTV, "发生时间：" + item.getCreatetime());
//        String DealTime = item.getProcesstime() == "" ? "--" : item.getProcesstime();
//        helper.setText(R.id.YiChuLiDealTimeTV, "处理时间：" + DealTime);

    }
}
