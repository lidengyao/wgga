package com.bcxd.wgga.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bcxd.wgga.AppContext;
import com.bcxd.wgga.R;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.info.RuleIndexInfo;
import com.bcxd.wgga.present.W_ChooseSystemPresent;
import com.bcxd.wgga.ui.view.W_ChooseSystemView;
import com.bcxd.wgga.utils.DbKeyS;
import com.bcxd.wgga.utils.SpUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class W_ChooseSystem_Activity extends MvpActivity<W_ChooseSystemPresent> implements W_ChooseSystemView {


    @Bind(R.id.juDuIV)
    ImageView juDuIV;
    @Bind(R.id.yiZhiBaoIV)
    ImageView yiZhiBaoIV;
    @Bind(R.id.baoZhaIV)
    ImageView baoZhaIV;
    @Bind(R.id.yanHuaIV)
    ImageView yanHuaIV;
    @Bind(R.id.MainLL)
    LinearLayout MainLL;
    @Bind(R.id.topLL)
    LinearLayout topLL;
    @Bind(R.id.downLL)
    LinearLayout downLL;
    private String JUDU = "100";
    private String YIZHIBAO = "101";
    private String BAOZHA = "102";
    private String YANHUA = "103";

    private ArrayList<String> systemS = new ArrayList<>();

    @Override
    protected W_ChooseSystemPresent createPresenter() {
        return new W_ChooseSystemPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.w_activity_choosesystem;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏

        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);


        if (AppContext.offLine) {
            SetOffLineData();
        } else {
            mPresenter.ruleIndex(this);
        }

    }

    private int getmipmap(String type) {
        if (type.equals(JUDU)) {
            return R.mipmap.judu;
        }
        if (type.equals(YIZHIBAO)) {
            return R.mipmap.yizhibao;
        }
        if (type.equals(BAOZHA)) {
            return R.mipmap.baozha;
        }
        if (type.equals(YANHUA)) {
            return R.mipmap.yanhua;
        }

        return -1;
    }

    private void ImageOnClick(ImageView imageView, final String type) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (type.equals(JUDU)) {
                    SpUtils.saveSettingNote(W_ChooseSystem_Activity.this, DbKeyS.systype, JUDU);
                }
                if (type.equals(YIZHIBAO)) {
                    SpUtils.saveSettingNote(W_ChooseSystem_Activity.this, DbKeyS.systype, YIZHIBAO);
                }
                if (type.equals(BAOZHA)) {
                    SpUtils.saveSettingNote(W_ChooseSystem_Activity.this, DbKeyS.systype, BAOZHA);
                }
                if (type.equals(YANHUA)) {
                    SpUtils.saveSettingNote(W_ChooseSystem_Activity.this, DbKeyS.systype, YANHUA);
                }
                Intent mainIntent = new Intent(W_ChooseSystem_Activity.this, W_Main_Activity.class);
                startActivity(mainIntent);
            }
        });
    }

    public int px2Dip(int px) {

        return this.getResources().getDimensionPixelSize(R.dimen.base_dip) * px;
    }

    private void SetOffLineData() {

        systemS.add(JUDU);
        systemS.add(YIZHIBAO);
        systemS.add(BAOZHA);
        systemS.add(YANHUA);

        SetData();
    }

    private void SetData() {
        if (systemS.size() == 2) {

            ImageView imageView = new ImageView(this);
            ViewGroup.LayoutParams keyTVParams = new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(keyTVParams);
            imageView.setImageResource(getmipmap(systemS.get(0)));
            ImageOnClick(imageView, systemS.get(0));
            MainLL.addView(imageView);

            ImageView imageView2 = new ImageView(this);
            ViewGroup.LayoutParams keyTVParams2 = new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView2.setLayoutParams(keyTVParams2);
            ((AppBarLayout.LayoutParams) keyTVParams2).topMargin = px2Dip(20);
            imageView2.setImageResource(getmipmap(systemS.get(1)));
            ImageOnClick(imageView2, systemS.get(1));

            MainLL.addView(imageView2);
        }
        if (systemS.size() == 3) {
            LinearLayout hangLL = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            hangLL.setOrientation(LinearLayout.HORIZONTAL);
            layoutParams.gravity = Gravity.CENTER;
            hangLL.setLayoutParams(layoutParams);

            ImageView imageView = new ImageView(this);
            ViewGroup.LayoutParams keyTVParams = new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(keyTVParams);
            imageView.setImageResource(getmipmap(systemS.get(0)));
            ImageOnClick(imageView, systemS.get(0));
            hangLL.addView(imageView);


            ImageView imageView2 = new ImageView(this);
            ViewGroup.LayoutParams keyTVParams2 = new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView2.setLayoutParams(keyTVParams2);
            ((AppBarLayout.LayoutParams) keyTVParams2).leftMargin = px2Dip(20);
            imageView2.setImageResource(getmipmap(systemS.get(1)));
            ImageOnClick(imageView2, systemS.get(1));
            hangLL.addView(imageView2);

            MainLL.addView(hangLL);

            LinearLayout hangLL2 = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            hangLL2.setOrientation(LinearLayout.HORIZONTAL);
            layoutParams3.topMargin = px2Dip(10);
            layoutParams3.gravity = Gravity.CENTER;
            hangLL2.setLayoutParams(layoutParams3);

            ImageView imageView3 = new ImageView(this);
            ViewGroup.LayoutParams keyTVParams3 = new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView3.setLayoutParams(keyTVParams3);
            imageView3.setImageResource(getmipmap(systemS.get(2)));
            ImageOnClick(imageView3, systemS.get(2));
            hangLL2.addView(imageView3);


            MainLL.addView(hangLL2);

        }
        if (systemS.size() == 4) {
            topLL.setVisibility(View.VISIBLE);
            downLL.setVisibility(View.VISIBLE);
        }


        ImageOnClick(juDuIV, JUDU);
        ImageOnClick(yiZhiBaoIV, YIZHIBAO);
        ImageOnClick(baoZhaIV, BAOZHA);
        ImageOnClick(yanHuaIV, YANHUA);
    }

    @Override
    public void ruleIndexSuccess(ArrayList<RuleIndexInfo> model) {
        for (int i = 0; i < model.size(); i++) {
            systemS.add(model.get(i).getId() + "");
        }

        SetData();
    }

    @Override
    public void onFailure(int code, String msg) {
        showMessage(msg);
    }


}
