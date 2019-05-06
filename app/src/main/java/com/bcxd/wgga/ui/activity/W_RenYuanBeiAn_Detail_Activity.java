package com.bcxd.wgga.ui.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bcxd.wgga.AppContext;
import com.bcxd.wgga.R;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.info.ToxicCompanyMemberInfo;
import com.bcxd.wgga.present.W_RenYuanBeiAn_DetailPresent;
import com.bcxd.wgga.ui.view.W_RenYuanBeiAn_DetailView;
import com.bcxd.wgga.utils.AnalyzeNormalXml;
import com.bcxd.wgga.utils.ObjectControl;
import com.bcxd.wgga.widget.Normal;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class W_RenYuanBeiAn_Detail_Activity extends MvpActivity<W_RenYuanBeiAn_DetailPresent> implements W_RenYuanBeiAn_DetailView {

    @Bind(R.id.SysNameIV)
    TextView SysName;
    @Bind(R.id.SaveRL)
    RelativeLayout SaveRL;
    @Bind(R.id.RootLL)
    LinearLayout RootLL;

    @Override
    protected W_RenYuanBeiAn_DetailPresent createPresenter() {
        return new W_RenYuanBeiAn_DetailPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.w_activity_renyuanbeian_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);


        if (AppContext.offLine) {
            SetOffLineData();
        } else {
            int id = Integer.parseInt(getIntent().getStringExtra("id"));
            mPresenter.toxicCompanyMemberDetail(this, id);
        }


    }

    private void SetOffLineData() {
        ToxicCompanyMemberInfo toxicCompanyMemberInfo = new ToxicCompanyMemberInfo();
        toxicCompanyMemberInfo.setName("张三");
        toxicCompanyMemberInfo.setQualificationNumber("苏87899709879");
        toxicCompanyMemberInfo.setQualificationName("高级程序员");
        toxicCompanyMemberInfo.setQualificationIssuing("中国程序员协会");
        toxicCompanyMemberInfo.setComparisonResult(1);
        toxicCompanyMemberInfo.setAliasName("小张");
        toxicCompanyMemberInfo.setNativePlace("江苏无锡");
        toxicCompanyMemberInfo.setHouseholdAddress("江苏无锡");
        toxicCompanyMemberInfo.setLiveAddress("江苏无锡新安镇");
        toxicCompanyMemberInfo.setCertificateNumber("410185198610076013");
        toxicCompanyMemberInfo.setCompanyValue("北辰科技");
        toxicCompanyMemberInfo.setCulturalValue("本科");
        toxicCompanyMemberInfo.setGenderValue("男");
        toxicCompanyMemberInfo.setRaceTypeValue("汉族");
        toxicCompanyMemberInfo.setTel("15251435195");
        toxicCompanyMemberInfo.setNowDuty("程序员");
        toxicCompanyMemberInfo.setCreateTime("2019-09-09");
        toxicCompanyMemberInfo.setUpdateTime("2019-09-10");
        toxicCompanyMemberInfo.setStatus(0);
        SetData(toxicCompanyMemberInfo);
    }

    private void SetData(ToxicCompanyMemberInfo model) {
        final ArrayList<Normal> tempNormalList = AnalyzeNormalXml.AnalyzeDetail(this, this, "renyuanbeian_detail", RootLL);
        if (model == null)
            return;
        HashMap<String, String> dic = new HashMap<>();
        dic = ObjectControl.ObjectToHashMap(model);

        ObjectControl.SetData(tempNormalList, dic);
    }

    @Override
    public void toxicCompanyMemberDetailSuccess(ToxicCompanyMemberInfo model) {
        SetData(model);
    }


    @Override
    public void onFailure(int code, String msg) {
        showMessage(msg);
    }

}
