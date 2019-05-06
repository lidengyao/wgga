package com.bcxd.wgga.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bcxd.wgga.R;
import com.bcxd.wgga.model.info.JiankongInfo;
import com.bcxd.wgga.ui.activity.Z_Daping_Activity;
import com.bcxd.wgga.ui.activity.Z_Jiance_Activity;

import java.util.List;

/**
 * Created by lidengyao on 2016-09-01 0001.
 */
public class JianKongAdapter extends CommonAdapter<JiankongInfo> {

    public JianKongAdapter(Context context, List<JiankongInfo> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
    }

    @Override
    public void convert(int position, ViewHolder helper, JiankongInfo item) {
//        helper.setText(R.id.itemxiaoxiTV, item.getMessageDesc());
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

//        helper.getView(R.id.jiankong_kongzhi_itemIV).setTag(item);
        helper.setOnClickListener(R.id.jiankong_jiance_itemLL, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CommonMessageInfo temp = (CommonMessageInfo) v.getTag();

                Intent intent = new Intent(mContext, Z_Jiance_Activity.class);
//                intent.putExtra("messageId", temp.getMessageId());
                mContext.startActivity(intent);
            }
        });

        helper.setOnClickListener(R.id.jiankong_kongzhi_itemLL, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CommonMessageInfo temp = (CommonMessageInfo) v.getTag();

                Intent intent = new Intent(mContext, Z_Daping_Activity.class);
//                intent.putExtra("messageId", temp.getMessageId());
                mContext.startActivity(intent);
            }
        });
    }
}
