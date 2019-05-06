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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.adapter.DicDataAdapter;
import com.bcxd.wgga.adapter.W_XiaoXiAdapter;
import com.bcxd.wgga.model.info.DicDataInfo;
import com.bcxd.wgga.widget.PullLoadMoreListView;

import java.util.ArrayList;

/**
 * Created by lidengyao on 2016-10-11 0011.
 */
public class DicData_ActionSheet {


    public static Dialog showSheet(Context context,
                                   final
                                   Activity activity, final EditText editText, final ArrayList<DicDataInfo> dicDataInfos) {
        final Dialog dlg = new Dialog(context, R.style.ActionSheet);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(
                R.layout.dicdata_actionsheet, null);
        PullLoadMoreListView dicDataList = (PullLoadMoreListView) layout.findViewById(R.id.DicDataListView);
        LinearLayout setuserheadimgsheetclose = (LinearLayout) layout.findViewById(R.id.setuserheadimgsheetclose);
        DicDataAdapter dicDataAdapter = new DicDataAdapter(activity, dicDataInfos, R.layout.w_item_dicdata);
        dicDataList.setAdapter(dicDataAdapter);
        setuserheadimgsheetclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
            }
        });
        dicDataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String dicvalue = dicDataInfos.get(position).getLby_type_name();
                int dicid = dicDataInfos.get(position).getId();
                editText.setText(dicvalue);
                editText.setTag(dicid);
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
        lp.gravity = Gravity.BOTTOM;
        dlg.onWindowAttributesChanged(lp);
        dlg.setCanceledOnTouchOutside(false);

        dlg.setContentView(layout);
        dlg.show();

        return dlg;
    }

}
