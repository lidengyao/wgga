package com.bcxd.wgga.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bcxd.wgga.AppContext;
import com.bcxd.wgga.R;
import com.bcxd.wgga.base.MvpFragment;
import com.bcxd.wgga.model.info.ChildMenuInfo;
import com.bcxd.wgga.present.W_ShouYe_Present;
import com.bcxd.wgga.ui.activity.W_CheLiangJianKong_Activity;
import com.bcxd.wgga.ui.activity.W_ChuRuKu_Activity;
import com.bcxd.wgga.ui.activity.W_DanWeiBeiAnBianGeng_Activity;
import com.bcxd.wgga.ui.activity.W_DanWeiBeiAn_Activity;
import com.bcxd.wgga.ui.activity.W_FaLvFaGui_Activity;
import com.bcxd.wgga.ui.activity.W_GouMaiXuKeZhengChaXun_Activity;
import com.bcxd.wgga.ui.activity.W_GouMaiXuKeZhengShenPi_Activity;
import com.bcxd.wgga.ui.activity.W_GouMaiZheng_Activity;
import com.bcxd.wgga.ui.activity.W_JieSuoShenQingShenPi_Activity;
import com.bcxd.wgga.ui.activity.W_KuFangJianKong_Activity;
import com.bcxd.wgga.ui.activity.W_Normal_Activity;
import com.bcxd.wgga.ui.activity.W_QiYeSuoDing_Activity;
import com.bcxd.wgga.ui.activity.W_RenYuanBeiAn_Activity;
import com.bcxd.wgga.ui.activity.W_SuoDingJiLuChaXun_Activity;
import com.bcxd.wgga.ui.activity.W_TongZhiGongGao_Activity;
import com.bcxd.wgga.ui.activity.W_YeWuZiXun_Activity;
import com.bcxd.wgga.ui.activity.W_YingJiYuAnGuanLi_Activity;
import com.bcxd.wgga.ui.activity.W_YingJiZhuanJiaGuanLi_Activity;
import com.bcxd.wgga.ui.activity.W_YongHuGuanLi_Activity;
import com.bcxd.wgga.ui.activity.W_YuJingGuanLi_Activity;
import com.bcxd.wgga.ui.activity.Z_Login_Activity;
import com.bcxd.wgga.ui.view.W_ShouYe_View;
import com.bcxd.wgga.utils.ChooseMenuIcon;
import com.bcxd.wgga.utils.DbKeyS;
import com.bcxd.wgga.utils.SpUtils;
import com.bcxd.wgga.utils.SysType;
import com.bcxd.wgga.utils.TokenUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lidengyao on 2016-10-12 0012.
 */
public class W_ShouYe_Fragment extends MvpFragment<W_ShouYe_Present> implements W_ShouYe_View {


    @Bind(R.id.ShouYeBannerIV)
    ImageView ShouYeBannerIV;
    @Bind(R.id.TitleTV)
    TextView TitleTV;
    @Bind(R.id.ShouYeBgRL)
    RelativeLayout ShouYeBgRL;
    @Bind(R.id.MenuLL)
    LinearLayout MenuLL;
    @Bind(R.id.SysNameIV)
    TextView SysName;
    @Bind(R.id.TestLL)
    LinearLayout TestLL;

