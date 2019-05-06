package com.bcxd.wgga.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.bean.W_LoginInfo;
import com.bcxd.wgga.present.W_LoginPresent;
import com.bcxd.wgga.ui.view.W_LoginView;
import com.bcxd.wgga.utils.AnalyzeNormalXml;
import com.bcxd.wgga.widget.Normal;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class W_Normal_Activity extends MvpActivity<W_LoginPresent> implements W_LoginView {
    @Bind(R.id.SysNameIV)
    TextView SysName;
    @Bind(R.id.RootLL)
    LinearLayout RootLL;
    @Bind(R.id.SaveRL)
    RelativeLayout SaveRL;

    @Override
    protected W_LoginPresent createPresenter() {
        return new W_LoginPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.w_activity_normal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

//        final ArrayList<Normal> normalArrayList = AnalyzeNormalXml.Analyze(this, this, "suodingdetail", RootLL, null, null);


        SaveRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ArrayList<Normal> temp = AnalyzeNormalXml.GetAnalyzeData(normalArrayList);
//                showMessage(temp.size() + "");



            }
        });
    }

    @Override
    public void loginSuccess(W_LoginInfo model) {

    }

    @Override
    public void onFailure(int code, String msg) {

    }





}
