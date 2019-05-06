package com.bcxd.wgga.utils;


import com.bcxd.wgga.R;

public class ChooseMenuIcon {

    public static int a;

    public static int getMenuIcon(int id) {

        switch (id) {
            case SysType.danweibeian:
                return R.mipmap.danweibeian;
            case SysType.danweibeianbiangeng:
                return R.mipmap.danweibeianbiangeng;
            case SysType.renyuanbeian:
                return R.mipmap.renyuanbeian;
            case SysType.goumaizheng:
                return R.mipmap.goumaizheng;
            case SysType.churuku:
                return R.mipmap.churuku;
            case SysType.qiyesuoding:
                return R.mipmap.qiyesuoding;
            case SysType.yingjiyuanguanli:
                return R.mipmap.yingjiyuanguanli;
            case SysType.yingjizhuanjiaguanli:
                return R.mipmap.yingjizhuanjiaguanli;
            case SysType.kufangjiankong:
                return R.mipmap.kufangjiankong;
            case SysType.cheliangguanli:
                return R.mipmap.cheliangguanli;
            case SysType.tongzhigonggao:
                return R.mipmap.tongzhigonggao;
            case SysType.falvfagui:
                return R.mipmap.falvfagui;
            case SysType.yewuzixun:
                return R.mipmap.yewuzixun;
            case SysType.yonghuguanli:
                return R.mipmap.yonghuguanli;
            case SysType.goumaixukeshenpi:
                return R.mipmap.goumaixukeshenpi;
            case SysType.goumaixukezhengchaxun:
                return R.mipmap.goumaixukezhengchaxun;
            case SysType.jiesuoshenqingshenpi:
                return R.mipmap.jiesuoshenqingshenpi;
            case SysType.suodingjiluchaxun:
                return R.mipmap.suodingjiluchaxun;
            case SysType.yuanguanli:
                return R.mipmap.yuanguanli;
            case SysType.daibanshixiang:
                return R.mipmap.daibanshixiang;
            case SysType.yujingtixing:
                return R.mipmap.yujingtixing;
            case SysType.danweiguanli:
                return R.mipmap.danweiguanli;
            case SysType.renyuanguanli:
                return R.mipmap.renyuanguanli;
            default:
                return R.mipmap.logo;
        }

    }
}
