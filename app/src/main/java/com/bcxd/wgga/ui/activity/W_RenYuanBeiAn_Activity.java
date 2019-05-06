package com.bcxd.wgga.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bcxd.wgga.AppContext;
import com.bcxd.wgga.R;
import com.bcxd.wgga.adapter.W_RenYuanBeiAnAdapter;
import com.bcxd.wgga.adapter.W_XiaoXiAdapter;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.info.ToxicCompanyMemberInfo;
import com.bcxd.wgga.model.info.XiaoXiInfo;
import com.bcxd.wgga.present.W_RenYuanBeiAnPresent;
import com.bcxd.wgga.ui.view.W_RenYuanBeiAnView;
import com.bcxd.wgga.utils.AnalyzeNormalXml;
import com.bcxd.wgga.utils.ObjectControl;
import com.bcxd.wgga.widget.Normal;
import com.bcxd.wgga.widget.PullLoadMoreListView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class W_RenYuanBeiAn_Activity extends MvpActivity<W_RenYuanBeiAnPresent> implements W_RenYuanBeiAnView {
    @Bind(R.id.SysNameIV)
    TextView SysName;
    @Bind(R.id.DataListView)
    PullLoadMoreListView dataListView;
    @Bind(R.id.SerchTV)
    TextView SerchTV;
    @Bind(R.id.title_bar)
    RelativeLayout titleBar;
    @Bind(R.id.fragment_layout)
    LinearLayout fragmentLayout;
    @Bind(R.id.ChildLL)
    LinearLayout ChildLL;
    @Bind(R.id.right)
    RelativeLayout right;
    @Bind(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    @Bind(R.id.ChongZhiOKLL)
    LinearLayout ChongZhiOKLL;
    @Bind(R.id.FilterLL)
    LinearLayout filterLL;
    @Bind(R.id.DicListView)
    PullLoadMoreListView DicListView;
    @Bind(R.id.AddRL)
    RelativeLayout AddRL;
    @Bind(R.id.ChongZhiLL)
    LinearLayout ChongZhiLL;
    @Bind(R.id.OKLL)
    LinearLayout OKLL;
    /**
     * 当前页码
     */
    private int pageIndex = 1;
    private int pageSize = 10;
    private ArrayList<XiaoXiInfo.RecordsBean> recordsBeanArrayList = new ArrayList<>();
    W_XiaoXiAdapter w_xiaoXiAdapter;

    @Override
    protected W_RenYuanBeiAnPresent createPresenter() {
        return new W_RenYuanBeiAnPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.w_activity_renyuanbeian;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        final ArrayList<Normal> normalArrayList = AnalyzeNormalXml.Analyze(this, this, "renyuanbeian_filter", filterLL, "0", ChildLL, DicListView, drawerlayout);

        if (AppContext.offLine) {
            SetOffLineData();
        } else {
            getData();
        }

        ChongZhiLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < normalArrayList.size(); i++) {
                    Normal normal = normalArrayList.get(i);
                    normal.getNormalEditText().setText("");
                }
            }
        });


        AddRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(W_RenYuanBeiAn_Activity.this, W_RenYuanBeiAn_Add_Activity.class);
                startActivity(mainIntent);
            }
        });
        SerchTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.openDrawer(Gravity.RIGHT);
            }
        });


        drawerlayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                right.setClickable(true);
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

    }

    private void getData() {
        mPresenter.toxicCompanyMember(this);
    }

    private void SetOffLineData() {
        ArrayList<ToxicCompanyMemberInfo> model = new ArrayList<>();


//        for (int i = 0; i < 5; i++) {
//            ToxicCompanyMemberInfo toxicCompanyMemberInfo = new ToxicCompanyMemberInfo();
//            toxicCompanyMemberInfo.setName("张三");
//            toxicCompanyMemberInfo.setQualificationNumber("苏87899709879");
//            toxicCompanyMemberInfo.setQualificationName("高级程序员");
//            toxicCompanyMemberInfo.setQualificationIssuing("中国程序员协会");
//            toxicCompanyMemberInfo.setComparisonResult(1);
//            toxicCompanyMemberInfo.setAliasName("小张");
//            toxicCompanyMemberInfo.setNativePlace("江苏无锡");
//            toxicCompanyMemberInfo.setHouseholdAddress("江苏无锡");
//            toxicCompanyMemberInfo.setLiveAddress("江苏无锡新安镇");
//            toxicCompanyMemberInfo.setCertificateNumber("410185198610076013");
//            toxicCompanyMemberInfo.setCompanyValue("北辰科技");
//            toxicCompanyMemberInfo.setCulturalValue("本科");
//            toxicCompanyMemberInfo.setGenderValue("男");
//            toxicCompanyMemberInfo.setRaceTypeValue("汉族");
//            toxicCompanyMemberInfo.setTel("15251435195");
//            toxicCompanyMemberInfo.setNowDuty("程序员");
//            toxicCompanyMemberInfo.setCreateTime("2019-09-09");
//            toxicCompanyMemberInfo.setUpdateTime("2019-09-10");
//            toxicCompanyMemberInfo.setStatus(0);
//            model.add(toxicCompanyMemberInfo);
//        }

//        ToxicCompanyMemberInfo toxicCompanyMemberInfo = new ToxicCompanyMemberInfo();
//        toxicCompanyMemberInfo.setName("张三");
//        toxicCompanyMemberInfo.setQualificationNumber("苏87899709879");
//        toxicCompanyMemberInfo.setQualificationName("高级程序员");
//        toxicCompanyMemberInfo.setQualificationIssuing("中国程序员协会");
//        toxicCompanyMemberInfo.setComparisonResult(1);
//        toxicCompanyMemberInfo.setAliasName("小张");
//        toxicCompanyMemberInfo.setNativePlace("江苏无锡");
//        toxicCompanyMemberInfo.setHouseholdAddress("江苏无锡");
//        toxicCompanyMemberInfo.setLiveAddress("江苏无锡新安镇");
//        toxicCompanyMemberInfo.setCertificateNumber("410185198610076013");
//        toxicCompanyMemberInfo.setCompanyValue("北辰科技");
//        toxicCompanyMemberInfo.setCulturalValue("本科");
//        toxicCompanyMemberInfo.setGenderValue("男");
//        toxicCompanyMemberInfo.setRaceTypeValue("汉族");
//        toxicCompanyMemberInfo.setTel("15251435195");
//        toxicCompanyMemberInfo.setNowDuty("程序员");
//        toxicCompanyMemberInfo.setCreateTime("2019-09-09");
//        toxicCompanyMemberInfo.setUpdateTime("2019-09-10");
//        toxicCompanyMemberInfo.setStatus(0);
//        model.add(toxicCompanyMemberInfo);

        ToxicCompanyMemberInfo toxicCompanyMemberInfo1 = new ToxicCompanyMemberInfo();
        toxicCompanyMemberInfo1.setName("张三1");
        toxicCompanyMemberInfo1.setQualificationNumber("苏87899709879");
        toxicCompanyMemberInfo1.setQualificationName("高级程序员");
        toxicCompanyMemberInfo1.setQualificationIssuing("中国程序员协会");
        toxicCompanyMemberInfo1.setComparisonResult(1);
        toxicCompanyMemberInfo1.setAliasName("小张");
        toxicCompanyMemberInfo1.setNativePlace("江苏无锡");
        toxicCompanyMemberInfo1.setHouseholdAddress("江苏无锡");
        toxicCompanyMemberInfo1.setLiveAddress("江苏无锡新安镇");
        toxicCompanyMemberInfo1.setCertificateNumber("410185198610076013");
        toxicCompanyMemberInfo1.setCompanyValue("北辰科技");
        toxicCompanyMemberInfo1.setCulturalValue("本科");
        toxicCompanyMemberInfo1.setGenderValue("男");
        toxicCompanyMemberInfo1.setRaceTypeValue("汉族");
        toxicCompanyMemberInfo1.setTel("15251435195");
        toxicCompanyMemberInfo1.setNowDuty("程序员");
        toxicCompanyMemberInfo1.setCreateTime("2019-09-09");
        toxicCompanyMemberInfo1.setUpdateTime("2019-09-10");
        toxicCompanyMemberInfo1.setStatus(0);
        model.add(toxicCompanyMemberInfo1);

        ToxicCompanyMemberInfo toxicCompanyMemberInfo2 = new ToxicCompanyMemberInfo();
        toxicCompanyMemberInfo2.setName("张三2");
        toxicCompanyMemberInfo2.setQualificationNumber("苏87899709879");
        toxicCompanyMemberInfo2.setQualificationName("高级程序员");
        toxicCompanyMemberInfo2.setQualificationIssuing("中国程序员协会");
        toxicCompanyMemberInfo2.setComparisonResult(1);
        toxicCompanyMemberInfo2.setAliasName("小张");
        toxicCompanyMemberInfo2.setNativePlace("江苏无锡");
        toxicCompanyMemberInfo2.setHouseholdAddress("江苏无锡");
        toxicCompanyMemberInfo2.setLiveAddress("江苏无锡新安镇");
        toxicCompanyMemberInfo2.setCertificateNumber("410185198610076013");
        toxicCompanyMemberInfo2.setCompanyValue("北辰科技");
        toxicCompanyMemberInfo2.setCulturalValue("本科");
        toxicCompanyMemberInfo2.setGenderValue("男");
        toxicCompanyMemberInfo2.setRaceTypeValue("汉族");
        toxicCompanyMemberInfo2.setTel("15251435195");
        toxicCompanyMemberInfo2.setNowDuty("程序员");
        toxicCompanyMemberInfo2.setCreateTime("2019-09-09");
        toxicCompanyMemberInfo2.setUpdateTime("2019-09-10");
        toxicCompanyMemberInfo2.setStatus(0);
        model.add(toxicCompanyMemberInfo2);

        ToxicCompanyMemberInfo toxicCompanyMemberInfo3 = new ToxicCompanyMemberInfo();
        toxicCompanyMemberInfo3.setName("张三3");
        toxicCompanyMemberInfo3.setQualificationNumber("苏87899709879");
        toxicCompanyMemberInfo3.setQualificationName("高级程序员");
        toxicCompanyMemberInfo3.setQualificationIssuing("中国程序员协会");
        toxicCompanyMemberInfo3.setComparisonResult(1);
        toxicCompanyMemberInfo3.setAliasName("小张");
        toxicCompanyMemberInfo3.setNativePlace("江苏无锡");
        toxicCompanyMemberInfo3.setHouseholdAddress("江苏无锡");
        toxicCompanyMemberInfo3.setLiveAddress("江苏无锡新安镇");
        toxicCompanyMemberInfo3.setCertificateNumber("410185198610076013");
        toxicCompanyMemberInfo3.setCompanyValue("北辰科技");
        toxicCompanyMemberInfo3.setCulturalValue("本科");
        toxicCompanyMemberInfo3.setGenderValue("男");
        toxicCompanyMemberInfo3.setRaceTypeValue("汉族");
        toxicCompanyMemberInfo3.setTel("15251435195");
        toxicCompanyMemberInfo3.setNowDuty("程序员");
        toxicCompanyMemberInfo3.setCreateTime("2019-09-09");
        toxicCompanyMemberInfo3.setUpdateTime("2019-09-10");
        toxicCompanyMemberInfo3.setStatus(0);
        model.add(toxicCompanyMemberInfo3);

        ToxicCompanyMemberInfo toxicCompanyMemberInfo4 = new ToxicCompanyMemberInfo();
        toxicCompanyMemberInfo4.setName("张三4");
        toxicCompanyMemberInfo4.setQualificationNumber("苏87899709879");
        toxicCompanyMemberInfo4.setQualificationName("高级程序员");
        toxicCompanyMemberInfo4.setQualificationIssuing("中国程序员协会");
        toxicCompanyMemberInfo4.setComparisonResult(1);
        toxicCompanyMemberInfo4.setAliasName("小张");
        toxicCompanyMemberInfo4.setNativePlace("江苏无锡");
        toxicCompanyMemberInfo4.setHouseholdAddress("江苏无锡");
        toxicCompanyMemberInfo4.setLiveAddress("江苏无锡新安镇");
        toxicCompanyMemberInfo4.setCertificateNumber("410185198610076013");
        toxicCompanyMemberInfo4.setCompanyValue("北辰科技");
        toxicCompanyMemberInfo4.setCulturalValue("本科");
        toxicCompanyMemberInfo4.setGenderValue("男");
        toxicCompanyMemberInfo4.setRaceTypeValue("汉族");
        toxicCompanyMemberInfo4.setTel("15251435195");
        toxicCompanyMemberInfo4.setNowDuty("程序员");
        toxicCompanyMemberInfo4.setCreateTime("2019-09-09");
        toxicCompanyMemberInfo4.setUpdateTime("2019-09-10");
        toxicCompanyMemberInfo4.setStatus(0);
        model.add(toxicCompanyMemberInfo4);

        ToxicCompanyMemberInfo toxicCompanyMemberInfo5 = new ToxicCompanyMemberInfo();
        toxicCompanyMemberInfo5.setName("张三5");
        toxicCompanyMemberInfo5.setQualificationNumber("苏87899709879");
        toxicCompanyMemberInfo5.setQualificationName("高级程序员");
        toxicCompanyMemberInfo5.setQualificationIssuing("中国程序员协会");
        toxicCompanyMemberInfo5.setComparisonResult(1);
        toxicCompanyMemberInfo5.setAliasName("小张");
        toxicCompanyMemberInfo5.setNativePlace("江苏无锡");
        toxicCompanyMemberInfo5.setHouseholdAddress("江苏无锡");
        toxicCompanyMemberInfo5.setLiveAddress("江苏无锡新安镇");
        toxicCompanyMemberInfo5.setCertificateNumber("410185198610076013");
        toxicCompanyMemberInfo5.setCompanyValue("北辰科技");
        toxicCompanyMemberInfo5.setCulturalValue("本科");
        toxicCompanyMemberInfo5.setGenderValue("男");
        toxicCompanyMemberInfo5.setRaceTypeValue("汉族");
        toxicCompanyMemberInfo5.setTel("15251435195");
        toxicCompanyMemberInfo5.setNowDuty("程序员");
        toxicCompanyMemberInfo5.setCreateTime("2019-09-09");
        toxicCompanyMemberInfo5.setUpdateTime("2019-09-10");
        toxicCompanyMemberInfo5.setStatus(0);
        model.add(toxicCompanyMemberInfo5);


        SetData(model);
    }

    private void SetData(final ArrayList<ToxicCompanyMemberInfo> model) {
        final ArrayList<HashMap<String, String>> dicDataArrayList = new ArrayList<>();

        for (int m = 0; m < model.size(); m++) {
            HashMap<String, String> dic = new HashMap<>();
            ToxicCompanyMemberInfo toxicCompanyMemberInfo = model.get(m);
            dic = ObjectControl.ObjectToHashMap(toxicCompanyMemberInfo);
            dicDataArrayList.add(dic);

        }

        W_RenYuanBeiAnAdapter w_renYuanBeiAnAdapter = new W_RenYuanBeiAnAdapter(this, model, R.layout.w_item_renyuanbeian);
        dataListView.setAdapter(w_renYuanBeiAnAdapter);
        dataListView.setOnPullLoadMoreListener(new PullLoadMoreListView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {

                if (AppContext.offLine) {
                    dataListView.refreshComplete();
                } else {
                    pageIndex = 1;
                    getData();
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 500);

            }

            @Override
            public void onLoadMore() {
                if (AppContext.offLine) {
                    dataListView.refreshComplete();
                } else {
                    pageIndex += 1;
                    getData();
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 500);
            }
        });