    @Override
    protected W_ShouYe_Present createPresenter() {
        return new W_ShouYe_Present();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.w_fragment_shouye_judu;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        String systype = SpUtils.getSettingNote(getContext(), DbKeyS.systype);

        if (AppContext.offLine) {
            SetOffLineData();
        } else {
            presenter.getMenuByUser(Integer.parseInt(systype), getContext());
        }

        //
        TestLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getContext(), W_Normal_Activity.class);
                startActivity(mainIntent);
            }
        });

        if (systype.equals("100")) {
            SysName.setText("剧毒化学品");
        }
        if (systype.equals("101")) {
            SysName.setText("易制爆危险品");
        }
        if (systype.equals("102")) {
            SysName.setText("民用爆炸物品");
        }
        if (systype.equals("103")) {
            SysName.setText("烟花爆竹");
        }


    }


    private void SetOffLineData() {
        ArrayList<ChildMenuInfo> model = new ArrayList<>();


        //region 常用
        ChildMenuInfo changyongChildMenuInfo = new ChildMenuInfo();
        changyongChildMenuInfo.setTitle("常用");


        ArrayList<ChildMenuInfo.RulesBean> changyongRulesBean = new ArrayList<>();
        ChildMenuInfo.RulesBean daibanshixiang = new ChildMenuInfo.RulesBean();
        daibanshixiang.setId(SysType.daibanshixiang);
        daibanshixiang.setTitle("代办事项");
        changyongRulesBean.add(daibanshixiang);

        ChildMenuInfo.RulesBean yujingtixing = new ChildMenuInfo.RulesBean();
        yujingtixing.setId(SysType.yujingtixing);
        yujingtixing.setTitle("预警提醒");
        changyongRulesBean.add(yujingtixing);

        ChildMenuInfo.RulesBean tongzhigonggao = new ChildMenuInfo.RulesBean();
        tongzhigonggao.setId(SysType.tongzhigonggao);
        tongzhigonggao.setTitle("通知公告");
        changyongRulesBean.add(tongzhigonggao);

        ChildMenuInfo.RulesBean yewuzixun = new ChildMenuInfo.RulesBean();
        yewuzixun.setId(SysType.yewuzixun);
        yewuzixun.setTitle("业务咨询");
        changyongRulesBean.add(yewuzixun);

        ChildMenuInfo.RulesBean jiesuoshenqingshenpi = new ChildMenuInfo.RulesBean();
        jiesuoshenqingshenpi.setId(SysType.jiesuoshenqingshenpi);
        jiesuoshenqingshenpi.setTitle("解锁申请审批");
        changyongRulesBean.add(jiesuoshenqingshenpi);

        ChildMenuInfo.RulesBean danweiguanli = new ChildMenuInfo.RulesBean();
        danweiguanli.setId(SysType.danweiguanli);
        danweiguanli.setTitle("单位管理");
        changyongRulesBean.add(danweiguanli);

        ChildMenuInfo.RulesBean renyuanguanli = new ChildMenuInfo.RulesBean();
        renyuanguanli.setId(SysType.renyuanguanli);
        renyuanguanli.setTitle("人员管理");
        changyongRulesBean.add(renyuanguanli);

        ChildMenuInfo.RulesBean goumaizheng = new ChildMenuInfo.RulesBean();
        goumaizheng.setId(SysType.goumaizheng);
        goumaizheng.setTitle("购买证");
        changyongRulesBean.add(goumaizheng);


        changyongChildMenuInfo.setRules(changyongRulesBean);
        model.add(changyongChildMenuInfo);
        //endregion

//        //region 业务办理
        ChildMenuInfo yewubanliChildMenuInfo = new ChildMenuInfo();
        yewubanliChildMenuInfo.setTitle("常用");

        ArrayList<ChildMenuInfo.RulesBean> yewubanliRulesBean = new ArrayList<>();
        ChildMenuInfo.RulesBean goumaixukeshenpi = new ChildMenuInfo.RulesBean();
        goumaixukeshenpi.setId(SysType.goumaixukeshenpi);
        goumaixukeshenpi.setTitle("购买许可证审批");
        yewubanliRulesBean.add(goumaixukeshenpi);

        ChildMenuInfo.RulesBean goumaixukezhengchaxun = new ChildMenuInfo.RulesBean();
        goumaixukezhengchaxun.setId(SysType.goumaixukezhengchaxun);
        goumaixukezhengchaxun.setTitle("购买许可证查询");
        yewubanliRulesBean.add(goumaixukezhengchaxun);

        ChildMenuInfo.RulesBean jiesuoshenqingshenpi2 = new ChildMenuInfo.RulesBean();
        jiesuoshenqingshenpi2.setId(SysType.jiesuoshenqingshenpi);
        jiesuoshenqingshenpi2.setTitle("解锁申请审批");
        yewubanliRulesBean.add(jiesuoshenqingshenpi2);

        ChildMenuInfo.RulesBean suodingjiluchaxun = new ChildMenuInfo.RulesBean();
        suodingjiluchaxun.setId(SysType.suodingjiluchaxun);
        suodingjiluchaxun.setTitle("锁定记录查询");
        yewubanliRulesBean.add(suodingjiluchaxun);

        yewubanliChildMenuInfo.setRules(yewubanliRulesBean);
        model.add(yewubanliChildMenuInfo);
//        //endregion

//        //region 查询统计
        ChildMenuInfo chaxutongjiliChildMenuInfo = new ChildMenuInfo();
        chaxutongjiliChildMenuInfo.setTitle("查询统计");


        ArrayList<ChildMenuInfo.RulesBean> chaxuntongjiRulesBean = new ArrayList<>();
        ChildMenuInfo.RulesBean danweibeian = new ChildMenuInfo.RulesBean();
        danweibeian.setId(SysType.danweibeian);
        danweibeian.setTitle("单位备案");
        chaxuntongjiRulesBean.add(danweibeian);

        ChildMenuInfo.RulesBean danweibeianbiangeng = new ChildMenuInfo.RulesBean();
        danweibeianbiangeng.setId(SysType.danweibeianbiangeng);
        danweibeianbiangeng.setTitle("单位备案变更");
        chaxuntongjiRulesBean.add(danweibeianbiangeng);

        ChildMenuInfo.RulesBean renyuanbeian2 = new ChildMenuInfo.RulesBean();
        renyuanbeian2.setId(SysType.renyuanbeian);
        renyuanbeian2.setTitle("人员备案");
        chaxuntongjiRulesBean.add(renyuanbeian2);

        ChildMenuInfo.RulesBean goumaizheng1 = new ChildMenuInfo.RulesBean();
        goumaizheng1.setId(SysType.goumaizheng);
        goumaizheng1.setTitle("购买证");
        chaxuntongjiRulesBean.add(goumaizheng1);


        ChildMenuInfo.RulesBean churuku = new ChildMenuInfo.RulesBean();
        churuku.setId(SysType.churuku);
        churuku.setTitle("出入库");
        chaxuntongjiRulesBean.add(churuku);


        ChildMenuInfo.RulesBean qiyesuoding = new ChildMenuInfo.RulesBean();
        qiyesuoding.setId(SysType.qiyesuoding);
        qiyesuoding.setTitle("企业锁定");
        chaxuntongjiRulesBean.add(qiyesuoding);

        chaxutongjiliChildMenuInfo.setRules(chaxuntongjiRulesBean);
        model.add(chaxutongjiliChildMenuInfo);
//        //endregion

//        //region 安全监管
        ChildMenuInfo anquanjianguanChildMenuInfo = new ChildMenuInfo();
        anquanjianguanChildMenuInfo.setTitle("安全监管");


        ArrayList<ChildMenuInfo.RulesBean> anquanjianguanRulesBean = new ArrayList<>();
        ChildMenuInfo.RulesBean yingjiyuanguanli = new ChildMenuInfo.RulesBean();
        yingjiyuanguanli.setId(SysType.yingjiyuanguanli);
        yingjiyuanguanli.setTitle("应急预案管理");
        anquanjianguanRulesBean.add(yingjiyuanguanli);

        ChildMenuInfo.RulesBean yingjizhuanjiaguanli = new ChildMenuInfo.RulesBean();
        yingjizhuanjiaguanli.setId(SysType.yingjizhuanjiaguanli);
        yingjizhuanjiaguanli.setTitle("应急专家管理");
        anquanjianguanRulesBean.add(yingjizhuanjiaguanli);


        anquanjianguanChildMenuInfo.setRules(anquanjianguanRulesBean);
        model.add(anquanjianguanChildMenuInfo);
//        //endregion

//        //region 预警监控
        ChildMenuInfo yujingjiankongChildMenuInfo = new ChildMenuInfo();
        yujingjiankongChildMenuInfo.setTitle("预警监控");


        ArrayList<ChildMenuInfo.RulesBean> yujingjiankongRulesBean = new ArrayList<>();
        ChildMenuInfo.RulesBean kufangjiankong = new ChildMenuInfo.RulesBean();
        kufangjiankong.setId(SysType.kufangjiankong);
        kufangjiankong.setTitle("库房监控");
        yujingjiankongRulesBean.add(kufangjiankong);

        ChildMenuInfo.RulesBean cheliangguanli = new ChildMenuInfo.RulesBean();
        cheliangguanli.setId(SysType.cheliangguanli);
        cheliangguanli.setTitle("车辆监控");
        yujingjiankongRulesBean.add(cheliangguanli);

        ChildMenuInfo.RulesBean yujingtixing2 = new ChildMenuInfo.RulesBean();
        yujingtixing2.setId(SysType.yujingtixing);
        yujingtixing2.setTitle("预警管理");
        yujingjiankongRulesBean.add(yujingtixing2);


        yujingjiankongChildMenuInfo.setRules(yujingjiankongRulesBean);
        model.add(yujingjiankongChildMenuInfo);
        //endregion

        //region 信息互动
        ChildMenuInfo xinxihudongChildMenuInfo = new ChildMenuInfo();
        xinxihudongChildMenuInfo.setTitle("信息互动");


        ArrayList<ChildMenuInfo.RulesBean> xinxihudongRulesBean = new ArrayList<>();
        ChildMenuInfo.RulesBean tongzhigonggao2 = new ChildMenuInfo.RulesBean();
        tongzhigonggao2.setId(SysType.tongzhigonggao);
        tongzhigonggao2.setTitle("通知公告");
        xinxihudongRulesBean.add(tongzhigonggao2);

        ChildMenuInfo.RulesBean falvfagui = new ChildMenuInfo.RulesBean();
        falvfagui.setId(SysType.falvfagui);
        falvfagui.setTitle("法律法规");
        xinxihudongRulesBean.add(falvfagui);

        ChildMenuInfo.RulesBean yewuzixun1 = new ChildMenuInfo.RulesBean();
        yewuzixun1.setId(SysType.yewuzixun);
        yewuzixun1.setTitle("业务咨询");
        xinxihudongRulesBean.add(yewuzixun1);


        xinxihudongChildMenuInfo.setRules(xinxihudongRulesBean);
        model.add(xinxihudongChildMenuInfo);
//        //endregion

        //region 系统管理
        ChildMenuInfo xitongguanliChildMenuInfo = new ChildMenuInfo();
        xitongguanliChildMenuInfo.setTitle("系统管理");


        ArrayList<ChildMenuInfo.RulesBean> xitongguanliRulesBean = new ArrayList<>();
        ChildMenuInfo.RulesBean yonghuguanli = new ChildMenuInfo.RulesBean();
        yonghuguanli.setId(SysType.yonghuguanli);
        yonghuguanli.setTitle("用户管理");
        xitongguanliRulesBean.add(yonghuguanli);

        xitongguanliChildMenuInfo.setRules(xitongguanliRulesBean);
        model.add(xitongguanliChildMenuInfo);
        //endregion

        SetData(model);

    }

    private void SetData(ArrayList<ChildMenuInfo> model) {
        if (model == null)
            return;
        ArrayList<Menu> menuArrayList = new ArrayList<>();

        //常用

        for (int i = 0; i < model.size(); i++) {
            ChildMenuInfo childMenuInfo = model.get(i);
            Menu menu = new Menu();
            menu.setTitle(childMenuInfo.getTitle());

            for (int m = 0; m < childMenuInfo.getRules().size(); m++) {
                ChildMenuInfo.RulesBean rulesBean = childMenuInfo.getRules().get(m);
                ChildMenu childMenu1 = new ChildMenu();
                childMenu1.setKey(rulesBean.getId());
                childMenu1.setName(rulesBean.getTitle());
                childMenu1.setIconPath(rulesBean.getImg());
                menu.childMenus.add(childMenu1);
            }
            menuArrayList.add(menu);
        }


        for (int i = 0; i < menuArrayList.size(); i++) {

            LinearLayout menuLL = new LinearLayout(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            menuLL.setOrientation(LinearLayout.VERTICAL);
            layoutParams.gravity = Gravity.CENTER;
            menuLL.setLayoutParams(layoutParams);

            Menu menu = menuArrayList.get(i);

            //region 添加分类菜单

            TextView menuTV = new TextView(getContext());
            ViewGroup.LayoutParams keyTVParams = new AppBarLayout.LayoutParams(px2Dip(120), ViewGroup.LayoutParams.WRAP_CONTENT);
            menuTV.setLayoutParams(keyTVParams);
            ((AppBarLayout.LayoutParams) keyTVParams).leftMargin = px2Dip(15);
            ((AppBarLayout.LayoutParams) keyTVParams).topMargin = px2Dip(10);
            ((AppBarLayout.LayoutParams) keyTVParams).bottomMargin = px2Dip(10);
            menuTV.setText(menu.getTitle());
            menuTV.setTextSize(13);
            menuTV.setTextColor(getResources().getColor(R.color.menutxtColor));
            menuLL.addView(menuTV);
            //endregion

            LinearLayout ChildrenLL = getLinearLayout(menu);
            menuLL.addView(ChildrenLL);

            //region 画线
            if (i < menuArrayList.size() - 1) {
                View lineView = new View(getContext());
                lineView.setBackgroundColor(getResources().getColor(R.color.line_color));
                ViewGroup.LayoutParams lineLayoutParams = new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, px2Dip(1));
                ((AppBarLayout.LayoutParams) lineLayoutParams).leftMargin = px2Dip(18);
                ((AppBarLayout.LayoutParams) lineLayoutParams).rightMargin = px2Dip(18);
                ((AppBarLayout.LayoutParams) lineLayoutParams).topMargin = px2Dip(5);
                ((AppBarLayout.LayoutParams) lineLayoutParams).bottomMargin = px2Dip(5);
                lineView.setLayoutParams(lineLayoutParams);
                menuLL.addView(lineView);
            }

            //endregion

            MenuLL.addView(menuLL);
        }
    }

    public int px2Dip(int px) {

        return this.getResources().getDimensionPixelSize(R.dimen.base_dip) * px;
    }

    private LinearLayout getLinearLayout(Menu menu) {

        LinearLayout menuLL = new LinearLayout(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        menuLL.setOrientation(LinearLayout.VERTICAL);
        layoutParams.gravity = Gravity.CENTER;
        menuLL.setLayoutParams(layoutParams);


        int lineCount = menu.childMenus.size() / 4;
        int lastLineItemCount = menu.childMenus.size() % 4;

        for (int i = 0; i < lineCount; i++) {

            View childmenuView = View.inflate(getContext(), R.layout.w_item_childmenu, null);
            LinearLayout RootOneLL = childmenuView.findViewById(R.id.RootOneLL);
            LinearLayout RootTwoLL = childmenuView.findViewById(R.id.RootTwoLL);
            LinearLayout RootThreeLL = childmenuView.findViewById(R.id.RootThreeLL);
            LinearLayout RootFourLL = childmenuView.findViewById(R.id.RootFourLL);

            TextView oneNameTxt = (TextView) childmenuView.findViewById(R.id.nameOne);
            TextView twoNameTxt = (TextView) childmenuView.findViewById(R.id.nameTwo);
            TextView threeNameTxt = (TextView) childmenuView.findViewById(R.id.nameThree);
            TextView fourNameTxt = (TextView) childmenuView.findViewById(R.id.nameFour);
            for (int b = 0; b < 4; b++) {

                int index = i * 4 + b;
                ChildMenuOnClick(RootOneLL);
                ChildMenuOnClick(RootTwoLL);
                ChildMenuOnClick(RootThreeLL);
                ChildMenuOnClick(RootFourLL);
                int tagValue = menu.childMenus.get(index).getKey();


                if (b == 0) {
                    RootOneLL.setTag(tagValue);
                    oneNameTxt.setText(menu.childMenus.get(i * 4 + b).getName());
                    ImageView imageViewOne = (ImageView) childmenuView.findViewById(R.id.imageOne);
                    imageViewOne.setImageResource(ChooseMenuIcon.getMenuIcon(tagValue));

                }
                if (b == 1) {

                    RootTwoLL.setTag(tagValue);
                    twoNameTxt.setText(menu.childMenus.get(i * 4 + b).getName());
                    ImageView imageTwo = (ImageView) childmenuView.findViewById(R.id.imageTwo);
                    imageTwo.setImageResource(ChooseMenuIcon.getMenuIcon(tagValue));
                }
                if (b == 2) {

                    RootThreeLL.setTag(tagValue);
                    threeNameTxt.setText(menu.childMenus.get(i * 4 + b).getName());
                    ImageView imageThree = (ImageView) childmenuView.findViewById(R.id.imageThree);
                    imageThree.setImageResource(ChooseMenuIcon.getMenuIcon(tagValue));
                }
                if (b == 3) {
                    RootFourLL.setTag(tagValue);
                    fourNameTxt.setText(menu.childMenus.get(i * 4 + b).getName());
                    ImageView imageFour = (ImageView) childmenuView.findViewById(R.id.imageFour);
                    imageFour.setImageResource(ChooseMenuIcon.getMenuIcon(tagValue));
                }

            }
            menuLL.addView(childmenuView);


        }

        if (lastLineItemCount > 0) {

            View childmenuView = View.inflate(getContext(), R.layout.w_item_childmenu, null);
            LinearLayout RootOneLL = childmenuView.findViewById(R.id.RootOneLL);
            LinearLayout RootTwoLL = childmenuView.findViewById(R.id.RootTwoLL);
            LinearLayout RootThreeLL = childmenuView.findViewById(R.id.RootThreeLL);
            LinearLayout RootFourLL = childmenuView.findViewById(R.id.RootFourLL);

            TextView oneNameTxt = (TextView) childmenuView.findViewById(R.id.nameOne);
            TextView twoNameTxt = (TextView) childmenuView.findViewById(R.id.nameTwo);
            TextView threeNameTxt = (TextView) childmenuView.findViewById(R.id.nameThree);
            TextView fourNameTxt = (TextView) childmenuView.findViewById(R.id.nameFour);
            for (int b = 0; b < lastLineItemCount; b++) {
                System.out.println(lineCount * 4 + b + "");

                int index = lineCount * 4 + b;
                ChildMenuOnClick(RootOneLL);
                ChildMenuOnClick(RootTwoLL);
                ChildMenuOnClick(RootThreeLL);
                ChildMenuOnClick(RootFourLL);
                int tagValue = menu.childMenus.get(index).getKey();


                LinearLayout twoLL = (LinearLayout) childmenuView.findViewById(R.id.TwoLL);
                LinearLayout threeLL = (LinearLayout) childmenuView.findViewById(R.id.ThreeLL);
                LinearLayout fourLL = (LinearLayout) childmenuView.findViewById(R.id.FourLL);
                if (lastLineItemCount == 1) {

                    if (b == 0) {
                        RootOneLL.setTag(tagValue);
                        oneNameTxt.setText(menu.childMenus.get(lineCount * 4 + b).getName());
                        ImageView imageOne = (ImageView) childmenuView.findViewById(R.id.imageOne);
                        imageOne.setImageResource(ChooseMenuIcon.getMenuIcon(tagValue));
                    }

                    twoLL.setVisibility(View.GONE);
                    threeLL.setVisibility(View.GONE);
                    fourLL.setVisibility(View.GONE);
                }

                if (lastLineItemCount == 2) {
                    if (b == 0) {
                        RootOneLL.setTag(tagValue);
                        oneNameTxt.setText(menu.childMenus.get(lineCount * 4 + b).getName());
                        ImageView imageOne = (ImageView) childmenuView.findViewById(R.id.imageOne);
                        imageOne.setImageResource(ChooseMenuIcon.getMenuIcon(tagValue));
                    }
                    if (b == 1) {
                        RootTwoLL.setTag(tagValue);
                        twoNameTxt.setText(menu.childMenus.get(lineCount * 4 + b).getName());
                        ImageView imageTwo = (ImageView) childmenuView.findViewById(R.id.imageTwo);
                        imageTwo.setImageResource(ChooseMenuIcon.getMenuIcon(tagValue));

                    }


                    threeLL.setVisibility(View.GONE);
                    fourLL.setVisibility(View.GONE);
                }


                if (lastLineItemCount == 3) {
                    if (b == 0) {
                        RootOneLL.setTag(tagValue);
                        oneNameTxt.setText(menu.childMenus.get(lineCount * 4 + b).getName());
                        ImageView imageOne = (ImageView) childmenuView.findViewById(R.id.imageOne);
                        imageOne.setImageResource(ChooseMenuIcon.getMenuIcon(tagValue));
                    }
                    if (b == 1) {
                        RootTwoLL.setTag(tagValue);
                        twoNameTxt.setText(menu.childMenus.get(lineCount * 4 + b).getName());
                        ImageView imageTwo = (ImageView) childmenuView.findViewById(R.id.imageTwo);
                        imageTwo.setImageResource(ChooseMenuIcon.getMenuIcon(tagValue));
                    }
                    if (b == 2) {
                        RootThreeLL.setTag(tagValue);
                        threeNameTxt.setText(menu.childMenus.get(lineCount * 4 + b).getName());
                        ImageView imageThree = (ImageView) childmenuView.findViewById(R.id.imageThree);
                        imageThree.setImageResource(ChooseMenuIcon.getMenuIcon(tagValue));
                    }
                    fourLL.setVisibility(View.GONE);
                }
            }
            menuLL.addView(childmenuView);
        }

        return menuLL;
    }

    private void ChildMenuOnClick(final LinearLayout linearLayout) {

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (linearLayout.getTag() != null) {
                    int key = (Integer) linearLayout.getTag();
                    if (key == SysType.renyuanbeian) {
                        Intent mainIntent = new Intent(getContext(), W_RenYuanBeiAn_Activity.class);
                        startActivity(mainIntent);
                    } else if (key == SysType.goumaixukeshenpi) {
                        Intent mainIntent = new Intent(getContext(), W_GouMaiXuKeZhengShenPi_Activity.class);
                        startActivity(mainIntent);
                    } else if (key == SysType.goumaixukezhengchaxun) {
                        Intent mainIntent = new Intent(getContext(), W_GouMaiXuKeZhengChaXun_Activity.class);
                        startActivity(mainIntent);
                    } else if (key == SysType.jiesuoshenqingshenpi) {
                        Intent mainIntent = new Intent(getContext(), W_JieSuoShenQingShenPi_Activity.class);
                        startActivity(mainIntent);
                    } else if (key == SysType.suodingjiluchaxun) {
                        Intent mainIntent = new Intent(getContext(), W_SuoDingJiLuChaXun_Activity.class);
                        startActivity(mainIntent);
                    } else if (key == SysType.danweibeian) {
                        Intent mainIntent = new Intent(getContext(), W_DanWeiBeiAn_Activity.class);
                        startActivity(mainIntent);
                    } else if (key == SysType.danweibeianbiangeng) {
                        Intent mainIntent = new Intent(getContext(), W_DanWeiBeiAnBianGeng_Activity.class);
                        startActivity(mainIntent);
                    } else if (key == SysType.goumaizheng) {
                        Intent mainIntent = new Intent(getContext(), W_GouMaiZheng_Activity.class);
                        startActivity(mainIntent);
                    } else if (key == SysType.churuku) {
                        Intent mainIntent = new Intent(getContext(), W_ChuRuKu_Activity.class);
                        startActivity(mainIntent);
                    } else if (key == SysType.qiyesuoding) {
                        Intent mainIntent = new Intent(getContext(), W_QiYeSuoDing_Activity.class);
                        startActivity(mainIntent);
                    } else if (key == SysType.kufangjiankong) {
                        Intent mainIntent = new Intent(getContext(), W_KuFangJianKong_Activity.class);
                        startActivity(mainIntent);
                    } else if (key == SysType.cheliangguanli) {
                        Intent mainIntent = new Intent(getContext(), W_CheLiangJianKong_Activity.class);
                        startActivity(mainIntent);
                    } else if (key == SysType.yujingtixing) {
                        Intent mainIntent = new Intent(getContext(), W_YuJingGuanLi_Activity.class);
                        startActivity(mainIntent);
                    } else if (key == SysType.yuanguanli) {
                        Intent mainIntent = new Intent(getContext(), W_YuJingGuanLi_Activity.class);
                        startActivity(mainIntent);
                    } else if (key == SysType.yingjiyuanguanli) {
                        Intent mainIntent = new Intent(getContext(), W_YingJiYuAnGuanLi_Activity.class);
                        startActivity(mainIntent);
                    } else if (key == SysType.yingjizhuanjiaguanli) {
                        Intent mainIntent = new Intent(getContext(), W_YingJiZhuanJiaGuanLi_Activity.class);
                        startActivity(mainIntent);
                    } else if (key == SysType.tongzhigonggao) {
                        Intent mainIntent = new Intent(getContext(), W_TongZhiGongGao_Activity.class);
                        startActivity(mainIntent);
                    } else if (key == SysType.falvfagui) {
                        Intent mainIntent = new Intent(getContext(), W_FaLvFaGui_Activity.class);
                        startActivity(mainIntent);
                    } else if (key == SysType.yewuzixun) {
                        Intent mainIntent = new Intent(getContext(), W_YeWuZiXun_Activity.class);
                        startActivity(mainIntent);
                    } else if (key == SysType.yonghuguanli) {
                        Intent mainIntent = new Intent(getContext(), W_YongHuGuanLi_Activity.class);
                        startActivity(mainIntent);
                    } else {
                        showMessage("该模块暂未开放");
                    }

                }

            }
        });

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent mainIntent = new Intent(W_ChooseSystem_Activity.this, W_Main_Activity.class);
//                startActivity(mainIntent);
//                if (type.equals(JUDU)) {
//                    SpUtils.saveSettingNote(W_ChooseSystem_Activity.this, DbKeyS.systype, JUDU);
//                }
//                if (type.equals(YIZHIBAO)) {
//                    SpUtils.saveSettingNote(W_ChooseSystem_Activity.this, DbKeyS.systype, YIZHIBAO);
//                }
//                if (type.equals(BAOZHA)) {
//                    SpUtils.saveSettingNote(W_ChooseSystem_Activity.this, DbKeyS.systype, BAOZHA);
//                }
//                if (type.equals(YANHUA)) {
//                    SpUtils.saveSettingNote(W_ChooseSystem_Activity.this, DbKeyS.systype, YANHUA);
//                }
//
//            }
//        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void getMenuByUserSuccess(ArrayList<ChildMenuInfo> model) {
        SetData(model);

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

    public class Menu {
        public String title;

        public ArrayList<ChildMenu> childMenus = new ArrayList<>();

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public ArrayList<ChildMenu> getChildMenus() {
            return childMenus;
        }

        public void setChildMenus(ArrayList<ChildMenu> childMenus) {
            this.childMenus = childMenus;
        }
    }

    public class ChildMenu {
        public String name;
        public String iconPath;
        public int key;

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIconPath() {
            return iconPath;
        }

        public void setIconPath(String iconPath) {
            this.iconPath = iconPath;
        }
    }
}
