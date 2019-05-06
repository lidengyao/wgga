package com.bcxd.wgga.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bcxd.wgga.BuildConfig;
import com.bcxd.wgga.R;
import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.base.MvpActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Z_About_Activity extends MvpActivity {

    @Bind(R.id.homeTopLL)
    RelativeLayout homeTopLL;
    @Bind(R.id.VersionNameTV)
    TextView VersionNameTV;

    @Override
    protected BasePresent createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.z_activity_about;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        String VERSION_NAME = getVERSION_NAME(this);
        VersionNameTV.setText("当前版本:" + VERSION_NAME);

    }

    private String getVERSION_NAME(Context context) {
        String VERSION_NAME = "";
        VERSION_NAME = BuildConfig.VERSION_NAME;
        return VERSION_NAME;
    }

}
