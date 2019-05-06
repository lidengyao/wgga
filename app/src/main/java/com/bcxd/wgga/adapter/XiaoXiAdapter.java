package com.bcxd.wgga.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bcxd.wgga.R;
import com.bcxd.wgga.model.info.XiaoXiInfo;
import com.bcxd.wgga.ui.activity.Z_BaojingChuLi_Activity;
import com.bcxd.wgga.ui.activity.Z_ChuLiJianCha_Activity;
import com.bcxd.wgga.ui.activity.Z_ZhengGaiChuLi_Activity;

import java.util.List;

/**
 * Created by lidengyao on 2016-09-01 0001.
 */
public class XiaoXiAdapter extends CommonAdapter<XiaoXiInfo.RecordsBean> {

    private Context _context;

    public XiaoXiAdapter(Context context, List<XiaoXiInfo.RecordsBean> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
        _context = context;
    }

    @Override
    public void convert(int position, ViewHolder helper, XiaoXiInfo.RecordsBean item) {
        helper.setText(R.id.MsgContentTV, item.getMdesc());
        helper.setText(R.id.MsgDateTV, item.getCreatetime());
        int mtype = item.getMtype();
        int bid = item.getBid();
        if (mtype == 1 || mtype == 2 || mtype == 3) {
            helper.setVisibility(R.id.XiaoxiJianTouIV, View.VISIBLE);
        } else {
            helper.setVisibility(R.id.XiaoxiJianTouIV, View.GONE);
        }
        if (mtype == 1) {
            helper.setText(R.id.TypeTV, "发现问题");
        }
        //整改通知
        else if (mtype == 2) {
            helper.setText(R.id.TypeTV, "整改通知");
        }
        //报警通知
        else if (mtype == 3) {
            helper.setText(R.id.TypeTV, "报警通知");
        } else {
            helper.setText(R.id.TypeTV, "普通消息");
        }

        helper.getView(R.id.itempengRL).setTag(item);
        helper.setOnClickListener(R.id.itempengRL, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XiaoXiInfo.RecordsBean temp = (XiaoXiInfo.RecordsBean) v.getTag();
                int mtype = temp.getMtype();
                int bid = temp.getBid();
                //检查通知
                if (mtype == 1) {
                    Intent i = new Intent(_context, Z_ChuLiJianCha_Activity.class);
                    i.putExtra("btype", mtype + "");
                    i.putExtra("bid", bid + "");
                    //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    _context.startActivity(i);
                }

                //整改通知
                if (mtype == 2) {
                    Intent i = new Intent(_context, Z_ZhengGaiChuLi_Activity.class);
                    i.putExtra("btype", mtype + "");
                    i.putExtra("bid", bid + "");
                    //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    _context.startActivity(i);
                }

                //报警通知
                if (mtype == 3) {
                    Intent i = new Intent(_context, Z_BaojingChuLi_Activity.class);
                    i.putExtra("btype", mtype + "");
                    i.putExtra("bid", bid + "");
                    //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    _context.startActivity(i);
                }
            }
        });
    }
}
