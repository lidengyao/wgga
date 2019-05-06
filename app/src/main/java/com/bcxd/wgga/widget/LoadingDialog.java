package com.bcxd.wgga.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.bcxd.wgga.R;


public class LoadingDialog {
    private Dialog loadingDialog;

    public LoadingDialog(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.view_progress, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局

        loadingDialog = new Dialog(context, R.style.loadingDialog);// 创建自定义样式dialog

        loadingDialog.setCancelable(false);// 不可以用“返回键”取消
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局

//        loadingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_SEARCH) {
//                    return true;
//                } else {
//                    return false; //默认返回 false
//                }
//            }
//        });
    }

    public void show() {
        loadingDialog.show();
    }

    public void dismiss() {
        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    public boolean isShowing() {
        return loadingDialog.isShowing();
    }


}
