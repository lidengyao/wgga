package com.bcxd.wgga.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;

import com.bcxd.wgga.R;
import com.bcxd.wgga.adapter.CommonMessageAdapter;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.info.CommonMessageInfo;
import com.bcxd.wgga.present.BtnLoadMoreDataPresent;
import com.bcxd.wgga.ui.view.BtnLoadMoreDataView;
import com.bcxd.wgga.widget.PullLoadMoreListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lidengyao on 2016-09-17 0017.
 */
public class BtnLoadMoreDataAvtivity extends MvpActivity<BtnLoadMoreDataPresent> implements BtnLoadMoreDataView {
    @Bind(R.id.homeTopLL)
    RelativeLayout homeTopLL;
    @Bind(R.id.XiaoXiListView)
    PullLoadMoreListView XiaoXiListView;
    CommonMessageAdapter commonMessageAdapter;
    /**
     * 当前页码
     */
    private int pageIndex = 1;
    /**
     * 列表数据集合
     */
    private ArrayList<CommonMessageInfo> commonMessageInfos = new ArrayList<CommonMessageInfo>();

    @Override
    protected BtnLoadMoreDataPresent createPresenter() {
        return new BtnLoadMoreDataPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_btnloadmoredata;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        commonMessageAdapter = new CommonMessageAdapter(this, commonMessageInfos, R.layout.ldy_item_xiaoxizhongxin);
        XiaoXiListView.setAdapter(commonMessageAdapter);
        XiaoXiListView.setOnPullLoadMoreListener(new PullLoadMoreListView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                pageIndex = 1;
                commonMessage();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 2000);

            }

            @Override
            public void onLoadMore() {
                pageIndex += 1;
                commonMessage();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 2000);
            }
        });
        XiaoXiListView.autoRefresh();
    }


    private void commonMessage() {
//        if (this.pageIndex == 1) {
//            ArrayList<CommonMessageInfo> commonMessageInfos = new ArrayList<>();
//            for (int i = 0; i < 10; i++) {
//                CommonMessageInfo commonMessageInfo=new CommonMessageInfo();
//                commonMessageInfo.setMessageId(i);
//            }
//
//            commonMessageInfos = model;
//            commonMessageAdapter.refresh(commonMessageInfos);
//        } else {
//            commonMessageInfos.addAll(model);
//            commonMessageAdapter.notifyDataSetChanged();
//        }
//        XiaoXiListView.refreshComplete();
//        if (commonMessageInfos.size() == 0) {
//            XiaoXiListView.setVisibility(View.GONE);
//        } else {
//            XiaoXiListView.setVisibility(View.VISIBLE);
//            //判断有没有下一页了
//            if (model.size() < 10) {
//                XiaoXiListView.loadMoreFinish(false, false);
//            } else {
//                XiaoXiListView.loadMoreFinish(false, true);
//            }
//        }
        mPresenter.commonMessage("6d53fb5a-8969-465c-9097-38a944f0b399", "1", pageIndex + "", "10",this);
    }


    @Override
    public void Success(ArrayList<CommonMessageInfo> model) {
        if (this.pageIndex == 1) {

            commonMessageInfos = model;
            commonMessageAdapter.refresh(commonMessageInfos);
        } else {
            commonMessageInfos.addAll(model);
            commonMessageAdapter.notifyDataSetChanged();
        }
        XiaoXiListView.refreshComplete();
        if (commonMessageInfos.size() == 0) {
            XiaoXiListView.setVisibility(View.GONE);
        } else {
            XiaoXiListView.setVisibility(View.VISIBLE);
            //判断有没有下一页了
            if (model.size() < 10) {
                XiaoXiListView.loadMoreFinish(false, false);
            } else {
                XiaoXiListView.loadMoreFinish(false, true);
            }
        }
    }
}
