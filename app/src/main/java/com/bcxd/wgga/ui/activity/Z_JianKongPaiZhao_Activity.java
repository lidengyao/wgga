package com.bcxd.wgga.ui.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bcxd.wgga.R;
import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.base.MvpActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Z_JianKongPaiZhao_Activity extends MvpActivity {
    @Bind(R.id.civOne)
    ImageView civOne;
    @Bind(R.id.wentiSpinner)
    Spinner wentiSpinner;
    @Bind(R.id.zerenrenSpinner)
    Spinner zerenrenSpinner;
    @Bind(R.id.BtnTiJiao)
    Button BtnTiJiao;

    @Override
    protected BasePresent createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.z_activity_jiankongpaizhao;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);


//        String imgUrlOne = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1543220306731&di=7443e3d7ff14441190dac1c5b84ea71a&imgtype=0&src=http%3A%2F%2Fimg.tpy888.cn%2Fupload%2F201804%2F05%2F17-44-55-83-158630.jpg";
//        Glide.with(this).load(imgUrlOne).centerCrop().transform(new GlideRoundTransform(this, 5)).into(civOne);


        String[] zerenren = new String[]{"安全部-李志安", "环保部-王权", "服务部-刘翔"};//将province中内容添加到数组city中
        ArrayAdapter<String> zerenrenadapter = new ArrayAdapter<String>(this, R.layout.z_myspinner, zerenren);
        zerenrenSpinner.setAdapter(zerenrenadapter);

        String[] wenti = new String[]{"施工安全", "墙体开裂", "噪声污染"};//将province中内容添加到数组city中
        ArrayAdapter<String> wentiadapter = new ArrayAdapter<String>(this, R.layout.z_myspinner, wenti);
        wentiSpinner.setAdapter(wentiadapter);

    }
}
