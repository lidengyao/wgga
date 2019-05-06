package com.bcxd.wgga.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.adapter.W_XiaoXiAdapter;
import com.bcxd.wgga.adapter.XiaoXiAdapter;
import com.bcxd.wgga.base.MvpFragment;
import com.bcxd.wgga.model.info.XiaoXiInfo;
import com.bcxd.wgga.present.Z_XiaoXi_Present;
import com.bcxd.wgga.ui.activity.Z_Login_Activity;
import com.bcxd.wgga.ui.view.Z_XiaoXi_View;
import com.bcxd.wgga.utils.TokenUtils;
import com.bcxd.wgga.widget.CenterTextView;
import com.bcxd.wgga.widget.PullLoadMoreListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lidengyao on 2016-10-12 0012.
 */
public class W_XiaoXi_Fragment extends MvpFragment<Z_XiaoXi_Present> implements Z_XiaoXi_View {

    W_XiaoXiAdapter w_xiaoXiAdapter;
    @Bind(R.id.imageOne)
    ImageView imageOne;
    @Bind(R.id.nameOne)
    CenterTextView nameOne;
    @Bind(R.id.XiaoXiListView)
    PullLoadMoreListView XiaoXiListView;
    /**
     * 当前页码
     */
    private int pageIndex = 1;
    private int pageSize = 10;
    private int sid = -1;
    private ArrayList<XiaoXiInfo.RecordsBean> recordsBeanArrayList=new ArrayList<>();

    @Override
    protected Z_XiaoXi_Present createPresenter() {
        return new Z_XiaoXi_Present();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.w_fragment_xiaoxi_judu;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);


        for (int i = 0; i < 10; i++) {
            XiaoXiInfo.RecordsBean recordsBean = new XiaoXiInfo.RecordsBean();
            recordsBeanArrayList.add(recordsBean);
        }

//        XiaoXiListView.autoRefresh();
        w_xiaoXiAdapter = new W_XiaoXiAdapter(getActivity(), recordsBeanArrayList, R.layout.w_item_xiaoxi);
        XiaoXiListView.setAdapter(w_xiaoXiAdapter);

        XiaoXiListView.setOnPullLoadMoreListener(new PullLoadMoreListView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                pageIndex = 1;
                if (sid == -1) {
//                    getMsgList();
                } else {
//                    getMsgByIdList();
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 500);

            }

            @Override
            public void onLoadMore() {
                pageIndex += 1;
                if (sid == -1) {
//                    getMsgList();
                } else {
//                    getMsgByIdList();
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 500);
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void BandData(XiaoXiInfo model) {
        if (model == null)
            return;


    }

    @Override
    public void listSuccess(XiaoXiInfo model) {
        BandData(model);
    }

    @Override
    public void listBySidSuccess(XiaoXiInfo model) {
        BandData(model);
    }

    @Override
    public void refreshtokenSuccess(String model) {
        TokenUtils.ResetToken(model, getContext());
    }

    @Override
    public void onFailure(int code, String msg) {

        if (code == 499) {
            presenter.refreshToken(getContext());
        } else if (code == 498) {
            //REFRESH TOKEN过期
            showMessage("登录超时，请重新登录");
            //ACCESS TOKEN过期
            Intent intent = new Intent(getContext(), Z_Login_Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            showMessage(msg);
        }
    }
}
