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
import com.bcxd.wgga.utils.ObjectControl;
import com.bcxd.wgga.widget.PullLoadMoreListView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class W_YingJiZhuanJiaGuanLi_Activity extends MvpActivity<W_RenYuanBeiAnPresent> implements W_RenYuanBeiAnView {
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
    LinearLayout ChongZhiLL;
    @Bind(R.id.FilterLL)
    LinearLayout filterLL;
    @Bind(R.id.DicListView)
    PullLoadMoreListView DicListView;
    @Bind(R.id.AddRL)
    RelativeLayout AddRL;
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
        return R.layout.w_activity_yingjizhuanjiaguanli;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

//        final ArrayList<Normal> normalArrayList = AnalyzeNormalXml.Analyze(this, this, "renyuanbeian_filter", filterLL, "0", ChildLL, DicListView);

        if (AppContext.offLine) {
            SetOffLineData();
        } else {
            getData();
        }


        AddRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(W_YingJiZhuanJiaGuanLi_Activity.this, W_RenYuanBeiAn_Add_Activity.class);
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


        for (int i = 0; i < 5; i++) {
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
            model.add(toxicCompanyMemberInfo);
        }
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

        W_RenYuanBeiAnAdapter w_renYuanBeiAnAdapter = new W_RenYuanBeiAnAdapter(this, model, R.layout.w_item_yingjizhuanjiaguanli);
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

                Intent mainIntent = new Intent(W_YingJiZhuanJiaGuanLi_Activity.this, W_RenYuanBeiAn_Detail_Activity.class);
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