//        W_ItemAdapter w_dicAdapter = new W_ItemAdapter(this, dicDataArrayList, R.layout.w_item_root, "item_renyuanbeianstyle");
//        dataListView.setAdapter(w_dicAdapter);

        dataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ToxicCompanyMemberInfo toxicCompanyMemberInfo = model.get(position);

                Intent mainIntent = new Intent(W_RenYuanBeiAn_Activity.this, W_RenYuanBeiAn_Detail_Activity.class);
                mainIntent.putExtra("id", toxicCompanyMemberInfo.getId() + "");
                startActivity(mainIntent);
//                DicData tempDicData = dicDataArrayList.get(position);
//
//                EditText callBackET = (EditText) DicListView.getTag();
//                callBackET.setText(tempDicData.getValue());
//
//                Animation outAnima = new TranslateAnimation(0, getWindowManager().getDefaultDisplay().getWidth(), 0, 0); //设置入动画
//                outAnima.setDuration(300);
//                ChildLL.setAnimation(outAnima);
//                ChildLL.setVisibility(View.GONE);
//
//                callBackET.setTag(tempDicData);
            }
        });
    }

    @Override
    public void toxicCompanyMemberSuccess(ArrayList<ToxicCompanyMemberInfo> model) {
        SetData(model);


    }

    @Override
    public void onFailure(int code, String msg) {

    }

}
