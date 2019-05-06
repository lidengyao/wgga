package com.bcxd.wgga.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bcxd.wgga.R;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.bean.LoginInfo;
import com.bcxd.wgga.model.info.GongDiInfo;
import com.bcxd.wgga.model.info.Z_UserInfo;
import com.bcxd.wgga.present.Z_LoginPresent;
import com.bcxd.wgga.ui.view.Z_LoginView;
import com.bcxd.wgga.utils.DbKeyS;
import com.bcxd.wgga.utils.SpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

public class Z_Login_Activity extends MvpActivity<Z_LoginPresent> implements Z_LoginView {

    @Bind(R.id.shishishujuIV)
    ImageView shishishujuIV;
    @Bind(R.id.BtnLogin)
    Button BtnLogin;
    @Bind(R.id.NameET)
    EditText NameET;
    @Bind(R.id.PwdET)
    EditText PwdET;
    @Bind(R.id.ZiDongLL)
    LinearLayout ZiDongLL;
    @Bind(R.id.ZiDongIV)
    ImageView ZiDongIV;

    @Override
    protected Z_LoginPresent createPresenter() {
        return new Z_LoginPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.z_activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏

        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);


//        String status = AppContext.dbHelper.GetValue(DbKeyS.status);
        String status = SpUtils.getSettingNote(Z_Login_Activity.this, DbKeyS.status);
        if (status == null || status.equals("0") || status.equals("")) {
            ZiDongIV.setImageResource(R.mipmap.normal);
            Gson gsonZU = new Gson();
//            String data = AppContext.dbHelper.GetValue(DbKeyS.z_userinfo);
            String data = SpUtils.getSettingNote(Z_Login_Activity.this, DbKeyS.z_userinfo);
            Z_UserInfo z_userInfo = gsonZU.fromJson(data, Z_UserInfo.class);
            if (z_userInfo != null) {
                NameET.setText(z_userInfo.getUsercode());
            }

        } else {
            ZiDongIV.setImageResource(R.mipmap.checked);
//            BtnLogin.setEnabled(false);
            Intent mainIntent = new Intent(Z_Login_Activity.this, Z_Main_Sec_Activity.class);
            startActivity(mainIntent);
            return;
        }

//        AppContext.dbHelper.SetData(DbKeyS.logininfo, "");
        SpUtils.saveSettingNote(Z_Login_Activity.this, DbKeyS.logininfo, "");
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_account = NameET.getText().toString();
                String password = PwdET.getText().toString();

                if (user_account.equals("")) {
                    showMessage("账号不能为空");
                    return;
                }

                if (password.equals("")) {
                    showMessage("密码不能为空");
                    return;
                }
                String registrationId = JPushInterface.getRegistrationID(getApplicationContext());
                mPresenter.login(user_account, password,registrationId,Z_Login_Activity.this);
            }
        });

        shishishujuIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(Z_Login_Activity.this, Picture_Select_Activity.class);
                startActivity(mainIntent);
            }
        });

        ZiDongLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String status = AppContext.dbHelper.GetValue(DbKeyS.status);
                String status = SpUtils.getSettingNote(Z_Login_Activity.this, DbKeyS.status);
                if (status == null || status.equals("0") || status.equals("")) {
                    ZiDongIV.setImageResource(R.mipmap.checked);
//                    AppContext.dbHelper.SetData(DbKeyS.status, "1");
                    SpUtils.saveSettingNote(Z_Login_Activity.this, DbKeyS.status, "1");
                } else {
                    ZiDongIV.setImageResource(R.mipmap.normal);
//                    AppContext.dbHelper.SetData(DbKeyS.status, "0");
                    SpUtils.saveSettingNote(Z_Login_Activity.this, DbKeyS.status, "0");

                }
            }
        });


    }


    @Override
    public void loginSuccess(LoginInfo model) {
        String pwd = PwdET.getText().toString();
//        AppContext.dbHelper.SetData(DbKeyS.pwd, pwd);

        SpUtils.saveSettingNote(this, DbKeyS.pwd, pwd);
        Gson gson = new Gson();
        String logininfo = gson.toJson(model);
        if (SpUtils.saveSettingNote(this, DbKeyS.logininfo, logininfo)) {
//            mPresenter.getuserinfo();
//            JPushInterface.setAlias(this, 2,"15251435195");
//            JPushInterface.init(this);            // 初始化 JPush
            if (JPushInterface.isPushStopped(getApplicationContext())) {
                JPushInterface.resumePush(getApplicationContext());
            }
//            TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
//            tagAliasBean.action = 2;
//            tagAliasBean.alias = model.getUser_id() + "";
//            tagAliasBean.isAliasAction = true;
//            TagAliasOperatorHelper.getInstance().handleAction(this, 1, tagAliasBean);


            Intent mainIntent = new Intent(Z_Login_Activity.this, Z_Main_Sec_Activity.class);
            startActivity(mainIntent);
        }

    }

    @Override
    public void getuserinfoSuccess(Z_UserInfo model) {

    }

    @Override
    public void listSuccess(ArrayList<GongDiInfo> model) {

    }

    @Override
    public void onFailure(int code, String msg) {

    }


}
