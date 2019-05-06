package com.bcxd.wgga.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.model.info.RealtimeInfo;

import java.util.List;

/**
 * Created by lidengyao on 2016-09-01 0001.
 */
public class JianCeAdapter extends CommonAdapter<RealtimeInfo> {

    public JianCeAdapter(Context context, List<RealtimeInfo> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
    }

    @Override
    public void convert(int position, ViewHolder helper, RealtimeInfo item) {
        helper.setText(R.id.JianCeNameTV, item.getName());
        helper.setText(R.id.JianCeValueTV, item.getValue() + item.getUnit());
        helper.setText(R.id.JianCeTimeTV, item.getTime());
        helper.setText(R.id.ItemNameTV, item.getName());

        ImageView imageView = helper.getView(R.id.ItemtypeIV);
        String name = item.getName();
        if (name.equals("悬浮颗粒物指数")) {
            imageView.setImageResource(R.mipmap.keliwuicon);
        }
        if (name.equals("PM2.5")) {
            imageView.setImageResource(R.mipmap.pmerwuicon);
        }
        if (name.equals("PM10")) {
            imageView.setImageResource(R.mipmap.pmshiicon);
        }
        if (name.equals("噪声指数")) {
            imageView.setImageResource(R.mipmap.shengyin);
        }
        if (name.equals("温度")) {
            imageView.setImageResource(R.mipmap.wenduicon);
        }
        if (name.equals("湿度")) {
            imageView.setImageResource(R.mipmap.shiduicon);
        }
        if (name.equals("风速")) {
            imageView.setImageResource(R.mipmap.fengsuicon);
        }
        if (name.equals("空气质量指数")) {
            imageView.setImageResource(R.mipmap.kongqiicon);
        }
        if (name.equals("风向")) {
            imageView.setImageResource(R.mipmap.fengxiangicon);
        }
    }
}
