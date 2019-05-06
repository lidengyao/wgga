package com.bcxd.wgga.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.adapter.ZhengGaiAdapter;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.bean.ZhengGaiBean;
import com.bcxd.wgga.model.info.ProjectInfo;
import com.bcxd.wgga.model.info.ZhengGaiInfo;
import com.bcxd.wgga.present.Z_ZhengGai_Present;
import com.bcxd.wgga.ui.view.Z_ZhengGai_View;
import com.bcxd.wgga.utils.TokenUtils;
import com.bcxd.wgga.widget.PullLoadMoreListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Z_ZhengGai_Activity extends MvpActivity<Z_ZhengGai_Present> implements Z_ZhengGai_View {


    @Bind(R.id.XmTitleTV)
    TextView XmTitleTV;
    @Bind(R.id.ZhengGaiListView)
    PullLoadMoreListView zhenggaiListView;
    private ProjectInfo _projectInfo;
    private ZhengGaiAdapter zhengGaiAdapter;
    ArrayList<ZhengGaiInfo.RecordsBean> baoJingInfoArrayList = new ArrayList<>();
    private int pageIndex = 1;
    private int pageSize = 10;

    @Override
    protected Z_ZhengGai_Present createPresenter() {
        return new Z_ZhengGai_Present();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.z_activity_zhenggai;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        _projectInfo = (ProjectInfo) getIntent().getSerializableExtra("ProjectInfo");
        XmTitleTV.setText(_projectInfo.getPname());

        querypagebycondition();
        zhengGaiAdapter = new ZhengGaiAdapter(Z_ZhengGai_Activity.this, baoJingInfoArrayList, R.layout.z_item_zhenggai);
        zhenggaiListView.setAdapter(zhengGaiAdapter);
        zhenggaiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ZhengGaiInfo.RecordsBean recordsBean = (ZhengGaiInfo.RecordsBean) baoJingInfoArrayList.get(position);
                Intent intent = new Intent(Z_ZhengGai_Activity.this, Z_ZhengGaiChuLi_Activity.class);
                intent.putExtra("RecordsBean", recordsBean);
                intent.putExtra("btype", -1 + "");
                startActivity(intent);

            }
        });
        zhenggaiListView.setOnPullLoadMoreListener(new PullLoadMoreListView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                pageIndex = 1;
                querypagebycondition();
                zhenggaiListView.refreshComplete();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 500);

            }

            @Override
            public void onLoadMore() {
                pageIndex += 1;
                querypagebycondition();
                zhenggaiListView.refreshComplete();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 500);
            }
        });
    }

    private void querypagebycondition() {

        ZhengGaiBean zhengGaiBean = new ZhengGaiBean();
        zhengGaiBean.setCurrent(pageIndex);
        zhengGaiBean.setPid(_projectInfo.getPid());
        zhengGaiBean.setSize(pageSize);
        zhengGaiBean.setZtype(0);
        mPresenter.querypagebycondition(zhengGaiBean,this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        querypagebycondition();
    }

    @Override
    public void querypagebyconditionSuccess(ZhengGaiInfo model) {
        if (model == null)
            return;

        if (this.pageIndex == 1) {
            baoJingInfoArrayList = model.getRecords();
            zhengGaiAdapter.refresh(baoJingInfoArrayList);
        } else {
            baoJingInfoArrayList.addAll(model.getRecords());
            zhengGaiAdapter.notifyDataSetChanged();
        }
        zhenggaiListView.refreshComplete();

        if (model.getRecords().size() < this.pageSize) {
            zhenggaiListView.loadMoreFinish(false, false);
        } else {
            zhenggaiListView.loadMoreFinish(false, true);
        }

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
            Intent intent = new Intent(Z_ZhengGai_Activity.this, Z_Login_Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            showMessage(msg);
        }
    }
}
