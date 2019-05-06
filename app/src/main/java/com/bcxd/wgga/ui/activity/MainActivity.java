package com.bcxd.wgga.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bcxd.wgga.R;
import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.base.MvpActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lidengyao on 2016-09-17 0017.
 */
public class MainActivity extends MvpActivity {

    @Bind(R.id.BtnGetWebDataEndQuery)
    Button BtnGetWebDataEndQuery;
    @Bind(R.id.BtnGetWebDataBody)
    Button BtnGetWebDataBody;
    @Bind(R.id.BtnLoadMoreData)
    Button BtnLoadMoreData;
    @Bind(R.id.BtnUpLoadFile)
    Button BtnUpLoadFile;
    @Bind(R.id.BtnBanner)
    Button BtnBanner;
    @Bind(R.id.BtnLayoutRefresh)
    Button BtnLayoutRefresh;
    @Bind(R.id.BtnBottomMenu)
    Button BtnBottomMenu;
    @Bind(R.id.BtnMoreHeagImage)
    Button BtnMoreHeagImage;
    @Bind(R.id.BtnListViewItemSwitch)
    Button BtnListViewItemSwitch;
    @Bind(R.id.BtnTabLayout)
    Button BtnTabLayout;
    @Bind(R.id.JPush)
    EditText jpushEditText;


    public static boolean isForeground = false;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    @Override
    protected BasePresent createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        BtnGetWebDataEndQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BtnGetWebDataEndQueryIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(BtnGetWebDataEndQueryIntent);
            }
        });

        BtnGetWebDataBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BtnGetWebDataBodyIntent = new Intent(MainActivity.this, GetWebDataBodyAvtivity.class);
                startActivity(BtnGetWebDataBodyIntent);
            }
        });

        BtnLoadMoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BtnLoadMoreDataIntent = new Intent(MainActivity.this, BtnLoadMoreDataAvtivity.class);
                startActivity(BtnLoadMoreDataIntent);
            }
        });

        BtnUpLoadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BtnUpLoadFileIntent = new Intent(MainActivity.this, BtnUpLoadFileActivity.class);
                startActivity(BtnUpLoadFileIntent);
            }
        });

        BtnLayoutRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BtnLayoutRefreshIntent = new Intent(MainActivity.this, RefreshActivity.class);
                startActivity(BtnLayoutRefreshIntent);
            }
        });

        BtnBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BannerIntent = new Intent(MainActivity.this, BannerActivity.class);
                startActivity(BannerIntent);
            }
        });

        BtnBottomMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent BottomMenuIntent = new Intent(MainActivity.this, BottomMenuActivity.class);
                startActivity(BottomMenuIntent);
            }
        });

        BtnMoreHeagImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MoreHeadImageIntent = new Intent(MainActivity.this, MoreHeadImageActivity.class);
                startActivity(MoreHeadImageIntent);
            }
        });

        BtnListViewItemSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent ListViewItemSwitchIntent = new Intent(MainActivity.this, ListViewItemSwitchActivity.class);
//                startActivity(ListViewItemSwitchIntent);
            }
        });

        BtnTabLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TabLayoutIntent = new Intent(MainActivity.this, TabLayoutActivtiy.class);
                startActivity(TabLayoutIntent);
            }
        });
    }



    private void setCostomMsg(String msg){
        if (null != jpushEditText) {
            jpushEditText.setText(msg);
            jpushEditText.setVisibility(android.view.View.VISIBLE);
        }
    }
}
