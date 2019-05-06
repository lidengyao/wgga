package com.bcxd.wgga.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.model.info.JianChaInfo;
import com.bcxd.wgga.utils.QuestionType;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by lidengyao on 2016-09-01 0001.
 */
public class JianChaAdapter extends CommonAdapter<JianChaInfo.RecordsBean> {
    protected Context mContext;

    public JianChaAdapter(Context context, List<JianChaInfo.RecordsBean> data, int itemLayoutId) {
        super(context, data, itemLayoutId);
        mContext = context;
    }

    @Override
    public void convert(int position, ViewHolder helper, JianChaInfo.RecordsBean item) {
        helper.setText(R.id.JianChaDesTV, item.getDesc());
        helper.setText(R.id.JianChaDateTV, item.getCreatetime());
        TextView jianchaTypeTV = helper.getView(R.id.JianChaTypeTV);
        jianchaTypeTV.setText(QuestionType.getTypeValue(item.getStatus()));
        if (item.getStatus() == 0) {
            jianchaTypeTV.setTextColor(mContext.getResources().getColor(R.color.color26));
        }
        if (item.getStatus() == 1) {
            jianchaTypeTV.setTextColor(mContext.getResources().getColor(R.color.color21));
        }
        ImageView ItemJianChaIV = helper.getView(R.id.ItemJianChaIV);
        String picurl = "";
        if (item.getPiclist().size() > 0) {
            JianChaInfo.RecordsBean.PiclistBean piclistBean = item.getPiclist().get(0);
            picurl = piclistBean.getUrl();

        }

        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.logo)//图片加载出来前，显示的图片
                .fallback(R.mipmap.logo) //url为空的时候,显示的图片
                .error(R.mipmap.logo);//图片加载失败后，显示的图片
        Glide.with(mContext).load(picurl).apply(options).apply(RequestOptions.bitmapTransform(new RoundedCorners(6))).into(ItemJianChaIV);


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
