package com.bcxd.wgga.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Xml;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bcxd.wgga.ActionSheet.DicData_ActionSheet;
import com.bcxd.wgga.R;
import com.bcxd.wgga.adapter.DicDataAdapter;
import com.bcxd.wgga.model.info.DicDataInfo;
import com.bcxd.wgga.timepaker.ChangeDatePopwindow;
import com.bcxd.wgga.ui.activity.W_Login_Activity;
import com.bcxd.wgga.widget.Normal;
import com.bcxd.wgga.widget.PullLoadMoreListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AnalyzeNormalXml {

    /**
     * @param context
     * @param activity
     * @param XmlName（配置文件名称）
     * @param rootLL               (添加自动生成控件的父节点)
     * @param dialogType           （0：侧面弹出选择列表，1：底部弹出选择列表）
     * @param FilterListview       （侧面筛选条件弹出的选择侧边栏）
     * @param pullLoadMoreListView （侧面筛选条件弹出的选择侧边栏里的ListView）
     * @param mainview             （主页面的跟节点）
     * @return
     */
    public static ArrayList<Normal> Analyze(final Context context, final Activity activity, String XmlName, LinearLayout rootLL, final String dialogType, final View FilterListview, final PullLoadMoreListView pullLoadMoreListView, final View mainview) {

        ArrayList<Normal> normalArrayList = new ArrayList<>();
        try {
            InputStream is = context.getResources().getAssets().open(XmlName + ".xml");// getResources().getAssets().open(fileName);
            XmlPullParser xmlParser = Xml.newPullParser();
            xmlParser.setInput(is, "utf-8");
            int event = xmlParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        if ("Item".equals(xmlParser.getName())) {

                            String Title = xmlParser.getAttributeValue(null, "Title");
                            String ViewType = xmlParser.getAttributeValue(null, "ViewType");
                            String Key = xmlParser.getAttributeValue(null, "Key");
                            String Hint = xmlParser.getAttributeValue(null, "Hint");
                            String IsNull = xmlParser.getAttributeValue(null, "IsNull");

                            //region Normal
                            if (ViewType.equals("Normal")) {
                                View normalView = View.inflate(context, R.layout.w_item_normal, null);
                                TextView normalTitleTV = (TextView) normalView.findViewById(R.id.normalTitleTV);
                                EditText normalValueET = (EditText) normalView.findViewById(R.id.normalValueET);
                                TextView miTV = (TextView) normalView.findViewById(R.id.MiTV);
                                if (GetIsNull(IsNull)) {
                                    miTV.setVisibility(View.GONE);
                                } else {
                                    miTV.setVisibility(View.VISIBLE);
                                }
                                if (Hint.equals("")) {
                                    normalValueET.setHint("请输入" + Title);
                                } else {
                                    normalValueET.setHint(Hint);
                                }

                                normalTitleTV.setText(Title);
                                rootLL.addView(normalView);

                                Normal tempNormal = new Normal();
                                tempNormal.setNull(GetIsNull(IsNull));
                                tempNormal.setType(ViewType);
                                tempNormal.setKey(Key);
                                tempNormal.setNormalEditText(normalValueET);
                                normalArrayList.add(tempNormal);
                            }
                            //endregion

                            //region Arraylist
                            if (ViewType.equals("ArrayList")) {
                                final String TypeKey = xmlParser.getAttributeValue(null, "TypeKey");
                                View normalView = View.inflate(context, R.layout.w_item_dic, null);
                                TextView normalTitleTV = (TextView) normalView.findViewById(R.id.normalTitleTV);

                                final EditText normalValueET = (EditText) normalView.findViewById(R.id.normalValueET);
                                TextView miTV = (TextView) normalView.findViewById(R.id.MiTV);
                                if (GetIsNull(IsNull)) {
                                    miTV.setVisibility(View.GONE);
                                } else {
                                    miTV.setVisibility(View.VISIBLE);
                                }
                                if (Hint.equals("")) {
                                    normalValueET.setHint("请选择" + Title);
                                } else {
                                    normalValueET.setHint(Hint);
                                }
                                normalTitleTV.setText(Title);
                                rootLL.addView(normalView);

                                Normal tempNormal = new Normal();
                                tempNormal.setNull(GetIsNull(IsNull));
                                tempNormal.setType(ViewType);
                                tempNormal.setKey(Key);
                                tempNormal.setNormalEditText(normalValueET);
                                normalArrayList.add(tempNormal);

                                normalValueET.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        if (dialogType.equals("0")) {
                                            if (FilterListview != null) {
                                                FilterListview.setVisibility(View.VISIBLE);

                                                Animation outAnima = new TranslateAnimation(activity.getWindowManager().getDefaultDisplay().getWidth(), 0, 0, 0); //设置入动画
                                                outAnima.setDuration(300);
                                                FilterListview.setAnimation(outAnima);

                                                final ArrayList<DicDataInfo> dicDataInfos = DicUtils.getDicData(Integer.parseInt(TypeKey), context);
                                                DicDataAdapter dicDataAdapter = new DicDataAdapter(activity, dicDataInfos, R.layout.w_item_dicdata);
                                                pullLoadMoreListView.setAdapter(dicDataAdapter);
                                                pullLoadMoreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                    @Override
                                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                        DicDataInfo temp = dicDataInfos.get(position);
                                                        String dicvalue = temp.getLby_type_name();
                                                        int dicid = dicDataInfos.get(position).getId();
                                                        normalValueET.setText(dicvalue);
                                                        normalValueET.setTag(temp);


                                                        Animation outAnima = new TranslateAnimation(0, activity.getWindowManager().getDefaultDisplay().getWidth(), 0, 0); //设置入动画
                                                        outAnima.setDuration(300);
                                                        FilterListview.setAnimation(outAnima);
                                                        FilterListview.setVisibility(View.GONE);
                                                    }
                                                });


                                            }


                                        }
                                        if (dialogType.equals("1")) {

                                            final DialogFromBottom dialogFromBottom = new DialogFromBottom(context);
                                            View view = LayoutInflater.from(context).inflate(R.layout.dicdata_actionsheet, null, false);
                                            dialogFromBottom.setContentView(view);
                                            PullLoadMoreListView pullLoadMoreListView = (PullLoadMoreListView) view.findViewById(R.id.DicDataListView);
                                            final ArrayList<DicDataInfo> dicDataInfos = DicUtils.getDicData(Integer.parseInt(TypeKey), context);
                                            DicDataAdapter dicDataAdapter = new DicDataAdapter(activity, dicDataInfos, R.layout.w_item_dicdata);
                                            pullLoadMoreListView.setAdapter(dicDataAdapter);

                                            LinearLayout setuserheadimgsheetclose = (LinearLayout) view.findViewById(R.id.setuserheadimgsheetclose);
                                            setuserheadimgsheetclose.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialogFromBottom.dismiss();
                                                }
                                            });
                                            pullLoadMoreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                    String dicvalue = dicDataInfos.get(position).getLby_type_name();
                                                    int dicid = dicDataInfos.get(position).getId();
                                                    normalValueET.setText(dicvalue);
                                                    normalValueET.setTag(dicid);
                                                    dialogFromBottom.dismiss();

                                                }
                                            });

                                            dialogFromBottom.show();

                                        }

                                    }
                                });
                            }
                            //endregion

                            //region CheckBoxList
                            if (ViewType.equals("CheckBoxList")) {
                                final String TypeKey = xmlParser.getAttributeValue(null, "TypeKey");
                                View checkboxListView = View.inflate(context, R.layout.w_item_checkboxlist, null);
                                TextView miTV = (TextView) checkboxListView.findViewById(R.id.MiTV);
                                if (GetIsNull(IsNull)) {
                                    miTV.setVisibility(View.GONE);
                                } else {
                                    miTV.setVisibility(View.VISIBLE);
                                }


                                TextView normalTitleTV = (TextView) checkboxListView.findViewById(R.id.normalTitleTV);
                                AutoLinefeedLayout autoLinefeedLayout = (AutoLinefeedLayout) checkboxListView.findViewById(R.id.CheckBoxListALL);

                                //添加checkbox控件
                                final ArrayList<DicDataInfo> dicDataInfos = DicUtils.getDicData(Integer.parseInt(TypeKey), context);
                                ArrayList<CheckBox> checkBoxArrayList = new ArrayList<>();
                                for (int i = 0; i < dicDataInfos.size(); i++) {
                                    DicDataInfo dicDataInfo = dicDataInfos.get(i);
                                    CheckBox checkBox = new CheckBox(context);
                                    checkBox.setText(dicDataInfo.getLby_type_name());
                                    autoLinefeedLayout.addView(checkBox);
                                    checkBoxArrayList.add(checkBox);
                                }

                                normalTitleTV.setText(Title);
                                rootLL.addView(checkboxListView);

                                Normal tempNormal = new Normal();
                                tempNormal.setNull(GetIsNull(IsNull));
                                tempNormal.setType(ViewType);
                                tempNormal.setKey(Key);
                                tempNormal.setCheckBoxArrayList(checkBoxArrayList);
                                normalArrayList.add(tempNormal);

                            }
                            //endregion

                            //region BetweenDate
                            if (ViewType.equals("BetweenDate")) {

                                View betweendateView = View.inflate(context, R.layout.w_item_betweendate, null);
                                TextView normalTitleTV = (TextView) betweendateView.findViewById(R.id.normalTitleTV);
                                final EditText BeginDateET = (EditText) betweendateView.findViewById(R.id.BeginDateET);
                                final EditText EndDateET = (EditText) betweendateView.findViewById(R.id.EndDateET);
                                TextView miTV = (TextView) betweendateView.findViewById(R.id.MiTV);
                                if (GetIsNull(IsNull)) {
                                    miTV.setVisibility(View.GONE);
                                } else {
                                    miTV.setVisibility(View.VISIBLE);
                                }

                                normalTitleTV.setText(Title);
                                rootLL.addView(betweendateView);

                                String BeginDateKey = xmlParser.getAttributeValue(null, "BeginDateKey");
                                Normal beginNormal = new Normal();
                                beginNormal.setNull(GetIsNull(IsNull));
                                beginNormal.setType(ViewType);
                                beginNormal.setKey(BeginDateKey);
                                beginNormal.setNormalEditText(BeginDateET);
                                normalArrayList.add(beginNormal);

                                String EndDateKey = xmlParser.getAttributeValue(null, "EndDateKey");
                                Normal endNormal = new Normal();
                                endNormal.setNull(GetIsNull(IsNull));
                                endNormal.setType(ViewType);
                                endNormal.setKey(EndDateKey);
                                endNormal.setNormalEditText(EndDateET);
                                normalArrayList.add(endNormal);

                                BeginDateET.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        final String[] str = new String[10];
                                        ChangeDatePopwindow mChangeBirthDialog = new ChangeDatePopwindow(context);
//                                        mChangeBirthDialog.setDate("2017", "6", "20");
                                        mChangeBirthDialog.showAtLocation(mainview, Gravity.CENTER, 0, 0);
                                        mChangeBirthDialog.setBirthdayListener(new ChangeDatePopwindow.OnBirthListener() {

                                            @Override
                                            public void onClick(String year, String month, String day) {
                                                // TODO Auto-generated method stub
//                                                Toast.makeText(context, year + "-" + month + "-" + day, Toast.LENGTH_LONG).show();
                                                StringBuilder sb = new StringBuilder();
                                                sb.append(year.substring(0, year.length() - 1)).append("-").append(month.substring(0, month.length() - 1)).append("-").append(day.substring(0, day.length() - 1));
                                                String showDate = sb.toString();

                                                BeginDateET.setText(showDate);
//                            button5.setText(str[0]);

                                            }
                                        });
                                    }
                                });

                                EndDateET.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        final String[] str = new String[10];
                                        ChangeDatePopwindow mChangeBirthDialog = new ChangeDatePopwindow(context);
