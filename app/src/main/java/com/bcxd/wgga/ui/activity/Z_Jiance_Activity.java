package com.bcxd.wgga.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.adapter.JianCeAdapter;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.info.ProjectInfo;
import com.bcxd.wgga.model.info.RealtimeInfo;
import com.bcxd.wgga.present.Z_Jiance_Present;
import com.bcxd.wgga.ui.view.Z_JianCe_View;
import com.bcxd.wgga.utils.TokenUtils;
import com.bcxd.wgga.widget.PullLoadMoreListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Z_Jiance_Activity extends MvpActivity<Z_Jiance_Present> implements Z_JianCe_View {
    @Bind(R.id.jianceListView)
    PullLoadMoreListView jianceListView;
    JianCeAdapter jianCeAdapter;
    @Bind(R.id.JianCeTitle)
    TextView JianCeTitle;

    @Override
    protected Z_Jiance_Present createPresenter() {
        return new Z_Jiance_Present();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.z_activity_jiance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        ProjectInfo _projectInfo = (ProjectInfo) getIntent().getSerializableExtra("ProjectInfo");
        JianCeTitle.setText(_projectInfo.getPname());
        mPresenter.realtimebypid(_projectInfo.getPid(),this);
    }

    @Override
    public void realtimebysiteidSuccess(ArrayList<RealtimeInfo> model) {


//        jianceListView.autoRefresh();
        jianCeAdapter = new JianCeAdapter(this, model, R.layout.z_item_jiance);
        jianceListView.setAdapter(jianCeAdapter);


        jianceListView.setOnPullLoadMoreListener(new PullLoadMoreListView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
//                pageIndex = 1;
//                findHouseList();
                jianceListView.refreshComplete();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 500);

            }

            @Override
            public void onLoadMore() {
//                pageIndex += 1;
//                findHouseList();
                jianceListView.refreshComplete();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 500);
            }
        });
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
