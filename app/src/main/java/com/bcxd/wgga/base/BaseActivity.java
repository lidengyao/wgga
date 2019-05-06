package com.bcxd.wgga.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import com.bcxd.wgga.AppContext;
import com.bcxd.wgga.utils.StatusBarUtil;
import com.jakewharton.rxbinding.view.RxView;
import com.bcxd.wgga.R;
import com.bcxd.wgga.widget.LoadingDialog;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jinxh on 16/1/4.
 * QQ:123489504
 */
public abstract class BaseActivity extends AppCompatActivity {
    private SystemBarTintManager mTintManager;
    private LoadingDialog mLoadingDialog;
    private CompositeSubscription mCompositeSubscription;
    private boolean isShowCode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        if (isHiddenStatusBar()) {
//            initBarTint();
//            initTopBar();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                View decorView = getWindow().getDecorView();

                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

                decorView.setSystemUiVisibility(option);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }

        }
        initData();
        initEvent();
        initView();
    }

    protected boolean isHiddenStatusBar() {
        return AppContext.HIDDEN_STATUS_BAR;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void backButtonPressed(View view) {
        finish();
    }

    public void dicButtonPressed(View view) {
        View childLL = findViewById(R.id.ChildLL);
        if (childLL == null) {
            return;
        } else {
            Animation outAnima = new TranslateAnimation(0, getWindowManager().getDefaultDisplay().getWidth(), 0, 0); //设置入动画
            outAnima.setDuration(300);
            childLL.setAnimation(outAnima);
            childLL.setVisibility(View.GONE);
        }
    }

    protected abstract int getLayoutId();

    protected void initView() {

    }

    protected void initData() {

    }

    protected void initEvent() {

    }

    protected void setOnClickListener(View view, View.OnClickListener listener) {
        view.setOnClickListener(listener);
        // 快速点击
        RxView.clicks(view).throttleFirst(500, TimeUnit.MILLISECONDS);
    }

    protected void setOnClickThrottleFirst(View view) {
        // 快速点击
        RxView.clicks(view).throttleFirst(500, TimeUnit.MILLISECONDS);
    }

    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    public void dismissLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    private void initBarTint() {
        mTintManager = new SystemBarTintManager(this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setNavigationBarTintEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // 全透明
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.color21));
            } else {
                setTranslucentStatus(window);
            }
        }
        setTintResource(getTintResourceId());
    }

    private static int calculateStatusColor(@ColorInt int color, int alpha) {
        if (alpha == 0) {
            return color;
        }
        float a = 1 - alpha / 255f;
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;
        red = (int) (red * a + 0.5);
        green = (int) (green * a + 0.5);
        blue = (int) (blue * a + 0.5);
        return 0xff << 24 | red << 16 | green << 8 | blue;
    }

    private void initTopBar() {
        // 4.4以上设置头部导航高度
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View topBar = findViewById(R.id.activity_top_bar);
            if (topBar == null) {
                return;
            }
            SystemBarTintManager.SystemBarConfig config = new SystemBarTintManager(this).getConfig();
            int statusBarHeight = config.getStatusBarHeight();
            topBar.setPadding(0, statusBarHeight, 0, 0);

            int height = topBar.getLayoutParams().height;
            topBar.getLayoutParams().height = statusBarHeight + height;
        }
    }

    protected void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    @TargetApi(19)
    private void setTranslucentStatus(Window window) {
        WindowManager.LayoutParams winParams = window.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        winParams.flags |= bits;
        window.setAttributes(winParams);
    }

    public void showMessage(int res) {
        Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
    }

    public void showMessage(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void showMessage(int code, CharSequence text) {
        if (isShowCode == true) {
            Toast.makeText(this, code + "" + text, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }

    }

    protected void setTintResource(int res) {
        mTintManager.setTintResource(res);
    }

    protected int getTintResourceId() {
        return R.color.transparent;
    }

    /***
     * 设置沉浸式状态栏  大多数时可能跟随toolbar的背景色
     *
     * @param bgColor 背景颜色
     */
    public void setSystemBar(@ColorRes int bgColor, boolean statusTxtColorIsDark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (getSystemProperty("ro.miui.ui.version.name").equals("V6")
                    || getSystemProperty("ro.miui.ui.version.name").equals("V7")
                    || getSystemProperty("ro.miui.ui.version.name").equals("V8")) {

                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

                SystemBarTintManager tintManager = new SystemBarTintManager(this);
                // 激活状态栏设置
                tintManager.setStatusBarTintEnabled(true);
                // 激活导航栏设置
                tintManager.setNavigationBarTintEnabled(false);
                // 设置一个颜色给系统栏
                tintManager.setStatusBarTintResource(bgColor);

                setMiuiStatusBarDarkMode(this, statusTxtColorIsDark);
            }

        }
    }

    private static String getSystemProperty(String propName) {
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            // Log.e(TAG, "Unable to read sysprop " + propName, ex);
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    // Log.e(TAG, "Exception while closing InputStream", e);
                }
            }
        }
        return line;
    }


    public static boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            java.lang.reflect.Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            java.lang.reflect.Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
