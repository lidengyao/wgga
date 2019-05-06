package com.bcxd.wgga.adapter;

import android.content.Context;
import android.util.Xml;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bcxd.wgga.R;
import com.bcxd.wgga.widget.DicData;
import com.bcxd.wgga.widget.Normal;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lidengyao on 2016-09-01 0001.
 */
public class W_ItemAdapter extends CommonAdapter<HashMap<String, String>> {

    private Context _context;
    private String _itemXmlName;

    public W_ItemAdapter(Context context, List<HashMap<String, String>> data, int itemLayoutId, String itemXmlName) {
        super(context, data, itemLayoutId);
        _context = context;
        _itemXmlName = itemXmlName;
    }

    @Override
    public void convert(int position, ViewHolder helper, HashMap<String, String> item) {

        LinearLayout rootLL = helper.getView(R.id.RootLL);

        Object k = rootLL.getTag();
        if (k != null)
            return;
        try {

            HashMap<String, TextView> viewHashMap = new HashMap<>();
            InputStream is = _context.getResources().getAssets().open(_itemXmlName + ".xml");// getResources().getAssets().open(fileName);
            XmlPullParser xmlParser = Xml.newPullParser();
            try {
                xmlParser.setInput(is, "utf-8");
                int event = xmlParser.getEventType();
                while (event != XmlPullParser.END_DOCUMENT) {
                    switch (event) {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            if ("Item".equals(xmlParser.getName())) {

                                String ViewType = xmlParser.getAttributeValue(null, "ViewType");

                                if (ViewType.equals("Two")) {
                                    String leftTitle = xmlParser.getAttributeValue(null, "leftTitle");
                                    String rightTitle = xmlParser.getAttributeValue(null, "rightTitle");

                                    String leftKey = xmlParser.getAttributeValue(null, "leftKey");
                                    String rightkey = xmlParser.getAttributeValue(null, "rightkey");

                                    View twoView = View.inflate(_context, R.layout.w_item_two, null);
                                    TextView leftTitleTV = (TextView) twoView.findViewById(R.id.leftTitle);
                                    TextView rightTitleTV = (TextView) twoView.findViewById(R.id.rightTitle);

                                    TextView leftValueTV = (TextView) twoView.findViewById(R.id.leftValue);
                                    TextView rightValueTV = (TextView) twoView.findViewById(R.id.rightValue);


                                    leftTitleTV.setText(leftTitle);
                                    rightTitleTV.setText(rightTitle);

                                    ArrayList<String> tagList = (ArrayList<String>) rootLL.getTag();

                                    if (tagList == null) {
                                        ArrayList<String> tempList = new ArrayList<>();

                                        viewHashMap.put(leftKey, leftValueTV);
                                        viewHashMap.put(rightkey, rightValueTV);


                                        tempList.add(leftKey);
                                        tempList.add(rightkey);
                                        rootLL.addView(twoView);
                                        rootLL.setTag(tempList);
                                    } else {
                                        if (!tagList.contains(leftKey)) {

                                            viewHashMap.put(leftKey, leftValueTV);
                                            viewHashMap.put(rightkey, rightValueTV);


                                            tagList.add(leftKey);
                                            tagList.add(rightkey);
                                            rootLL.addView(twoView);
                                            rootLL.setTag(tagList);
                                        }
                                    }


                                    if (item.containsKey(leftKey)) {
                                        leftValueTV.setText(item.get(leftKey));
                                    }
                                    if (item.containsKey(rightkey)) {
                                        rightValueTV.setText(item.get(rightkey));
                                    }

                                }
                                if (ViewType.equals("One")) {
                                    String Title = xmlParser.getAttributeValue(null, "Title");
                                    String Key = xmlParser.getAttributeValue(null, "Key");

                                    View oneView = View.inflate(_context, R.layout.w_item_one, null);
                                    TextView oneTitleTV = (TextView) oneView.findViewById(R.id.oneTitle);
                                    TextView oneValueTV = (TextView) oneView.findViewById(R.id.oneValue);
                                    oneTitleTV.setText(Title);


                                    ArrayList<String> tagList = (ArrayList<String>) rootLL.getTag();

                                    if (tagList == null) {
                                        ArrayList<String> tempList = new ArrayList<>();
                                        tempList.add(Key);
                                        rootLL.addView(oneView);
                                        rootLL.setTag(tempList);
                                        viewHashMap.put(Key, oneValueTV);
                                    } else {
                                        if (!tagList.contains(Key)) {
                                            viewHashMap.put(Key, oneValueTV);
                                            tagList.add(Key);
                                            rootLL.addView(oneView);
                                            rootLL.setTag(tagList);
                                        }
                                    }


                                    if (item.containsKey(Key)) {
                                        oneValueTV.setText(item.get(Key));
                                    }
                                }

                            }
                            break;
                        case XmlPullParser.END_TAG:
                            break;
                    }
                    event = xmlParser.next();
                }
                is.close();
                rootLL.setTag(position);

            } catch (XmlPullParserException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //控件赋值

//            for (String key : viewHashMap.keySet()) {
//                if (item.containsKey(key)) {
//                    viewHashMap.get(key).setText(item.get(key));
//                }
//            }

        } catch (
                IOException e)

        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
