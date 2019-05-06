package com.bcxd.wgga;

import android.app.Application;

import com.bcxd.wgga.model.db.DBHelper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cn.jpush.android.api.JPushInterface;
//import cn.jpush.android.api.JPushInterface;


/**
 * Created by jinxh on 15/11/19.
 * QQ:123489504
 */
public class AppContext extends Application {

    public static boolean offLine = true;
    public static String API_BASE_URL = "http://zejun.free.idcfengye.com/";
    //    public  static String API_BASE_URL = "http://192.168.0.133:8080/";
    // 配置是否要沉浸式头部
    public final static boolean HIDDEN_STATUS_BAR = true;
    public static String NET_ERROR_MSG;
    public static DBHelper dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        // 捕捉应用异常信息
        CustomActivityOnCrash.install(this);
        initConstant();
        dbHelper = new DBHelper(getApplicationContext());


        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush

        closeAndroidPDialog();

    }

    private void initConstant() {
        NET_ERROR_MSG = getString(R.string.alert_net_error);
    }

    //解决androidp弹出Detected problems with API compatibility(visit g.co/dev/appcompat for more info
    private void closeAndroidPDialog() {
        try {
            Class aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
