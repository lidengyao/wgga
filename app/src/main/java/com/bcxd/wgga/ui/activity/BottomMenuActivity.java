package com.bcxd.wgga.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.base.BasePresent;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.ui.fragment.Ldy_LiShiQuXian_Fragment;
import com.bcxd.wgga.ui.fragment.Ldy_SheBeiKongZhi_Fragment;
import com.bcxd.wgga.ui.fragment.Ldy_SheShiXinXi_Fragment;
import com.bcxd.wgga.ui.fragment.Ldy_ShiShiShuJu_Fragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lidengyao on 2016-10-12 0012.
 */
public class BottomMenuActivity extends MvpActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private final static int PAGE_SHISHISHUJU = 0;
    private final static int PAGE_SHEBEIKONGZHI = 1;
    private final static int PAGE_LISHIQUXIAN = 2;
    private final static int PAGE_SHESHIXINXI = 3;
    @Bind(R.id.dapengTitleTV)
    TextView dapengTitleTV;
    @Bind(R.id.xiugaiRL)
    RelativeLayout xiugaiRL;
    @Bind(R.id.dapengTopLL)
    RelativeLayout dapengTopLL;
    @Bind(R.id.dapengContentFL)
    ViewPager dapengContentFL;
    @Bind(R.id.shishishujuIV)
    ImageView shishishujuIV;
    @Bind(R.id.shishishujuTV)
    TextView shishishujuTV;
    @Bind(R.id.ShiShiShuJuLL)
    LinearLayout ShiShiShuJuLL;
    @Bind(R.id.shebeikongzhiIV)
    ImageView shebeikongzhiIV;
    @Bind(R.id.shebeikongzhiTV)
    TextView shebeikongzhiTV;
    @Bind(R.id.SheBeiKongZhiLL)
    LinearLayout SheBeiKongZhiLL;
    @Bind(R.id.lishiquxianIV)
    ImageView lishiquxianIV;
    @Bind(R.id.lishiquxianTV)
    TextView lishiquxianTV;
    @Bind(R.id.LiShiQuXianLL)
    LinearLayout LiShiQuXianLL;
    @Bind(R.id.sheshixinxiIV)
    ImageView sheshixinxiIV;
    @Bind(R.id.sheshixinxiTV)
    TextView sheshixinxiTV;
    @Bind(R.id.SheShiXinXiLL)
    LinearLayout SheShiXinXiLL;
    @Bind(R.id.dapengBottomLL)
    LinearLayout dapengBottomLL;

    ArrayList<Fragment> fragments = new ArrayList<>();
    private MyFragmentPagerAdapter mPagerAdapter;

    @Override
    protected BasePresent createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bottommenu;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        ShiShiShuJuLL.setOnClickListener(this);
        SheBeiKongZhiLL.setOnClickListener(this);
        LiShiQuXianLL.setOnClickListener(this);
        SheShiXinXiLL.setOnClickListener(this);
        xiugaiRL.setOnClickListener(this);


        initPager();
    }

    private void initPager() {
        fragments = new ArrayList<>();
        fragments.add(new Ldy_ShiShiShuJu_Fragment());
        fragments.add(new Ldy_SheBeiKongZhi_Fragment());
        fragments.add(new Ldy_LiShiQuXian_Fragment());
        fragments.add(new Ldy_SheShiXinXi_Fragment());
        mPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        dapengContentFL.setAdapter(mPagerAdapter);
        dapengContentFL.addOnPageChangeListener(this);
        dapengContentFL.setOffscreenPageLimit(3);
        switchPage(0);
    }

    private void switchPage(int position) {
        switch (position) {
            case PAGE_SHISHISHUJU:
//                dapengTitleTV.setText(AppContext.pengListInfo.getPengName());
                xiugaiRL.setVisibility(View.GONE);
                shishishujuIV.setImageResource(R.mipmap.ldy_shishishujus);
                shishishujuTV.setTextColor(getResources().getColor(R.color.color5));

                shebeikongzhiIV.setImageResource(R.mipmap.ldy_shebeikongzhi);
                shebeikongzhiTV.setTextColor(getResources().getColor(R.color.color4));

                lishiquxianIV.setImageResource(R.mipmap.ldy_lishiquxian);
                lishiquxianTV.setTextColor(getResources().getColor(R.color.color4));

                sheshixinxiIV.setImageResource(R.mipmap.ldy_sheshixinxi);
                sheshixinxiTV.setTextColor(getResources().getColor(R.color.color4));
                break;
            case PAGE_SHEBEIKONGZHI:
                dapengTitleTV.setText("设备控制");
                xiugaiRL.setVisibility(View.GONE);
                shishishujuIV.setImageResource(R.mipmap.ldy_shishishuju);
                shishishujuTV.setTextColor(getResources().getColor(R.color.color4));

                shebeikongzhiIV.setImageResource(R.mipmap.ldy_shebeikongzhis);
                shebeikongzhiTV.setTextColor(getResources().getColor(R.color.color5));

                lishiquxianIV.setImageResource(R.mipmap.ldy_lishiquxian);
                lishiquxianTV.setTextColor(getResources().getColor(R.color.color4));

                sheshixinxiIV.setImageResource(R.mipmap.ldy_sheshixinxi);
                sheshixinxiTV.setTextColor(getResources().getColor(R.color.color4));
                break;
            case PAGE_LISHIQUXIAN:
                dapengTitleTV.setText("历史曲线");
                xiugaiRL.setVisibility(View.GONE);

                shishishujuIV.setImageResource(R.mipmap.ldy_shishishuju);
                shishishujuTV.setTextColor(getResources().getColor(R.color.color4));

                shebeikongzhiIV.setImageResource(R.mipmap.ldy_shebeikongzhi);
                shebeikongzhiTV.setTextColor(getResources().getColor(R.color.color4));

                lishiquxianIV.setImageResource(R.mipmap.ldy_lishiquxians);
                lishiquxianTV.setTextColor(getResources().getColor(R.color.color5));

                sheshixinxiIV.setImageResource(R.mipmap.ldy_sheshixinxi);
                sheshixinxiTV.setTextColor(getResources().getColor(R.color.color4));
                break;
            case PAGE_SHESHIXINXI:
                dapengTitleTV.setText("设施信息");
                xiugaiRL.setVisibility(View.VISIBLE);
                shishishujuIV.setImageResource(R.mipmap.ldy_shishishuju);
                shishishujuTV.setTextColor(getResources().getColor(R.color.color4));

                shebeikongzhiIV.setImageResource(R.mipmap.ldy_shebeikongzhi);
                shebeikongzhiTV.setTextColor(getResources().getColor(R.color.color4));

                lishiquxianIV.setImageResource(R.mipmap.ldy_lishiquxian);
                lishiquxianTV.setTextColor(getResources().getColor(R.color.color4));

                sheshixinxiIV.setImageResource(R.mipmap.ldy_sheshixinxis);
                sheshixinxiTV.setTextColor(getResources().getColor(R.color.color5));
                break;
        }

    }

    @Override
    public void onClick(View v) {
        setOnClickThrottleFirst(v);
        switch (v.getId()) {
            case R.id.ShiShiShuJuLL:
                dapengContentFL.setCurrentItem(PAGE_SHISHISHUJU, false);
                break;
            case R.id.SheBeiKongZhiLL:
                dapengContentFL.setCurrentItem(PAGE_SHEBEIKONGZHI, false);
                break;
            case R.id.LiShiQuXianLL:
                dapengContentFL.setCurrentItem(PAGE_LISHIQUXIAN, false);
                break;
            case R.id.SheShiXinXiLL:
                dapengContentFL.setCurrentItem(PAGE_SHESHIXINXI, false);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switchPage(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {


        public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

    }

}
