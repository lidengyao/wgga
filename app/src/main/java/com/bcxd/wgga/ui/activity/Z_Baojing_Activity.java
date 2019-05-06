package com.bcxd.wgga.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.adapter.BaoJingAdapter;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.bean.BaoJingBean;
import com.bcxd.wgga.model.info.BaoJingInfo;
import com.bcxd.wgga.model.info.ProjectInfo;
import com.bcxd.wgga.present.Z_BaoJing_Present;
import com.bcxd.wgga.ui.view.Z_BaoJing_View;
import com.bcxd.wgga.utils.TokenUtils;
import com.bcxd.wgga.widget.PullLoadMoreListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Z_Baojing_Activity extends MvpActivity<Z_BaoJing_Present> implements Z_BaoJing_View {


    @Bind(R.id.XmTitleTV)
    TextView XmTitleTV;
    @Bind(R.id.BaojingListView)
    PullLoadMoreListView BaojingListView;
    @Bind(R.id.HaveDataLL)
    LinearLayout HaveDataLL;
    @Bind(R.id.NoDataLL)
    LinearLayout NoDataLL;
    private ProjectInfo _projectInfo;
    private BaoJingAdapter baoJingAdapter;
    ArrayList<BaoJingInfo.RecordsBean> baoJingInfoArrayList = new ArrayList<>();
    private int pageIndex = 1;
    private int pageSize = 10;

    @Override
    protected Z_BaoJing_Present createPresenter() {
        return new Z_BaoJing_Present();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.z_activity_baojing;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        _projectInfo = (ProjectInfo) getIntent().getSerializableExtra("ProjectInfo");
        XmTitleTV.setText(_projectInfo.getPname());


        alertListbyuidandpage();


        baoJingAdapter = new BaoJingAdapter(Z_Baojing_Activity.this, baoJingInfoArrayList, R.layout.z_item_baojing);
        BaojingListView.setAdapter(baoJingAdapter);
        BaojingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                BaoJingInfo.RecordsBean recordsBean = (BaoJingInfo.RecordsBean) baoJingInfoArrayList.get(position);
                Intent intent = new Intent(Z_Baojing_Activity.this, Z_BaojingChuLi_Activity.class);
                intent.putExtra("RecordsBean", recordsBean);
                intent.putExtra("btype", -1 + "");
                startActivity(intent);

            }
        });
        BaojingListView.setOnPullLoadMoreListener(new PullLoadMoreListView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                pageIndex = 1;
                alertListbyuidandpage();
                BaojingListView.refreshComplete();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 500);

            }

            @Override
            public void onLoadMore() {
                pageIndex += 1;
                alertListbyuidandpage();
                BaojingListView.refreshComplete();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 500);
            }
        });
    }

    private void alertListbyuidandpage() {
        BaoJingBean baoJingBean = new BaoJingBean();
        baoJingBean.setCurrent(pageIndex);
        baoJingBean.setPid(_projectInfo.getPid());
        baoJingBean.setSize(pageSize);
        mPresenter.alertListbyuidandpage(baoJingBean,Z_Baojing_Activity.this);
    }

    @Override
    public void alertListbyuidandpageSuccess(BaoJingInfo model) {
        if (model == null || model.getRecords().size() == 0) {
            HaveDataLL.setVisibility(View.GONE);
            NoDataLL.setVisibility(View.VISIBLE);
            return;
        }
        HaveDataLL.setVisibility(View.VISIBLE);
        NoDataLL.setVisibility(View.GONE);
        if (this.pageIndex == 1) {
            baoJingInfoArrayList = model.getRecords();
            baoJingAdapter.refresh(baoJingInfoArrayList);
        } else {
            baoJingInfoArrayList.addAll(model.getRecords());
            baoJingAdapter.notifyDataSetChanged();
        }
        BaojingListView.refreshComplete();

        if (model.getRecords().size() < this.pageSize) {
            BaojingListView.loadMoreFinish(false, false);
        } else {
            BaojingListView.loadMoreFinish(false, true);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        alertListbyuidandpage();
    }

    @Override
    public void refreshtokenSuccess(String model) {
        TokenUtils.ResetToken(model,this);
    }

    @Override
    public void onFailure(int code, String msg) {
        if (code == 499) {
            mPresenter.refreshToken(Z_Baojing_Activity.this);
        } else if (code == 498) {
            //REFRESH TOKEN过期
            showMessage("登录超时，请重新登录");
            //ACCESS TOKEN过期
            Intent intent = new Intent(Z_Baojing_Activity.this, Z_Login_Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            showMessage(msg);
        }
    }
}
