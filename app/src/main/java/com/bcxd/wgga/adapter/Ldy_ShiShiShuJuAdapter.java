package com.bcxd.wgga.adapter;

import android.app.Activity;
import android.content.Context;

import com.bcxd.wgga.R;
import com.bcxd.wgga.model.info.PengStatusInfo;

import java.util.List;


/**
 * Created by lidengyao on 2016-08-26 0026.
 */
public class Ldy_ShiShiShuJuAdapter extends CommonAdapter<PengStatusInfo> {

    private Activity _activity;
    List<PengStatusInfo> _data;

    public Ldy_ShiShiShuJuAdapter(Context context, List<PengStatusInfo> data, int itemLayoutId, Activity activity) {
        super(context, data, itemLayoutId);
        _activity = activity;
        _data = data;
    }

    public void remove(int arg0) {//删除指定位置的item
        _data.remove(arg0);
        this.notifyDataSetChanged();//不要忘记更改适配器对象的数据源
    }

    public void insert(PengStatusInfo item, int arg0) {
        _data.add(arg0, item);
        this.notifyDataSetChanged();
    }

    @Override
    public void convert(int position, ViewHolder helper, PengStatusInfo item) {
        helper.setText(R.id.itemchuanganqisensorNameIV, item.getIndex() + "");

    }

}
