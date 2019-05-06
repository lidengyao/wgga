package com.bcxd.wgga.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.info.ProjectDetailInfo;
import com.bcxd.wgga.model.info.ProjectInfo;
import com.bcxd.wgga.model.info.apppicInfo;
import com.bcxd.wgga.present.Z_XiangMuXiangQing_Present;
import com.bcxd.wgga.ui.view.Z_XiangMuXiangQing_View;
import com.bcxd.wgga.utils.TokenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.math.BigDecimal;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Z_XiangMuXiangQing_Activity extends MvpActivity<Z_XiangMuXiangQing_Present> implements Z_XiangMuXiangQing_View {


    @Bind(R.id.XiangMuXinXi_Top_LL)
    LinearLayout XiangMuXinXiTopLL;
    @Bind(R.id.XiangMuXinXi_Bottom_LL)
    LinearLayout XiangMuXinXiBottomLL;
    @Bind(R.id.JianChaLL)
    LinearLayout JianChaLL;
    @Bind(R.id.JianCeLL)
    LinearLayout JianCeLL;
    @Bind(R.id.JianKongLL)
    LinearLayout JianKongLL;
    @Bind(R.id.BaoJingLL)
    LinearLayout BaoJingLL;
    @Bind(R.id.ZhengGaiLL)
    LinearLayout ZhengGaiLL;
    @Bind(R.id.XmTitleTV)
    TextView XmTitleTV;
    @Bind(R.id.bannerTV)
    TextView bannerTV;
    @Bind(R.id.XiangMuBannerIV)
    ImageView XiangMuBannerIV;

    private int xiangmuTextSize = 14;
    private ProjectInfo _projectInfo;

    @Override
    protected Z_XiangMuXiangQing_Present createPresenter() {
        return new Z_XiangMuXiangQing_Present();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.z_activity_xiangmuxiangqing;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        _projectInfo = (ProjectInfo) getIntent().getSerializableExtra("ProjectInfo");
        XmTitleTV.setText(_projectInfo.getPname());

        mPresenter.projectDetail(_projectInfo.getPid(),this);
        mPresenter.apppicQuery(2, _projectInfo.getPid(),this);
        //region 点击事件
        JianChaLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Z_XiangMuXiangQing_Activity.this, Z_FaXianWenTi_Activity.class);
                intent.putExtra("ProjectInfo", _projectInfo);
                startActivity(intent);
            }
        });
        JianCeLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Z_XiangMuXiangQing_Activity.this, Z_Jiance_Activity.class);
                intent.putExtra("ProjectInfo", _projectInfo);
                startActivity(intent);
            }
        });

        JianKongLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Z_XiangMuXiangQing_Activity.this, Z_GongDiXiangQing_Activity.class);
                intent.putExtra("ProjectInfo", _projectInfo);
                startActivity(intent);
            }
        });
        BaoJingLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Z_XiangMuXiangQing_Activity.this, Z_Baojing_Activity.class);
                intent.putExtra("ProjectInfo", _projectInfo);
                startActivity(intent);
            }
        });
        ZhengGaiLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Z_XiangMuXiangQing_Activity.this, Z_ZhengGai_Activity.class);
                intent.putExtra("ProjectInfo", _projectInfo);
                startActivity(intent);
            }
        });
        //endregion
    }

    public int px2Dip(int px) {

        return this.getResources().getDimensionPixelSize(R.dimen.base_dip) * px;
    }

    @Override
    public void projectDetail(ProjectDetailInfo model) {
        if (model == null)
            return;

        ArrayList<XiangMuData> xiangMuDataArrayList = new ArrayList<>();
        //region  填充数据
        XiangMuData xiangMuData0 = new XiangMuData();
        xiangMuData0.setDataKey("项目名称");
        xiangMuData0.setDataValue(model.getPname());
        xiangMuDataArrayList.add(xiangMuData0);

        XiangMuData xiangMuData1 = new XiangMuData();
        xiangMuData1.setDataKey("地址");
        xiangMuData1.setDataValue(model.getAddress());
        xiangMuDataArrayList.add(xiangMuData1);

        XiangMuData xiangMuData2 = new XiangMuData();
        xiangMuData2.setDataKey("立项批文文号");
        xiangMuData2.setDataValue(model.getApprovedfileno());
        xiangMuDataArrayList.add(xiangMuData2);

        XiangMuData xiangMuData3 = new XiangMuData();
        xiangMuData3.setDataKey("立项批准单位");
        xiangMuData3.setDataValue(model.getApproveddepart());
        xiangMuDataArrayList.add(xiangMuData3);

        XiangMuData xiangMuData4 = new XiangMuData();
        xiangMuData4.setDataKey("项目类型");
        xiangMuData4.setDataValue(model.getProjecttype());
        xiangMuDataArrayList.add(xiangMuData4);

        XiangMuData xiangMuData5 = new XiangMuData();
        xiangMuData5.setDataKey("安全监督单位");
        xiangMuData5.setDataValue(model.getSafetysupervisedepart());
        xiangMuDataArrayList.add(xiangMuData5);

        XiangMuData xiangMuData6 = new XiangMuData();
        xiangMuData6.setDataKey("扬尘监管单位");
        xiangMuData6.setDataValue(model.getDustsupervisedepart());
        xiangMuDataArrayList.add(xiangMuData6);

        XiangMuData xiangMuData7 = new XiangMuData();
        xiangMuData7.setDataKey("渣土监管单位");
        xiangMuData7.setDataValue(model.getDirtsupervisedepart());
        xiangMuDataArrayList.add(xiangMuData7);

        XiangMuData xiangMuData8 = new XiangMuData();
        xiangMuData8.setDataKey("计划开工日期");
        xiangMuData8.setDataValue(model.getStarttime());
        xiangMuDataArrayList.add(xiangMuData8);

        XiangMuData xiangMuData9 = new XiangMuData();
        xiangMuData9.setDataKey("计划竣工日期");
        xiangMuData9.setDataValue(model.getEndtime());
        xiangMuDataArrayList.add(xiangMuData9);

        XiangMuData xiangMuData10 = new XiangMuData();
        xiangMuData10.setDataKey("投资金额");
        if (model.getInvestamount() == null) {
            xiangMuData10.setDataValue("");
        } else
            xiangMuData10.setDataValue(new BigDecimal(String.valueOf(model.getInvestamount())).toPlainString() + "元");

        xiangMuDataArrayList.add(xiangMuData10);

        XiangMuData xiangMuData11 = new XiangMuData();
        xiangMuData11.setDataKey("建安费");
        if (model.getJiananamount() == null) {
            xiangMuData11.setDataValue("");

        } else
            xiangMuData11.setDataValue(new BigDecimal(String.valueOf(model.getJiananamount())).toPlainString() + "元");
        xiangMuDataArrayList.add(xiangMuData11);

        XiangMuData xiangMuData12 = new XiangMuData();
        xiangMuData12.setDataKey("建设单位");
        xiangMuData12.setDataValue(model.getConstructunit());
        xiangMuDataArrayList.add(xiangMuData12);

        XiangMuData xiangMuData13 = new XiangMuData();
        xiangMuData13.setDataKey("项目负责人");
        xiangMuData13.setDataValue(model.getDutyname());
        xiangMuDataArrayList.add(xiangMuData13);

        XiangMuData xiangMuData14 = new XiangMuData();
        xiangMuData14.setDataKey("施工单位");
        xiangMuData14.setDataValue(model.getWorkunit());
        xiangMuDataArrayList.add(xiangMuData14);

        XiangMuData xiangMuData15 = new XiangMuData();
        xiangMuData15.setDataKey("项目经理");
        xiangMuData15.setDataValue(model.getProjectmanager());
        xiangMuDataArrayList.add(xiangMuData15);

        XiangMuData xiangMuData16 = new XiangMuData();
        xiangMuData16.setDataKey("监理单位");
        xiangMuData16.setDataValue(model.getJianliunit());
        xiangMuDataArrayList.add(xiangMuData16);

        XiangMuData xiangMuData17 = new XiangMuData();
        xiangMuData17.setDataKey("总监理工程师");
        xiangMuData17.setDataValue(model.getJianliengineer());
        xiangMuDataArrayList.add(xiangMuData17);

        XiangMuData xiangMuData18 = new XiangMuData();
        xiangMuData18.setDataKey("设备运维单位");
        xiangMuData18.setDataValue(model.getEquipoperatingunit());
        xiangMuDataArrayList.add(xiangMuData18);

        XiangMuData xiangMuData19 = new XiangMuData();
        xiangMuData19.setDataKey("是否通过规格化管理");
        xiangMuData19.setDataValue(model.getNormalized() == 0 ? "否" : "是");
        xiangMuDataArrayList.add(xiangMuData19);
        //endregion

        //region 加载项目信息数据

        final TextView XiangQingTV = new TextView(this);
        final TextView ShouQiTV = new TextView(this);
        if (xiangMuDataArrayList.size() > 4) {
            for (int i = 0; i < 4; i++) {
                LinearLayout hangLL = new LinearLayout(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                hangLL.setOrientation(LinearLayout.HORIZONTAL);
                hangLL.setPadding(px2Dip(0), px2Dip(3), 0, px2Dip(3));
                hangLL.setLayoutParams(layoutParams);

                TextView keyTV = new TextView(this);
                ViewGroup.LayoutParams keyTVParams = new AppBarLayout.LayoutParams(px2Dip(120), ViewGroup.LayoutParams.WRAP_CONTENT);
                keyTV.setLayoutParams(keyTVParams);
                keyTV.setText(xiangMuDataArrayList.get(i).getDataKey());
                keyTV.setTextSize(xiangmuTextSize);
                hangLL.addView(keyTV);

                TextView ValueTV = new TextView(this);
                ViewGroup.LayoutParams valueTVParams = new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                ValueTV.setLayoutParams(valueTVParams);
                ((AppBarLayout.LayoutParams) valueTVParams).rightMargin = px2Dip(20);
                ValueTV.setTextSize(xiangmuTextSize);
                ValueTV.setText(xiangMuDataArrayList.get(i).getDataValue());
                hangLL.addView(ValueTV);

                //添加“详情”控件


                XiangMuXinXiTopLL.addView(hangLL);
            }
            ViewGroup.LayoutParams XiangQingTVParams = new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            XiangQingTV.setLayoutParams(XiangQingTVParams);
            XiangQingTV.setText("详情");
            XiangQingTV.setTextSize(xiangmuTextSize);
            XiangQingTV.setPadding(px2Dip(0), px2Dip(3), px2Dip(3), px2Dip(3));
            XiangQingTV.setTextColor(getResources().getColor(R.color.color20));
            XiangMuXinXiTopLL.addView(XiangQingTV);

            XiangQingTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    XiangMuXinXiBottomLL.setVisibility(View.VISIBLE);
                    XiangQingTV.setVisibility(View.GONE);
                    ShouQiTV.setVisibility(View.VISIBLE);
                }

            });


            for (int i = 4; i < xiangMuDataArrayList.size(); i++) {
                LinearLayout hangLL = new LinearLayout(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                hangLL.setOrientation(LinearLayout.HORIZONTAL);
                hangLL.setPadding(px2Dip(0), px2Dip(3), 0, px2Dip(3));
                hangLL.setLayoutParams(layoutParams);

                TextView keyTV = new TextView(this);
                ViewGroup.LayoutParams keyTVParams = new AppBarLayout.LayoutParams(px2Dip(120), ViewGroup.LayoutParams.WRAP_CONTENT);
                keyTV.setLayoutParams(keyTVParams);
                keyTV.setTextSize(xiangmuTextSize);
                keyTV.setText(xiangMuDataArrayList.get(i).getDataKey());
                hangLL.addView(keyTV);

                TextView ValueTV = new TextView(this);
                ViewGroup.LayoutParams valueTVParams = new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                ValueTV.setLayoutParams(valueTVParams);
                ValueTV.setTextSize(xiangmuTextSize);
                ValueTV.setText(xiangMuDataArrayList.get(i).getDataValue());
                hangLL.addView(ValueTV);

                if (i == xiangMuDataArrayList.size() - 1) {


                }
                XiangMuXinXiBottomLL.addView(hangLL);
            }

            ViewGroup.LayoutParams ShouqiTVParams = new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ShouQiTV.setLayoutParams(ShouqiTVParams);
            ShouQiTV.setText("收起");
            ShouQiTV.setTextSize(xiangmuTextSize);
            ShouQiTV.setPadding(px2Dip(0), px2Dip(3), px2Dip(3), px2Dip(3));
            ShouQiTV.setTextColor(getResources().getColor(R.color.color20));
            XiangMuXinXiBottomLL.addView(ShouQiTV);
            ShouQiTV.setVisibility(View.GONE);
            ShouQiTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    XiangMuXinXiBottomLL.setVisibility(View.GONE);
                    XiangQingTV.setVisibility(View.VISIBLE);
                    ShouQiTV.setVisibility(View.GONE);
                }

            });
        }

        //endregion
    }

    @Override
    public void apppicQuery(apppicInfo model) {
        if (model == null)
            return;
        bannerTV.setText(model.getTitle());

        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.shouyebanner)//图片加载出来前，显示的图片
                .fallback(R.mipmap.shouyebanner) //url为空的时候,显示的图片
                .error(R.mipmap.shouyebanner);//图片加载失败后，显示的图片
        Glide.with(this)
                .load(model.getPicurl()) //图片地址
                .apply(options)
                .into(XiangMuBannerIV);
    }

    @Override
    public void refreshtokenSuccess(String model) {
        TokenUtils.ResetToken(model,this);
    }

    @Override
    public void onFailure(int code, String msg) {
        if (code == 499) {

            mPresenter.refreshToken(this);
        } else if (code == 498) {
            //REFRESH TOKEN过期
            showMessage("登录超时，请重新登录");
            //ACCESS TOKEN过期
            Intent intent = new Intent(this, Z_Login_Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            showMessage(msg);
        }
    }


    public class XiangMuData {
        public String DataKey;
        public String DataValue;

        public String getDataKey() {
            return DataKey;
        }

        public void setDataKey(String dataKey) {
            DataKey = dataKey;
        }

        public String getDataValue() {
            return DataValue;
        }

        public void setDataValue(String dataValue) {
            DataValue = dataValue;
        }
    }
}
