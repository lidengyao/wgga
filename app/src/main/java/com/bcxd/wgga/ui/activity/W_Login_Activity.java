package com.bcxd.wgga.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bcxd.wgga.AppContext;
import com.bcxd.wgga.R;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.bean.W_LoginInfo;
import com.bcxd.wgga.present.W_LoginPresent;
import com.bcxd.wgga.timepaker.ChangeDatePopwindow;
import com.bcxd.wgga.ui.view.W_LoginView;
import com.bcxd.wgga.utils.DbKeyS;
import com.bcxd.wgga.utils.SpUtils;
import com.bcxd.wgga.widget.Normal;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class W_Login_Activity extends MvpActivity<W_LoginPresent> implements W_LoginView {


    @Bind(R.id.userET)
    EditText userET;
    @Bind(R.id.PwdET)
    EditText PwdET;
    @Bind(R.id.BtnLogin)
    Button BtnLogin;
    @Bind(R.id.forgetPwd)
    TextView forgetPwd;
    @Bind(R.id.SysNameIV)
    ImageView SysNameIV;
    @Bind(R.id.SysNameTV)
    TextView SysNameTV;
    @Bind(R.id.MainLL)
    LinearLayout MainLL;

    @Override
    protected W_LoginPresent createPresenter() {
        return new W_LoginPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.w_activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        SysNameIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppContext.API_BASE_URL = "http://192.168.0.133:8080/";
                showMessage(AppContext.API_BASE_URL);
            }
        });
        SysNameTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppContext.API_BASE_URL = "http://zejun.free.idcfengye.com/";
                showMessage(AppContext.API_BASE_URL);
            }
        });
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userAccount = userET.getText().toString();
                String userPassword = PwdET.getText().toString();

                if (userAccount.equals("")) {
                    showMessage("请输入用户名");
                    return;
                }


                if (userPassword.equals("")) {
                    showMessage("请输入密码");
                    return;
                }

                if (AppContext.offLine) {
                    Intent mainIntent = new Intent(W_Login_Activity.this, W_ChooseSystem_Activity.class);
                    startActivity(mainIntent);

                } else
                    mPresenter.w_login(userAccount, userPassword, W_Login_Activity.this);


//                ArrayList<Normal> normalArrayList = new ArrayList<>();
//
//                Normal aNormal = new Normal();
//                aNormal.setKey("AD");
//                aNormal.setValue("ADValue");
//                normalArrayList.add(aNormal);
//
//                Normal bNormal = new Normal();
//                bNormal.setKey("BD");
//                bNormal.setValue("BDValue");
//                normalArrayList.add(bNormal);
//
//
//                try {
////                    Class<?> classu = Class.forName("com.bcxd.wgga.ui.activity.TestClass");
////                    Field[] fields = TestClass.class.getFields();
//
//                    TestClass testClass = new TestClass();
//                    Field[] fields = testClass.getClass().getDeclaredFields();
//                    for (int i = 0; i < fields.length; i++) {
//                        String filedName = fields[i].getName();
//                        if (fields[i].isSynthetic())
//                            continue;
//                        if (filedName.equals("serialVersionUID"))
//                            continue;
//                        fields[i].set(testClass, getData(filedName, normalArrayList));
//                    }
//
//                    showMessage(testClass.getBD());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }


            }
        });
    }

    private String getData(String fieldName, ArrayList<Normal> normalArrayList) {
        String value = "";
        for (int i = 0; i < normalArrayList.size(); i++) {
            Normal normal = normalArrayList.get(i);
            if (normal.getKey().equals(fieldName)) {
                value = normal.getValue();
                break;
            }
        }
        return value;

    }


    @Override
    public void loginSuccess(W_LoginInfo model) {
//        SpUtils.saveSettingNote(this, DbKeyS.token, model.getToken());
        SpUtils.saveSettingNote(this, DbKeyS.token, "0645404e1cf343d286ccf315441a4655");
        Intent mainIntent = new Intent(W_Login_Activity.this, W_ChooseSystem_Activity.class);
        startActivity(mainIntent);
    }

    @Override
    public void onFailure(int code, String msg) {

    }


    public class TestClass implements Serializable {
        public String AD;
        public String BD;


        public String getAD() {
            return AD;
        }

        public void setAD(String AD) {
            this.AD = AD;
        }

        public String getBD() {
            return BD;
        }

        public void setBD(String BD) {
            this.BD = BD;
        }
    }
}
