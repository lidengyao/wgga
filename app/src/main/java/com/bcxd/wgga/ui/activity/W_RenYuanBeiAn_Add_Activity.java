package com.bcxd.wgga.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.bean.ToxicCompanyMemberBean;
import com.bcxd.wgga.present.W_RenYuanBeiAn_AddPresent;
import com.bcxd.wgga.ui.view.W_RenYuanBeiAn_AddView;
import com.bcxd.wgga.utils.AnalyzeNormalXml;
import com.bcxd.wgga.utils.ObjectControl;
import com.bcxd.wgga.widget.Normal;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class W_RenYuanBeiAn_Add_Activity extends MvpActivity<W_RenYuanBeiAn_AddPresent> implements W_RenYuanBeiAn_AddView {

    @Bind(R.id.SysNameIV)
    TextView SysName;
    @Bind(R.id.SaveRL)
    RelativeLayout SaveRL;
    @Bind(R.id.RootLL)
    LinearLayout RootLL;
    @Bind(R.id.SaveBtn)
    Button SaveBtn;
    @Bind(R.id.MainLL)
    LinearLayout MainLL;

    @Override
    protected W_RenYuanBeiAn_AddPresent createPresenter() {
        return new W_RenYuanBeiAn_AddPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.w_activity_renyuanbeian_add;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        final ArrayList<Normal> tempNormalList = AnalyzeNormalXml.Analyze(this, this, "renyuanbeian_add", RootLL, "1", null, null,MainLL);

        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ArrayList<Normal> normalArrayList = AnalyzeNormalXml.GetAnalyzeData(tempNormalList);
                if (ObjectControl.IsNullDialog(W_RenYuanBeiAn_Add_Activity.this, normalArrayList) == false)
                    return;
                try {
                    ToxicCompanyMemberBean toxicCompanyMemberBean = new ToxicCompanyMemberBean();
                    Field[] fields = toxicCompanyMemberBean.getClass().getDeclaredFields();
                    for (int i = 0; i < fields.length; i++) {
                        String filedName = fields[i].getName();
                        if (fields[i].isSynthetic())
                            continue;
                        if (filedName.equals("serialVersionUID"))
                            continue;
                        fields[i].setAccessible(true);

                        //如果normalArrayList里包含filedName就继续
                        if (isHavFieldName(filedName, normalArrayList))
                            fields[i].set(toxicCompanyMemberBean, getData(filedName, normalArrayList));
                    }

                    toxicCompanyMemberBean.setCompanyId("1");//写死
                    toxicCompanyMemberBean.setCertificateType("1");//写死
                    toxicCompanyMemberBean.setIdcardFront("13213.jpg");//写死
                    toxicCompanyMemberBean.setIdcardBack("13213.jpg");//写死
                    toxicCompanyMemberBean.setBirthday("1994-01-01");//写死
                    mPresenter.toxicCompanyMemberadd(W_RenYuanBeiAn_Add_Activity.this, toxicCompanyMemberBean);
//                    showMessage("ddd");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private Object getData(String fieldName, ArrayList<Normal> normalArrayList) {
        Object value = null;
        for (int i = 0; i < normalArrayList.size(); i++) {
            Normal normal = normalArrayList.get(i);
            if (normal.getKey().equals(fieldName)) {
                value = normal.getValue();
                break;
            }
        }
        return value;

    }

    private boolean isHavFieldName(String fieldName, ArrayList<Normal> normalArrayList) {
        boolean b = false;
        for (int i = 0; i < normalArrayList.size(); i++) {
            Normal normal = normalArrayList.get(i);
            if (normal.getKey().equals(fieldName)) {
                b = true;
            }
        }
        return b;

    }

    @Override
    public void toxicCompanyMemberaddSuccess(String model) {
        showMessage("添加成功");
        finish();
    }

    @Override
    public void onFailure(int code, String msg) {
        showMessage(msg);
    }

}
