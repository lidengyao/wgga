package com.bcxd.wgga.adapter;

import android.content.Context;

import com.bcxd.wgga.R;
import com.bcxd.wgga.model.info.GongDiInfo;

import java.util.List;

/**
 * Created by lidengyao on 2016-09-01 0001.
 */
public class GongDiAdapter extends CommonAdapter<GongDiInfo> {

    public GongDiAdapter(Context context, List<GongDiInfo> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
    }

    @Override
    public void convert(int position, ViewHolder helper, GongDiInfo item) {
        helper.setText(R.id.GongdiNameTV, item.getSname());
//        helper.setText(R.id.itemxiaoxidateTV, item.getMessageTime());
//
//        if (item.getMessageType() == 1) {
//            helper.setVisibility(R.id.itemxiaoxistatusIV, View.VISIBLE);
//        }
//        else {
//            helper.setVisibility(R.id.itemxiaoxistatusIV, View.GONE);
//        }
//
//        TextView txt = helper.getView(R.id.itemxiaoxiTV);
//        if (item.getMessageStatus() == 0) {
//            txt.setTextColor(mContext.getResources().getColor(R.color.color15));
//        } else {
//            txt.setTextColor(mContext.getResources().getColor(R.color.color12));
//        }

//        helper.getView(R.id.itemxiaoxiRL).setTag(item);
//        helper.setOnClickListener(R.id.itemxiaoxiRL, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CommonMessageInfo temp = (CommonMessageInfo) v.getTag();
//
////                Intent intent = new Intent(mContext, Ldy_XiaoXiXiangQing_Activity.class);
////                intent.putExtra("messageId", temp.getMessageId());
////                mContext.startActivity(intent);
//            }
//        });
    }
}
