package com.bcxd.wgga.adapter;

import android.content.Context;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.model.info.BaoJingInfo;

import java.util.List;

/**
 * Created by lidengyao on 2016-09-01 0001.
 */
public class BaoJingAdapter extends CommonAdapter<BaoJingInfo.RecordsBean> {
    protected Context mContext;

    public BaoJingAdapter(Context context, List<BaoJingInfo.RecordsBean> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
        this.mContext = context;
    }

    @Override
    public void convert(int position, ViewHolder helper, BaoJingInfo.RecordsBean item) {
        helper.setText(R.id.TitleTV, item.getDevicesn() + item.getRuledescription());
        helper.setText(R.id.CreateTimeTV, "发生时间：" + item.getCreatetime());
        String DealTime = item.getProcesstime() == "" ? "--" : item.getProcesstime();
        helper.setText(R.id.YiChuLiDealTimeTV, "处理时间：" + DealTime);

        TextView statusTV = helper.getView(R.id.StatusTV);
        if (item.getStatus() == 0) {
            statusTV.setText("未处理");
            statusTV.setTextColor(mContext.getResources().getColor(R.color.color26));
//            helper.getView(R.id.jianTouIV).setVisibility(View.VISIBLE);
        }
        if (item.getStatus() == 1) {
            statusTV.setText("已处理");
            statusTV.setTextColor(mContext.getResources().getColor(R.color.color14));
//            helper.getView(R.id.jianTouIV).setVisibility(View.GONE);
        }
    }
}
