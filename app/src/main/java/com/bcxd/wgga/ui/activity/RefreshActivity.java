package com.bcxd.wgga.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.base.MvpActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lidengyao on 2016-09-27 0027.
 */
public class RefreshActivity extends MvpActivity {
    @Bind(R.id.textView1)
    TextView textView1;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;

    @Override
    protected BasePresent createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_layoutrefresh;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        //设置刷新时动画的颜色，可以设置4个
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // TODO Auto-generated method stub
                showMessage("在这里执行需要的事件");

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        swipeContainer.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }
}
