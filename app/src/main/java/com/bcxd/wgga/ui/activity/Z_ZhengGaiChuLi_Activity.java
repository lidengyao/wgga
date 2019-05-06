package com.bcxd.wgga.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.adapter.DataAdapter;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.info.ZhengGaiInfo;
import com.bcxd.wgga.model.info.ZhengGaiInfoDetail;
import com.bcxd.wgga.present.Z_ZhengGaiChuLi_Present;
import com.bcxd.wgga.ui.view.Z_ZhengGaiChuLi_View;
import com.bcxd.wgga.utils.TokenUtils;
import com.bcxd.wgga.widget.DialogFromBottom;
import com.bcxd.wgga.widget.PullLoadMoreListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Z_ZhengGaiChuLi_Activity extends MvpActivity<Z_ZhengGaiChuLi_Present> implements Z_ZhengGaiChuLi_View {

    @Bind(R.id.XmTitleTV)
    TextView XmTitleTV;
    @Bind(R.id.ChuLiXiangMuNextIV)
    ImageView ChuLiXiangMuNextIV;
    @Bind(R.id.ChuLiXiangMuTV)
    TextView ChuLiXiangMuTV;
    @Bind(R.id.ChuLiXiangMuRL)
    RelativeLayout ChuLiXiangMuRL;
    @Bind(R.id.ChuliLL)
    LinearLayout ChuliLL;
    @Bind(R.id.YiChuLiLL)
    LinearLayout YiChuLiLL;
    @Bind(R.id.BtnOK)
    Button BtnOK;
    @Bind(R.id.ContentTV)
    TextView YiChuLiContentTV;
    @Bind(R.id.YiChuLiXiaFaTimeTV)
    TextView YiChuLiXiaFaTimeTV;
    @Bind(R.id.YiChuLiDealTimeTV)
    TextView YiChuLiDealTimeTV;
    @Bind(R.id.ChuLiContentTV)
    TextView ChuLiContentTV;
    @Bind(R.id.ChuLiXiaFaTimeTV)
    TextView ChuLiXiaFaTimeTV;
    @Bind(R.id.MyWebView)
    WebView ChuliMyWebView;
    @Bind(R.id.MyYiChuLiWebView)
    WebView YiChuLiWebView;

    private DialogFromBottom DialogFromBottom_Status;
    private View dialogContent_Status;
    private int _Status = 0;
    private int zid;

    @Override
    protected Z_ZhengGaiChuLi_Present createPresenter() {
        return new Z_ZhengGaiChuLi_Present();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.z_activity_zhenggaichuli;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        String btype = getIntent().getStringExtra("btype");
        if (btype.equals("-1")) {
            ZhengGaiInfo.RecordsBean recordsBean = (ZhengGaiInfo.RecordsBean) getIntent().getSerializableExtra("RecordsBean");
            zid = recordsBean.getZid();

        }
        if (btype.equals("2")) {
            zid = Integer.parseInt(getIntent().getStringExtra("bid"));
        }
        mPresenter.zhenggaiquerybyzid(zid, this);


        dialogContent_Status = LayoutInflater.from(this).inflate(R.layout.z_dialog_list, null, false);
        DialogFromBottom_Status = new DialogFromBottom(this);
        DialogFromBottom_Status.setContentView(dialogContent_Status);

        final ArrayList<String> dataList = new ArrayList<>();
        dataList.add("否");
        dataList.add("是");

        DataAdapter dataAdapter = new DataAdapter(Z_ZhengGaiChuLi_Activity.this, dataList, R.layout.z_item_data);
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
                ChuLiXiangMuTV.setText(temp);
                DialogFromBottom_Status.dismiss();
            }
        });


        ChuLiXiangMuRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFromBottom_Status.show();
            }
        });
        BtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.zhenggaiUpdate(zid, _Status, Z_ZhengGaiChuLi_Activity.this);
            }
        });
    }

    @Override
    public void zhenggaiUpdateSuccess(String model) {
        showMessage("提交成功");
        finish();
    }

    private String URL;

    @Override
    public void zhenggaiquerybyzidSuccess(ZhengGaiInfoDetail model) {
        if (model == null)
            return;
        URL = "<html><body>" + model.getDescription() + "</body></html>";

        YiChuLiContentTV.setText(model.getDescription());
        YiChuLiXiaFaTimeTV.setText(model.getCreatetime());
        YiChuLiDealTimeTV.setText(model.getDealtime());
        YiChuLiWebView.loadDataWithBaseURL(null, URL, "text/html", "utf-8", null);

        ChuLiContentTV.setText(model.getDescription());
        ChuLiXiaFaTimeTV.setText(model.getCreatetime());
        ChuliMyWebView.loadDataWithBaseURL(null, URL, "text/html", "utf-8", null);


        if (model.getStatus() == 0) {
            ChuliLL.setVisibility(View.VISIBLE);
            YiChuLiLL.setVisibility(View.GONE);
            BtnOK.setVisibility(View.VISIBLE);
        }

        if (model.getStatus() == 1) {

            ChuliLL.setVisibility(View.GONE);
            YiChuLiLL.setVisibility(View.VISIBLE);
            BtnOK.setVisibility(View.GONE);
        }

    }

    @Override
    public void refreshtokenSuccess(String model) {
        TokenUtils.ResetToken(model, this);
    }

    @Override
    public void onFailure(int code, String msg) {
        if (code == 499) {
            mPresenter.refreshToken(this);
        } else if (code == 498) {
            //REFRESH TOKEN过期
            showMessage("登录超时，请重新登录");
            //ACCESS TOKEN过期
            Intent intent = new Intent(Z_ZhengGaiChuLi_Activity.this, Z_Login_Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            showMessage(msg);
        }
    }
}
