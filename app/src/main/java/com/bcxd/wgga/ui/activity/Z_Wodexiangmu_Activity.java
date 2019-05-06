package com.bcxd.wgga.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.adapter.GongDiAdapter;
import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.info.GongDiInfo;
import com.bcxd.wgga.widget.PullLoadMoreListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Z_Wodexiangmu_Activity extends MvpActivity {
    @Bind(R.id.xiangmuListView)
    PullLoadMoreListView xiangmuListView;
    GongDiAdapter gongDiAdapter;

    @Override
    protected BasePresent createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.z_activity_wodexiangmu;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        ArrayList<GongDiInfo> gongDiInfoArrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GongDiInfo gongDiInfo = new GongDiInfo();
            gongDiInfoArrayList.add(gongDiInfo);
        }

        xiangmuListView.autoRefresh();
        gongDiAdapter = new GongDiAdapter(this, gongDiInfoArrayList, R.layout.z_item_gongdi);
        xiangmuListView.setAdapter(gongDiAdapter);
        xiangmuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent jianceIntent = new Intent(Z_Wodexiangmu_Activity.this, Z_GongDiXiangQing_Activity.class);
                startActivity(jianceIntent);
            }
        });

        xiangmuListView.setOnPullLoadMoreListener(new PullLoadMoreListView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
//                pageIndex = 1;
//                findHouseList();
                xiangmuListView.refreshComplete();
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
                xiangmuListView.refreshComplete();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 500);
            }
        });
    }
}
