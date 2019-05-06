package com.bcxd.wgga.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.model.info.CommonMessageInfo;

import java.util.List;

/**
 * Created by lidengyao on 2016-09-01 0001.
 */
public class CommonMessageAdapter extends CommonAdapter<CommonMessageInfo> {

    public CommonMessageAdapter(Context context, List<CommonMessageInfo> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
    }

    @Override
    public void convert(int position, ViewHolder helper, CommonMessageInfo item) {
        helper.setText(R.id.itemxiaoxiTV, item.getMessageDesc());
        helper.setText(R.id.itemxiaoxidateTV, item.getMessageTime());

        if (item.getMessageType() == 1) {
            helper.setVisibility(R.id.itemxiaoxistatusIV, View.VISIBLE);
        }
        else {
            helper.setVisibility(R.id.itemxiaoxistatusIV, View.GONE);
        }

        TextView txt = helper.getView(R.id.itemxiaoxiTV);
        if (item.getMessageStatus() == 0) {
            txt.setTextColor(mContext.getResources().getColor(R.color.color15));
        } else {
            txt.setTextColor(mContext.getResources().getColor(R.color.color12));
        }

        helper.getView(R.id.itemxiaoxiRL).setTag(item);
        helper.setOnClickListener(R.id.itemxiaoxiRL, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonMessageInfo temp = (CommonMessageInfo) v.getTag();

//                Intent intent = new Intent(mContext, Ldy_XiaoXiXiangQing_Activity.class);
//                intent.putExtra("messageId", temp.getMessageId());
//                mContext.startActivity(intent);
            }
        });
    }
}
