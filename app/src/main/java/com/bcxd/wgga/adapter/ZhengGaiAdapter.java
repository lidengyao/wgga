package com.bcxd.wgga.adapter;

import android.content.Context;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.model.info.ZhengGaiInfo;

import java.util.List;

/**
 * Created by lidengyao on 2016-09-01 0001.
 */
public class ZhengGaiAdapter extends CommonAdapter<ZhengGaiInfo.RecordsBean> {
    protected Context mContext;

    public ZhengGaiAdapter(Context context, List<ZhengGaiInfo.RecordsBean> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
        this.mContext = context;
    }

    @Override
    public void convert(int position, ViewHolder helper, ZhengGaiInfo.RecordsBean item) {
        helper.setText(R.id.TitleTV, item.getTitle());
        helper.setText(R.id.CreateTimeTV, "下发时间：" + item.getCreatetime());
        String DealTime = item.getDealtime() == "" ? "--" : item.getDealtime();
        helper.setText(R.id.YiChuLiDealTimeTV, "处理时间：" + DealTime);

        TextView statusTV = helper.getView(R.id.StatusTV);
        if (item.getStatus() == 0) {
            statusTV.setText("未处理");
            statusTV.setTextColor(mContext.getResources().getColor(R.color.color26));
        }
        if (item.getStatus() == 1) {
            statusTV.setText("已处理");
            statusTV.setTextColor(mContext.getResources().getColor(R.color.color14));
        }


    }
}
