package com.bcxd.wgga.adapter;

import android.content.Context;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.model.info.ToxicCompanyMemberInfo;
import com.bcxd.wgga.model.info.XiaoXiInfo;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by lidengyao on 2016-09-01 0001.
 */
public class W_RenYuanBeiAnAdapter extends CommonAdapter<ToxicCompanyMemberInfo> {

    private Context _context;

    public W_RenYuanBeiAnAdapter(Context context, List<ToxicCompanyMemberInfo> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
        _context = context;
    }

    @Override
    public void convert(int position, ViewHolder helper, ToxicCompanyMemberInfo item) {


        TextView textView = helper.getView(R.id.nameTV);

        helper.setText(R.id.nameTV, item.getName());
//        helper.setText(R.id.certificateNumberTV, item.getCertificateNumber());
//        helper.setText(R.id.companyValueTV, item.getCompanyValue());
//        helper.setText(R.id.culturalValueTV, item.getCulturalValue());
//        helper.setText(R.id.genderValueTV, item.getGenderValue());
//        helper.setText(R.id.raceTypeValueTV, item.getRaceTypeValue());
//        helper.setText(R.id.telTV, item.getTel());
//
//        helper.setText(R.id.nowDutyTV, item.getNowDuty());
//
//        helper.setText(R.id.createTimeTV, item.getCreateTime());
//        helper.setText(R.id.updateTimeTV, item.getUpdateTime());
//        helper.setText(R.id.statusTV, item.getStatus() + "");


    }

}
