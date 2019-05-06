package com.bcxd.wgga.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.PreferenceUtils;
import com.bcxd.wgga.model.info.PhoneLoginInfo;
import com.bcxd.wgga.present.LoginPresent;
import com.bcxd.wgga.ui.view.LoginView;
import com.bcxd.wgga.utils.FormatCheckUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jinxh on 16/2/1.
 */
public class LoginActivity extends MvpActivity<LoginPresent> implements View.OnClickListener, LoginView {

    @Override
    protected LoginPresent createPresenter() {
        return new LoginPresent();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
    }


    @Override
    public void loginSuccess(PhoneLoginInfo model) {
        showMessage("登陆成功");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            View decorView = getWindow().getDecorView();
//
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//
//            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }

    }
}
