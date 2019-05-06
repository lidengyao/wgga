package com.bcxd.wgga.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bcxd.wgga.R;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.bean.ChangePwdObject;
import com.bcxd.wgga.present.Z_ChangePwd_Present;
import com.bcxd.wgga.ui.view.Z_ChangePwd_View;
import com.bcxd.wgga.utils.DbKeyS;
import com.bcxd.wgga.utils.SpUtils;
import com.bcxd.wgga.utils.TokenUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Z_ChangePwd_Activity extends MvpActivity<Z_ChangePwd_Present> implements Z_ChangePwd_View {
    @Bind(R.id.oldPwdET)
    EditText oldPwdET;
    @Bind(R.id.newpwdET)
    EditText newpwdET;
    @Bind(R.id.rePassET)
    EditText rePassET;
    @Bind(R.id.BtnOK)
    Button BtnOK;

    @Override
    protected Z_ChangePwd_Present createPresenter() {
        return new Z_ChangePwd_Present();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.z_activity_changepwd;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        BtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePwd();
            }
        });
    }

    private void ChangePwd() {
        String oldPwd = oldPwdET.getText().toString();
        String newPwd = newpwdET.getText().toString();
        String reNewPwd = rePassET.getText().toString();

        if (oldPwd.equals("")) {
            showMessage("请填写原始密码");
            return;
        }

        if (newPwd.equals("") || reNewPwd.equals("")) {
            showMessage("请填写新密码");
            return;
        }

        if (!newPwd.equals(reNewPwd)) {
            showMessage("两次输入新密码不一致");
            return;
        }

//        String oldP = AppContext.dbHelper.GetValue(DbKeyS.pwd);
        String oldP = SpUtils.getSettingNote(Z_ChangePwd_Activity.this, DbKeyS.pwd);
        if (!oldP.equals(oldPwd)) {
            showMessage("旧密码输入错误");
            return;
        }

        ChangePwdObject changePwdObject = new ChangePwdObject();
        changePwdObject.setUserpass(newPwd);
        mPresenter.changepwdSucess(changePwdObject,Z_ChangePwd_Activity.this);

    }

    @Override
    public void changepwdSucess(String model) {
        showMessage("密码修改成功");
        Intent intent = new Intent(this, Z_Login_Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
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
}
