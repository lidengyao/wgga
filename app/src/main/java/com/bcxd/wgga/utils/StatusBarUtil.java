package com.bcxd.wgga.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.IntRange;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.bcxd.wgga.R;

public class StatusBarUtil {
    public static void setColor(Activity activity, int color, boolean isDrawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 生成一个状态栏大小的矩形
            View statusView = createStatusView(activity, color, isDrawable);
            // 添加 statusView 到布局中
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            //在一个界面在来回切换顶部状态栏的时候导致透明度的状态栏不能显示 需remove掉
            if (decorView.getChildCount() >= 2) {
                decorView.removeViewAt(1);
            }
            decorView.addView(statusView);
            // 设置根布局的参数
            setRootView(activity);
        }
    }


    /**
     * 生成一个状态栏大小的矩形
     *
     * @param activity
     * @param color
     * @param isDrawable
     * @return
     */
    @SuppressLint({"NewApi", "ResourceType"})
    private static View createStatusView(Activity activity, int color, boolean isDrawable) {
        // 绘制一个和状态栏一样高的矩形
        // 获得状态栏高度
        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight(activity));
        statusView.setLayoutParams(params);
        if (isDrawable) {
            statusView.setBackground(ContextCompat.getDrawable(activity, color));
        } else {
            statusView.setBackgroundColor(ContextCompat.getColor(activity, color));
        }
        statusView.setId(R.drawable.status_shape);
        return statusView;
    }


    /**
     * 设置根布局参数
     */
    private static void setRootView(Activity activity) {
        ViewGroup parent = (ViewGroup) activity.findViewById(android.R.id.content);
        for (int i = 0, count = parent.getChildCount(); i < count; i++) {
            View childView = parent.getChildAt(i);
            if (childView instanceof ViewGroup) {
                childView.setFitsSystemWindows(true);
                ((ViewGroup) childView).setClipToPadding(true);
            }
        }
    }


    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    private static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }
}