//                                        mChangeBirthDialog.setDate("2017", "6", "20");
                                        mChangeBirthDialog.showAtLocation(mainview, Gravity.CENTER, 0, 0);
                                        mChangeBirthDialog.setBirthdayListener(new ChangeDatePopwindow.OnBirthListener() {

                                            @Override
                                            public void onClick(String year, String month, String day) {
                                                // TODO Auto-generated method stub
//                                                Toast.makeText(context, year + "-" + month + "-" + day, Toast.LENGTH_LONG).show();
                                                StringBuilder sb = new StringBuilder();
                                                sb.append(year.substring(0, year.length() - 1)).append("-").append(month.substring(0, month.length() - 1)).append("-").append(day.substring(0, day.length() - 1));
                                                String showDate = sb.toString();
                                                EndDateET.setText(showDate);
//                            button5.setText(str[0]);

                                            }
                                        });
                                    }
                                });

                            }
                            //endregion

                            //region Date
                            if (ViewType.equals("Date")) {
                                View dateView = View.inflate(context, R.layout.w_item_date, null);
                                TextView titleTV = (TextView) dateView.findViewById(R.id.TitleTV);
                                final EditText DateET = (EditText) dateView.findViewById(R.id.DateET);
                                TextView miTV = (TextView) dateView.findViewById(R.id.MiTV);
                                if (GetIsNull(IsNull)) {
                                    miTV.setVisibility(View.GONE);
                                } else {
                                    miTV.setVisibility(View.VISIBLE);
                                }

                                titleTV.setText(Title);
                                DateET.setHint(Hint);
                                rootLL.addView(dateView);

                                Normal normal = new Normal();
                                normal.setNull(GetIsNull(IsNull));
                                normal.setType(ViewType);
                                normal.setKey(Key);
                                normal.setNormalEditText(DateET);
                                normalArrayList.add(normal);

                                DateET.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        final String[] str = new String[10];

                                        ChangeDatePopwindow mChangeBirthDialog = new ChangeDatePopwindow(context);
//                                        mChangeBirthDialog.setDate("2017", "6", "20");
                                        mChangeBirthDialog.showAtLocation(mainview, Gravity.CENTER, 0, 0);
                                        mChangeBirthDialog.setBirthdayListener(new ChangeDatePopwindow.OnBirthListener() {

                                            @Override
                                            public void onClick(String year, String month, String day) {
                                                // TODO Auto-generated method stub
//                                                Toast.makeText(context, year + "-" + month + "-" + day, Toast.LENGTH_LONG).show();
                                                StringBuilder sb = new StringBuilder();
                                                sb.append(year.substring(0, year.length() - 1)).append("-").append(month.substring(0, month.length() - 1)).append("-").append(day.substring(0, day.length() - 1));
                                                String showDate = sb.toString();


                                                DateET.setText(showDate);

                                            }
                                        });
                                    }
                                });
                            }
                            //endregion

                            //region Lines
                            if (ViewType.equals("Lines")) {
                                View linesView = View.inflate(context, R.layout.w_item_lines, null);
                                TextView normalTitleTV = (TextView) linesView.findViewById(R.id.normalTitleTV);
                                EditText normalValueET = (EditText) linesView.findViewById(R.id.normalValueET);
                                TextView miTV = (TextView) linesView.findViewById(R.id.MiTV);
                                if (GetIsNull(IsNull)) {
                                    miTV.setVisibility(View.GONE);
                                } else {
                                    miTV.setVisibility(View.VISIBLE);
                                }


                                normalValueET.setHint(Hint);
                                normalTitleTV.setText(Title);
                                rootLL.addView(linesView);

                                Normal tempNormal = new Normal();
                                tempNormal.setNull(GetIsNull(IsNull));
                                tempNormal.setType(ViewType);
                                tempNormal.setKey(Key);
                                tempNormal.setNormalEditText(normalValueET);
                                normalArrayList.add(tempNormal);
                            }
                            //endregion

                            //region ListView
                            if (ViewType.equals("ListView")) {
                                View linesView = View.inflate(context, R.layout.w_item_lines, null);
                                TextView normalTitleTV = (TextView) linesView.findViewById(R.id.normalTitleTV);
                                EditText normalValueET = (EditText) linesView.findViewById(R.id.normalValueET);
                                TextView miTV = (TextView) linesView.findViewById(R.id.MiTV);
                                if (GetIsNull(IsNull)) {
                                    miTV.setVisibility(View.GONE);
                                } else {
                                    miTV.setVisibility(View.VISIBLE);
                                }


                                normalValueET.setHint(Hint);
                                normalTitleTV.setText(Title);
                                rootLL.addView(linesView);

                                Normal tempNormal = new Normal();
                                tempNormal.setNull(GetIsNull(IsNull));
                                tempNormal.setType(ViewType);
                                tempNormal.setKey(Key);
                                tempNormal.setNormalEditText(normalValueET);
                                normalArrayList.add(tempNormal);
                            }
                            //endregion

                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                event = xmlParser.next();
            }
            is.close();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return normalArrayList;
    }

    public static ArrayList<Normal> AnalyzeDetail(final Context context, final Activity activity, String XmlName, LinearLayout rootLL) {

        ArrayList<Normal> normalArrayList = new ArrayList<>();
        try {
            InputStream is = context.getResources().getAssets().open(XmlName + ".xml");// getResources().getAssets().open(fileName);
            XmlPullParser xmlParser = Xml.newPullParser();
            xmlParser.setInput(is, "utf-8");
            int event = xmlParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        if ("Item".equals(xmlParser.getName())) {

                            String Title = xmlParser.getAttributeValue(null, "Title");
                            String ViewType = xmlParser.getAttributeValue(null, "ViewType");
                            String Key = xmlParser.getAttributeValue(null, "Key");
                            if (ViewType.equals("Normal")) {
                                View normalView = View.inflate(context, R.layout.w_item_normal_view, null);
                                TextView normalTitleTV = (TextView) normalView.findViewById(R.id.normalTitleTV);
                                EditText normalValueET = (EditText) normalView.findViewById(R.id.normalValueET);

                                normalTitleTV.setText(Title);
                                rootLL.addView(normalView);

                                Normal tempNormal = new Normal();
                                tempNormal.setType(ViewType);
                                tempNormal.setKey(Key);
                                tempNormal.setNormalEditText(normalValueET);
                                normalArrayList.add(tempNormal);
                            }


                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                event = xmlParser.next();
            }
            is.close();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return normalArrayList;
    }


    public static ArrayList<Normal> GetAnalyzeData(ArrayList<Normal> normalArrayList) {
        for (int i = 0; i < normalArrayList.size(); i++) {
            Normal tempNormal = normalArrayList.get(i);
            if (tempNormal.getType().equals("ArrayList")) {
                EditText editText = tempNormal.getNormalEditText();
                if (editText.getTag() != null) {
                    String id = editText.getTag().toString();
                    tempNormal.setValue(id);
                } else
                    tempNormal.setValue("");

            } else if (tempNormal.getType().equals("CheckBoxList")) {
                String checkBoxSelectValue = "";
                for (int k = 0; k < tempNormal.getCheckBoxArrayList().size(); k++) {
                    checkBoxSelectValue += tempNormal.getCheckBoxArrayList().get(k).getText();
                }
                tempNormal.setValue(checkBoxSelectValue);

            } else {
                tempNormal.setValue(tempNormal.getNormalEditText().getText().toString());
            }

        }
        return normalArrayList;
    }

    public static boolean GetIsNull(String isNull) {
        if (isNull.equals("Yes"))
            return true;
        else {
            return false;
        }
    }
}
