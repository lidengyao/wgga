package com.bcxd.wgga.utils;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;

public class ImmersiveStatusbar {
    private static ImmersiveStatusbar immersiveStatusbar;

    // 构造函数私有化
    private ImmersiveStatusbar() {

    }

    // TODO: 2017/4/17 单例模式
    public static ImmersiveStatusbar getInstance() {

        if (immersiveStatusbar == null) {
            // 加锁提高使用效率
            synchronized (ImmersiveStatusbar.class) {
                if (immersiveStatusbar == null) {
                    immersiveStatusbar = new ImmersiveStatusbar();
                }
            }
        }
        return immersiveStatusbar;

    }

    public void Immersive(Window window ) {

        if (Build.VERSION.SDK_INT >= 21) {

            View view = window.getDecorView();
            // TODO: 2017/4/13 两个FLAG一起使用表示会让应用的主体内容占用系统状态栏的时空间
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            view.setSystemUiVisibility(option);
            // 将状态栏设置成透明色
            window.setStatusBarColor(Color.TRANSPARENT);

        }

    }
}
