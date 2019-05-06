package com.bcxd.wgga.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.bcxd.wgga.R;
import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.base.MvpActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Z_ZhangHaoAnQuan_Activity extends MvpActivity {
    @Bind(R.id.ChangePwdRL)
    RelativeLayout ChangePwdRL;

    @Override
    protected BasePresent createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.z_activity_zhanghaoanquan;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        ChangePwdRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Z_ZhangHaoAnQuan_Activity.this, Z_ChangePwd_Activity.class);
                startActivity(intent);
            }
        });
    }
}
