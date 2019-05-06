package com.bcxd.wgga.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.adapter.DataAdapter;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.info.BaoJingInfo;
import com.bcxd.wgga.model.info.BaoJingInfoDetail;
import com.bcxd.wgga.present.Z_BaojingChuLi_Present;
import com.bcxd.wgga.ui.view.Z_BaojingChuLi_View;
import com.bcxd.wgga.utils.TokenUtils;
import com.bcxd.wgga.widget.DialogFromBottom;
import com.bcxd.wgga.widget.PullLoadMoreListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Z_BaojingChuLi_Activity extends MvpActivity<Z_BaojingChuLi_Present> implements Z_BaojingChuLi_View {


    @Bind(R.id.XmTitleTV)
    TextView XmTitleTV;
    @Bind(R.id.BtnOK)
    Button BtnOK;
    @Bind(R.id.DaiChuLiContentTV)
    TextView DaiChuLiContentTV;
    @Bind(R.id.DaiChuLiXiaDaBaoJingTimeTV)
    TextView DaiChuLiXiaDaBaoJingTimeTV;
    @Bind(R.id.DaiChuLiXiangMuNextIV)
    ImageView DaiChuLiXiangMuNextIV;
    @Bind(R.id.DaiChuLiXiangMuTV)
    TextView DaiChuLiXiangMuTV;
    @Bind(R.id.DaiChuLiXiangMuRL)
    RelativeLayout DaiChuLiXiangMuRL;
    @Bind(R.id.DaiChuLiLL)
    LinearLayout DaiChuLiLL;
    @Bind(R.id.ContentTV)
    TextView YiChuLiContentTV;
    @Bind(R.id.YiChuLiXiaDaBaoJingTimeTV)
    TextView YiChuLiXiaDaBaoJingTimeTV;
    @Bind(R.id.YiChuLiBaoJingTimeTV)
    TextView YiChuLiBaoJingTimeTV;
    @Bind(R.id.YiChuLiStatusTV)
    TextView YiChuLiStatusTV;
    @Bind(R.id.YiChuLiLL)
    LinearLayout YiChuLiLL;
    @Bind(R.id.DaiChuLiSiteNameTV)
    TextView DaiChuLiSiteNameTV;
    @Bind(R.id.DaiChuLiDeviceNameTV)
    TextView DaiChuLiDeviceNameTV;
    @Bind(R.id.YiChuLiSiteNameTV)
    TextView YiChuLiSiteNameTV;
    @Bind(R.id.YiChuLiDeviceNameTV)
    TextView YiChuLiDeviceNameTV;
    @Bind(R.id.HavDataRL)
    RelativeLayout HavDataRL;
    @Bind(R.id.NoDataLL)
    LinearLayout NoDataLL;
    private DialogFromBottom DialogFromBottom_Status;
    private View dialogContent_Status;
    private int _Status = 0;

    private int rid;

    @Override
    protected Z_BaojingChuLi_Present createPresenter() {
        return new Z_BaojingChuLi_Present();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.z_activity_baojingchuli;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);


        String btype = getIntent().getStringExtra("btype");
        if (btype.equals("-1")) {
            BaoJingInfo.RecordsBean recordsBean = (BaoJingInfo.RecordsBean) getIntent().getSerializableExtra("RecordsBean");
            rid = recordsBean.getRid();

        }
        if (btype.equals("3")) {
            rid = Integer.parseInt(getIntent().getStringExtra("bid"));
        }

        mPresenter.alertsearchrealbyrid(rid,this);
        XmTitleTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage(rid + "");
            }
        });

        dialogContent_Status = LayoutInflater.from(this).inflate(R.layout.z_dialog_list, null, false);
        DialogFromBottom_Status = new DialogFromBottom(this);
        DialogFromBottom_Status.setContentView(dialogContent_Status);

        final ArrayList<String> dataList = new ArrayList<>();
        dataList.add("否");
        dataList.add("是");

        DataAdapter dataAdapter = new DataAdapter(Z_BaojingChuLi_Activity.this, dataList, R.layout.z_item_data);
        final PullLoadMoreListView dataListView = (PullLoadMoreListView) dialogContent_Status.findViewById(R.id.DataListView);
        dataListView.setAdapter(dataAdapter);

        dataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String temp = dataList.get(position);
                if (temp.equals("否")) {
                    _Status = 0;
                }
                if (temp.equals("是")) {
                    _Status = 1;
                }
                DaiChuLiXiangMuTV.setText(temp);
                DialogFromBottom_Status.dismiss();
            }
        });
        DaiChuLiXiangMuRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFromBottom_Status.show();
            }
        });

        BtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.updatealertstatus(rid, _Status,Z_BaojingChuLi_Activity.this);
            }
        });
    }

    @Override
    public void updatealertstatusSuccess(String model) {
        showMessage("提交成功");
        finish();
    }

    @Override
    public void alertsearchrealbyridSuccess(BaoJingInfoDetail model) {

        if (model == null) {
//            showMessage("报警已消除");
            HavDataRL.setVisibility(View.GONE);
            NoDataLL.setVisibility(View.VISIBLE);
            XmTitleTV.setText("报警处理");
            return;
        }
        HavDataRL.setVisibility(View.VISIBLE);
        NoDataLL.setVisibility(View.GONE);
        XmTitleTV.setText(model.getSitename());
        if (model.getStatus() == 0) {
            DaiChuLiLL.setVisibility(View.VISIBLE);
            YiChuLiLL.setVisibility(View.GONE);
            DaiChuLiContentTV.setText(model.getRuledescription());
            DaiChuLiXiaDaBaoJingTimeTV.setText(model.getCreatetime());
            DaiChuLiSiteNameTV.setText(model.getSitename());
            DaiChuLiDeviceNameTV.setText(model.getDevicesn());
        }

        if (model.getStatus() == 1) {
            DaiChuLiLL.setVisibility(View.GONE);
            YiChuLiLL.setVisibility(View.VISIBLE);
            BtnOK.setVisibility(View.GONE);

            YiChuLiBaoJingTimeTV.setText(model.getProcesstime());
            YiChuLiContentTV.setText(model.getRuledescription());
            YiChuLiStatusTV.setText("已处理");
            YiChuLiXiaDaBaoJingTimeTV.setText(model.getCreatetime());
            YiChuLiSiteNameTV.setText(model.getSitename());
            YiChuLiDeviceNameTV.setText(model.getDevicesn());
        }

    }

    @Override
    public void refreshtokenSuccess(String model) {
        TokenUtils.ResetToken(model,this);
    }

    @Override
    public void onFailure(int code, String msg) {
        if (code == 499) {
            mPresenter.refreshToken(Z_BaojingChuLi_Activity.this);
        } else if (code == 498) {
            //REFRESH TOKEN过期
            showMessage("登录超时，请重新登录");
            //ACCESS TOKEN过期
            Intent intent = new Intent(Z_BaojingChuLi_Activity.this, Z_Login_Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            showMessage(msg);
        }
    }
}
