package com.bcxd.wgga.utils;

import com.bcxd.wgga.R;

public class ChooseSysMenuIcon {
    public static int getMenuIcon(int id) {

        switch (id) {
            case 100:
                return R.mipmap.judu;
            case 101:
                return R.mipmap.yizhibao;
            case 102:
                return R.mipmap.baozha;
            case 103:
                return R.mipmap.yanhua;

        }
        return -1;
    }
}
