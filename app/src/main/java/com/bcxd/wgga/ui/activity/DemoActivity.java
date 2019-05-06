package com.bcxd.wgga.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.bcxd.wgga.R;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.present.BannerPresent;
import com.bcxd.wgga.utils.AutoLinefeedLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lidengyao on 2016-10-11 0011.
 */
public class DemoActivity extends MvpActivity {

    @Bind(R.id.tongyi)
    CheckBox tongyi;
    @Bind(R.id.aa)
    CheckBox aa;
    @Bind(R.id.bb)
    CheckBox bb;
    @Bind(R.id.cc)
    CheckBox cc;
    @Bind(R.id.dd)
    CheckBox dd;
    @Bind(R.id.CheckBoxList)
    AutoLinefeedLayout CheckBoxList;
    @Bind(R.id.btn_sub)
    Button btnSub;

    @Override
    protected BannerPresent createPresenter() {
        return new BannerPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.demo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer sb=new StringBuffer();
                //拿到所有的子类长度
                int cNum = CheckBoxList.getChildCount();
                for (int i = 0; i < cNum; i++) {

                    View view= CheckBoxList.getChildAt(i);
                    if(view instanceof CheckBox)
                    {
                        //根据i 拿到每一个CheckBox
                        CheckBox cb= (CheckBox) CheckBoxList.getChildAt(i);
                        //判断CheckBox是否被选中
                        if(cb.isChecked()){
                            //把被选中的文字添加到StringBuffer中
                            sb.append(cb.getText().toString());
                        }
                    }

                }
                Toast.makeText(DemoActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
