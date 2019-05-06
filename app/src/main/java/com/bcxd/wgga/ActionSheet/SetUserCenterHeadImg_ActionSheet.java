package com.bcxd.wgga.ActionSheet;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bcxd.wgga.R;

/**
 * Created by lidengyao on 2016-10-11 0011.
 */
public class SetUserCenterHeadImg_ActionSheet {


    public static Dialog showSheet(Context context,
                                   final Ldy_OnActionSheetSelected actionSheetSelected,
                                   Activity activity) {
        final Dialog dlg = new Dialog(context, R.style.ActionSheet);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(
                R.layout.setusercenterheadimg_actionsheet, null);

        TextView cancelTextView = (TextView) layout
                .findViewById(R.id.setuserheadimgcancel);
        LinearLayout yyxqsheetcloseLayout = (LinearLayout) layout
                .findViewById(R.id.setuserheadimgsheetclose);
        TextView paizhaoTextView = (TextView) layout
                .findViewById(R.id.paizhaoTxt);
        TextView fromxiangceTextView = (TextView) layout
                .findViewById(R.id.fromxiangceTxt);
        paizhaoTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                actionSheetSelected.onClick(3);
                dlg.dismiss();
            }
        });
        fromxiangceTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                actionSheetSelected.onClick(4);
                dlg.dismiss();
            }
        });
        yyxqsheetcloseLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dlg.dismiss();
            }
        });
        cancelTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dlg.dismiss();
            }
        });
        // 给图片控件设置属性
        DisplayMetrics dm = new DisplayMetrics();
        // 取得窗口属性

        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        // 窗口的宽度
        int screenWidth = dm.widthPixels;

        layout.setMinimumWidth(screenWidth);

        Window w = dlg.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.x = 0;
        final int cMakeBottom = 0;
        lp.y = cMakeBottom;
        lp.gravity = Gravity.CENTER;
        dlg.onWindowAttributesChanged(lp);
        dlg.setCanceledOnTouchOutside(false);

        dlg.setContentView(layout);
        dlg.show();

        return dlg;
    }

}
