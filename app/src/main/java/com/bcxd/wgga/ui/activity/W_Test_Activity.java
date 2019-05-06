package com.bcxd.wgga.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.adapter.W_DicAdapter;
import com.bcxd.wgga.adapter.W_XiaoXiAdapter;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.model.bean.W_LoginInfo;
import com.bcxd.wgga.model.info.XiaoXiInfo;
import com.bcxd.wgga.present.W_LoginPresent;
import com.bcxd.wgga.ui.view.W_LoginView;
import com.bcxd.wgga.widget.DicData;
import com.bcxd.wgga.widget.PullLoadMoreListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class W_Test_Activity extends MvpActivity<W_LoginPresent> implements W_LoginView {
    @Bind(R.id.SysNameIV)
    TextView SysName;
    @Bind(R.id.SaveRL)
    RelativeLayout SaveRL;
    @Bind(R.id.DataListView)
    PullLoadMoreListView testListView;
    @Bind(R.id.SerchTV)
    TextView SerchTV;
    @Bind(R.id.title_bar)
    RelativeLayout titleBar;
    @Bind(R.id.fragment_layout)
    LinearLayout fragmentLayout;
    @Bind(R.id.ChildLL)
    LinearLayout ChildLL;
    @Bind(R.id.right)
    RelativeLayout right;
    @Bind(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    @Bind(R.id.ChongZhiOKLL)
    LinearLayout ChongZhiLL;
    @Bind(R.id.FilterLL)
    LinearLayout ContentLL;
    @Bind(R.id.DicListView)
    PullLoadMoreListView DicListView;
    /**
     * 当前页码
     */
    private int pageIndex = 1;
    private int pageSize = 10;
    private int sid = -1;
    private ArrayList<XiaoXiInfo.RecordsBean> recordsBeanArrayList = new ArrayList<>();
    W_XiaoXiAdapter w_xiaoXiAdapter;

    @Override
    protected W_LoginPresent createPresenter() {
        return new W_LoginPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.w_activity_test;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

//        final ArrayList<Normal> normalArrayList = AnalyzeNormalXml.Analyze(this, this, "test", filterLL, ChildLL, DicListView);

        final ArrayList<DicData> dicDataArrayList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            DicData dicData = new DicData();
            dicData.setKey(i + "");
            dicData.setValue(i + "value");
            dicDataArrayList.add(dicData);
        }

        W_DicAdapter w_dicAdapter = new W_DicAdapter(this, dicDataArrayList, R.layout.w_item_dicdata);
        DicListView.setAdapter(w_dicAdapter);

        DicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DicData tempDicData = dicDataArrayList.get(position);

                EditText callBackET = (EditText) DicListView.getTag();
                callBackET.setText(tempDicData.getValue());

                Animation outAnima = new TranslateAnimation(0, getWindowManager().getDefaultDisplay().getWidth(), 0, 0); //设置入动画
                outAnima.setDuration(300);
                ChildLL.setAnimation(outAnima);
                ChildLL.setVisibility(View.GONE);

                callBackET.setTag(tempDicData);
            }
        });
        SerchTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.openDrawer(Gravity.RIGHT);
            }
        });

        for (int i = 0; i < 10; i++) {
            XiaoXiInfo.RecordsBean recordsBean = new XiaoXiInfo.RecordsBean();
            recordsBeanArrayList.add(recordsBean);
        }

//        XiaoXiListView.autoRefresh();
        w_xiaoXiAdapter = new W_XiaoXiAdapter(this, recordsBeanArrayList, R.layout.w_item_xiaoxi);
        testListView.setAdapter(w_xiaoXiAdapter);

        testListView.setOnPullLoadMoreListener(new PullLoadMoreListView.PullLoadMoreListener() {
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

        drawerlayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                right.setClickable(true);
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

    }

    @Override
    public void loginSuccess(W_LoginInfo model) {

    }

    @Override
    public void onFailure(int code, String msg) {

    }

}
