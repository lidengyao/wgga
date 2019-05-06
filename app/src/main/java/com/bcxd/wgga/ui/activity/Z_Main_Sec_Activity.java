package com.bcxd.wgga.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bcxd.wgga.BuildConfig;
import com.bcxd.wgga.R;
import com.bcxd.wgga.base.MvpActivity;
import com.bcxd.wgga.jpush.ExampleUtil;
import com.bcxd.wgga.jpush.LocalBroadcastManager;
import com.bcxd.wgga.model.info.GongDiInfo;
import com.bcxd.wgga.model.info.ProjectInfo;
import com.bcxd.wgga.model.info.VersionInfo;
import com.bcxd.wgga.model.info.Z_UserInfo;
import com.bcxd.wgga.present.Z_MainPresent;
import com.bcxd.wgga.ui.fragment.Z_ShouYe_Fragment;
import com.bcxd.wgga.ui.fragment.Z_Wo_Fragment;
import com.bcxd.wgga.ui.fragment.Z_XiaoXi_Fragment;
import com.bcxd.wgga.ui.view.Z_MainSecView;
import com.bcxd.wgga.utils.DbKeyS;
import com.bcxd.wgga.utils.SpUtils;
import com.bcxd.wgga.utils.TokenUtils;
import com.bcxd.wgga.widget.NoScrollViewPager;
import com.bcxd.wgga.widget.UpdateManager;
import com.google.gson.Gson;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Z_Main_Sec_Activity extends MvpActivity<Z_MainPresent> implements Z_MainSecView, View.OnClickListener, ViewPager.OnPageChangeListener {

    @Bind(R.id.jiankongContentFL)
    NoScrollViewPager jiankongContentFL;
    @Bind(R.id.ShouYeIV)
    ImageView ShouYeIV;
    @Bind(R.id.ShouYeTV)
    TextView ShouYeTV;
    @Bind(R.id.ShouYeLL)
    LinearLayout ShouYeLL;
    @Bind(R.id.xiaoxiIV)
    ImageView xiaoxiIV;
    @Bind(R.id.xiaoxiTV)
    TextView xiaoxiTV;
    @Bind(R.id.xiaoxiLL)
    LinearLayout xiaoxiLL;
    @Bind(R.id.woIV)
    ImageView woIV;
    @Bind(R.id.woTV)
    TextView woTV;
    @Bind(R.id.woLL)
    LinearLayout woLL;
    @Bind(R.id.ShouYeBottomLL)
    LinearLayout ShouyeBottomLL;

    ArrayList<Fragment> fragments = new ArrayList<>();
    private MyFragmentPagerAdapter mPagerAdapter;
    private final static int PAGE_SHOUYE = 0;
    private final static int PAGE_XIAOXI = 1;
    private final static int PAGE_WO = 2;
    private Rationale mRationale;
    public static boolean isForeground = false;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    @Override
    protected Z_MainPresent createPresenter() {
        return new Z_MainPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.z_activity_main_sec;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        String[] pers = new String[5];
        pers[0] = Permission.READ_EXTERNAL_STORAGE;
        pers[1] = Permission.WRITE_EXTERNAL_STORAGE;
        pers[2] = Permission.RECORD_AUDIO;
        pers[3] = Permission.CAMERA;
        pers[4] = Permission.READ_PHONE_STATE;
        requestPermission(pers);
        mPresenter.getuserinfo(this);

        mPresenter.checkVersion(this);
        registerMessageReceiver();  // used for receive msg
    }

    private int getVersionCode(Context context) {
        int versionCode = 0;
        versionCode = BuildConfig.VERSION_CODE;
        return versionCode;
    }

    //读取文件权限
    private boolean IS_READ_EXTERNAL_STORAGE = true;

    //写文件权限
    private boolean IS_WRITE_EXTERNAL_STORAGE = true;

    //录音权限
    private boolean IS_RECORD_AUDIO = true;

    //相机权限
    private boolean IS_CAMERA = true;

    //
    private boolean IS_READ_PHONE_STATE = true;

    private void requestPermission(String... permissions) {
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        if (permissions.contains("android.permission.READ_EXTERNAL_STORAGE")) {
                            IS_READ_EXTERNAL_STORAGE = true;
                        }
                        if (permissions.contains("android.permission.WRITE_EXTERNAL_STORAGE")) {
                            IS_WRITE_EXTERNAL_STORAGE = true;
                        }
                        if (permissions.contains("android.permission.RECORD_AUDIO")) {
                            IS_RECORD_AUDIO = true;
                        }
                        if (permissions.contains("android.permission.CAMERA")) {
                            IS_CAMERA = true;
                        }
                        if (permissions.contains("android.permission.READ_PHONE_STATE")) {
                            IS_READ_PHONE_STATE = true;
                        }

//                        toast(R.string.successfully);
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        //android.permission.READ_SMS
                        if (permissions.contains("android.permission.READ_EXTERNAL_STORAGE")) {
                            IS_READ_EXTERNAL_STORAGE = false;
                        }
                        if (permissions.contains("android.permission.WRITE_EXTERNAL_STORAGE")) {
                            IS_WRITE_EXTERNAL_STORAGE = false;
                        }
                        if (permissions.contains("android.permission.RECORD_AUDIO")) {
                            IS_RECORD_AUDIO = false;
                        }

                        if (permissions.contains("android.permission.CAMERA")) {
                            IS_CAMERA = false;
                        }
                        if (permissions.contains("android.permission.READ_PHONE_STATE")) {
                            IS_READ_PHONE_STATE = false;
                        }

                    }
                })
                .start();
    }

    private void initPager() {
        fragments = new ArrayList<>();
        fragments.add(new Z_ShouYe_Fragment());
        fragments.add(new Z_XiaoXi_Fragment());
        fragments.add(new Z_Wo_Fragment());
        mPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        jiankongContentFL.setAdapter(mPagerAdapter);
        jiankongContentFL.addOnPageChangeListener(this);
        jiankongContentFL.setOffscreenPageLimit(2);
        switchPage(0);
    }

    private void switchPage(int position) {
        switch (position) {
            case PAGE_SHOUYE:
                ShouYeIV.setImageResource(R.mipmap.shouyelan);
                ShouYeTV.setTextColor(getResources().getColor(R.color.color5));


                xiaoxiIV.setImageResource(R.mipmap.xiaoxiiconhui);
                xiaoxiTV.setTextColor(getResources().getColor(R.color.color4));

                woIV.setImageResource(R.mipmap.woiconhui);
                woTV.setTextColor(getResources().getColor(R.color.color4));
                break;
            case PAGE_XIAOXI:
//                maintitleTV.setText("消息");
                ShouYeIV.setImageResource(R.mipmap.shouyehui);
                ShouYeTV.setTextColor(getResources().getColor(R.color.color4));


                xiaoxiIV.setImageResource(R.mipmap.xiaoxiicon);
                xiaoxiTV.setTextColor(getResources().getColor(R.color.color5));

                woIV.setImageResource(R.mipmap.woiconhui);
                woTV.setTextColor(getResources().getColor(R.color.color4));
                break;

            case PAGE_WO:
//                maintitleTV.setText("我");
                ShouYeIV.setImageResource(R.mipmap.shouyehui);
                ShouYeTV.setTextColor(getResources().getColor(R.color.color4));


                xiaoxiIV.setImageResource(R.mipmap.xiaoxiiconhui);
                xiaoxiTV.setTextColor(getResources().getColor(R.color.color4));

                woIV.setImageResource(R.mipmap.woicon);
                woTV.setTextColor(getResources().getColor(R.color.color5));
                break;
        }

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        switchPage(position);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View v) {
        setOnClickThrottleFirst(v);
        switch (v.getId()) {
            case R.id.ShouYeLL:
                jiankongContentFL.setCurrentItem(PAGE_SHOUYE, false);
                break;
            case R.id.xiaoxiLL:
                jiankongContentFL.setCurrentItem(PAGE_XIAOXI, false);
                break;
            case R.id.woLL:
                jiankongContentFL.setCurrentItem(PAGE_WO, false);
                break;
        }
    }


    @Override
    public void getuserinfoSuccess(Z_UserInfo model) {
        if (model != null) {
            Gson gson = new Gson();
            String z_userinfo = gson.toJson(model);
            if (SpUtils.saveSettingNote(Z_Main_Sec_Activity.this, DbKeyS.z_userinfo, z_userinfo)) {
//            if (AppContext.dbHelper.SetData(DbKeyS.z_userinfo, z_userinfo)) {
//                AppContext.dbHelper.SetData(DbKeyS.uid, model.getUid() + "");
                SpUtils.saveSettingNote(Z_Main_Sec_Activity.this, DbKeyS.uid, model.getUid() + "");
                mPresenter.projectlist(3, 3,this);

            }
//            TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
//            tagAliasBean.action = ACTION_SET;
//            Set<String> tags = getInPutTags();
//            tagAliasBean.tags = tags;
//            TagAliasOperatorHelper.getInstance().handleAction(getApplicationContext(), sequence, tagAliasBean);


        }
    }

    @Override
    public void projectlistSuccess(ArrayList<ProjectInfo> model) {
        if (model == null)
            return;
        Gson gson = new Gson();
        String gsonData = gson.toJson(model);
        if (SpUtils.saveSettingNote(Z_Main_Sec_Activity.this, DbKeyS.ProjectInfo, gsonData)) {
//        if (AppContext.dbHelper.SetData(DbKeyS.ProjectInfo, gsonData)) {

            mRationale = new DefaultRationale();
            ShouYeLL.setOnClickListener(this);
            xiaoxiLL.setOnClickListener(this);
            woLL.setOnClickListener(this);


            initPager();


        }

    }

    @Override
    public void listSuccess(ArrayList<GongDiInfo> model) {
        if (model != null && model.size() > 0) {
            Gson gson = new Gson();
            String gsonData = gson.toJson(model);
//            AppContext.dbHelper.SetData(DbKeyS.gongdilist, gsonData);
            SpUtils.saveSettingNote(Z_Main_Sec_Activity.this, DbKeyS.gongdilist, gsonData);
        } else {
//            AppContext.dbHelper.SetData(DbKeyS.gongdilist, "");
            SpUtils.saveSettingNote(Z_Main_Sec_Activity.this, DbKeyS.gongdilist, "");
        }


    }

    @Override
    public void checkVersionSuccess(VersionInfo model) {
        if (model == null)
            return;
        else {
            int versionCode = getVersionCode(this);
            int serviceCode = model.getVersion();
            if (serviceCode > versionCode) {
                UpdateManager updateManager = new UpdateManager(this);
                updateManager.forceCheckUpdate(model.getUrl());


//                if (model.getForce().equals("true")) {
//                    updateManager.forceCheckUpdate(model.getUrl(), model.getVersionName());
//                } else {
//                    updateManager.noForceCheckUpdate(model.getUrl(), model.getVersionName());
//                }
            } else {
                mPresenter.getuserinfo(this);
            }
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
            Intent intent = new Intent(this, Z_Login_Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            showMessage(msg);
        }
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Intent i = new Intent(Intent.ACTION_MAIN);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addCategory(Intent.CATEGORY_HOME);
            startActivity(i);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * 获取输入的tags
     */
    private Set<String> getInPutTags() {

        Set<String> tagSet = new LinkedHashSet<String>();
        tagSet.add("15251435196");
        return tagSet;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isForeground = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isForeground = false;
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
//                    setCostomMsg(showMsg.toString());
                }
            } catch (Exception e) {
            }
        }
    }

    private MessageReceiver mMessageReceiver;

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }
}
